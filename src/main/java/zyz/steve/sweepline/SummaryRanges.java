package zyz.steve.sweepline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class SummaryRanges {
    TreeSet<int[]> set;

    public SummaryRanges() {
        set = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    }

    public void addNum(int value) {
        // add 时， 按interval 添加
        int[] interval = new int[]{value, value};
        if (set.contains(interval)) return;
//        set.add(value);
        int[] low = set.lower(interval), high = set.higher(interval);
        if (low != null && low[1] >= value) return; // 包含在 low 或者high 中吗？
        if (high != null && high[0] <= value) return; //if already there return
        // not with low and high at all
        // 检查是否相邻
        if (low != null && low[1] + 1 == value && high != null && high[0] - 1 == value) {
            // 正好可以连接low and high , then merge low and high
            low[1] = high[1];
            set.remove(high);
            return;
        }
        if (low != null && low[1] + 1 == value) {
            low[1] = value;
            return;
        } // merge to low
        if (high != null && high[0] - 1 == value) {
            high[0] = value;
            return;
        } // merge to high
        // 完全不相邻 直接添加
        set.add(interval);
    }

    public int[][] getIntervals() {
        List<int[]> res = new ArrayList<>(set);
        return res.toArray(new int[res.size()][]);
    }
    public static void main(String[] args){
        SummaryRanges ranges = new SummaryRanges();

        ranges.addNum(6);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(6);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(0);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(4);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(8);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(7);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(6);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(4);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(7);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
        ranges.addNum(5);
        System.out.println(Arrays.deepToString(ranges.getIntervals()));
    }
}
