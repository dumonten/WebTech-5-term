package by.bsuir.Task_13;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ProgrammerBook> books = new ArrayList<ProgrammerBook>();
        books.add(new ProgrammerBook());
        books.add(new ProgrammerBook("Python", 3));
        books.add(new ProgrammerBook("Python", 3));
        books.add(new ProgrammerBook("Python in the world of mess", "Grass Rupert", 12));
        books.add(new ProgrammerBook("Python in the world of mess", "Grass Rupert", 12, "Python", 3));

        for (int i = 0; i < books.size(); i++) {
            System.out.format("[%d]: %s\n", i+1, books.get(i));
        }
        System.out.format("Book with 2 number equals 3: %b\n", books.get(1).equals(books.get(2)));
        System.out.format("Hashcode of books 2, 3, 4, 5 : %d, %d, %d, %d\n", books.get(1).hashCode(), books.get(2).hashCode(),
                                                                             books.get(3).hashCode(), books.get(4).hashCode());
    }
}
