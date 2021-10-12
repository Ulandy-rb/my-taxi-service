package ru.digitalleague.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.digitalleague.core.api.TaxiService;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaxiService taxiService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/order-taxi")
    public ResponseEntity<String> beginTrip(@RequestBody OrderDetails orderDetails) {
        //log.info("Received message from postman" + orderDetails);

       // String result = taxiService.notifyTaxi(orderDetails);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<OrderDetails> request = new HttpEntity<>(orderDetails, headers);

        int port = taxiService.getPort(orderDetails);

        ResponseEntity<TaxiDriverInfoModel> entity = restTemplate.postForEntity("http://localhost:" + port + "/",
                request,
                TaxiDriverInfoModel.class);

        log.info(entity.getBody().toString());
        return ResponseEntity.ok("Найден водитель:" + entity.getBody().getFirstName());
    }
}
