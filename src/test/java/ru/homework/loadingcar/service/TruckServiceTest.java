package ru.homework.loadingcar.service;

import org.junit.jupiter.api.Test;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.truck.TruckServiceImpl;
import ru.homework.loadingcar.service.truck.TruckServiceV2Impl;
import ru.homework.loadingcar.service.truck.TruckServiceV3Impl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TruckServiceTest {

    @Test
    void getLoadingTrucksListTest() {

        List<Cargo> packageList = new ArrayList<>();
        packageList.add(Cargo.builder()
                        .number(1)
                        .size(new int[]{1})
                .build());
        TruckService truckService = new TruckServiceImpl();
        List<Truck> truckList = (List<Truck>) truckService.getLoadingTrucksList(packageList);
        assertEquals(1, truckList.get(0).getCargoTruck()[0][0]);
    }

    @Test
    void getLoadingTrucksListV2Test() {

        List<Cargo> packageList = new ArrayList<>();

        packageList.add(Cargo.builder()
                        .number(1)
                        .size(new int[]{1})
                .build());
        TruckService truckService = new TruckServiceV2Impl();
        List<Truck> truckList = (List<Truck>) truckService.getLoadingTrucksList(packageList);
        assertEquals(1, truckList.get(0).getCargoTruck()[0][0]);
    }

    @Test
    void getLoadingTrucksListV3Test() {

        List<Cargo> packageList = new ArrayList<>();

        packageList.add(Cargo.builder()
                .number(1)
                .size(new int[]{1})
                .build());
        TruckService truckService = new TruckServiceV3Impl();
        List<Truck> truckList = (List<Truck>) truckService.getLoadingTrucksList(packageList);
        assertEquals(1, truckList.get(0).getCargoTruck()[0][0]);
    }
}