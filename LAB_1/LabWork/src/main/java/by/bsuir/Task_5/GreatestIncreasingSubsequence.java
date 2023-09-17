package by.bsuir.Task_5;

public class GreatestIncreasingSubsequence {

    public static int numbersToDelete(int[] arr) {
        int[] d = new int[arr.length];
        for (int i = 0; i < d.length; i++) {
            d[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    d[i] = Math.max(d[i], 1 + d[j]);
                }
            }
        }
        int result = d[0];
        for (int i = 0; i < arr.length; ++i) {
            result = Math.max(result, d[i]);
        }
        return arr.length - result;
    }
}
