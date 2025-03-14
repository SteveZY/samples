
package zyz.steve.greedy;
//https://www.youtube.com/watch?v=FPEMBWg_WlY&t=686s

import java.util.*;

//Prim's algo

//refer: https://juejin.cn/post/7035842708786642981
public class MinimumSpanningTree {

    static int[] visited;

    /**
     * 输入需要是 adj matrix,元素本身是weight
     *
     * @param graph
     */

    //Prim
    public static void findMinSpaningTree(int[][] graph) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[0]));
        boolean[] visited = new boolean[graph.length]; //already in MST
        visited[0] = true;//从零号 节点开始
        for (int i = 0; i < graph.length; i++) {//从节点0开始，查看其邻接点， 并用其初始化 pq
            if (graph[0][i] != 0) {
                //pq 中存放 节点信息  第一个是 weight， src， dst
                //一开始只存 0 号节点开始 相关信息，其他的节点遍历到的时候 再加进去，不需要特别更新 节点的权重，
                // 反正每次访问到给定节点时都会加入pq，大了的话自然排到后面去了
                pq.offer(new Integer[]{graph[0][i], 0, i});
            }
        }
        int count = 1;
        //非连通图的时候，需要此判断
        while (count < graph.length) {
            Integer[] curNode = null;
            Integer curNodeIdx = 0;
            while (!pq.isEmpty()) {
                curNode = pq.poll();
                curNodeIdx = curNode[2];
                if (!visited[curNodeIdx]) {
                    break;
                }
            }
            visited[curNodeIdx] = true;
            System.out.println("start:" + curNode[1] + " end: " + curNodeIdx + " w:" + curNode[0]);
            for (int i = 0; i < graph.length; i++) {
                int weightToNext = graph[curNodeIdx][i];
                if (weightToNext != 0 && !visited[i]) { //
                    //add to pq
                    pq.offer(new Integer[]{weightToNext, curNodeIdx, i});
                }
            }
            count++;
        }
    }

    public static void MSTRecur(int[][] graph) {
        visited = new int[graph.length];
        Arrays.fill(visited, -1);
        recursively(graph, 0);
    }

    /**
     * mst(u) = min ( mst(adg(u))
     *
     * @param graph
     */
    public static int recursively(int[][] graph, int node) {

        if (visited[node] != -1) {
            return Integer.MAX_VALUE;//记录当前节点得到的MST 的weight
        }
        visited[node] = 0;
        int min = Integer.MAX_VALUE;
        int nextHop = node;
        int weight = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] != 0 && visited[i] != 0) {
                weight = recursively(graph, i);
                if (min > weight) {
                    nextHop = i;
                    //更新 min，
                    min = weight;
                }
            }

        }
        if (nextHop == node) {
            return 0;
        }
        System.out.println("start:" + node + " end:" + nextHop + "w:" + graph[node][nextHop]);
        //标记

        return graph[node][nextHop] + min;
        //        return visited[node];

    }

    //Dijkstra shortest path
    public static void findShortestPathD(int[][] g, int start) {
        //Idx 0 位置 存放距离
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(
                //                Comparator.comparingInt(e -> e[0])
                (o1, o2) -> o1[0].compareTo(o2[0])
        );
        boolean[] alreadyInS = new boolean[g.length];
        alreadyInS[start] = true;
        for (int i = 0; i < g.length; i++) {
            if (g[start][i] != 0) {
                pq.offer(new Integer[]{g[start][i], i});
            }
        }
        int count = 1;
        while (!pq.isEmpty() && count++ < g.length) {
            //get the closest one
//            count++;
//            if(count>=g.length) break;
            Integer[] u = pq.poll();
            alreadyInS[u[1]] = true;//放入 S
            for (int i = 0; i < g.length; i++) {//all adjs
                if (g[u[1]][i] != 0 && !alreadyInS[i]) {
                    //update distance from v to start
                    pq.offer(new Integer[]{g[u[1]][i] + u[0], i});
                }

            }
            System.out.println("shortest dist from " + start + " to " + u[1] + " is " + u[0]);
        }

    }

    public static boolean bellmanFord(int[][] g, int s) {

        int[] result = new int[g.length];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[s] = 0;
        for (int v = 0; v < g.length; v++) {
            if (g[s][v] != 0)
                result[v] = result[s] + g[s][v];
        }
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                int du = result[i];//s 到达 当前 i的 距离，目前最短
                int w = g[i][j]; //从 i 到 j 的距离
                if (w != 0 && result[j] > du + w) //若从 i 到 j 的距离 加入 s-> i 距离，小于当前已知的 从 s->j 的距离 则 relax，即找到一个更短的距离
                    result[j] = du + w;
            }

        }

        //report negative weight
        for (int u = 0; u < g.length; u++) {
            for (int v = 0; v < g.length; v++) {
                if (g[u][v] != 0 && result[v] > result[u] + g[u][v]) return false;
            }
        }
        return true;
    }

//https://www.thejoboverflow.com/p/p1110/
    public static int getMaxViableValue(int [] arr){
        List<List<Integer>> pyramid = new ArrayList<>();
        int depth = 0;
        for (int i = 0; i < arr.length; ) {
            depth++;//记录每层的 元素个数
            List<Integer> row = new ArrayList<>();
            int j = i;
            for (; j < i+depth; j++) {
//                i= i+j;
                row.add(arr[j]);
            }
            i = j;
            pyramid.add(row);
        }

        //bottom up
        System.out.println(depth);

        for (int i = depth-2; i >=0 ; i--) {
            List<Integer> curLevel = pyramid.get(i);
            List<Integer> oneLevelDown = pyramid.get(i + 1);

            for (int j = 0; j < curLevel.size(); j++) {
                curLevel.set(j, curLevel.get(j) + Math.max(oneLevelDown.get(j),oneLevelDown.get(j+1)));
            }
        }
        return pyramid.get(0).get(0);
    }
    public int getDepth(int[] arr) {
        return (int) (Math.sqrt(
                arr.length * 2 * 4 + 1) - 1) >> 1;
    }
    public void enumArr(int[] arr) {
        int depth = getDepth(arr);
        //level indx from 0  to depth -1
        for (int i = depth -2 ; i >=0; i--) {//from the second last

        }
    }
    public static void main(String[] args) {
        System.out.println(getMaxViableValue(new int[]{2, 3, 4, 16, 7, 8}));
    }
}
