package algorithm.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Solution503 {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> pre = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!pre.isEmpty() && nums[pre.peek()] < num) {
                next[pre.pop()] = num;
            }
            if (i < n) {
                pre.push(i);
            }
        }
        return next;
    }
}
