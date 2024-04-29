package zyz.steve.sorting;

import java.util.Arrays;

public class SorttingToSolve {
    //https://leetcode.cn/problems/meeting-rooms-ii/description/?company_slug=microsoft
    /**
     * 给你一个会议时间安排的数组 intervals ，
     * 每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
     * Microsoft
     */

    public static int minMeetingRooms(int[][] intervals){
        int[] ss = new int[intervals.length];
        int[] e = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            ss[i]=intervals[i][0];
            e[i]=intervals[i][1];
        }
        Arrays.sort(ss);
        Arrays.sort(e);
        int j=0;
        int numOfRooms = 0;
        for (int i = 0; i < intervals.length; i++) {
            if(ss[i]>e[j]){
                //晚开始 可重用房间
                j++;
                continue;
            }
            //没有会议结束，需要新开房间
            numOfRooms++;
        }
        return numOfRooms;
    }


    //TODO
    public static int minMeetingRoomsWithPQ(int[][] intervals){

        return 0;
    }
}
