package ru.homework.loadingcar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.loadingcar.service.ProcessingInComMessageService;

@Slf4j
@ShellComponent
public class Commands {
    private final String CURRENCIES_TSV_FILE_NAME = "package3.npk";
    private final ProcessingInComMessageService processingIncomMessageService;

    @Autowired
    public Commands() {
        this.processingIncomMessageService = new ProcessingInComMessageService();
    }

    @ShellMethod(key = "print", value = "Print cargo file")
    public String print(@ShellOption(defaultValue = CURRENCIES_TSV_FILE_NAME) String fileName) {
        log.info("file: '{}'", fileName);
        return processingIncomMessageService.shellPrintCargo(fileName);
    }

    @ShellMethod(key = "load", value = "load car")
    public String load(@ShellOption(defaultValue = CURRENCIES_TSV_FILE_NAME) String fileName,
                       @ShellOption(defaultValue = "alg1") String alg) {
        return processingIncomMessageService.shellPrintLoadingTruck(fileName, alg);
    }
}