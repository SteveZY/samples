package zyz.steve.patterns;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridDP {


    //针对 只能向右 以及向下 遍历的 情形
    public int maxScore(List<List<Integer>> grid) {
        int ans;
        int h = grid.size();//height
        int w = grid.get(0).size(); // width
        int []dp = new int[w];//只用一行，也可以 选择  h， w 较小者，可用更小内存，但是循环的方向要 从上到下了
        final int MAXXX = (1<<23) ;
        final int MINNN = -MAXXX; // 需要根据 问题 的大小进行调整 ，实在不行用 long
//        dp[0][0]=MINNN;
        ans = MINNN;
        for (int i=0;i<h;i++){
            for (int j = 0; j < w; j++) {
                int mleft = inB(i,j-1)?dp[j - 1]: MINNN;
                int left= inB(i,j-1)? grid.get(i).get(j-1):MAXXX;

                int mtop = inB(i-1,j)? dp[j]:MINNN;
                int top = inB(i-1,j)? grid.get(i-1).get(j):MAXXX;
                Integer cur = grid.get(i).get(j);
                int diffToTop = cur - top;
                int diffToLeft = cur - left;
                dp[j] = Math.max
                        (Math.max(mleft + diffToLeft, mtop + diffToTop),
                                Math.max(diffToTop,diffToLeft));
                ans=Math.max(ans,dp[j]);
            }
        }

        return ans;
    }
    private static boolean inB(int x, int y){
//        Set<Character> ss = new HashSet<>();
//        HashMap<Object, Object> sssss = new HashMap<>();
//        sssss.values()
//        String s = "ssss";s.substring()
//        s.toLowerCase()
//        s.add(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        return x>=0&&y>=0;
    }
}
