package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Модель деталей заказа")
public class OrderDetails {
    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Идентификатор клиента")
    private Long clientNumber;

    /**
     * Желаемый класс поездки (бизнес, эконом, и т.п.)
     */
    @ApiModelProperty("Желаемый класс поездки (бизнес, эконом, и т.п.)")
    private int level;

    /**
     * Модель машины.
     */
    @ApiModelProperty("Модель машины")
    private String carModel;

    /**
     * Название города.
     */
    @ApiModelProperty("Название города")
    private String city;

}
