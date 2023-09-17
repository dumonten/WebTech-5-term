package by.bsuir.Task_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter a, b and h with a space between: ");

        double a = 0.0, b = 0.0, h = 0.0;
        if (scanner.hasNextDouble())
            a = scanner.nextDouble();
        else {
            System.out.println("Your data is incorrect!");
            System.exit(1);
        }
        if (scanner.hasNextDouble())
            b = scanner.nextDouble();
        if (scanner.hasNextDouble())
            h = scanner.nextDouble();

        FunctionCalculating tangentFunction = new FunctionCalculating(a, b, h, Math::tan);
        ArrayList<Double> x_values = tangentFunction.getXValues(), y_values = tangentFunction.getYValues();

        System.out.println("+----------------+---------------+");
        System.out.println("|       x        |        y      |");
        System.out.println("+----------------+---------------+");
        for (int i = 0, n = x_values.size(); i < n; i++) {
            String x_space, y_space;
            x_space = x_values.get(i) < 0 ? "" : " ";
            y_space = y_values.get(i) < 0 ? "" : " ";
            System.out.format("|    " + x_space + "%.4f    |    " + y_space + "%.4f    |\n", x_values.get(i), y_values.get(i));
        }
        System.out.println("+----------------+---------------+");
    }
}
