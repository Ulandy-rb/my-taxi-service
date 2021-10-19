package ru.digitalleague.taxi_company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.mapper.DriverInfoMapper;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.service.TaxiInfoService;

@Service
public class TaxiInfoServiceImpl implements TaxiInfoService {
    @Autowired
    private DriverInfoMapper mapper;

    @Override
    public TaxiDriverInfoModel findDriver(String city, int level) {
        return mapper.getDriverByCityWithTopRating(city, level);
    }

    @Override
    public void setDriverBusy(Long id) {
        mapper.setBusy(id);
    }

    @Override
    public void setDriverFree(Long id) {
        mapper.setFree(id);
    }


    @Override
    public void rateDriver(int rate, Long driver_id) {
        mapper.rateDriverById(rate, driver_id);
    }

    @Override
    public Integer getDriverCost(Long driverId) {
        return mapper.getDriverCostById(driverId);
    }
}
