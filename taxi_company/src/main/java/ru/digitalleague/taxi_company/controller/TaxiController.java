package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.digitalleague.taxi_company.mapper.DriverInfoMapper;
import ru.digitalleague.taxi_company.model.OrderDetails;
import ru.digitalleague.taxi_company.model.OrderModel;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.service.OrderService;
import ru.digitalleague.taxi_company.service.OrderTotalService;
import ru.digitalleague.taxi_company.service.TaxiInfoService;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
public class TaxiController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TaxiInfoService taxiInfoService;
    @Autowired
    private OrderTotalService orderTotalService;

    private final int CENTRALPORT = 8080;

    /**
     * Метод получает инфо о завершении поездки, расчитывает стоимость и оповещает клиента
     * @param order_id номер поездки
     * */
    @ApiOperation("Контроллер окончания поездки")
    @PutMapping ("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestParam(value = "order_id") Long order_id) {
        if(order_id == null) return ResponseEntity.badRequest().body("Неверный формат данных");

        //amqpTemplate.convertAndSend("trip-result", message);

        //Установить время окончаня поездки
        orderService.updateOrderEnd(order_id);
        //Освободить водителя
        taxiInfoService.setDriverFree(orderService.getOrder(order_id).getDriverId());
        //Найти стоимость поездки
        Integer costTrip = orderTotalService.setSum(order_id);

        String message = "Услуга оказана" + "\n" + "Стоимость поездки составила " + costTrip + "р";
        //Оповестить клиента о конце поездки и передать стоимость
        restTemplate.put("http://localhost:" + CENTRALPORT + "/trip-begin", message);
        return ResponseEntity.ok(message);
    }

    /**
     * Метод получает инфо о начале поездки, оповещает клиента
     * @param order_id номер поездки
     * */
    @ApiOperation("Контроллер начала поездки")
    @PutMapping("/trip-begin")
    public ResponseEntity<String> beginTrip(@RequestParam(value = "order_id") Long order_id) {
        if(order_id == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        //Обновить дату старта поездки
        orderService.updateOrderStart(order_id);
        //Оповестить клиента о начале поездки
        restTemplate.put("http://localhost:" + CENTRALPORT + "/trip-begin", "Поездка началась");
        return ResponseEntity.ok("Поездка началась");
    }

    /**
     * Метод получает инфо о заказе, подбирает водителя и оповещает клиента
     * @param orderDetails детали заказа
     * */
    @ApiOperation("Контроллер подбора водителя")
    @PostMapping("/")
    public ResponseEntity<TaxiDriverInfoModel> findDriver(@RequestBody OrderDetails orderDetails) {
        if(orderDetails == null) return ResponseEntity.badRequest().body(null);
        //Создать заказ
        Long order = orderService.createOrder(orderDetails.getClientNumber());

        //Поиск водителя по критериям
        TaxiDriverInfoModel driver = taxiInfoService.findDriver(orderDetails.getCity(), orderDetails.getLevel());
        //Добавть водителя в заказ
        orderService.updateOrderDriver(order, driver.getDriverId());
        //Занять водителя
        taxiInfoService.setDriverBusy(driver.getDriverId());
        return ResponseEntity.ok(driver);
    }
    /**
     * Оценить водителя такси, запрос приходит от клиента
     * @param orderId номер заказа
     * @param rate оценка клиента
     */
    @ApiOperation("Контроллер оценки водителя")
    @PostMapping("/rate-driver")
    public ResponseEntity<String> rateDriver(@RequestBody Integer rate, @RequestParam(value = "order_id") Long orderId ) {
        if(rate == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        OrderModel order = orderService.getOrder(orderId);
        Long driverId = order.getDriverId();
        //Оценить водителя
        taxiInfoService.rateDriver(rate, driverId);

        return ResponseEntity.ok("Спасибо за оценку");
    }
}
