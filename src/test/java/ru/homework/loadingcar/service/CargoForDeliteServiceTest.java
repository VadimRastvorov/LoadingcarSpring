package ru.homework.loadingcar.service;

import org.junit.jupiter.api.Test;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.service.cargo.CargoServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoForDeliteServiceTest {

    @Test
    void getPackageList() {
        CargoServiceImpl cargoServiceImpl = new CargoServiceImpl();
        List<Cargo> packageList = cargoServiceImpl.getCargoList("333");
        assertEquals(3, packageList.get(0).number());
    }

}