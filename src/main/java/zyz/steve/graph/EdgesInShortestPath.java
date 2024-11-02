package zyz.steve.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdgesInShortestPath {

    public static boolean[] findAnswer(int n, int[][] edges) {
        boolean[] ans = new boolean[edges.length];
        int [][][] g = new int[n][][];
        List<int[]>[] gg = new List[n];
        for(int[]e:edges){
//            List<int[]> next ;
            if(gg[e[0]] == null) {
                gg[e[0]]=new ArrayList<>();
            }
            if(gg[e[1]] == null) {
                gg[e[1]]=new ArrayList<>();
            }

            gg[e[0]].add( Arrays.copyOfRange(e,1,3));
            gg[e[1]].add( new int[]{e[0],e[2]});

        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] ed = new int[][] {{0, 1, 4}, {0, 2, 1}, {1, 3, 2}, {1, 4, 3}, {1, 5, 1}, {2, 3, 1}, {3, 5, 3}, {4, 5, 2}};

        System.out.println(Arrays.toString(Arrays.copyOfRange(ed[0], 1, 3)));
        findAnswer(6, ed);
    }
}
