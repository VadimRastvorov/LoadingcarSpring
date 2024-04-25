package ru.homework.loadingcar.service;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmService {
    private static final Map<AlgorithmType, TruckService> map = new HashMap<>();

    static {
        Reflections reflections = new Reflections(TruckService.class.getPackageName());
        reflections.getSubTypesOf(TruckService.class)
                .forEach(aClass -> createTruckServiceAndPutIntoMap(aClass));

        for (var algorithmType : AlgorithmType.values()) {
            if (!map.containsKey(algorithmType)) {
                throw new IllegalStateException("Not all values of AlgorithmType have appropriate TruckService");
            }
        }
    }

    @SneakyThrows
    private static void createTruckServiceAndPutIntoMap(Class<? extends TruckService> aClass) {
        TruckService truckService = aClass.newInstance();
        map.put(truckService.getAlgorithmType(), truckService);
    }

    public static List<Truck> loadingTrucksList(List<Cargo> cargoList, AlgorithmType algorithmType) {
        return map.get(algorithmType).getLoadingTrucksList(cargoList);
    }
}
