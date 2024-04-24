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
@Service("truckService")
public class TruckServiceImpl implements TruckService {
    @Override
    public List<Truck> getLoadingTrucksList(List<Cargo> cargoList) {
        return loadingCars(packageListSorted(cargoList));
    }

    @Override
    public AlgorithmType getAlgorithmType() {
        return AlgorithmType.ALGORITHM_V1;
    }

    private List<Truck> loadingCars(List<Cargo> cargoList) {
        int[][] cargoMatrix = new int[TruckUtil.CAR_CASE_HEIGHT][TruckUtil.CAR_CASE_WIDTH];
        List<Cargo> cargoLoad = new ArrayList<>();
        List<Truck> truckList = new ArrayList<>();
        for (Cargo cargo : cargoList) {
            CargoPosition position = TruckUtil.getCargoPosition(cargoMatrix, cargo);
            if (position.isLoadingNewCar()) {
                truckList.add(loadingTruck(cargoLoad, cargoMatrix));
                cargoMatrix = new int[TruckUtil.CAR_CASE_HEIGHT][TruckUtil.CAR_CASE_WIDTH];
                cargoLoad.clear();
            }
            addOnePackage(cargoMatrix, cargo, position, cargoLoad);
        }
        truckList.add(loadingTruck(cargoLoad, cargoMatrix));
        return truckList;
    }

    private void addOnePackage(int[][] cargoMatrix, Cargo cargo, CargoPosition cargoPosition, List<Cargo> cargoList) {
        for (int i = 0; i < cargo.size().length; i++) {
            for (int j = 0; j < cargo.size()[i]; j++) {
                cargoMatrix[i + cargoPosition.getStartHeight()][j + cargoPosition.getStartWidth()] = cargo.number();
            }
        }
        cargoList.add(cargo);
    }

    private List<Cargo> packageListSorted(List<Cargo> cargoList) {
        return cargoList.stream().sorted((a, b) -> b.number() - a.number()).toList();
    }

    private Truck loadingTruck(List<Cargo> cargoList, int[][] cargo) {
        log.info("погрузка автомобиля: '{}'", cargoList.stream().map(a -> a.number()).toList());
        return Truck.builder()
                .cargoTruck(cargo)
                .cargoList(cargoList)
                .build();
    }
}
