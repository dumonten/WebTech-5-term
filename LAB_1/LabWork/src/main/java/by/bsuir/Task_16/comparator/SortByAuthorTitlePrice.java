package by.bsuir.Task_16.comparator;

import java.util.Comparator;
import by.bsuir.Task_16.Book;

public class SortByAuthorTitlePrice implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if (book1.getAuthor().equals(book2.getAuthor())) {
            if (book1.getTitle().equals(book2.getTitle())) {
                return book1.getPrice() - book2.getPrice();
            }
            return book1.getTitle().compareTo(book2.getTitle());
        }
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}
