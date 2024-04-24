package ru.homework.loadingcar.json.map;

//import lombok.extern.slf4j.Slf4j;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.json.dto.TruckJson;
import ru.homework.loadingcar.json.dto.TrucksJson;

import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class TrucksToTrucksJson {
    private final List<Truck> trucks;

    public TrucksToTrucksJson(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public TrucksJson mapToTrucksJson() {
        //log.info("вызов метода TrucksToTrucksJson.mapToTrucksJson()");
        List<TruckJson> truckJsonList = new ArrayList<>();
        for (Truck truckV2 : trucks) {
            List<Integer> cargoJson = new ArrayList<>();
            for (Cargo cargo : truckV2.getCargoList()) {
                cargoJson.add(cargo.number());
            }
            truckJsonList.add(new TruckJson(cargoJson));
        }
        return new TrucksJson(truckJsonList);
    }
}