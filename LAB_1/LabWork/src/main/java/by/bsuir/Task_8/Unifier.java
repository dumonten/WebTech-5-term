package by.bsuir.Task_8;

import java.util.*;

public class Unifier {

    public static ArrayList<Integer> unifyIncreasingSequences(List<Double> arr1, List<Double> arr2) {
        LinkedList<Double> sortedArr = new LinkedList<>();
        sortedArr.addAll(arr1);
        sortedArr.addAll(arr2);
        Collections.sort(sortedArr);

        System.out.println();
        for (Double a : sortedArr) {
            System.out.format("%.3f ", a);
        }
        System.out.println();

        ArrayList<Integer> positions = new ArrayList<>();
        int i = 0, j = 0;
        while (i < arr1.size() && j < sortedArr.size()) {
            if (Objects.equals(arr1.get(i), sortedArr.get(j))) {
                i++;
                j++;
            } else {
                positions.add(j);
                j++;
            }
        }
        if (i >= arr1.size()) {
            while (j < sortedArr.size()) {
                positions.add(j);
                j++;
            }
        }
        return positions;
    }
}
