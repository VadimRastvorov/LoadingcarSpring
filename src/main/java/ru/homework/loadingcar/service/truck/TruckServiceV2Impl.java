package ru.homework.loadingcar.service.truck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.TruckService;
import ru.homework.loadingcar.service.truck.dto.CargoPosition;
import ru.homework.loadingcar.service.truck.util.TruckUtil;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("truckServiceV2")
public class TruckServiceV2Impl implements TruckService {

    @Override
    public List<Truck> getLoadingTrucksList(List<Cargo> cargoList) {
        return loadingTruck(cargoList, new ArrayList<>());
    }

    @Override
    public AlgorithmType getAlgorithmType() {
        return AlgorithmType.ALGORITHM_V2;
    }

    private List<Truck> loadingTruck(List<Cargo> cargoList, List<Truck> truckList) {
        List<Cargo> packagesLoad = new ArrayList<>();
        List<Cargo> packagesNoLoad = new ArrayList<>();
        int[][] truckCargo = new int[TruckUtil.CAR_CASE_HEIGHT][TruckUtil.CAR_CASE_WIDTH];
        for (Cargo cargo : cargoList) {
            CargoPosition cargoPosition = TruckUtil.getCargoPosition(truckCargo, cargo);
            if (cargoPosition.isLoadingNewCar()) {
                packagesNoLoad.add(cargo);
            } else {
                truckCargo = cargoPosition.getCargoTruck();
                packagesLoad.add(cargo);
            }
        }
        truckList.add(Truck.builder()
                .cargoTruck(truckCargo)
                .cargoList(packagesLoad)
                .build());

        if (packagesNoLoad.size() > 0) {
            loadingTruck(packagesNoLoad, truckList);
        }
        return truckList;
    }
}