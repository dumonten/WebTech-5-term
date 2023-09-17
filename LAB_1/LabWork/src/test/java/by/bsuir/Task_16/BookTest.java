package by.bsuir.Task_16;

import org.junit.jupiter.api.Test;
import by.bsuir.Task_16.comparator.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private final ArrayList<Book> originBooks = new ArrayList<>(Arrays.asList(
                new Book("Ab", "Ba", 10),
                new Book("Bb", "Aa", 99),
                new Book("Ac", "Ba", 12),
                new Book("Db", "Ca", 124),
                new Book("Ab", "Ba", 25),
                new Book("Cb", "Da", 124),
                new Book("Bb", "Ab", 123),
                new Book("Dc", "Cb", 23)));

    //Test_1
    @Test
    void SortByTitleTest() {
        ArrayList<Book> expectedArray  = new ArrayList<>(Arrays.asList(
                new Book("Ab", "Ba", 10),
                new Book("Ab", "Ba", 25),
                new Book("Ac", "Ba", 12),
                new Book("Bb", "Aa", 99),
                new Book("Bb", "Ab", 123),
                new Book("Cb", "Da", 124),
                new Book("Db", "Ca", 124),
                new Book("Dc", "Cb", 23)));
        ArrayList<Book> books = new ArrayList<>(originBooks);
        Collections.sort(books, new SortByTitle());
        assertEquals(books, expectedArray, "Test_1 (SortByTitle() is failed");
    }

    //Test_2
    @Test
    void SortByTitleAuthorTest() {
        ArrayList<Book> expectedArray  = new ArrayList<>(Arrays.asList(
                new Book("Ab", "Ba", 10),
                new Book("Ab", "Ba", 25),
                new Book("Ac", "Ba", 12),
                new Book("Bb", "Aa", 99),
                new Book("Bb", "Ab", 123),
                new Book("Cb", "Da", 124),
                new Book("Db", "Ca", 124),
                new Book("Dc", "Cb", 23)));
        ArrayList<Book> books = new ArrayList<>(originBooks);
        Collections.sort(books, new SortByTitleAuthor());
        assertEquals(books, expectedArray, "Test_2 (SortByTitleAuthor() is failed");
    }

    //Test_3
    @Test
    void SortByAuthorTitleTest() {
        ArrayList<Book> expectedArray  = new ArrayList<>(Arrays.asList(
                new Book("Bb", "Aa", 99),
                new Book("Bb", "Ab", 123),
                new Book("Ab", "Ba", 10),
                new Book("Ab", "Ba", 25),
                new Book("Ac", "Ba", 12),
                new Book("Db", "Ca", 124),
                new Book("Dc", "Cb", 23),
                new Book("Cb", "Da", 124)));
        ArrayList<Book> books = new ArrayList<>(originBooks);
        Collections.sort(books, new SortByAuthorTitle());
        assertEquals(books, expectedArray, "Test_3 (SortByAuthorTitle() is failed");
    }

    //Test_4
    @Test
    void SortByAuthorTitlePriceTest() {
        ArrayList<Book> expectedArray  = new ArrayList<>(Arrays.asList(
                new Book("Bb", "Aa", 99),
                new Book("Bb", "Ab", 123),
                new Book("Ab", "Ba", 10),
                new Book("Ab", "Ba", 25),
                new Book("Ac", "Ba", 12),
                new Book("Db", "Ca", 124),
                new Book("Dc", "Cb", 23),
                new Book("Cb", "Da", 124)));
        ArrayList<Book> books = new ArrayList<>(originBooks);
        Collections.sort(books, new SortByAuthorTitlePrice());
        assertEquals(books, expectedArray, "Test_4 (SortByAuthorTitlePrice() is failed");
    }

}