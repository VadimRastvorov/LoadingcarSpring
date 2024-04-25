package ru.homework.loadingcar.json.map;

import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.json.dto.TruckJson;
import ru.homework.loadingcar.json.dto.TrucksJson;

import java.util.ArrayList;
import java.util.List;

public class TrucksToTrucksJson {
    private final List<Truck> trucks;

    public TrucksToTrucksJson(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public TrucksJson mapToTrucksJson() {
        List<TruckJson> truckJsonList = new ArrayList<>();
        for (Truck truck : trucks) {
            List<Integer> cargoJson = new ArrayList<>();
            for (Cargo cargo : truck.getCargoList()) {
                cargoJson.add(cargo.number());
            }
            truckJsonList.add(new TruckJson(cargoJson));
        }
        return new TrucksJson(truckJsonList);
    }
}