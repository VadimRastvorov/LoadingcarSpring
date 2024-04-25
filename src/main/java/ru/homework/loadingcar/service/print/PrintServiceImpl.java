package ru.homework.loadingcar.service.print;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.PrintService;

import java.util.List;

@Slf4j
@Service
public class PrintServiceImpl implements PrintService {
    public String printTruckListToString(List<Truck> truckList) {
        StringBuilder stringBuilder = new StringBuilder();
        truckList
                .forEach(a -> printTruckToStringBuilder(a, stringBuilder));
        return stringBuilder.toString();
    }

    private void printTruckToStringBuilder(Truck truck, StringBuilder stringBuilder) {
        stringBuilder.append(System.lineSeparator()).append(truck.toString());
    }
}
