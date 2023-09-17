package by.bsuir.Task_6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareMatrixTest {

    @Test
    void getMatrix() {
        double[] arr = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        double[][] expectedMatrix = {{0.0, 1.0, 2.0, 3.0, 4.0, 5.0},
                {1.0, 2.0, 3.0, 4.0, 5.0, 0.0},
                {2.0, 3.0, 4.0, 5.0, 0.0, 1.0},
                {3.0, 4.0, 5.0, 0.0, 1.0, 2.0},
                {4.0, 5.0, 0.0, 1.0, 2.0, 3.0},
                {5.0, 0.0, 1.0, 2.0, 3.0, 4.0}};
        double[][] squareMatrix = SquareMatrix.getMatrix(arr);

        boolean result;
        for (int i = 0; i < squareMatrix.length; i++) {
            for (int j = 0; j < squareMatrix.length; j++) {
                result = Math.abs(squareMatrix[i][j] - expectedMatrix[i][j]) < 1E-4;
                assertTrue(result, "Test_1 (setOtherData() function) is failed");
            }
        }
    }
}