package example.container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Класс тестов класса Container
 */
class ContainerTest {
    private Container container = new Container();
    private Item item = new Item(1);

    /**
     * Проверка добавления элемента в контейнер
     */
    @Test
    void testAdd() {
        assertTrue(container.add(item));
        assertEquals(1, container.size());
        assertTrue(container.contains(item));
    }

    /**
     * Проверка удаления элемента из контейнера
     */
    @Test
    void testRemove() {
        container.add(item);
        assertTrue(container.remove(item));
        assertEquals(0, container.size());
        assertFalse(container.contains(item));
    }
}