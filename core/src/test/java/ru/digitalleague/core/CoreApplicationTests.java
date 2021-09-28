package ru.digitalleague.core;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.digitalleague.core.mapper.TaxiInfoMapper;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CoreApplicationTests {

    @Autowired
    private TaxiInfoMapper taxiInfoMapper;

    @Test
    void contextLoads() {

        TaxiDriverInfoModel taxiDriverInfoModel = TaxiDriverInfoModel.builder()
                .carModel("BMW")
                .lastName("Иванов")
                .firstName("Иван")
                .level(3)
                .createDttm(OffsetDateTime.now())
                .build();
        int insert = taxiInfoMapper.insert(taxiDriverInfoModel);

        assertThat(insert).isEqualTo(1);
    }

}
