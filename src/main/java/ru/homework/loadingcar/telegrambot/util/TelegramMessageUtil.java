package ru.homework.loadingcar.telegrambot.util;

import ru.homework.loadingcar.telegrambot.dto.TelegramMessage;
import ru.homework.loadingcar.type.AlgorithmType;
import ru.homework.loadingcar.type.TelegramCommandType;

public class TelegramMessageUtil {
    public static TelegramMessage getTelegramMessage(String message) {
        String command = "";
        String alg = "";
        for (String string : message.trim().toLowerCase().split(" ")) {
            if (!string.isEmpty()) {
                if (command.isEmpty()) {
                    command = string;
                } else if (alg.isEmpty()) {
                    alg = string;
                }
            }
        }
        return TelegramMessage.builder()
                .telegramCommandType(TelegramCommandType.get(command))
                .algorithmType(AlgorithmType.get(alg))
                .build();
    }
}
