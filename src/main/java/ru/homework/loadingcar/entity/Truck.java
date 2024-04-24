package ru.homework.loadingcar.entity;

import lombok.Builder;
import lombok.Getter;
import ru.homework.loadingcar.service.truck.util.TruckUtil;

import java.util.List;

@Builder
@Getter
public class Truck {
    private final int[][] cargoTruck;
    private final List<Cargo> cargoList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+      +");
        stringBuilder.append(System.getProperty("line.separator"));
        for (int i = TruckUtil.CAR_CASE_HEIGHT - 1; i >= 0; i--) {
            stringBuilder.append("+");
            for (int j = 0; j < TruckUtil.CAR_CASE_WIDTH; j++) {
                stringBuilder.append(cargoTruck[i][j] == 0 ? " " : cargoTruck[i][j]);
            }
            stringBuilder.append("+");
            stringBuilder.append(System.getProperty("line.separator"));
        }
        stringBuilder.append("++++++++");
        return stringBuilder.toString();
    }
}
