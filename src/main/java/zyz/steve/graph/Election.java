package zyz.steve.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://codeforces.com/problemset/problem/369/C
public class Election {
    public static List<Integer []>[] adj;
    private static boolean [] elected;

    public static List<Integer[]> ans;
    public static void buildTree(int numOfNodes, int[][] edges){
        ans = new ArrayList<>();
        elected = new boolean[numOfNodes+1];
        adj = new LinkedList[numOfNodes+1];
        for (int i = 1; i <= numOfNodes; i++) {
            adj[i] = new LinkedList<>();
        }
//        Arrays.fill(adj,new ArrayList<>());

        for (int i = 0; i < numOfNodes-1 ; i++) {
            int d1 = edges[i][0];
            int d2 = edges[i][1];
            int type = edges[i][2];
            Integer [] pairD1 = new Integer[2];
            Integer [] pairD2 = new Integer[2];

            pairD1[0] = d2;
            pairD2[1]  = pairD1[1] = type;
            pairD2[0] = d1;
//            pairD2[1] = type;
            adj[d1].add(pairD1);
            adj[d2].add(pairD2);
        }
    }
    public static void dfs(Integer v, Integer parent){
        List<Integer[]> adjs = adj[v];

        for (Integer[] u : adjs) {
            //遍历所有相邻节点
            if (u[0].equals(parent)) {
                //确保不要检测父节点
                continue;
            }
            //深度优先搜索
            dfs(u[0], v);
            int type = u[1];
            if (type == 2) {//需要维修
                if (!elected[u[0]]) {
                    //如果该远端点还没有被选中，其即为一个要选的节点，更新状态，并将其加入 ans 列表
                    ans.add(u);
                    elected[u[0]] = true;
                }

            }
            //子节点 对应的子树 如果有节点被选中，置其父节点为选中状态
            elected[v] = elected[v] || elected[u[0]];
        }
    }

}
