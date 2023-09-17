package by.bsuir.Task_4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {

    //Test_1
    @Test
    void isPrime() {
        int prime = 7, notPrime = 16;
        boolean result = PrimeChecker.isPrime(prime) && !PrimeChecker.isPrime(notPrime);
        assertTrue(result, "Test_1 (isPrime() for a number) is failed");
    }

    //Test_2
    @Test
    void testIsPrime() {
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
                4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33};
        ArrayList<Integer> indexesOfPrimeNumbers = PrimeChecker.isPrime(arr);
        boolean result = indexesOfPrimeNumbers.size() == arr.length / 2;
        assertTrue(result, "Test_2 (isPrime() for an array) is failed");
    }
}