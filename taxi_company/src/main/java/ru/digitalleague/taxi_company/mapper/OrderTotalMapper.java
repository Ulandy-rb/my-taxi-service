package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.OrderModel;

@Repository
@Mapper
public interface OrderTotalMapper {
    /**
     * Сохранить стоимость заказа.
     *
     * @param orderId информация о заказе.
     * @param sum стоимость поездки
     */
    @Insert(" insert into order_total (order_id, sum) " +
            " values(#{orderId}, #{sum} )")
    void saveOrderTotalAndReturnSum(Long orderId, Integer sum);
}
