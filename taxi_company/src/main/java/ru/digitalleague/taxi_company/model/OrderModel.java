package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@ApiModel("Модель заказа")
public class OrderModel {

    /**
     * Идентификатор поездки.
     */
    @ApiModelProperty("Идентификатор поездки")
    private Long orderId;

    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Идентификатор клиента")
    private Long clientNumber;

    /**
     * Идентификатор водителя.
     */
    @ApiModelProperty("Идентификатор водителя")
    private Long driverId;

    /**
     * Дата, время начала поездки.
     */
    @ApiModelProperty("Дата, время начала поездки")
    private OffsetDateTime startTrip;

    /**
     * Дата, время окончания поездки.
     */
    @ApiModelProperty("Дата, время окончания поездки")
    private OffsetDateTime endTrip;
}
