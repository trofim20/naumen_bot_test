package example.note;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Класс тестов класса NoteLogic
 */
class NoteLogicTest {
    NoteLogic notelogic = new NoteLogic();

    /**
     * Проверка работы добавления заметок и их просмотра
     */
    @Test
    void testAdd() {
        notelogic.handleMessage("/add Note 1");
        String notes = notelogic.handleMessage("/notes");
        assertEquals("Your notes: Note 1", notes);
    }

    /**
     * Проверка работы редактирования заметок
     */
    @Test
    void testEdit() {
        notelogic.handleMessage("/add Note 1");
        notelogic.handleMessage("/edit Note 2");
        String notes = notelogic.handleMessage("/notes");
        assertEquals("Your notes: Note 2", notes);
    }

    /**
     * Проверка работы удаления заметок
     */
    @Test
    void testDel() {
        notelogic.handleMessage("/add Note 1");//если я знаю что он не работает, я могу так реализовать, что бы он лег?
        notelogic.handleMessage("/del");
        String notes = notelogic.handleMessage("/notes");
        assertEquals("Your notes: Note 1", notes);
    }

}