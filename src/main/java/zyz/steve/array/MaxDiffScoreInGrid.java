package zyz.steve.array;

import java.sql.Array;
import java.util.*;
//https://leetcode.com/problems/maximum-difference-score-in-a-grid/description/
//#3148
public class MaxDiffScoreInGrid {
    public static int maxScore(List<List<Integer>> grid) {
        int ans;
        int h = grid.size();//height
        int w = grid.get(0).size(); // width
        int []dp = new int[w];
        final int MINNN = (1<<23) * -1;
        final int MAXXX = (1<<23) ;
//        dp[0][0]=MINNN;//可以简化为只用一行 毕竟 后续的每个元素 只会用到其顶部 和左侧的 dp 值
        ans = MINNN;
        for (int i=0;i<h;i++){
            for (int j = 0; j < w; j++) {
                int mleft = inB(i,j-1)?dp[j - 1]: MINNN;//处理边界情况
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
        return x>=0&&y>=0;
    }

    public static void main(String[] args) {
        int [][] gg = {{9, 5, 7, 3}, {8, 9, 6, 1}, {6, 7, 14, 3}, {2, 5, 3, 1}};
        List<List<Integer>> g = new ArrayList<>();
        for (int i=0;i<gg.length;i++){
            ArrayList<Integer> ll = new ArrayList<>();
            for (int j = 0; j < gg[0].length; j++) {
                ll.add(gg[i][j]);
            }
            g.add(ll);
        }

        System.out.println(maxScore(g));
    }
}
