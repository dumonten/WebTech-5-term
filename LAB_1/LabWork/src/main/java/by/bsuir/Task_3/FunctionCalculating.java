package by.bsuir.Task_3;

import java.util.ArrayList;

public class FunctionCalculating {
    private Double a;
    private Double b;
    private Double h;
    private FunctionReference function;
    private ArrayList<Double> x_values = new ArrayList<Double>();
    private ArrayList<Double> y_values = new ArrayList<Double>();


    public FunctionCalculating(Double a, Double b, Double h, FunctionReference function) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.function = function;
        this.fillListsOfValues();
    }

    private void fillListsOfValues() {
        if (!x_values.isEmpty())
            x_values.clear();
        if (!y_values.isEmpty())
            y_values.clear();
        for (Double x = a; x <= b; x += h) {
            this.x_values.add(x);
            this.y_values.add(this.function.executeMathSingleParameter(x));
        }
    }

    public void setOtherData(Double a, Double b, Double h) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.fillListsOfValues();
    }

    public ArrayList<Double> getXValues() {
        return x_values;
    }

    public ArrayList<Double> getYValues() {
        return y_values;
    }

}
