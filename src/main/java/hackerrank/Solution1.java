package hackerrank;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        int[] count = new int[3];
        for (Integer ar : arr) {
            if (ar > 0) {
                count[0]++;
            } else if (ar < 0) {
                count[1]++;
            } else {
                count[2]++;
            }
        }
        BigDecimal positiveDecimal = BigDecimal.valueOf((double) count[0] / arr.size()).setScale(6, RoundingMode.HALF_UP);
        BigDecimal negativeDecimal = BigDecimal.valueOf((double) count[1] / arr.size()).setScale(6, RoundingMode.HALF_UP);;
        BigDecimal zeroDecimal = BigDecimal.valueOf((double) count[2] / arr.size()).setScale(6, RoundingMode.HALF_UP);
        System.out.println(positiveDecimal);
        System.out.println(negativeDecimal);
        System.out.println(zeroDecimal);
    }

}

public class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}