package zyz.steve.dp;
import java.util.*;
//3154. Find Number of Ways to Reach the K-th Stair
public class WaysToReachStair {

    private Map<String, Integer> map;

    /**
     *
     * @param curr 当前所在台阶
     * @param j 记录第几次 jump 初始为0
     * @param k 要到达的台阶 index ， 从0开始计算
     * @param flag 表示是否可以向下
     * @return
     */
    int rec(int curr, int j, int k, int flag) {
        if(curr-k > 1) return 0; //跳过的距离超过 1 以后 就不可能再回来，故返回0，表示无法到达。因为没有操作可以从远处返回 <= k 的位置了

        //memoization
        String key = (curr+""+j+""+flag);//需要记住当前位置， j，以及 flag，故用他们做key
        if(map.containsKey(key)) return map.get(key);

        int ct = curr==k? 1: 0;//到了k 就记录 为 1 种 办法
        //继续看
        // 1.往回 走一个，看有多少办法 再到 k
        // 2. 往远处跳一次，有多少种办法再回到k
        // 3. 全部加起来就是， 从curr 到 k的 办法总数
        if(flag==1 && curr>0){
            ct += rec(curr-1, j, k, 0);//前一次不是往回跳的, 并且目前不在0位置，故往回跳一下，递归看能到达k 的路径有多少
        }
        ct += rec(curr+(1<<j), j+1, k, 1);// 再往前跳，看能到达 k 的路径有多少，并跟上一步累加

        map.put(key, ct);//记住 次数，方便后续调用
        return ct;
    }
    public int waysToReachStair(int k) {
        map = new HashMap<>();
        return rec(1, 0, k, 1);
    }

    public static void main(String[] args) {
        WaysToReachStair solution = new WaysToReachStair();
        System.out.println(solution.waysToReachStair(1));
    }

}
