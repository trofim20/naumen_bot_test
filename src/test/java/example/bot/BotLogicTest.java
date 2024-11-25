package example.bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестов класса BotLogic
 */
class BotLogicTest {

    /**
     * Логика бота
     */
    private BotLogic botLogic;

    /**
     * Пользователь
     */
    private User user;

    /**
     * Бот для тестов
     */
    private TestBot testBot;

    /**
     * Инициализация одинаковых действий
     */
    @BeforeEach
    void setUp() {
        testBot = new TestBot();
        user = new User(1L);
        botLogic = new BotLogic(testBot);
    }

    /**
     * Тестирование команды /test c правильными ответами
     */
    @Test
    void testTestWithCorrectAnswer() {
        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2",testBot.getMessages().get(0));

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!",testBot.getMessages().get(1));
        assertEquals("Сколько будет 2 + 2 * 2", testBot.getMessages().get(2));

        botLogic.processCommand(user, "6");
        assertEquals("Правильный ответ!", testBot.getMessages().get(3));
        assertEquals("Тест завершен", testBot.getMessages().get(4));
    }

    /**
     * Тестирование команды /test c неправильными ответами
     */
    @Test
    void testTestWithIncorrectAnswer() {
        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2",testBot.getMessages().get(0));

        botLogic.processCommand(user, "10");
        assertEquals("Вы ошиблись, верный ответ: 100",testBot.getMessages().get(1));
        assertEquals("Сколько будет 2 + 2 * 2", testBot.getMessages().get(2));

        botLogic.processCommand(user, "2");
        assertEquals("Вы ошиблись, верный ответ: 6", testBot.getMessages().get(3));
        assertEquals("Тест завершен", testBot.getMessages().get(4));
    }

    /**
     * Тестирование команды /repeat при 2 неправильных ответах и правильном ответе после повтора
     */
    @Test
    void testRepeatWithTwoIncorrectAnswer(){
        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2",testBot.getMessages().get(0));

        botLogic.processCommand(user, "10");
        assertEquals("Вы ошиблись, верный ответ: 100",testBot.getMessages().get(1));
        assertEquals("Сколько будет 2 + 2 * 2", testBot.getMessages().get(2));

        botLogic.processCommand(user, "2");
        assertEquals("Вы ошиблись, верный ответ: 6", testBot.getMessages().get(3));
        assertEquals("Тест завершен", testBot.getMessages().get(4));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Вычислите степень: 10^2", testBot.getMessages().get(5));

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!", testBot.getMessages().get(6));
        assertEquals("Сколько будет 2 + 2 * 2", testBot.getMessages().get(7));

        botLogic.processCommand(user, "6");
        assertEquals("Правильный ответ!", testBot.getMessages().get(8));
        assertEquals("Тест завершен", testBot.getMessages().get(9));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Нет вопросов для повторения", testBot.getMessages().get(10));
    }

    /**
     * Тестирование команды /repeat при неправильном ответе в тесте и при повторении
     */
    @Test
    void testRepeatWithTwoIncorrectAnswerForQuestion() {
        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2", testBot.getMessages().get(0));

        botLogic.processCommand(user, "10");
        assertEquals("Вы ошиблись, верный ответ: 100", testBot.getMessages().get(1));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Вычислите степень: 10^2", testBot.getMessages().get(3));

        botLogic.processCommand(user, "10");
        assertEquals("Вы ошиблись, верный ответ: 100", testBot.getMessages().get(4));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Вычислите степень: 10^2", testBot.getMessages().get(6));

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!", testBot.getMessages().get(7));
        assertEquals("Тест завершен", testBot.getMessages().get(8));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Нет вопросов для повторения", testBot.getMessages().get(9));
    }

    /**
     * Тестирование команды /notify c указанием времени в неправильном формате,а после в правильном
     */
    @Test
    void testNotifyWithIncorrectTime() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        assertEquals("Введите текст напоминания",testBot.getMessages().get(0));

        botLogic.processCommand(user, "Напоминание");
        assertEquals("Через сколько секунд напомнить?",testBot.getMessages().get(1));

        botLogic.processCommand(user, "3.3");
        assertEquals("Пожалуйста, введите целое число",testBot.getMessages().get(2));

        botLogic.processCommand(user, "1");
        assertEquals("Напоминание установлено",testBot.getMessages().get(3));
        assertEquals(4, testBot.getMessages().size());
        Thread.sleep(1010);
        assertEquals("Сработало напоминание: 'Напоминание'",testBot.getMessages().get(4));
    }
}
