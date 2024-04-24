package ru.homework.loadingcar.telegrambot.util;

import ru.homework.loadingcar.telegrambot.dto.TelegramMessage;

public class TelegramMessageUtil {
    public static TelegramMessage getTelegramMessage(String message)
    {
        String command = "";
        String alg = "";
        for (String string : message.trim().toLowerCase().split(" "))
        {
            if(!string.equals("")) {
                if (command.equals("")) {
                    command = string;
                } else if (alg.equals("")) {
                    alg = string;
                }
            }
        }
        return TelegramMessage.builder()
                .command(command)
                .alg(alg)
                .build();
    }
}
