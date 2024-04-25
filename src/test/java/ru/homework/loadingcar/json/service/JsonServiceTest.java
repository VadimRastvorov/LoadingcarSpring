package ru.homework.loadingcar.json.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.TruckService;
import ru.homework.loadingcar.service.truck.TruckServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonServiceTest {
    @Test
    void serializationTest() {
        List<Cargo> packageList = new ArrayList<>();
        packageList.add(Cargo.builder()
                .number(1)
                .size(new int[]{1})
                .build());
        TruckService truckService = new TruckServiceImpl();
        List<Truck> truckList = truckService.getLoadingTrucksList(packageList);
        JsonService jsonService = new JsonService();
        jsonService.serialization(truckList, "serialization");
    }

    @SneakyThrows
    @Test
    void deSerializationTest() {
        JsonService jsonService = new JsonService();
        List<Truck> truckList = jsonService.deSerialization("serialization");
        assertEquals(1, truckList.get(0).getCargoTruck()[0][0]);
    }
}