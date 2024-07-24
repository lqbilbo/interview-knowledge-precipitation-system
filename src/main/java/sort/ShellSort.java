package sort;

import java.util.Arrays;

public class ShellSort {

    public static <T extends Comparable<? super T>> void shellSort(T[] a) {

        int j;

        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{81,94,11,96,12,35,17,95,28,58,41,75,15};
        shellSort(a);
        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
    }
}
