package by.bsuir.Task_2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureGraphTest {

    //Test_1
    @Test
    void checkIfIn() {
        boolean ok = FigureGraph.checkIfIn(-10, 12);
        assertFalse(ok, "Test_1 for checkIfIn() function is failed");
    }

    //Test_2
    @Test
    void checkIfInTopRectArea() {
        boolean ok = FigureGraph.checkIfIn(-3.999999999, 1);
        assertTrue(ok, "Test_2 for checkIfInTopRectArea() function is failed");
    }

    //Test_3
    @Test
    void checkIfInBottomRectArea() {
        boolean ok = FigureGraph.checkIfIn(-5.999924141, -2.98888888);
        assertTrue(ok, "Test_3 for checkIfInBottomRectArea() function is failed");
    }

}