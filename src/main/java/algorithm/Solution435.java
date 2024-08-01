package algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Solution435 {

    static class Interval {
        private int start;
        private int end;
    }
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.end));
        int cnt = 1;
        int end = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < end) {
                continue;
            }
            end = intervals[i].end;
            cnt++;
        }
        return intervals.length - cnt;
    }
}
