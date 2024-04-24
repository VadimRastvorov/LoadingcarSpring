package ru.homework.loadingcar.service.print;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.PrintService;

import java.util.List;

@Slf4j
@Service
public class PrintServiceImpl implements PrintService {
    public String Print(List<Truck> truckList) {
        StringBuilder stringBuilder = new StringBuilder();
        truckList.stream()
                .forEach(a -> PrintOneCar(a, stringBuilder));
        return stringBuilder.toString();
    }

    private StringBuilder PrintOneCar(Truck truck, StringBuilder stringBuilder) {
        return stringBuilder.append(System.getProperty("line.separator")).append(truck.toString());
    }
}
