package zyz.steve.greedy;

public class CanJump {
    public static boolean canJump(int [] s){
        //https://leetcode.com/problems/jump-game/
        //https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51636861
        if(s.length<=1) return true;
        //用s[0] 存储当前可以到达的距离，节省一个变量
        for (int i = 1; i < s.length && i <= s[0]; i++) {
            if (s[0] >= s.length - 1) {
                // can = true;
                return true;
                // break;
            }
            s[0] = Math.max((i + s[i]), s[0]);
        }
        return false;
    }

}
