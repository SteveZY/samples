package zyz.steve.backtrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//#39
//https://leetcode.com/problems/combination-sum/description/
public class Combination {

    public List<List<Integer>> combinationSum(int[] c, int t) {
        ArrayDeque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        if(c.length==0) return res;

        dfs(c, 0, t, path, res);
        return res;
    }

    private void dfs(int[] c, int begin, int t, Deque<Integer> path, List<List<Integer>> res) {
        if (t < 0) {
            return;
        }
        if (t == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < c.length; i++) {
            path.add(c[i]);
            dfs(c, i, t - c[i], path, res);
            path.removeLast();
        }
    }

}
