package ru.digitalleague.core.api;

import ru.digitalleague.core.model.OrderDetails;

/**
 * Сервис отправки информации о заказе.
 */
public interface TaxiService {

    /**
     * Информируем такси о поступлении заказа.
     * @param orderDetails детали заказа
     */
    String notifyTaxi(OrderDetails orderDetails);

    /**
     * Получить порт сервера обслуживания такси
     * @param orderDetails детали заказа
     */
    int getPort(OrderDetails orderDetails);

    /**
     * Получить порт сервера обслуживания такси по номеру заказа
     * @param orderId номер заказа
     */
    int getPort(Long orderId);
}
