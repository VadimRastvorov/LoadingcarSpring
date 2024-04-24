package ru.homework.loadingcar.service.truck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.TruckService;
import ru.homework.loadingcar.service.truck.util.TruckUtil;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("truckServiceV3")
public class TruckServiceV3Impl implements TruckService {

    @Override
    public List<Truck> getLoadingTrucksList(List<Cargo> cargoList) {
        return loadingTruck(cargoList);
    }

    @Override
    public AlgorithmType getAlgorithmType() {
        return AlgorithmType.ALGORITHM_V3;
    }

    private List<Truck> loadingTruck(List<Cargo> cargoList) {
        return cargoList.stream()
                .map(this::loadingTruck)
                .collect(Collectors.toList());
    }

    private Truck loadingTruck(Cargo cargo) {
        return Truck.builder()
                .cargoTruck(TruckUtil.getCargoPosition(new int[TruckUtil.CAR_CASE_HEIGHT][TruckUtil.CAR_CASE_WIDTH],cargo).getCargoTruck())
                .cargoList(List.of(cargo))
                .build();
    }
}