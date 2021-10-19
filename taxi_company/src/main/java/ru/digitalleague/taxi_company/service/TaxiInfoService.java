package ru.digitalleague.taxi_company.service;

import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

public interface TaxiInfoService {
    TaxiDriverInfoModel findDriver(String city, int level);
    void setDriverBusy(Long id);
    void setDriverFree(Long id);
    void rateDriver(int rate, Long driverId);
    Integer getDriverCost(Long driverId);
}
