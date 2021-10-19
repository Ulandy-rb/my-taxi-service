package ru.digitalleague.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import ru.digitalleague.core.mapper.CityMapper;
import ru.digitalleague.core.mapper.DriverInfoMapper;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.api.TaxiService;

@Service
public class TaxiServiceImpl implements TaxiService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public String notifyTaxi(OrderDetails orderDetails) {

        String message = null;
        try {
            message = objectMapper.writeValueAsString(orderDetails);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String queueByCity = cityMapper.getQueueByCity(orderDetails.getCity());

        if (ObjectUtils.isEmpty(queueByCity)) return "Заказ не принят, город не известен";

        //amqpTemplate.convertAndSend(queueByCity, message);

        return "Заказ принят";
    }

    @Override
    public int getPort(OrderDetails orderDetails) {
        return cityMapper.getPortByCity(orderDetails.getCity());
    }

    @Override
    public int getPort(Long orderId) {
        return cityMapper.getPortByOrderId(orderId);
    }
}
