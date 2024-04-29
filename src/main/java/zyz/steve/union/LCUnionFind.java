package zyz.steve.union;

import java.util.HashSet;
import java.util.Set;

public class LCUnionFind {
    public static boolean equationsPossible(String[] equations) {
        WeightedQuickUnion uf = new WeightedQuickUnion(128);
        int[] data = new int[128];
        for (int i = 0; i < 128; i++) {
            data[i]=i;
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                union(data,equation.charAt(0), equation.charAt(3));
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (connected(data, equation.charAt(0), equation.charAt(3)))
                    return false;
            }
        }
        return true;
    }
    private static void union(int [] data, int a, int b){

        //set a's id to b，data[a] = b
        int ra = root(data,a);
        int rb = root(data,b);

        if(ra==rb) return;
        data[ra] = rb;
    }
    private static int root(int [] data, int a){
        int temp = a;
        while (data[temp] != temp){
            //扁平化 tree
            data[temp] = data[data[temp]];
            temp = data[temp];
        }
        return temp;
    }
    private static boolean connected(int[]data, int a, int b){
        return root(data,b) == root(data,a);
    }
    public static int findCircleNum(int [][] isConnected){
        int[] data = new int[isConnected.length];

        for (int i = 0; i < data.length; i++) {
            data[i]=i;

        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i+1; j < isConnected.length; j++) {
                if(1==isConnected[i][j]){
                    union(data,i,j);
                }

            }
        }

        Set<Integer> ans = new HashSet<>();
        for (int e:data){
            ans.add(root(data,e));
        }
        return ans.size();
    }
    public static int findCircleNumDfs( int [][] isConnected){
        int ans = 0;
        boolean [] visited = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if(!visited[i]){
                ans++;
                dfs(i,isConnected,visited);
            }
        }
        return ans;
    }

    private static void dfs(int node, int[][] isConnected, boolean[] visited) {

        visited[node] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if(isConnected[node][i]==1 && !visited[i]){
                dfs(i,isConnected,visited);
            }
        }
    }

    //TODO; BFS 实现 跟DFS 一样，只要链接在一起，就可以遍历到 同为一组的所有元素
}
