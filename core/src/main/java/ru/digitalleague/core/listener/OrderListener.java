package ru.digitalleague.core.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class OrderListener {
    @RabbitListener(queues = "order")
    public void listen(String in) {
        System.out.println("Message read from order : " + in);
    }
}
