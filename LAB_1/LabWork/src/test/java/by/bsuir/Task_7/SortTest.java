package by.bsuir.Task_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void Test_1() {
        double[] arr = {10.624, 6.848, 4.293, 7.056, 2.863, -5.009, 5.022, -7.802, -1.702, 10.319, -3.186, 1.503, 7.039, -9.085, -0.114};
        double[] expectedArr = {-9.085, -7.802, -5.009, -3.186, -1.702, -0.114, 1.503, 2.863, 4.293, 5.022, 6.848, 7.039, 7.056, 10.319, 10.624};
        double[] sortedArr = Sort.sort(arr);

        for (int i = 0; i < sortedArr.length; i++) {
            boolean result = Math.abs(expectedArr[i] - sortedArr[i]) < 1E-4;
            assertTrue(result, "Test_1 (sort() function) is failed");
        }
    }

    @Test
    void Test_2() {
        double[] arr = {0.549, -0.714, 7.416, -3.029, 1.573, -3.164, 5.320, 7.870, -4.475, 5.847, 3.309, 2.218};
        double[] expectedArr = {-4.475, -3.164, -3.029, -0.714, 0.549, 1.573, 2.218, 3.309, 5.320, 5.847, 7.416, 7.870};
        double[] sortedArr = Sort.sort(arr);

        for (int i = 0; i < sortedArr.length; i++) {
            boolean result = Math.abs(expectedArr[i] - sortedArr[i]) < 1E-4;
            assertTrue(result, "Test_1 (sort() function) is failed");
        }
    }
}