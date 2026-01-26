package zyz.steve.twopointers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.w3c.dom.ls.LSOutput;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

record Interval(int start, int end) {

}

// Leetcode 56 - https://leetcode.com/problems/merge-intervals/description/
public class IntervalSolutions {
    record JobInterval(int start, int end) {
    }

    record TimePoint(int point, int type) {
    }

    /**
     * 输入 为 一组  {start, end} and start < end
     * merge 有重叠的然后 return merge 后的结果，
     * input:  {{1,5} ,{2,3},{1,4}, {8,9}}
     * <p>
     * 由于 {2,3} {1,4} 都在 {1, 5 } 的范围之内，所以结果就是
     * output: {{1,5}, {8,9}}
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        // 首先 按开始时间排序输入
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[] curInterval = intervals[0];
//        记录当前 start  and end
        int start = curInterval[0];
        int end = curInterval[1];

        // 检测 开始是否跟结尾重合，start.next < end.cur
        for (int[] interval : intervals) {
            if (interval[0] <= end) {
                // the new start 在 end 的前面，所以 重合了，要调整end 然后merge
                end = Math.max(end, interval[1]);
            } else {
                // cur start 比 之前记录 end 大， 故没有交集， 可加入结果集
                res.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[res.size()][]);
    }


    private static List<TimePoint> dedup(int[][] intervals) {
        // 将时间点相等的 start ，end 去重
        List<TimePoint> res = Lists.newArrayList();
        // to save all TPs with  start and end cancelled with each other
        // key 为具体的时刻， 值为 该时刻 有多少个开始或结束的点 ，负值 - 表示 有多少个在 该时刻 结束， 正的 - 则表示有多少个在该点开始
        Map<Integer, Integer> tpMapWithStartEndCancelled = Maps.newHashMap();
        // get start and end points to maps
        for (int[] interval : intervals) {
            tpMapWithStartEndCancelled.compute(interval[0], (k, v) -> v == null ? 1 : v + 1); // start points increase
            tpMapWithStartEndCancelled.compute(interval[1], (k, v) -> v == null ? -1 : v - 1); // end points decrease the total num of the points at the given time
        }
        for (Map.Entry<Integer, Integer> s : tpMapWithStartEndCancelled.entrySet()) {
            int endxx = Math.abs(s.getValue());
            int sign = Integer.signum(s.getValue());
            if (endxx != 0) { // 根据某个时刻有多少 个 start 或 end 的点 生成 时间点对象，
                IntStream.range(0, endxx).forEach(k -> res.add(new TimePoint(s.getKey(), sign)));
            }

        }
        return res;
    }

    /**
     * 1  2  3  4  5
     * 1-2
     * 2--3
     * 3------5
     * 也可以把所有开始 结束的点 排序， 从头开始扫描，
     * 碰到每一个 开始点，就记录下来 例如 cur number of  in progress jobs ， curJobInProgress
     * 碰到 一个 结束 点， 就减少curJobInProgress ， 一旦变0，就是说 在该点 没有 job 进行，
     * 但这有个问题是， 如果下一点的开始时间跟 咱们最后结束的点 是相等的，咱们就不能这么简单的减法了
     * 也许可以排好序后， 再遍历一遍， 如果 一个结束 点 的 后一个点 是一样的时间的开始点，就把两个都扔掉 ，这样 相当于 先 merge 了.
     * 还是不行 ，当后面有多个 开始时间点一样的  就不行了 。
     * <p>
     * 可以把所有的 时间点 都放到 一个 map ， 用 那个时间点做 key， 值 是该点的出现的次数，出现在interval的开始 就 增 一·，否则就减一。
     * 这样等于在map产生的过程中， 就把start， end 互相 cancel 掉，或者说merge 起来了。还可以减少数据量
     */
    public static int[][] mergeIntervalsByTimepoint(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        List<TimePoint> timepoints = dedup(intervals);// cancel 掉 相同时间 的 开始 和 结束  时间点
        timepoints.sort(Comparator.comparingInt(TimePoint::point));
        int inProgressJobs = 0;
        int start = 0;
//        int end = 0;
        for (int i = 0; i < timepoints.size(); i++) {
            TimePoint tp = timepoints.get(i);
            // 因为type 可以 开始 表示为 1， 结束 表示为 -1， 所以inProgressJobs 会根据 时间点类型被增加或者减少
            inProgressJobs += tp.type();
            if (inProgressJobs >= 1 && start == 0) {
                // 有正则进行中的 job， 且 还未 记录 start 说明 一个全新的开始 被扫描到了，记录 该点为start
                start = tp.point;
            } else if (inProgressJobs == 0) { // no jobs in progress anymore , got one interval then
                // 被变成0了，说明 碰到了 一个 end 的时间点， 记录 该 interval 并 reset start 为下一个做准备
                res.add(new int[]{start, tp.point()});
                start = 0; // reset start
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 遍历所有时间点，碰到 开始点，就将 jobs WIP 增 一，否则减一
     * 一旦 有大于等于两个 在进行， 并 start 还未设置，说明，还未开始记录符合要求的intervals，就记录 start
     * 一旦 减小到 一， 并且 start 已经设定过，说明 符合条件的interval 在该点 结束了， 算是找到一个符合要求的interval ，记录，并 reset start
     *
     * @param intervals
     * @return
     */
    public static int[][] findIntervalsWithAtLeast2jobs(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        List<TimePoint> timepoints = dedup(intervals);// cancel 掉 相同时间 的 开始 和 结束  时间点
        timepoints.sort(Comparator.comparingInt(TimePoint::point));
        int inProgressJobs = 0;
        int start = 0;
//        int end = 0;
        for (int i = 0; i < timepoints.size(); i++) {
            TimePoint tp = timepoints.get(i);
            inProgressJobs += tp.type();
            if (inProgressJobs >= 2 && start == 0) {
                // greater than 2 jobs and 还未设置 start,
                // record the start
                start = tp.point;
            } else if (inProgressJobs == 1 && start != 0) { // once from over 1  down to less than two jobs in progress  ,  we got one interval then
                res.add(new int[]{start, tp.point()});
                start = 0; // reset start
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 要求找到 所有的 时间窗中，至少有两个 jobs在 进行中
     * 给出 intervals 1 ～ 3 ， 2～5，7～9， 8～10
     * 按开始点，排序， 用 min PQ 存放结束点 （当最小的end 不小于当前开始）
     * 遍历 所有 intervals
     * 一旦 开始点 大于 min PQ ，就说明 有 job 结束了，下一个job开始， 可以通过查看 PQ 中的 结束点数量 知道当前有多少个 任务 进行中
     *
     * @param intervals
     * @return
     */
    public static int[][] findIntervalsWithAtLeast2jobsWithPQ(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        PriorityQueue<Integer> endPQ = new PriorityQueue<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int start = 0, end = 0;
        for (int[] interval : intervals) {
            if (!endPQ.isEmpty()) {
                //保存下来，万一是要结束的时候
                end = endPQ.peek();
            }
            while (!endPQ.isEmpty() && interval[0] >= endPQ.peek()) {
                //  一旦 开始点 大于 min PQ ，就说明 有 job 结束了，下一个job开始, 要弹出 所有小于等于 当前 start 的 end ，表示 之前 记录的 job 结束了
                if(endPQ.size() == 2) end = endPQ.peek(); // 要进一步减小 Q ，需要记录 end，说明 之后没有两个 job 在同时运行了
                endPQ.poll();
            }
            if (endPQ.size() < 2 && start != 0) {
                res.add(new int[]{start, end});
                start = 0; // reset start
            }
            endPQ.offer(interval[1]);
            if (endPQ.size() == 2 && start == 0) start = interval[0];
        }
//     deal with the last segment
        if(endPQ.size() > 1 && start != 0) {
            while (!endPQ.isEmpty() ) {
                if(endPQ.size() == 2) {
                    end = endPQ.peek(); // 要进一步减小 Q ，需要记录 end，说明 之后没有两个 以上job 在同时运行了
                    break;
                }
                endPQ.poll();
            }
            res.add(new int[]{start, end});
        }

        return res.toArray(new int[res.size()][]);
    }

    public static int[] findBusiestWindow(int[][] intervals) {
//        List<int[]> res = new ArrayList<>();
        List<TimePoint> timepoints = dedup(intervals);// cancel 掉 相同时间 的 开始 和 结束  时间点
        timepoints.sort(Comparator.comparingInt(TimePoint::point));
        int inProgressJobs = 0;
        int max = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < timepoints.size(); i++) {
            TimePoint tp = timepoints.get(i);
            inProgressJobs += tp.type();
            if (inProgressJobs > max) {
                // found more jobs running, increase the max
                max = inProgressJobs;
                start = tp.point;
            } else if (tp.type() == -1 && max - inProgressJobs == 1) {
                // less job running,
                // end should only be updated when max goes down  by 1 because this one can be the end we look for
                end = tp.point;
            }
        }
        if (start == 0) {
            return null;
        }
        return new int[]{start, end};
    }

    public static List<JobInterval> getOverlappingIntervals(List<JobInterval> jobs) {
        List<JobInterval> result = new ArrayList<>();
        // List to store time points: start with +1 and end with -1
        List<TimePoint> timePoints = new ArrayList<>();
        // Convert job intervals into time points
        for (JobInterval job : jobs) {
            timePoints.add(new TimePoint(job.start, 1));  // start of a job
            timePoints.add(new TimePoint(job.end, -1));   // end of a job
        }

        // Sort time points by time. If time is the same, process end (-1) before start (+1)
        timePoints.sort((a, b) -> a.point == b.point ? a.type - b.type : a.point - b.point);
        int activeJobs = 0;
        Integer overlapStart = null;
        // Sweep through the time points
        for (TimePoint timePoint : timePoints) {
            int time = timePoint.point;
            int type = timePoint.type; // +1 for start, -1 for end
            // Update the count of active jobs
            activeJobs += type;
            // If we have exactly 2 jobs running, we start a new overlap interval
            if (activeJobs == 2 && type == 1) {
                overlapStart = time;
            }
            // If we drop below 2 jobs, we close the current overlap interval
            if (activeJobs == 1 && overlapStart != null) {
                result.add(new JobInterval(overlapStart, time));
                overlapStart = null;
            }
        }
        return result;
    }

    // 找到 那些 intervals ， 那里有 至少 两个 输入 的 intervals 有 overlap
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(mergeIntervals(new int[][]{{2, 5}, {12, 15}, {4, 8}})));
        System.out.println(Arrays.deepToString(mergeIntervals(new int[][]{{2, 5}, {2, 6}, {3, 4}})));

        System.out.println(Arrays.deepToString(
                getOverlappingIntervals(
                        Arrays.asList(
                                new JobInterval(1, 5),
                                new JobInterval(2, 3),
                                new JobInterval(3, 6))).toArray()));
        List<TimePoint> xxx = dedup(new int[][]{{1, 2}, {2, 3}, {3, 4}, {2, 5}, {2, 7}, {8, 9}});
        System.out.println(xxx);

        System.out.println(Arrays.deepToString(mergeIntervalsByTimepoint(new int[][]{{1, 2}, {2, 3}, {3, 4}, {2, 5}, {2, 7}, {8, 9}})));
        System.out.println(Arrays.deepToString(mergeIntervalsByTimepoint(new int[][]{{1, 2}, {3, 4}, {8, 9}})));
        int[][] winWith2Jobs = findIntervalsWithAtLeast2jobs(new int[][]{{2, 5}, {12, 15}, {4, 8}, {3, 6}, {13, 17}});
        System.out.println(Arrays.deepToString(winWith2Jobs));

        System.out.println(Arrays.toString(findBusiestWindow(new int[][]{{1, 5}, {2, 4}, {6, 19}, {6, 18}, {11, 12}})));

        System.out.println(Arrays.deepToString(mergeIntervalsByTimepoint(new int[][]{{1, 4}, {12, 15}, {4, 7}, {9, 20}, {17, 19}, {17, 19}})));
        System.out.println(Arrays.toString(findBusiestWindow(new int[][]{{1, 4}, {12, 15}, {4, 7}, {9, 20}, {17, 19}, {17, 19}})));
        System.out.println(Arrays.deepToString(findIntervalsWithAtLeast2jobs(new int[][]{{1, 4}, {12, 15}, {4, 7}, {9, 20}, {17, 19}, {17, 19}})));
        System.out.println(Arrays.deepToString(findIntervalsWithAtLeast2jobsWithPQ(new int[][]{{1, 4}, {12, 15}, {4, 7}, {9, 20}, {17, 19}, {17, 19}})));
        System.out.println(Arrays.deepToString(findIntervalsWithAtLeast2jobsWithPQ(new int[][]{{1, 8}, {2, 9}, {3, 10}, {4, 11}})));
    }

}
