package ru.digitalleague.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.api.OrderService;
import ru.digitalleague.core.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper mapper;

    @Override
    public void createOrder(Long userId) {
        mapper.createOrder(userId);
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
}
