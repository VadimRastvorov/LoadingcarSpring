package ru.homework.loadingcar.service;

import ru.homework.loadingcar.entity.Truck;

import java.util.List;

public interface PrintService {
    String printTruckListToString(List<Truck> truckList);
}
