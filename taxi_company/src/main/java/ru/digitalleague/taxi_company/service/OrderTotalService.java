package ru.digitalleague.taxi_company.service;

/**
 * Сервис стоимости заказа
 * */
public interface OrderTotalService {
    /**
     * Установить стоимость поедки
     * @param orderId номер заказа
     * */
    Integer setSum(Long orderId);
}
