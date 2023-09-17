package by.bsuir.Task_16.comparator;

import java.util.Comparator;
import by.bsuir.Task_16.Book;

public class SortByAuthorTitle implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if (book1.getAuthor().equals(book2.getAuthor())) {
            return book1.getTitle().compareTo(book2.getTitle());
        }
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}

