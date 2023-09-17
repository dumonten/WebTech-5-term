package by.bsuir.Task_9;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Ball> balls = new ArrayList<>();
        Bucket bucket = new Bucket(balls);
        bucket.addBall(new Ball());
        bucket.addBall(new Ball(123.12));
        bucket.addBall(new Ball(Color.GRAY));
        bucket.addBall(new Ball(51.13, Color.RED));
        bucket.addBall(new Ball(686.23, Color.CYAN));

        System.out.format("Blue balls in bucket: %d\n", bucket.blueBallsAmount());
        System.out.format("Total weight: %.3f\n", bucket.allBallsWeight());
    }
}
