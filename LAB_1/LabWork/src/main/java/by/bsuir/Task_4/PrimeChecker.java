package by.bsuir.Task_4;

import java.util.ArrayList;

public class PrimeChecker {

    public static boolean isPrime(int n) {
        if (n == 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> isPrime(int[] arr) {
        ArrayList<Integer> indexesOfPrimeNumbers = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (isPrime(arr[i])) {
                indexesOfPrimeNumbers.add(i);
            }
        }
        return indexesOfPrimeNumbers;
    }
}
