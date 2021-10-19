package ru.digitalleague.core.api;

import ru.digitalleague.core.model.TaxiDriverInfoModel;

/**
 * Сервис водителя
 * */
public interface DriverInfoService {

    /**
     * Добавление водителя
     * @param record запись типа водитель
     * */
    int insert(TaxiDriverInfoModel record);

    /**
     * Выбрать водителя по первичному ключу
     * @param driverId запись типа водитель
     * */
    TaxiDriverInfoModel selectByPrimaryKey(Long driverId);

    /**
     * Обновить запись водителя
     * @param record запись типа водитель
     * */
    int updateByPrimaryKey(TaxiDriverInfoModel record);

    /**
     * Обновить уровень водителя
     * @param driverId запись типа водитель
     * */
    int getByIdAndUpdateLevel(Long driverId);
}
