package ru.homework.loadingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.cargo.CargoServiceImpl;
import ru.homework.loadingcar.service.fileread.FileReadServiceImpl;
import ru.homework.loadingcar.service.print.PrintServiceImpl;
import ru.homework.loadingcar.telegrambot.dto.TelegramMessage;
import ru.homework.loadingcar.telegrambot.util.TelegramMessageUtil;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.List;

@Service
public class ProcessingInComMessageService {
    private final String CURRENCIES_TSV_FILE_NAME = "package3.npk";
    private final FileReadService fileRead;
    private final CargoService cargoService;
    private final PrintService printService;

    @Autowired
    public ProcessingInComMessageService() {
        this.fileRead = new FileReadServiceImpl();
        this.cargoService = new CargoServiceImpl();
        this.printService = new PrintServiceImpl();
    }

    public String telegramPrint(String messageText, String name) {
        TelegramMessage telegramMessage = TelegramMessageUtil.getTelegramMessage(messageText);

        switch (telegramMessage.telegramCommandType()) {
            case START:
                return "Привет, " + name + "!" + "\n" +
                        "пример ввода команды: load";
            case LOAD:
                return printLoadingTruck(CURRENCIES_TSV_FILE_NAME, telegramMessage.algorithmType());
            case CARGO:
                return fileRead.createStringFileContent(CURRENCIES_TSV_FILE_NAME);
            default:
                return "Введенная команда не распознана";
        }
    }

    public String shellPrintLoadingTruck(String fileName, String alg) {
        return printLoadingTruck(fileName, AlgorithmType.get(alg));
    }

    public String shellPrintCargo(String fileName) {
        return fileRead.createStringFileContent(fileName);
    }

    private String printLoadingTruck(String fileName, AlgorithmType algorithmType) {
        String fileContent = fileRead.createStringFileContent(fileName);
        List<Cargo> cargoList = cargoService.createCargoList(fileContent);
        List<Truck> truckList = AlgorithmService.loadingTrucksList(cargoList, algorithmType);
        return printService.printTruckListToString(truckList);
    }
}
