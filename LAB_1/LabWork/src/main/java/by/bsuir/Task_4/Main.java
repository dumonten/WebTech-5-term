package by.bsuir.Task_4;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter n (size of array): ");

        int n = 0, minValue = -1000, maxValue = 1000;
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        else {
            System.out.println("Your data is incorrect!");
            System.exit(1);
        }

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
            System.out.format("[%d] = %d ", i, arr[i]);
        }

        System.out.print("\nIndexes of prime numbers in array: ");

        ArrayList<Integer> indexOfPrimeNumbers = PrimeChecker.isPrime(arr);
        for (Integer index : indexOfPrimeNumbers) {
            System.out.format("[%d] ", index);
        }
    }
}
