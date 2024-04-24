package ru.homework.currencies.enty;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Currency {
    private final String code;
    private final int nominal;
    private final String name;
    private final BigDecimal money;
}
