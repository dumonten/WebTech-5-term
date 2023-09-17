package by.bsuir.Task_13;

public class Book {
    protected String title;
    protected String author;
    protected int price;
    protected static int edition;


    public Book() {
        this.title = "No tittle";
        this.author = "Unknown";
        this.price = 0;
        edition = 1;
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
        edition = 1;
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
}

