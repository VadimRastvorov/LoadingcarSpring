package ru.homework.loadingcar.json.map;

import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.json.dto.TruckJson;
import ru.homework.loadingcar.json.dto.TrucksJson;
import ru.homework.loadingcar.service.truck.util.TruckUtil;
import ru.homework.loadingcar.type.CargoType;

import java.util.ArrayList;
import java.util.List;

public class TrucksJsonToTrucks {

    private final TrucksJson trucksJson;

    public TrucksJsonToTrucks(TrucksJson trucksJson) {
        this.trucksJson = trucksJson;
    }

    public List<Truck> mapToTruckList() {
        List<Truck> truckList = new ArrayList<>();
        for (TruckJson truckJson : trucksJson.getTruckList()) {
            List<Cargo> cargoList = new ArrayList<>();
            for (Integer cargoNumber : truckJson.getCargo()) {
                cargoList.add(Cargo.builder()
                        .size(CargoType.stream()
                                .filter(a -> a.getNumber() == cargoNumber)
                                .map(CargoType::getSize)
                                .findFirst()
                                .orElse(null))
                        .number(cargoNumber)
                        .build());
            }
            truckList.add(Truck.builder()
                    .cargoList(cargoList)
                    .cargoTruck(TruckUtil.getMatrixCargoTruck(cargoList))
                    .build());
        }
        return truckList;
    }
}
