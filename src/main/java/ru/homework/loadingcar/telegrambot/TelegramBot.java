package ru.homework.loadingcar.telegrambot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.entity.Truck;
import ru.homework.loadingcar.service.*;
import ru.homework.loadingcar.telegrambot.config.BotConfig;
import ru.homework.loadingcar.telegrambot.dto.TelegramMessage;
import ru.homework.loadingcar.telegrambot.util.TelegramMessageUtil;
import ru.homework.loadingcar.type.AlgorithmType;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final String CURRENCIES_TSV_FILE_NAME = "package3.npk";
    private final BotConfig botConfig;

    @Autowired
    private CargoService cargoService;
    @Autowired
    @Qualifier("truckService")
    private TruckService truckService;
    @Autowired
    @Qualifier("truckServiceV2")
    private TruckService truckServiceV2;
    @Autowired
    private FileReadService fileRead;
    @Autowired
    private PrintService printService;

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

            TelegramMessage telegramMessage = TelegramMessageUtil.getTelegramMessage(messageText);
            switch (telegramMessage.command()) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "load":
                    String stringCargo = fileRead.getStringFileContent(CURRENCIES_TSV_FILE_NAME);
                    List<Cargo> cargoList = cargoService.getCargoList(stringCargo);
                    List<Truck> truckList = AlgorithmService.loadingTrucksList(cargoList,
                            AlgorithmType.get(telegramMessage.alg().equals("") ? "alg1" : telegramMessage.alg()));
                    sendMessage(chatId, printService.Print(truckList));
                    break;
                case "cargo":
                    sendMessage(chatId, fileRead.getStringFileContent(CURRENCIES_TSV_FILE_NAME));
                    break;
                default:
                    sendMessage(chatId, "Введенная команда не распознана");
            }
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Привет, " + name + "!" + "\n" +
                "пример ввода команды: load";
        sendMessage(chatId, answer);
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