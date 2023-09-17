package by.bsuir.Task_9;

import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    //Test_1
    @Test
    void getWeight() {
        Ball ball = new Ball(1123);
        assertEquals(ball.getWeight(), 1123);
    }

    //Test_2
    @Test
    void getColor() {
        Ball ball = new Ball(4554, Color.ORANGE);
        assertEquals(ball.getColor(), Color.ORANGE);
    }
}