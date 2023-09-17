package by.bsuir.Task_9;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class BucketTest {
    //Test_1
    @Test
    void getAllBallsWeight() {
        Bucket bucket = new Bucket();
        bucket.addBall(new Ball());
        bucket.addBall(new Ball(12));
        bucket.addBall(new Ball(Color.CYAN));
        bucket.addBall(new Ball(12, Color.CYAN));
        assertEquals(Math.round(bucket.allBallsWeight()), 48);
    }

    //Test_2
    @Test
    void howManyBlueBalls() {
        Bucket bucket = new Bucket();
        bucket.addBall(new Ball());
        bucket.addBall(new Ball(12));
        bucket.addBall(new Ball(Color.BLUE));
        bucket.addBall(new Ball(12, Color.BLUE));
        bucket.addBall(new Ball(1213, Color.BLUE));
        bucket.addBall(new Ball(1211233, Color.BLUE));
        assertEquals(bucket.blueBallsAmount(), 4);
    }
}