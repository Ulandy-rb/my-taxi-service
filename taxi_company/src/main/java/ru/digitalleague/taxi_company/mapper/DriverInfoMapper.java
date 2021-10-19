package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

@Repository
@Mapper
public interface DriverInfoMapper {
    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "carModel", column = "car_model"),
            @Result(property = "createDttm", column = "create_dttm"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "minuteCost", column = "minute_cost"),
            @Result(property = "busyness", column = "busyness")
    })

    /**
     * Подобрать водителя для поездки
     * @param city название города
     * @param level уровень водителя
     * */
    @Select("SELECT * FROM taxi_drive_info tdi " +
            "WHERE busyness = false AND city_id = " +
            "(SELECT q.city_id FROM city_queue q where q.name = #{city})" +
            "and level = #{level} " +
            "order by rating " +
            "limit 1")
    TaxiDriverInfoModel getDriverByCityWithTopRating(String city, int level);

    /**
     * Установить водителя как занят
     * @param id номер водителя
     * */
    @Update("update taxi_drive_info set busyness = true where driver_id = #{id} ")
    void setBusy(Long id);

    /**
     * Установить водителя как свободен
     * @param id номер водителя
     * */
    @Update("update taxi_drive_info set busyness = false where driver_id = #{id} ")
    void setFree(Long id);

    /**
     * Обновить рейтинг водителя
     * @param rate рейтинг
     * @param driver_id с
     * */
    @Update("update taxi_drive_info set rating = round(((#{rate} + rating)/2)::numeric,2) where driver_id = #{driver_id}")
    void rateDriverById(Integer rate, Long driver_id);

    /**
     * Найти стоимость водителя
     * @param driverId номер водителя
     * */
    @Select("SELECT minute_cost from taxi_drive_info where driver_id = #{driverId}")
    Integer getDriverCostById(Long driverId);
}
