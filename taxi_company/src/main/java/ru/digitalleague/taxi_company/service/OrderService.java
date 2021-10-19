package ru.digitalleague.taxi_company.service;

import ru.digitalleague.taxi_company.model.OrderModel;

/**
 * Сервис заказов
 * */
public interface OrderService {
    /**
     * Создать заказ
     * @param userId номер клиента
     * */
    Long createOrder(Long userId);
    /**
     * Обновить дату начала поездки
     * @param orderId номер заказа
     * */
    void updateOrderStart(Long orderId);
    /**
     * Обновить дату конца поездки
     * @param orderId номер заказа
     * */
    void updateOrderEnd(Long orderId);
    /**
     * Обновить водителя поездки
     * @param orderId номер заказа
     * @param driverId номер водителя
     * */
    void updateOrderDriver(Long orderId, Long driverId);
    /**
     * Найти заказ
     * @param orderId номер заказа
     * */
    OrderModel getOrder(Long orderId);
    /**
     * Получить длительность поездки
     * @param orderId номер заказа
     * */
    Integer gerTripDuration(Long orderId);
}
