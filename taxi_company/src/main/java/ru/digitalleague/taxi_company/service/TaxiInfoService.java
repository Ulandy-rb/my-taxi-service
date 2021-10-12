package ru.digitalleague.taxi_company.service;

import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

public interface TaxiInfoService {
    TaxiDriverInfoModel findDriver(String city, String carModel, int level);
    void setDriverBusy(Long id);
    void setDriverFree(Long id);
}
