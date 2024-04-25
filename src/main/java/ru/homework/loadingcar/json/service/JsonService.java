package ru.homework.loadingcar.json.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.json.dto.TrucksJson;
import ru.homework.loadingcar.json.map.TrucksJsonToTrucks;
import ru.homework.loadingcar.json.map.TrucksToTrucksJson;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class JsonService {
    private final String FILE_DIRECTORY_RESOURCES = "json";

    @SneakyThrows
    public void serialization(List<Truck> truck, String fileName) {
        log.info("запуск метода Serialization с truckV2s: '{}'", truck.stream()
                .map(Truck::getCargoList)
                .toList());
        TrucksJson trucksJson = new TrucksToTrucksJson(truck).mapToTrucksJson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(getStringFileContent(fileName)), trucksJson);
    }

    public List<Truck> deSerialization(String fileName) throws IOException {
        log.info("запуск метода DeSerialization");
        ObjectMapper mapper = new ObjectMapper();
        String readString = Files.readString(Path.of(getStringFileContent(fileName)));
        TrucksJson trucksJsonRead = mapper.readValue(new StringReader(readString), TrucksJson.class);
        return new TrucksJsonToTrucks(trucksJsonRead).mapToTruckList();
    }

    private String getStringFileContent(String fileName) {
        return FILE_DIRECTORY_RESOURCES.concat("/".concat(fileName));
    }
}
