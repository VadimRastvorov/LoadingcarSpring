package ru.homework.loadingcar.service.truck.util;

import lombok.extern.slf4j.Slf4j;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.service.truck.dto.CargoPosition;

import java.util.List;

@Slf4j
public class TruckUtil {
    public static final int CAR_CASE_HEIGHT = 6;
    public static final int CAR_CASE_WIDTH = 6;

    public static CargoPosition getCargoPosition(int[][] cargoTruck, Cargo cargo) {
        log.info("метод getCargoPosition: {} {}", cargo.number(), cargo.size());
        int heightStart = 0;
        int widthStart = 0;
        boolean loadingNewCar = true;
        boolean heightFlag = true;
        for (int i = 0; i < CAR_CASE_HEIGHT && loadingNewCar; i++) {
            for (int j = 0; j < CAR_CASE_WIDTH && loadingNewCar; j++) {
                if (cargoTruck[i][j] == 0 && i + cargo.size().length <= CAR_CASE_HEIGHT && j + cargo.size()[0] <= CAR_CASE_WIDTH) {
                    var heigh = i + cargo.size().length - 1;
                    for (int c = i; c < cargo.size().length; c++) {
                        if (cargoTruck[heigh][j] > 0) {
                            heightFlag = false;
                        }
                    }
                    if (heightFlag) {
                        heightStart = i;
                        widthStart = j;
                        loadingNewCar = false;
                    }
                }
            }
        }
        return CargoPosition.builder()
                .startWidth(widthStart)
                .startHeight(heightStart)
                .loadingNewCar(loadingNewCar)
                .cargoTruck(getMatrixCargoTruck(cargo, cargoTruck, heightStart, widthStart, loadingNewCar))
                .build();
    }

    private static int[][] getMatrixCargoTruck(Cargo cargo, int[][] cargoTruck, int heightStart, int widthStart, boolean loadingNewCar) {
        log.info("метод fillCargoToTruck {} {} {} {}", widthStart, loadingNewCar, cargoTruck, heightStart);
        int[][] outCargoTruck = loadingNewCar ? new int[CAR_CASE_HEIGHT][CAR_CASE_WIDTH] : cargoTruck;
        for (int i = 0; i < cargo.size().length; i++) {
            for (int j = 0; j < cargo.size()[i]; j++) {
                outCargoTruck[i + heightStart][j + widthStart] = cargo.number();
            }
        }
        return outCargoTruck;
    }

    public static int[][] getMatrixCargoTruck(List<Cargo> cargoList) {
        log.info("запуск метода TruckUtil.getMatrixCargoTruck: {}", cargoList.stream().map(a -> a.number()).toList());
        int[][] outCargoTruck = new int[CAR_CASE_HEIGHT][CAR_CASE_WIDTH];
        for (Cargo cargoSize : cargoList) {
            boolean loadingNewCar = true;
            for (int i = 0; i < CAR_CASE_HEIGHT; i++) {
                for (int j = 0; j < CAR_CASE_WIDTH; j++) {
                    var heightFlag = true;
                    if (outCargoTruck[i][j] == 0 && i + cargoSize.size().length <= CAR_CASE_HEIGHT && j + cargoSize.size()[0] <= CAR_CASE_WIDTH && loadingNewCar) {
                        for (int c = i; c < cargoSize.size().length; c++) {
                            if (outCargoTruck[i + cargoSize.size().length - 1][j] > 0) {
                                heightFlag = false;
                            }
                        }
                        if (heightFlag) {
                            loadingNewCar = false;
                            for (int f = 0; f < cargoSize.size().length; f++) {
                                for (int g = 0; g < cargoSize.size()[f]; g++) {
                                    outCargoTruck[i + f][j + g] = cargoSize.number();
                                }
                            }
                        }
                    }
                }
            }
        }
        return outCargoTruck;
    }
}
