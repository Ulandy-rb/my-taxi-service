package ru.digitalleague.core.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ru.digitalleague.core.api.DriverInfoService;
import ru.digitalleague.core.mapper.DriverInfoMapper;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverInfoServiceImpl implements DriverInfoService {

    private final DriverInfoMapper driverInfoMapper;

    @Override
    public int insert(TaxiDriverInfoModel record) {
        return driverInfoMapper.insert(record);
    }

    @Override
    public TaxiDriverInfoModel selectByPrimaryKey(Long driverId) {

        return driverInfoMapper.selectByPrimaryKey(driverId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int updateByPrimaryKey(TaxiDriverInfoModel record) {
        return driverInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Retryable
    public int getByIdAndUpdateLevel(Long driverId) {
        TaxiDriverInfoModel taxiDriverInfoModel = selectByPrimaryKey(driverId);
        taxiDriverInfoModel.setLevel(taxiDriverInfoModel.getLevel() + 1);
        return driverInfoMapper.updateByPrimaryKey(taxiDriverInfoModel);
    }

    @SneakyThrows
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean twoSelectByIdWithSleep() {

        String lastName = "Иванов";
        List<TaxiDriverInfoModel> expected = driverInfoMapper.selectByLastName(lastName);
        log.debug("Expected size: {}", expected.size());
        Thread.sleep(5000);
        List<TaxiDriverInfoModel> actual = driverInfoMapper.selectByLastName(lastName);
        log.debug("Actual size: {}", actual.size());
        return expected.size() == actual.size();
    }
}
