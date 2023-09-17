package by.bsuir.Task_9;

import java.awt.*;

public class Ball {
    private final double weight;
    private final Color color;


    public Ball() {
        this.weight = 12;
        this.color = Color.BLACK;
    }

    public Ball(Color color) {
        this.weight = 12;
        this.color = color;
    }

    public Ball(double weight) {
        this.weight = weight;
        this.color = Color.BLACK;
    }

    public Ball(double weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}