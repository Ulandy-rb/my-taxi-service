package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Модель таксиста")
public class TaxiDriverInfoModel {

    @ApiModelProperty("Идентификатор водителя")
    private Long driverId;

    /**
     * Фамилия.
     */
    @ApiModelProperty("Фамилия водителя")
    private String lastName;

    /**
     * Имя.
     */
    @ApiModelProperty("Имя водителя")
    private String firstName;

    /**
     * Уровень.
     */
    @ApiModelProperty("Уровень")
    private int level;

    /**
     * Модель авто.
     */
    @ApiModelProperty("Модель авто")
    private String carModel;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Дата создания")
    private Date createDttm;

    @ApiModelProperty("Идентификатор города")
    private Long cityId;

    @ApiModelProperty("Рейтинг")
    private Double rating;

    @ApiModelProperty("Стоимость поездки за минуту")
    private int minuteCost;

    @ApiModelProperty("Занят или свободен")
    private boolean busyness;
}
