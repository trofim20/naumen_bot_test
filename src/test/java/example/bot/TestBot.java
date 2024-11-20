package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для тестирования
 */
public class TestBot implements Bot {
    /**
     * Сохраняем сообщения которые отправляет BotLogic
     */
    private final List<String> messages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }
    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }


}