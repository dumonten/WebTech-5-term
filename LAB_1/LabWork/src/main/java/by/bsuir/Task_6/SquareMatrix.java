package by.bsuir.Task_6;

public class SquareMatrix {

    public static double[][] getMatrix(double[] arr) {
        int offset = 0;
        double[][] squareMatrix = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                squareMatrix[i][j] = arr[(j + offset) % arr.length];
            }
            offset = (offset + 1) % arr.length;
        }
        return squareMatrix;
    }
}
