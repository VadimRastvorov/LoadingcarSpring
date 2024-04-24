package ru.homework.currencies.map;

import org.springframework.stereotype.Service;
import ru.homework.currencies.enty.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapToCurrencyList {
    private final String STRING_SPLIT_CHAR = "\t";
    private final String FIRST_STRING_SKIP_VALUE = "CODE";

    public List<Currency> StringListToCurrencyList(List<String> stringList) {
        List<Currency> currencyList = new ArrayList<>();
        for (String string : stringList) {
            String[] strings = string.split(STRING_SPLIT_CHAR);
            if (!strings[0].toString().equals(FIRST_STRING_SKIP_VALUE)) {
                currencyList.add(Currency.builder()
                                .code(strings[0])
                                .nominal(Integer.parseInt(strings[1].trim()))
                                .name(strings[2])
                                .money(BigDecimal.valueOf(Double.parseDouble(strings[3].trim().replace(',', '.'))))
                                .build());
            }
        }
        return currencyList;
    }
}
