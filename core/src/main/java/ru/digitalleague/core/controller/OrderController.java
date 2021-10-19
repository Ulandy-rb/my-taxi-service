package ru.digitalleague.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.digitalleague.core.api.TaxiService;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

/**
 * Контроллеры для такси со стороны клиента
 * */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private TaxiService taxiService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "Контроллер для заказа такси")
    @PostMapping("/order-taxi")
    public ResponseEntity<String> beginTrip(@RequestBody OrderDetails orderDetails) {
        //log.info("Received message from postman" + orderDetails);

       // String result = taxiService.notifyTaxi(orderDetails);
        if (orderDetails == null) return  ResponseEntity.badRequest().body("Неверный формат данных");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<OrderDetails> request = new HttpEntity<>(orderDetails, headers);

        int port = taxiService.getPort(orderDetails);

        ResponseEntity<TaxiDriverInfoModel> entity = restTemplate.postForEntity("http://localhost:" + port + "/",
                request,
                TaxiDriverInfoModel.class);

        log.info(entity.getBody().toString());
        return ResponseEntity.ok("Найден водитель:" + entity.getBody().getFirstName());
    }

    @ApiOperation(value = "Контроллер для начала поедки")
    @PutMapping("/trip-begin")
    public ResponseEntity<String> beginTrip(@RequestBody String message) {
        if(message == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        log.info(message);
        return ResponseEntity.ok(message);
    }

    @ApiOperation(value = "Контроллер для конца поездки")
    @PutMapping("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestBody String message) {
        if(message == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        log.info(message);
        return ResponseEntity.ok(message);
    }

    @ApiOperation(value = "Контроллер для оценк водителя")
    @PutMapping("/trip-rate")
    public ResponseEntity<String> rateTrip(@RequestParam(value = "rate") Integer rate, @RequestParam(value = "order_id") Long orderId) {

        if(rate == null) return ResponseEntity.badRequest().body("Неверный формат данных");
        int port = taxiService.getPort(orderId);

        ResponseEntity<String> entity = restTemplate.postForEntity("http://localhost:" + port + "/rate-driver?order_id="
                        + orderId,
                rate,
                String.class);
        return ResponseEntity.ok(entity.getBody());
    }
}
