package zyz.steve.sweepline;

import org.junit.Assert;
import org.mockito.internal.invocation.StubInfoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// LC 57 - https://leetcode.com/problems/insert-interval/description/
public class Solution {
    /**
     * 基本思路： 没有 overlap 最简单，直接加入
     * @param intervals - 按开始时间排好序的，若不排好序，就不好插入了
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        // main loop
        for (int[] cur : intervals) {
            if (newInterval == null || cur[1] < newInterval[0])res.add(cur); // new 跟当前没有overlap，仅加入当前到结果集
            else if(cur[0] > newInterval[1]) {
                // new 在cur的前面，可以两个都加入到结果集
                res.addAll(Arrays.asList(newInterval, cur));
                newInterval = null; // 因为已加入，故 置为空，表示没有 interval需要加入
            }
            else {
                // overlap 要跟当前的 interval merge
                newInterval[0] = Math.min(cur[0], newInterval[0]);
                newInterval[1] = Math.max(cur[1], newInterval[1]);
            }
        }
        if (newInterval != null)
            res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }

    /**
     * LC 1272 - https://leetcode.cn/problems/remove-interval/
     * @param intervals
     * @param tobeRemoved
     * @return
     */
    public static int[][] remove(int[][] intervals, int[] tobeRemoved){
        List<int[]> res = new ArrayList<>();
        for (int[] cur : intervals) {
            if (cur[1] <= tobeRemoved[0]) res.add(cur); // no overlap
            else if(cur[0] >= tobeRemoved[1]) {
                // no overlap
                res.add(cur);
            }else{
                if(cur[0] < tobeRemoved[0]) res.add(new int[]{cur[0],tobeRemoved[0]});
                if(cur[1] > tobeRemoved[1]) res.add(new int[]{tobeRemoved[1], cur[1]});
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * LC 1288 https://leetcode.com/problems/remove-covered-intervals/
     * 就是要按照 start time 升序排列， end time 降序 排列， 这样 就能保证interval A 如果能 cover interval B的话，A会首先出现，然后跟B 比较， 就可以决定是否移除 B
     * @param intervals
     * @return
     */
    public static int removeCoveredIntervals(int[][] intervals){
        int count = 0;
        Arrays.sort(intervals, (a,b)->a[0]== b[0]?b[1] - a[1] : a[0]-b[0]);
        ArrayList<int []> res = new ArrayList<>();
        // init curEnd to 0 , 即最小
        int curEnd = 0;
        for(int [] cur: intervals){
            // cur interval covered by previous end
            if (curEnd < cur[1]) {
                count++; // remaining intervals
                curEnd = cur[1]; // 保存 最新的 end ，再看下一个
            }
        }
        return count;
    }

    // TODO： LC 435 https://leetcode.cn/problems/non-overlapping-intervals/solutions/541543/wu-zhong-die-qu-jian-by-leetcode-solutio-cpsb/

    // LC 1229 Meeting scheduler
    public static int[] firstAvailableTimeSlot(int[][] slot1, int [][]slot2 , int duration){
        int[][] newDst = Arrays.copyOf(slot1, slot1.length + slot2.length);
        System.arraycopy(slot2,0,newDst,slot1.length, slot2.length);
        List<int []> timePoints = new ArrayList<>();
        for(int[] intv : newDst){
            timePoints.add(new int[]{intv[0],1});
            timePoints.add(new int[]{intv[1],-1});
        }
        timePoints.sort((a,b)->a[0] == b[0]?a[1]-b[1]:a[0]-b[0]);
        // scan through the points
        int start =0;
        int end =0;
        int numIntervalsForMeeting = 0;
        for (int i = 0; i < timePoints.size(); i++) {
            int[] tp = timePoints.get(i);
            numIntervalsForMeeting += tp[1];
            if(numIntervalsForMeeting >= 2 && start ==0){
                // 2 available slots and set start
                start = tp[0];
            }
            else if(numIntervalsForMeeting ==1 && start != 0 ){
                if (tp[0] - start >= duration){
                    // found
                    return new int[]{start, tp[0]};
                }
                start =0; // not find and reset start
            }

        }

        return null;
    }

    // LC 759 Employee free time, https://leetcode.cn/problems/employee-free-time/description/
    public static int [][] employeeFreeTime(int[][][] timeslots){
        List <int[]> timePoints = new ArrayList<>();
        List <int[]> res = new ArrayList<>();

        // 直接 将 所有的时间点都记录下来 排序， 然后merge
        for (int i = 0; i < timeslots.length; i++) {
            for (int j = 0; j < timeslots[i].length; j++) {
                timePoints.add( new int[]{timeslots[i][j][0],1});
                timePoints.add( new int[]{timeslots[i][j][1],-1});
            }
        }
        timePoints.sort((a,b)-> a[0]==b[0]? a[1]-b[1]: a[0]- b[0]);

        // iterate through timepoints
        int end =0;
        int inprogress =0;
        for (int [] tp: timePoints){
            inprogress+= tp[1];
            if(inprogress == 0){// found one end which ends all the jobs in progress
                end = tp[0];
            } else if (inprogress == 1 && end !=0){
                // got a start
                if(end < tp[0]) // not equal
                    res.add(new int[]{end,tp[0]});
                end = 0; // reset end
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    // LC 253 , meeting rooms II
    // 给 一系列会议的起止时间， 返回需要的最小房间数
    public static int minNumOfRooms(int[][] meetings){
        // 使用PQ 记录 正在进行中的会议，但是用结束时间作为排序依据，将最早结束的会议放在PQ的第一个，这样最先结束的那个 会议室就可以尽早的被冲用
        // 而且 会议 都是按开始时间从小到大排列的，所以下一个会议 开始 时间总是剩余会议中开始最早（数字较小）的，对比它跟PQ 最早结束的会议 就可以决定是否房间可重用
        // 如果新的会议开始时间晚于 之前的结束 时间，就可以重用上一个会议室，只是需要把结束时间调整成新的结束时间就好
        // 所以 PQ 中 存放的就是当前在使用的房间数。一开始，一个会议就压入 PQ ， 第二个来的时候如果第一个还没结束，也进PQ
        // 如果第三个来的时候 ， 之前的两个有一个结束了，就可以重用会议室
        if(null== meetings || meetings.length == 0) return 0;
        // sort by start time
        PriorityQueue<int[]> meetingInprogress = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        Arrays.sort(meetings,(a,b)->a[0]-b[0]);// sort increasingly by start time
        meetingInprogress.offer(meetings[0]); // init PQ with the first meeting
        for (int i = 1; i < meetings.length; i++) {
            // start from the second meeting
            int[] cur = meetingInprogress.peek();
            if(cur[1]<=meetings[i][0]) {
                // can reuse, use the new one to replace the one completing
                meetingInprogress.poll();
//                meetingInprogress.offer(meetings[i])
            }
            //else{ can not reuse, add the new meeting to PQ
                meetingInprogress.offer(meetings[i]);
//            }
        }
        return meetingInprogress.size();
    }
    public static void main (String[] args){
        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] toInsert = new int[]{2, 5};

        System.out.println(Arrays.deepToString(insert(intervals, toInsert)));

        intervals = new int[] [] {{0,2},{3,4},{5,7}};
        int[] toRemove = new int[]{1,6};
        System.out.println(Arrays.deepToString(remove(intervals, toRemove)));

        intervals = new int[][] {{1,4},{3,6},{2,8}};
        System.out.println(removeCoveredIntervals(intervals));
        intervals = new int[][] {{1,4},{2,3}};
        System.out.println(removeCoveredIntervals(intervals));

        int[][] slots1 = new int[] [ ] {{10,50},{60,120},{140,210}};
        int[][] slots2 = new int[] [ ] {{0,15},{60,70}};
        System.out.println(Arrays.toString(firstAvailableTimeSlot(slots1, slots2, 8)));

        int[][][] employeeSchedule = new int[][][]{{{1, 2}, {5, 6}}, {{1, 3}}, {{4, 10}}};

        System.out.println(Arrays.deepToString(employeeFreeTime(employeeSchedule)));

        // test meeting room II
        int[][] meetings= {{0,30},{15,40}, {5,10}};
//        System.out.println(minNumOfRooms(meetings));
        // 2 rooms are expected
        Assert.assertEquals(2, minNumOfRooms(meetings));

    }
}
