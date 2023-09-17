package by.bsuir.Task_15;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        System.out.println("Initial array: ");
        for (int i = 0; i < 10; i ++) {
            String title = String.format("Book №%d", i), author = "Grass Gilbert";
            Book book = new Book(title, author, 10);
            System.out.println(title + ": " + book.getIsbn());
            books.add(book);
        }
        System.out.println("\nSorted array: ");
        Collections.sort(books);
        for (Book book: books) {
            System.out.println(book.getTitle() + ": " + book.getIsbn());
        }
    }
}
