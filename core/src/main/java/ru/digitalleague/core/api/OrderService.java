package ru.digitalleague.core.api;

public interface OrderService {
    void createOrder(Long userId);
    void updateOrderStart(Long orderId);
    void updateOrderEnd(Long orderId);
    void updateOrderDriver(Long orderId, Long driverId);
}
