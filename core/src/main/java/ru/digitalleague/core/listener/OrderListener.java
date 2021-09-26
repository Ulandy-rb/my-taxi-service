package ru.digitalleague.core.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class OrderListener {
    @RabbitListener(queues = "order")
    public void listen(String in) {
        System.out.println("Message read from order : " + in);
    }
}
