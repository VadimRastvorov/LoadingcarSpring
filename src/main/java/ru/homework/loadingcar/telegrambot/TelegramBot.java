package ru.homework.loadingcar.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.homework.loadingcar.service.ProcessingInComMessageService;
import ru.homework.loadingcar.telegrambot.config.BotConfig;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final ProcessingInComMessageService processingInComMessageService;

    @Autowired
    public TelegramBot(BotConfig botConfig, ProcessingInComMessageService processingInComMessageService) {
        this.botConfig = botConfig;
        this.processingInComMessageService = processingInComMessageService;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            log.info("chatId: '{}' messageText: '{}'", chatId, messageText);
            sendMessage(chatId, processingInComMessageService.telegramPrint(messageText, update.getMessage().getChat().getFirstName()));
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.info("ошибка в методе sendMessage: '{}'", e);
        }
    }
}