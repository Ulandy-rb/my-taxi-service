package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CityMapper {
    /**
     * Находим очередь, в которую будем отправлять сообщения по названию города.
     * */
    @Select("SELECT queue FROM city_queue where name = #{cityName}")
    String getQueueByCity(String cityName);

    @Select("SELECT port FROM city_queue where name = #{cityName}")
    int getPortByCity(String city);

    @Select("SELECT port FROM orders o " +
            "join taxi_drive_info t on o.driver_id = t.driver_id " +
            "join city_queue c on c.city_id = t.city_id " +
            "where o.order_id = #{orderId} ")
    int getPortByOrderId(Long orderId);
}
