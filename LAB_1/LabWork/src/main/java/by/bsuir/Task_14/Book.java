package by.bsuir.Task_14;

public class Book {
    private String title;
    private String author;
    private int price;
    private static int edition = 1;


    public Book() {
        this.title = "No tittle";
        this.author = "Unknown";
        this.price = 0;
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book(Book book) {
        this.title = book.title;
        this.author = book.author;
        this.price = book.price;
    }

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
}

