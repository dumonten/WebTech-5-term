package by.bsuir.Task_8;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter n and m (sizes of array): ");

        int n = 0, m = 0, minValue = -10, maxValue = 10;
        if (scanner.hasNextDouble())
            n = scanner.nextInt();
        else {
            System.out.println("Your data is incorrect!");
            System.exit(1);
        }
        if (scanner.hasNextDouble())
            m = scanner.nextInt();

        List<Double> arr1 = new LinkedList<>(), arr2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            arr1.add(ThreadLocalRandom.current().nextDouble(minValue, maxValue + 1));
        }
        for (int i = 0; i < m; i++) {
            arr2.add(ThreadLocalRandom.current().nextDouble(minValue, maxValue + 1));
        }
        Collections.sort(arr1);
        Collections.sort(arr2);
        System.out.print("a: ");
        for (Double a : arr1) {
            System.out.format("%.3f ", a);
        }
        System.out.print("\nb: ");
        for (Double a : arr2) {
            System.out.format("%.3f ", a);
        }

        System.out.print("\nPositions of b in a: ");
        ArrayList<Integer>  positions = Unifier.unifyIncreasingSequences(arr1, arr2);
        for (Integer pos : positions) {
            System.out.format("%d ", pos);
        }
    }
}
