package ru.homework.currencies.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.currencies.enty.Currency;
import ru.homework.currencies.map.MapToCurrencyList;
import ru.homework.currencies.service.CurrencyService;
import ru.homework.currencies.service.FileReadService;

import java.util.List;

@ShellComponent
public class MyCommands {
    private final String CURRENCIES_TSV_FILE_NAME = "currencies.tsv";
    private final MapToCurrencyList mapToCurrencyList;
    private final FileReadService fileService;
    private final CurrencyService currencyService;

    public MyCommands(MapToCurrencyList mapToCurrencyList, FileReadService fileService, CurrencyService currencyService) {
        this.mapToCurrencyList = mapToCurrencyList;
        this.fileService = fileService;
        this.currencyService = currencyService;
    }

    @ShellMethod(key = "codes", value = "Print codes")
    public List<String> codes(@ShellOption(defaultValue = "PLN") String arg) {
        List<String> stringList = fileService.getListFileContent(CURRENCIES_TSV_FILE_NAME);
        List<Currency> currencyList = mapToCurrencyList.StringListToCurrencyList(stringList);
        return currencyService.getCurrencyCode(currencyList);
    }

    @ShellMethod(key = "convert", value = "Add numbers.")
    public String convert(int count, String from, String to) {
        List<String> stringList = fileService.getListFileContent(CURRENCIES_TSV_FILE_NAME);
        List<Currency> currencyList = mapToCurrencyList.StringListToCurrencyList(stringList);
        Currency currencyFrom = currencyService.getCurrencyByCode(currencyList, from);
        Currency currencyTo = currencyService.getCurrencyByCode(currencyList, to);
        return currencyService.exchange(count, currencyFrom, currencyTo);
    }
}