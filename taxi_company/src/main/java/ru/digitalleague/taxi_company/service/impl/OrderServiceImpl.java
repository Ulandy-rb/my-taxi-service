package ru.digitalleague.taxi_company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.OrderModel;
import ru.digitalleague.taxi_company.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper mapper;

    @Override
    public Long createOrder(Long userId) {
        return mapper.createOrderAndReturnId(userId);
    }

    @Override
    public void updateOrderStart(Long orderId) {
        mapper.updateOrderStartById(orderId);
    }

    @Override
    public void updateOrderEnd(Long orderId) {
        mapper.updateOrderEndById(orderId);
    }

    @Override
    public void updateOrderDriver(Long orderId, Long driverId) {
        mapper.updateOrderDriverById(orderId, driverId);
    }

    @Override
    public OrderModel getOrder(Long orderId) {
        return mapper.getOrderById(orderId);
    }

    @Override
    public Integer gerTripDuration(Long orderId) {
        return mapper.getDifferenceBetweenStartAndEnd(orderId);
    }
}
