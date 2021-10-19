package ru.digitalleague.taxi_company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.mapper.DriverInfoMapper;
import ru.digitalleague.taxi_company.mapper.OrderTotalMapper;
import ru.digitalleague.taxi_company.service.OrderService;
import ru.digitalleague.taxi_company.service.OrderTotalService;
import ru.digitalleague.taxi_company.service.TaxiInfoService;

@Service
public class OrderTotalServiceImpl implements OrderTotalService {
    @Autowired
    TaxiInfoService taxiInfoService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderTotalMapper mapper;

    @Override
    public Integer setSum(Long orderId) {
        int driverCost = taxiInfoService.getDriverCost(orderService.getOrder(orderId).getDriverId());
        int durationTrip = orderService.gerTripDuration(orderId);
        int costTrip = driverCost * durationTrip;
        mapper.saveOrderTotalAndReturnSum(orderId, costTrip);
        return  costTrip;
    }
}
