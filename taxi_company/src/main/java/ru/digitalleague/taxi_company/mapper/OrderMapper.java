package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.OrderModel;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     * @param order информация о заказе.
     */
    @Insert(" insert into orders (order_id, client_number, driver_id, start_trip, end_trip)" +
            " values(#{orderId}, #{clientNumber}, #{driverId}, #{startTrip}, #{endTrip})")
    void saveOrder(OrderModel order);

    /**
     * Установить время начала поездки.
     * @param orderId номер заказа
     */
    @Update("update orders set start_trip = current_timestamp where start_trip is null and order_id = #{orderId}")
    void updateOrderStartById(Long orderId);

    /**
     * Установить время окончания поездки.
     * @param orderId номер заказа
     */
    @Update("update orders set end_trip = current_timestamp where end_trip is null and start_trip is not null and order_id = #{orderId}")
    void updateOrderEndById(Long orderId);

    /**
     * Создать ордер.
     *
     * @param userId ID клиента.
     */
    @Select("insert into orders (client_number) values (#{userId}) RETURNING order_id")
    Long createOrderAndReturnId(Long userId);

    /**
     * Установить водителя такси.
     * @param orderId номер заказа
     * @param driverId номер водителя
     */
    @Update("update orders set driver_id = #{driverId} where order_id = #{orderId}")
    void updateOrderDriverById(Long orderId, Long driverId);

    /**
     * Найти заказ
     * @param Id номер заказа
     */
    @Select("select * from orders where order_id = #{Id}")
    OrderModel getOrderById(Long Id);

    /**
     * Найти время поездки
     * @param orderId номер заказа
     */
    @Select("select cast(extract(epoch FROM o.end_trip - o.start_trip)/60 as integer) " +
            "from test.orders o where o.start_trip is not null " +
            "and o.end_trip is not null " +
            "and o.order_id = #{orderId}")
    Integer getDifferenceBetweenStartAndEnd(Long orderId);

}