package ru.digitalleague.taxi_company.controller;

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
     * Метод получает инфо о завершении поездки.
     * @param order_id
     * */
    @PutMapping ("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestParam(value = "order_id") Long order_id) {
        if(order_id == null) return ResponseEntity.badRequest().body("Неверный формат данных");

        //amqpTemplate.convertAndSend("trip-result", message);
        orderService.updateOrderEnd(order_id);
        taxiInfoService.setDriverFree(orderService.getOrder(order_id).getDriverId());

        Integer costTrip = orderTotalService.setSum(order_id);
        String message = "Услуга оказана" + "\n" + "Стоимость поездки составила " + costTrip + "р";
        restTemplate.put("http://localhost:" + CENTRALPORT + "/trip-begin", message);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/trip-begin")
    public ResponseEntity<String> beginTrip(@RequestParam(value = "order_id") Long order_id) {

        if(order_id == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        orderService.updateOrderStart(order_id);
        restTemplate.put("http://localhost:" + CENTRALPORT + "/trip-begin", "Поездка началась");
        return ResponseEntity.ok("Поездка началась");
    }

    @PostMapping("/")
    public ResponseEntity<TaxiDriverInfoModel> findDriver(@RequestBody OrderDetails orderDetails) {
        if(orderDetails == null) return ResponseEntity.badRequest().body(null);
        Long order = orderService.createOrder(orderDetails.getClientNumber());

        TaxiDriverInfoModel driver = taxiInfoService.findDriver(orderDetails.getCity(), orderDetails.getLevel());
        orderService.updateOrderDriver(order, driver.getDriverId());
        taxiInfoService.setDriverBusy(driver.getDriverId());
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/rate-driver")
    public ResponseEntity<String> rateDriver(@RequestBody Integer rate, @RequestParam(value = "order_id") Long orderId ) {
        if(rate == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        OrderModel order = orderService.getOrder(orderId);
        Long driverId = order.getDriverId();
        taxiInfoService.rateDriver(rate, driverId);

        return ResponseEntity.ok("Спасибо за оценку");
    }
}
