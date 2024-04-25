package ru.homework.loadingcar.service;

import ru.homework.loadingcar.entity.Cargo;

import java.util.List;

public interface CargoService {
    List<Cargo> createCargoList(String contentString);
}
