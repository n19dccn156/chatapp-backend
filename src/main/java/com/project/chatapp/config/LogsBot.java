package com.project.chatapp.config;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class LogsBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
//        SendMessage sendMessage = new SendMessage();
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public String getBotToken() {
        return "6232131230:AAFf2YasRvzdWUnEXDo1Z7Z7gY3qFo2VtHk";
    }

    @Override
    public String getBotUsername() {
        return "ots_log_bot";
    }
}
