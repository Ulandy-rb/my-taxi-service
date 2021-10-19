package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("Модель стоимости заказа")
public class OrderTotalModel {
    /**
     * Идентификатор поездки.
     */
    @ApiModelProperty("Идентификатор поездки")
    private Long orderId;

    /**
     * Стоимость поездки.
     */
    @ApiModelProperty("Стоимость поездки")
    private Integer sum;
}
