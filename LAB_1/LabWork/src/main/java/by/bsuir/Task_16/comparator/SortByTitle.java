package by.bsuir.Task_16.comparator;

import java.util.Comparator;
import by.bsuir.Task_16.Book;

public class SortByTitle implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getTitle().compareTo(book2.getTitle());
    }
}