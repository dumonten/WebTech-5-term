package by.bsuir.Task_8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnifierTest {

    @Test
    void Test_1() {
        List<Double> arr1 = new LinkedList<>(Arrays.asList(-1.0, -1.0, 10.0, 20.0, 30.0));
        List<Double> arr2 = new LinkedList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        ArrayList<Integer> expectedPositions = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        boolean result = Unifier.unifyIncreasingSequences(arr1, arr2).equals(expectedPositions);
        assertTrue(result, "Test_1 (unifyIncreasingSequences() function) is failed");
    }

    @Test
    void Test_2() {
        List<Double> arr1 = new LinkedList<>(Arrays.asList(1.0, 1.0, 5.0));
        List<Double> arr2 = new LinkedList<>(Arrays.asList(1.0, 2.0, 5.0, 6.0));
        ArrayList<Integer> expectedPositions = new ArrayList<>(Arrays.asList(2, 3, 5, 6));
        boolean result = Unifier.unifyIncreasingSequences(arr1, arr2).equals(expectedPositions);
        assertTrue(result, "Test_2 (unifyIncreasingSequences() function) is failed");
    }
}