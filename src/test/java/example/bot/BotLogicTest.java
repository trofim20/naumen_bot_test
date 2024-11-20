package example.bot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестов класса BotLogic
 */
class BotLogicTest {

    /**
     * Тестирование команды /test и /repeat c правильными ответами
     */
    @Test
    void testTestWithCorrectAnswer() {
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);

        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2",testBot.getMessages().get(0));

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!",testBot.getMessages().get(1));
        assertEquals("Сколько будет 2 + 2 * 2", testBot.getMessages().get(2));

        botLogic.processCommand(user, "6");
        assertEquals("Правильный ответ!", testBot.getMessages().get(3));
        assertEquals("Тест завершен", testBot.getMessages().get(4));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Нет вопросов для повторения",testBot.getMessages().get(5));

    }

    /**
     * Тестирование команды /test c неправильными ответами
     */
    @Test
    void testTestWithIncorrectAnswer() {
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);

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
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);

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
     * Тестирование команды /repeat при 1 неправильном ответе
     */
    @Test
    void testRepeatWithOneIncorrectAnswer(){
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);

        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2",testBot.getMessages().get(0));

        botLogic.processCommand(user, "10");
        assertEquals("Вы ошиблись, верный ответ: 100",testBot.getMessages().get(1));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Вычислите степень: 10^2", testBot.getMessages().get(3));

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!", testBot.getMessages().get(4));
        assertEquals("Тест завершен", testBot.getMessages().get(5));

        botLogic.processCommand(user, "/repeat");
        assertEquals("Нет вопросов для повторения", testBot.getMessages().get(6));

    }
    /**
     * Тестирование команды /repeat при неправильном ответе в тесте и при повторении
     */
    @Test
    void testRepeatWithTwoIncorrectAnswerForQuestion() {
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);

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
     * Тестирование команды /notify c указанием времени в правильном формате
     */
    @Test
    void testNotify() throws InterruptedException {
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);
        botLogic.processCommand(user, "/notify");
        assertEquals("Введите текст напоминания",testBot.getMessages().get(0));

        botLogic.processCommand(user, "Напоминание");
        assertEquals("Через сколько секунд напомнить?",testBot.getMessages().get(1));

        botLogic.processCommand(user, "1");
        assertEquals("Напоминание установлено",testBot.getMessages().get(2));
        assertEquals(3, testBot.getMessages().size());
        Thread.sleep(1010);
        assertEquals("Сработало напоминание: 'Напоминание'",testBot.getMessages().get(3));
    }

    /**
     * Тестирование команды /notify c указанием времени в неправильном формате,а после в правильном
     */

    @Test
    void testNotifyWithIncorrectTime() throws InterruptedException {
        TestBot testBot = new TestBot();
        User user = new User(1L);
        BotLogic botLogic = new BotLogic(testBot);
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
