package by.bsuir.Task_7;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter n (size of array): ");

        int n = 0, minValue = -10, maxValue = 10;
        if (scanner.hasNextInt())
            n = scanner.nextInt();
        else {
            System.out.println("Your data is incorrect!");
            System.exit(1);
        }

        double[] arr = new double[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextDouble(minValue, maxValue + 1);
            System.out.format("%.3f, ", arr[i]);
        }

        System.out.println("\n");

        Sort.sort(arr);
        for (double a : arr) {
            System.out.format("%.3f, ", a);
        }
    }
}
