package algorithm.leetcode;

public class Solution198 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int pre3 = 0, pre2 = 0, pre1 = 0;
        for (int i = 0; i < n; i++) {
            int cur = Math.max(pre2, pre3) + nums[i];
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return Math.max(pre1, pre2);
    }
}
