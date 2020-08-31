package ru.geekbrains.thirdquarter.springintro.client.history;

import ru.geekbrains.thirdquarter.springintro.client.TextMessage;

import java.util.List;

public interface ChatHistory {

    void addMessage(TextMessage message);

    List<TextMessage> getLastMessages(int count);

    void flush();
}
