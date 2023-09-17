package by.bsuir.Task_14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void Test_1() {
        Book originBook = new Book();
        Book cloneBook = originBook.clone();
        assertEquals(cloneBook, originBook, "Test_1 (testClone() function) is failed");
    }

    @Test
    void Test_2() {
        Book originBook = new Book("Interesting book", "Alina Tot", 12123);
        Book cloneBook = originBook.clone();
        assertEquals(cloneBook, originBook, "Test_2 (testClone() function) is failed");
    }

    @Test
    void Test_3() {
        Book originBook = new Book("Interesting book", "Alina Tot", 12123);
        Book cloneBook = new Book(originBook);
        assertEquals(cloneBook, originBook, "Test_3 (cloning constructor) is failed");
    }
}