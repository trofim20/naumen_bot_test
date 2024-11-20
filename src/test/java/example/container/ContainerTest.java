package example.container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Класс тестов класса Container
 */
class ContainerTest {

    /**
     * Проверка добавления элемента в контейнер
     */
    @Test
    void testAdd() {
        Container container = new Container();
        Item item = new Item(1);
        assertTrue(container.add(item));
        assertEquals(1, container.size());
        assertTrue(container.contains(item));
    }

    /**
     * Проверка удаления элемента из контейнера
     */
    @Test
    void testRemove() {
        Container container = new Container();
        Item item = new Item(1);
        container.add(item);
        assertTrue(container.remove(item));
        assertEquals(0, container.size());
        assertFalse(container.contains(item));
    }
}