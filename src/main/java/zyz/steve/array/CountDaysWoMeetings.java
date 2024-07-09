package zyz.steve.array;

import java.util.*;
public class CountDaysWoMeetings {

    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });
        int st = meetings[0][0];
        int ret = 0;
        int [] cur = meetings[0];
        for(int i = 1; i < n ; ++i) {
            if (meetings[i][0] <= cur[1]) {
                cur[1] = Math.max(cur[1], meetings[i][1]);
            } else {
                ret += meetings[i][0] - cur[1] - 1;
                cur = meetings[i];
            }
        }
        ret += days - cur[1] + st - 1;
        return ret;
    }

}
