package com.example.AbramskiyExamenHelperBot.service;


import com.example.AbramskiyExamenHelperBot.bot.Config;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component

public class BotExam extends TelegramLongPollingBot {
    final Config config;
    public BotExam(Config config){
        this.config = config;
    }
    String[] listOfLinks = {
            "https://drive.google.com/file/d/1WizTh1TyR_hdsRo4IrNVKiaKnz_9WJAl/view?usp=sharing",
            "https://drive.google.com/file/d/1jb2ja4gBv_uPIZgLEM6XwdXuZxh4jAM5/view?usp=sharing",
            "https://drive.google.com/file/d/1UgYodxfMj1N_W9mF8vBJVwy2YHIb7GDe/view?usp=sharing",
            "https://drive.google.com/file/d/10VZPioLilpa-98RZA6dbZ6e9wvdFdjp4/view?usp=sharing",
            "https://drive.google.com/file/d/1uhnhSRn7epijE0slHyuHy_kOUr2XBtUU/view?usp=sharing",
            "https://drive.google.com/file/d/1Q3RCcA_Oznk2WZqO9Ai0cnXJj4w0cUpk/view?usp=sharing",
            "https://drive.google.com/file/d/1w76dW2B1ui0IMdkab1JRLsVD8zJAhLb_/view?usp=sharing",
            "https://drive.google.com/file/d/1KhaIrSQXnK7BygxbVL2OESV0y_zrgO6s/view?usp=sharing",
            "https://drive.google.com/file/d/1Y6CZrtz7f5iSRht89zxCUu0svZFiUGcg/view?usp=sharing",
    };

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            Thread thread = new Thread(()-> {
                switch (messageText){
                    case "/start" ->
                            startCommandReceived(chatId,update.getMessage().getChat().getFirstName());
                    case "1 - Вспомним 1 семетр" ->
                            sendMessage(
                                    chatId,
                                    "Подзаебала эта тема " + listOfLinks[0]
                            );
                    case "2 - Джинерики, Коллекции - начало" ->
                            sendMessage(
                                    chatId,
                                    "Дай бог им здоровье, спасибо за то что они меня спасали, если бы не они я бы конечно не выжил " + listOfLinks[1]
                            );
                    case "3 - Коллекции продолжение" ->
                            sendMessage(
                                    chatId,
                                    "Да мне было страшно но я это сделал " + listOfLinks[2]
                            );
                    case "4 - Функциональная Парадигма" ->
                            sendMessage(
                                    chatId,
                                    "Ну в общем-то да ну как это происходит я не понимаю у кого то это щёлкает, а у кого-то это не щёлкает" + listOfLinks[3]
                            );
                    case "5 - Тестирование Разработчикам" ->
                            sendMessage(
                                    chatId,
                                    "Виноваты ли мы?? Да конечно мы все виноваты в этом пиздеце " + listOfLinks[4]
                            );
                    case "6 - Input/Output Потоки" ->
                            sendMessage(
                                    chatId,
                                    "Мы любим такие формы, форматы ооа " + listOfLinks[5]
                            );
                    case "7 - Введение в многопоточность" ->
                            sendMessage(
                                    chatId,
                                    "Это было не просто смело, это было пиздеец как смело" + listOfLinks[6]
                            );
                    case "8 - Рефлексия" ->
                            sendMessage(
                                    chatId,
                                    "Я не мог в это поверить это было невозможно я спорил со всеми" + listOfLinks[7]
                            );
                    case "9 - Введение в Паттерны Проектирования" ->
                            sendMessage(
                                    chatId,
                                    "Ну мне взяло где-то 48 часов, как бы осознать че вообще происходит"+ listOfLinks[8]
                            );
                    default -> sendMessage(chatId,"Сочувствую");
                }
            });
            thread.start();
        }
    }
    public void startCommandReceived(long chatId,String userName){
        String answer = ("Привет, " + userName + "! Я специальный бот, созданный Тагиром и Данилом. Моя цель - облегчить вам подготовку к экзамену! Отправьте мне цифру от 1 до 9, и я пришлю вам презентацию Михаила Абрамского под этим номером.\n");
        sendMessage(chatId,answer);
    }
    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("1 - Вспомним 1 семетр");
        row.add("2 - Джинерики, Коллекции - начало");
        row.add("3 - Коллекции продолжение");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("4 - Функциональная Парадигма");
        row.add("5 - Тестирование Разработчикам");
        row.add("6 - Input/Output Потоки");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("7 - Введение в многопоточность");
        row.add("8 - Рефлексия");
        row.add("9 - Введение в Паттерны Проектирования");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
