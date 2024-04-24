package ru.homework.loadingcar.service.truck.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CargoPosition {
    private final int startHeight;
    private final int startWidth;
    private final boolean loadingNewCar;
    private final int[][] cargoTruck;
}
