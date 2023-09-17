package by.bsuir.Task_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void Test_1() {
        Expression expression = new Expression();
        double res = expression.calculate(12.23, 1421.13);
        boolean ok = String.format("%.3f", res).equals("12.336");
        assertTrue(ok, "Test_1 is failed");
    }

    @Test
    void Test_2() {
        Expression expression = new Expression();
        double res = expression.calculate(12313.12, 124124.412214);
        boolean ok = String.format("%.3f", res).equals("12313.120");
        assertTrue(ok, "Test_2 is failed");
    }

    @Test
    void Test_3() {
        Expression expression = new Expression();
        double res = expression.calculate(565656.1231, 999123.132);
        boolean ok = String.format("%.3f", res).equals("565656.123");
        assertTrue(ok, "Test_3 is failed");
    }
}