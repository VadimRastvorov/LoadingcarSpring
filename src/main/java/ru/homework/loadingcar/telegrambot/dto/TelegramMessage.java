package ru.homework.loadingcar.telegrambot.dto;

import lombok.Builder;

@Builder
public record TelegramMessage(String command, String alg) {
}
