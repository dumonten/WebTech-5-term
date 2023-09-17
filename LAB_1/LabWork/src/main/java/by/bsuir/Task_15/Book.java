package by.bsuir.Task_15;

import java.util.concurrent.ThreadLocalRandom;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int price;
    private static int edition = 1;
    private int isbn;


    public Book() {
        this.title = "No tittle";
        this.author = "Unknown";
        this.price = 0;
        this.isbn = 0;
    }

    public Book(String title, int isbn) {
        this.title = title;
        this.isbn = isbn;
        this.author = "Unknown";
        this.price = 0;
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
    }

    public Book(Book book) {
        this.title = book.title;
        this.author = book.author;
        this.price = book.price;
        this.isbn = book.isbn;
    }

    public String getTitle() { return this.title; }
    public String getAuthor() { return this.author; }
    public int getPrice() { return this.price; }
    public int getIsbn() { return this.isbn; }
    public static int getEdition() { return edition; }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            Book book = (Book)obj;
            return this.title.equals(book.title) && this.author.equals(book.author) && this.price == book.price;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 1303;
        int result = 1;
        result = prime * result + this.title.hashCode();
        result = prime * result + (this.price > 0 ? 2707 : 1433);
        result = prime * result + this.author.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  "Book: Title is '" + this.title +
                "', ed.№ " + edition +
                ", author: " + this.author +
                ", price: " + this.price + "$";
    }

    @Override
    public Book clone() {
        Book clone = null;
        try {
            clone = (Book)super.clone();
            clone.title = this.title;
            clone.author = this.author;
            clone.price = this.price;
        } catch (CloneNotSupportedException cns) {
            return new Book(this);
        }
        return clone;
    }

    @Override
    public int compareTo(Book book) {
        return this.isbn - book.isbn;
    }
}

