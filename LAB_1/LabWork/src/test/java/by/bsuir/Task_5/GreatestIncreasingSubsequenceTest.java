package by.bsuir.Task_5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreatestIncreasingSubsequenceTest {

    //Test_1
    @Test
    void Test_1() {
        int[] arr = {7, 1, 2, 3, 9, 4, 10, 22, 11};
        boolean result = 3 == GreatestIncreasingSubsequence.numbersToDelete(arr);
        assertTrue(result, "Test_1 (numbersToDelete()) is failed");
    }

    //Test_2
    @Test
    void Test_2() {
        int[] arr = {0, 1, 2, 3, 4, 5, -1, 2, -1, 10};
        boolean result = 3 == GreatestIncreasingSubsequence.numbersToDelete(arr);
        assertTrue(result, "Test_2 (numbersToDelete()) is failed");
    }
}