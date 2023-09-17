package by.bsuir.Task_9;

import java.awt.*;
import java.util.ArrayList;

public class Bucket {
    private final ArrayList<Ball> balls;
    private double weight;


    public Bucket() {
        this.balls = new ArrayList<Ball>();
        this.weight = 0.0;
    }

    public Bucket(ArrayList<Ball> balls) {
        this.balls = balls;
        for (Ball ball : balls) {
            this.weight += ball.getWeight();
        }
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);
        this.weight += ball.getWeight();
    }

    public double allBallsWeight() {
        return this.weight;
    }

    public int blueBallsAmount() {
        int totalBlueBalls = 0;
        for (Ball ball : balls) {
            if (ball.getColor().equals(Color.BLUE)) {
                totalBlueBalls++;
            }
        }
        return totalBlueBalls;
    }
}