package by.bsuir.Task_13;

public class ProgrammerBook extends Book {
    private final String language;
    private final int level;


    public ProgrammerBook() {
        super();
        language = "None";
        level = 0;
    }

    public ProgrammerBook(String title, String author, int price) {
        super(title, author, price);
        language = "None";
        level = 0;
    }

    public ProgrammerBook(String language, int level) {
        super();
        this.language = language;
        this.level = level;
    }

    public ProgrammerBook(String title, String author, int price, String language, int level) {
        super(title, author, price);
        this.language = language;
        this.level = level;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProgrammerBook pBook) {
            return super.equals(obj) && this.language.equals(pBook.language) && this.level == pBook.level;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 1303;
        int result = super.hashCode();
        result = prime * result + this.language.hashCode();
        result = prime * result + (this.level > 0 ? 2707 : 1433);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", language: " + this.language +
                ", what level is intended for: " +  this.level;
    }
}
