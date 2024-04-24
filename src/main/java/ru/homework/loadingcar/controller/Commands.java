package ru.homework.loadingcar.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.json.service.JsonService;
import ru.homework.loadingcar.service.*;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ShellComponent
public class Commands {
    private final String CURRENCIES_TSV_FILE_NAME = "package3.npk";

    @Autowired
    private FileReadService fileRead;
    @Autowired
    private CargoService cargoService;
    @Autowired
    @Qualifier("truckService")
    private TruckService truckService;
    @Autowired
    @Qualifier("truckServiceV2")
    private TruckService truckServiceV2;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private PrintService printService;

    @ShellMethod(key = "print", value = "Print cargo file")
    public String print(@ShellOption(defaultValue = CURRENCIES_TSV_FILE_NAME) String file) {
        log.info("file: '{}'", file);
        return fileRead.getStringFileContent(file);
    }

    @ShellMethod(key = "load", value = "load car")
    public String load(@ShellOption(defaultValue = CURRENCIES_TSV_FILE_NAME) String file,
                       @ShellOption(defaultValue = "alg1") String alg) {
        log.info("file: '{}' alg: '{}'", file, alg);
        String string = fileRead.getStringFileContent(file);
        List<Cargo> cargoList = cargoService.getCargoList(string);
        List<Truck> truckList = AlgorithmService.loadingTrucksList(cargoList, AlgorithmType.get(alg));
        return printService.Print(truckList);
    }


}