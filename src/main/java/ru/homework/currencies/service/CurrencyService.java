package ru.homework.currencies.service;

import org.springframework.stereotype.Service;
import ru.homework.currencies.enty.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyService {
    private final int BIG_DECIMAL_SCALE = 4;

    public String getOneValue(List<Currency> currencyList, String codeFrom, String codeTo) {
        return currencyList.stream()
                .filter(a -> a.getCode().toString().equals(codeFrom))
                .map(a -> a.getMoney())
                .findFirst()
                .get().toString();
    }

    public List<String> getCurrencyCode(List<Currency> currencyList) {
        return currencyList.stream()
                .map(a -> a.getCode())
                .toList();
    }

    public Currency getCurrencyByCode(List<Currency> currencyList, String code) {
        return currencyList.stream()
                .filter(a -> a.getCode().toString().equals(code))
                .findFirst().get();
    }

    public String exchange(int count, Currency currencyFrom, Currency currencyTo) {
        //USD	1	Доллар США	103,1618
        //KZT	100	Казахстанских тенге	20,3014

        BigDecimal moneyRUB = BigDecimal.valueOf(currencyFrom.getMoney().doubleValue() / currencyFrom.getNominal() * count);
        BigDecimal money = BigDecimal.valueOf(moneyRUB.doubleValue() / currencyTo.getMoney().doubleValue() * currencyTo.getNominal());
        return currencyTo.getCode().concat(": ").concat(String.valueOf(money.setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP)));
    }
}
