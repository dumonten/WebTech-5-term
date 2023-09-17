package by.bsuir.Task_12;

public class Main {

    public static void main(String[] args) {
        Book book = new Book();
        System.out.println(book);
        System.out.println(book.hashCode());
        Book fairyBook = new Book("Fairy in the world of pain", "Alina Tot", 12);
        Book fairyBook_2 = new Book("Fairy in the world of pain", "Alina Tot", 12);
        Book not_fairyBook = new Book("Not fairy in the world of happiness", "Alina Tot", 12);

        System.out.println(fairyBook);
        System.out.println(fairyBook.hashCode());
        System.out.println(fairyBook_2);
        System.out.println(fairyBook_2.hashCode());
        System.out.println(not_fairyBook);
        System.out.println(not_fairyBook.hashCode());
        System.out.println("Is fairyBook equals fairyBook_2 " + fairyBook.equals(fairyBook_2));
        System.out.println("Is fairyBook equals not_fairyBook " + fairyBook.equals(not_fairyBook));
    }
}
