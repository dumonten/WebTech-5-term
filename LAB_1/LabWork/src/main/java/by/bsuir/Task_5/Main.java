package by.bsuir.Task_5;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter n (size of array): ");

        int n = 0, minValue = -1000, maxValue = 1000;
        if (scanner.hasNextInt())
            n = scanner.nextInt();
        else {
            System.out.println("Your data is incorrect!");
            System.exit(1);
        }

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
            System.out.format("%d, ", arr[i]);
        }

        int numToDelete = GreatestIncreasingSubsequence.numbersToDelete(arr);
        System.out.format("\nNumbers to delete: %d", numToDelete);
    }
}
