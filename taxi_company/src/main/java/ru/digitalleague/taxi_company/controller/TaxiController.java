package ru.digitalleague.taxi_company.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.taxi_company.model.OrderDetails;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.service.OrderService;
import ru.digitalleague.taxi_company.service.TaxiInfoService;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
public class TaxiController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TaxiInfoService taxiInfoService;

    /**
     * Метод получает инфо о завершении поездки.
     * @param order_id
     * */
    @PutMapping("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestParam(value = "order_id") Long order_id) {
        //amqpTemplate.convertAndSend("trip-result", message);
        orderService.updateOrderEnd(order_id);
        taxiInfoService.setDriverFree(orderService.getOrder(order_id).getDriverId());
        return ResponseEntity.ok("Услуга оказана");
    }

    @PutMapping("/trip-begin")
    public ResponseEntity<String> beginTrip(@RequestParam(value = "order_id") Long order_id) {
        orderService.updateOrderStart(order_id);
        return ResponseEntity.ok("Поездка началась");
    }

    @PostMapping("/")
    public ResponseEntity<TaxiDriverInfoModel> findDriver(@RequestBody OrderDetails orderDetails) {
        Long order = orderService.createOrder(orderDetails.getClientNumber());

        TaxiDriverInfoModel driver = taxiInfoService.findDriver(orderDetails.getCity(), orderDetails.getCarModel(), orderDetails.getLevel());
        orderService.updateOrderDriver(order, driver.getDriverId());
        taxiInfoService.setDriverBusy(driver.getDriverId());
        return ResponseEntity.ok(driver);
    }
}
