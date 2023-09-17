package by.bsuir.Task_7;

public class Sort {

    public static double[] sort(double[] arr) {
        int i = 0;
        while (i < arr.length - 1) {
            if (arr[i] > arr[i + 1]) {
                double temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                i = Math.max(i - 1, 0);
            } else {
                i++;
            }
        }
        return arr;
    }
}
