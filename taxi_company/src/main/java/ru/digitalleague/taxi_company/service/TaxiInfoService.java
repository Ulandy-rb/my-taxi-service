package ru.digitalleague.taxi_company.service;

import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

/**
 * Сервис водителя
 * */
public interface TaxiInfoService {
    /**
     * Найти водителя по городу и уровню
     * @param city название города
     * @param level уровень водителя
     * */
    TaxiDriverInfoModel findDriver(String city, int level);
    /**
     * Сделать водителя занятым
     * @param id номер водителя
     * */
    void setDriverBusy(Long id);
    /**
     * Сделать водителя свободным
     * @param id номер водителя
     * */
    void setDriverFree(Long id);
    /**
     * Обновить рейтинг
     * @param rate рйтинг
     * @param driverId номер водителя
     * */
    void rateDriver(int rate, Long driverId);
    /**
     * Найти стоимость поедки у водителя
     * @param driverId номер водителя
     * */
    Integer getDriverCost(Long driverId);
}
