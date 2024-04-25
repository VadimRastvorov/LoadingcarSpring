package ru.homework.loadingcar.telegrambot.dto;

import lombok.Builder;
import ru.homework.loadingcar.type.AlgorithmType;
import ru.homework.loadingcar.type.TelegramCommandType;

@Builder
public record TelegramMessage(TelegramCommandType telegramCommandType, AlgorithmType algorithmType) {
}
