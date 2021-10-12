package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

import java.util.List;

@Repository
@Mapper
public interface TaxiInfoMapper {
    int getCount();

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

    @Select("SELECT * FROM taxi_drive_info tdi " +
            "where tdi.rating >=all(select rating from taxi_drive_info " +
            "WHERE busyness = false AND city_id = " +
            "(SELECT q.city_id FROM city_queue q where q.name = #{city})" +
            "and car_model = #{carModel} and level = #{level})" +
            "and busyness = false AND city_id = " +
            "(SELECT q.city_id FROM city_queue q where q.name = #{city})" +
            "and car_model = #{carModel} and level = #{level}" +
            "fetch first 1 rows only")
    TaxiDriverInfoModel getDriverByCityWithTopRating(String city, String carModel, int level);

    @Update("update taxi_drive_info set busyness = true where driver_id = #{id} ")
    void setBusy(Long id);

    @Update("update taxi_drive_info set busyness = false where driver_id = #{id} ")
    void setFree(Long id);
}
