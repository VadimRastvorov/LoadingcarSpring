package ru.homework.loadingcar.json.map;

//import lombok.extern.slf4j.Slf4j;
import ru.homework.loadingcar.json.dto.TrucksJson;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.json.dto.TruckJson;
import ru.homework.loadingcar.service.truck.util.TruckUtil;
import ru.homework.loadingcar.type.CargoSize;

import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class TrucksJsonToTrucks {

    private final TrucksJson trucksJson;

    public TrucksJsonToTrucks(TrucksJson trucksJson) {
        this.trucksJson = trucksJson;
    }

    public List<Truck> mapToTruckV2List() {
        //log.info("вызов метода TrucksJsonToTrucks.mapToTruckV2List()");
        List<Truck> truckV2List = new ArrayList<>();
        for (TruckJson truckJson : trucksJson.getTruckList()) {
            List<Cargo> cargoList = new ArrayList<>();
            for (Integer cargoNumber : truckJson.getCargo()) {
                cargoList.add(Cargo.builder()
                        .size(CargoSize.stream()
                                .filter(a -> a.getNumber() == cargoNumber)
                                .map(a -> a.getSize())
                                .findFirst()
                                .orElse(null))
                        .number(cargoNumber)
                        .build());
            }
            truckV2List.add(Truck.builder()
                    .cargoList(cargoList)
                    .cargoTruck(TruckUtil.getMatrixCargoTruck(cargoList))
                    .build());
        }
        return truckV2List;
    }
}
