package ru.homework.loadingcar.service;

import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.List;

public interface TruckService{
    List<Truck> getLoadingTrucksList(List<Cargo> cargoList);
    AlgorithmType getAlgorithmType();
}
