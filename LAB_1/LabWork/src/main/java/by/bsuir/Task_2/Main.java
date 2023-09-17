package by.bsuir.Task_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter x and y with a space between: ");

        double x = 0.0, y = 0.0;
        if (scanner.hasNextDouble())
            x = scanner.nextDouble();
        else {
            System.out.println("Your data is incorrect!");
            System.exit(1);
        }
        if (scanner.hasNextDouble())
            y = scanner.nextDouble();

        if (FigureGraph.checkIfIn(x, y))
           System.out.println("Point is in figure!");
        else
            System.out.println("Point is not in figure!");
    }
}
