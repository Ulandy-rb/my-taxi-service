package ru.digitalleague.core.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderDetails;

@RestController
public class OrderController {

    private RabbitTemplate rabbitTemplate;
    public OrderController(@Autowired RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/order-taxi")
    public void addOrder(@RequestBody OrderDetails orderDetails)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String order = objectMapper.writeValueAsString(orderDetails);
            rabbitTemplate.convertAndSend("order", order);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
