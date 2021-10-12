package ru.digitalleague.taxi_company.service;

public interface OrderService {
    Long createOrder(Long userId);
    void updateOrderStart(Long orderId);
    void updateOrderEnd(Long orderId);
    void updateOrderDriver(Long orderId, Long driverId);
}
