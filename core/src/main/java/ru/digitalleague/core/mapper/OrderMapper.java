package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.OrderModel;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param order информация о заказе.
     */
    @Insert(" insert into orders (order_id, client_number, driver_id, start_trip, end_trip)" +
            " values(#{orderId}, #{clientNumber}, #{driverId}, #{startTrip}, #{endTrip})")
    void saveOrder(OrderModel order);

    /**
     * Установить время начала поездки.
     */
    @Update("update orders set start_trip = current_timestamp where order_id = #{orderId}")
    void updateOrderStartById(Long orderId);

    /**
     * Установить время окончания поездки.
     */
    @Update("update orders set end_trip = current_timestamp where order_id = #{orderId}")
    void updateOrderEndById(Long orderId);

    /**
     * Создать ордер.
     *
     * @param userId ID клиента.
     */
    @Insert("insert into orders (client_number) values (#{userId}")
    void createOrder(Long userId);

    /**
     * Установить водителя такси.
     */
    @Update("update orders set driver_id = #{driverId} where order_id = #{orderId}")
    void updateOrderDriverById(Long orderId, Long driverId);

}