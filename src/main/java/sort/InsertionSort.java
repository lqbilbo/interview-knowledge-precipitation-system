package sort;

import java.util.Arrays;

public class InsertionSort {

    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {

        int j;

        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{34,8,64,51,32,21};
        insertionSort(a);
        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
    }
}
