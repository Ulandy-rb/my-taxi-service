package ru.digitalleague.taxi_company.model;

import lombok.Data;


@Data
public class OrderTotalModel {
    /**
     * Идентификатор поездки.
     */
    private Long orderId;

    /**
     * Стоимость поездки.
     */
    private Integer sum;
}
