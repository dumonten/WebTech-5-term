package by.bsuir.Task_15;

import by.bsuir.Task_16.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void Test_1() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book_1", 10));
        books.add(new Book("Book_2", 12));
        books.add(new Book("Book_3", 9));
        books.add(new Book("Book_4", 1));
        books.add(new Book("Book_5", 23));
        books.add(new Book("Book_6", 100));
        Collections.sort(books);
        int[] isbns = {1, 9, 10, 12, 23, 100};
        for (int i = 0; i < books.size(); i++) {
            assertEquals(isbns[i], books.get(i).getIsbn(), "Test_1 (sort in ascending order) is failed");
        }
    }

    @Test
    void Test_2() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book_1", 10));
        books.add(new Book("Book_2", 12));
        books.add(new Book("Book_3", 9));
        books.add(new Book("Book_4", 1));
        books.add(new Book("Book_5", 23));
        books.add(new Book("Book_6", 100));
        Collections.sort(books, Collections.reverseOrder());
        int[] isbns = {100, 23, 12, 10, 9, 1};
        for (int i = 0; i < books.size(); i++) {
            assertEquals(isbns[i], books.get(i).getIsbn(), "Test_2 (sort in descending order) is failed");
        }
    }
}