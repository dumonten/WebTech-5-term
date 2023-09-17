package by.bsuir.Task_3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FunctionCalculatingTest {

    //Test_1
    @Test
    void setOtherData() {
        ArrayList<Double> origin_x_values = new ArrayList<>(Arrays.asList(-10.0, -9.77, -9.54, -9.31, -9.08)),
                origin_y_values = new ArrayList<>(Arrays.asList(-0.6484, -0.3596, -0.1157, 0.1153, 0.3591));

        FunctionCalculating tangentFunction = new FunctionCalculating(23.0, 43.0, 2.0, Math::tan);
        tangentFunction.setOtherData(-10.0, -9.0, 0.23);
        ArrayList<Double> x_values = tangentFunction.getXValues();
        ArrayList<Double> y_values = tangentFunction.getYValues();

        for (int i = 0, n = x_values.size(); i < n; i++) {
            boolean result = Math.abs(x_values.get(i) - origin_x_values.get(i)) < 1E-4;
            assertTrue(result, "Test_1 (setOtherData() function) for x values is failed");
        }
        for (int i = 0, n = y_values.size(); i < n; i++) {
            boolean result = Math.abs(y_values.get(i) - origin_y_values.get(i)) < 1E-4;
            assertTrue(result, "Test_1 (setOtherData() function) for y values is failed");
        }
    }

    @Test
    void Test_2() {
        ArrayList<Double> origin_x_values = new ArrayList<>(Arrays.asList(1.0, 1.2, 1.4, 1.6, 1.8, 2.0)),
                origin_y_values = new ArrayList<>(Arrays.asList(1.5574, 2.5722, 5.7979, -34.2325, -4.2863, -2.1850));

        FunctionCalculating tangentFunction = new FunctionCalculating(1.0, 2.0, 0.2, Math::tan);
        ArrayList<Double> x_values = tangentFunction.getXValues();
        ArrayList<Double> y_values = tangentFunction.getYValues();

        for (int i = 0, n = x_values.size(); i < n; i++) {
            boolean result = Math.abs(x_values.get(i) - origin_x_values.get(i)) < 1E-4;
            assertTrue(result, "Test_2 for x values is failed");
        }
        for (int i = 0, n = y_values.size(); i < n; i++) {
            boolean result = Math.abs(y_values.get(i) - origin_y_values.get(i)) < 1E-4;
            assertTrue(result, "Test_2 for y values is failed");
        }
    }
}