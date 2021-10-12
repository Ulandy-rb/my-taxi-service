package ru.digitalleague.taxi_company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.mapper.TaxiInfoMapper;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.service.TaxiInfoService;

@Service
public class TaxiInfoServiceImpl implements TaxiInfoService {
    @Autowired
    private TaxiInfoMapper mapper;

    @Override
    public TaxiDriverInfoModel findDriver(String city, String carModel, int level) {
        return mapper.getDriverByCityWithTopRating(city, carModel, level);
    }

    @Override
    public void serDriverBusy(Long id) {
        mapper.setFree(id);
    }
}
