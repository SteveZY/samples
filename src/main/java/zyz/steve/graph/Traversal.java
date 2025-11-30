package zyz.steve.graph;



import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://www.youtube.com/watch?v=PMMc4VsIacU&t=40s
public class Traversal {
//DFS
    public static int[][] graph;
    public static int[] marker;
    static {
        // 邻接 列表 表示 一个graph ， 0 号顶点  有边 到 1 ，2， 5
        // 所以 5号 顶点， 会有 到0 的边， 这也是 会看到 有 0 在 它的值列表中 0，2，3，6
        graph = new int[][]{{1,2,5},{0,4},{0,3,5},{2,4,5},{1,3},{0,2,3,6},{5}};
        marker = new int[7];
    }
    public static void resetMarker(){
        Arrays.fill(marker, 0);
    }

    public static boolean contains(int vertex, int k){

        int[] adj = graph[vertex];

        int len = adj.length;
        if(len == 0) return false;
        if(len==1 &&  adj[0] ==k) return true;

        return findFromArray(adj,k,0,len-1);

    }
    static boolean  findFromArray(int [] data, int e, int start, int end){
        if(start > end ) return false;
        int mid = start + ((end - start) >> 1);// = (start + end) /2
        if(data[mid] == e) return true;

        if(data[mid] > e) {
            return findFromArray(data, e, 0,mid-1);
        }else {
            return findFromArray(data,e,mid+1,end);
        }
    }

    //1376. Time Needed to Inform All Employees
//    https://leetcode.com/problems/time-needed-to-inform-all-employees/description/
    //another similar solution
    //https://leetcode.com/problems/time-needed-to-inform-all-employees/solutions/3077609/java-solution-9-ms-beats-95/
//    https://leetcode.com/problems/time-needed-to-inform-all-employees/solutions/589738/java-dfs-memoization-w-explanation-complexity-analysis/

    /**
     * Bottom up DFS with memoization
     * 记录 从每个 employee 到 roo 的 用时， 然后取最大值，
     * 这是找 树根 到 叶子 的 最长路径
     * @param n
     * @param head
     * @param m
     * @param time
     * @return
     */

    public static int numOfMins(int n, int head, int[]m, int [] time){
        int total = 0;
        int [] marker = new int[n];
        Arrays.fill(marker,-1);
        for (int i = 0; i < n; i++) {
            if(marker[i] != -1)continue;
            total = Math.max(dfs(i, m, time, marker),total);
        }
        return total;
    }
    static  int  dfs(int id, int[]m, int [] time, int[] marker){
        if(m[id]==-1){
            return time[id];
        }
        if(marker[id] == -1) {
//            marker[id] = marker[id] + time[id];
////            return marker[id];
//        }else {
            marker[id] = time[id] + dfs(m[id], m, time, marker);
        }
        return marker[id];
    }
    /**
     * 也可以慢慢 构造 adj list ， 然后， 一个个 的做DFS，
     * top down DFS
     *
     * 缺点是用更多的内存，速度应该差不多
     */
    public static int numOfMinutes(int n, int head, int[] m, int[] time) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < m.length; i++) {
            int key = m[i];
            if (i == head) {
                key = head;
            }
            if (!adj.containsKey(key)) {

                adj.put(key, new ArrayList<>());
            }
            if (key != i) {
                adj.get(key).add(i);
            }
        }
        System.out.println(adj);

        //DFS 调用
//        int[] visited = new int[m.length];
//        Arrays.fill(visited,-1);
//        dfsT2D(head, adj, time,visited);
//        return visited[head];

        //BFS 调用
        return bfsTopDown(head, adj,time);
    }
    private static int dfsT2D(int id, Map<Integer, List<Integer>> adj, int[]time, int[] visited){
        if(visited[id]!=-1) return visited[id];
        if(!adj.containsKey(id)) {
            visited[id] = 0;
            return 0;
        }
        for(Integer nextId: adj.get(id)){
            visited[id] = Math.max(visited[id],time[id]+ dfsT2D(nextId,adj, time, visited));
        }
        return visited[id];
    }
    private static int bfsTopDown(int id, Map<Integer, List<Integer>> adj, int[ ] time){
        Queue<Integer> q = new ArrayDeque<>();
        int[] visited = new int[time.length];
        q.offer(id);
        int max =0;

        while (!q.isEmpty()) {
            Integer node = q.poll();
            if (visited[node] != 0) {
                continue;
            }
            visited[node] = 1;
            if (!adj.containsKey(node)) {
                continue;
            }
            for (Integer next : adj.get(node)) {
                q.offer(next);
                time[next] += time[node];
                max = Math.max(max, time[next]);
            }
        }
        return max;
    }

    //DFS recursively

    /**
     * Preorder DFS
     * dfs(G,v)
     *   explore(v)
     *   mark(v)
     *   for ele in G.neighbors(v)
     *      if ele is NOT marked
     *          dfs(G,ele)  //递归调用
     */
    public static void dfsRecurisivelyPre(int [][] g, int v){
        System.out.println(v);
        marker[v] = 1;
        for(int ele : g[v]){
            if(marker[ele] != 1)
                dfsRecurisivelyPre(g,ele);
        }

    }
    /**
     * 非递归，iteration p- preorder
     * 使用 stack
     * dfs(G,v)
     *   stack = [v]
     *   while stack NOT empty:
     *      v=stack.pop
     *      if  v NOT marked
     *          explore(v)
     *          mark(v)
     *          for w in G.neighbors(v)
     *              if w NOT marked
     *                  stack.push(w)
     *
     */
    public static void dfsIterPreorder(int [][] g, int v){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(v);
        while (!stack.isEmpty()){
            Integer ele = stack.pop();
            if(marker[ele] == 0){
                System.out.println(ele);
                marker[ele] =1;
                for(int w:g[ele]){
                    if(marker[w] == 0){//将没有探查过的元素进栈，下一个循环就可以拿到然后探查
                        stack.push(w);
                    }
                }
            }
        }
    }
    public static void dfsRecurisivelyPost(int [][] g, int v){
        marker[v] = 1;
        for(int ele : g[v]){//explore all neighbors
            if(marker[ele] != 1) {
                dfsRecurisivelyPost(g,ele);
            }
        }
        System.out.println(v);//explore after return from the dead end
    }

    //TODO: to be implemented.
    public static void dfsIterPostorder(int [][] g, int v) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(v);

        LinkedList<Integer> result = new LinkedList<>();
        while (!stack.isEmpty()) {
            Integer ele = stack.peek();
//            boolean allvisited = true;
            if (marker[ele] == 0) {
                //                System.out.println(ele);
                marker[ele] = 1;
                result.addFirst(ele);
                for (int w : g[ele]) {
                    if (marker[w] == 0) {
                        stack.push(w);
//                        allvisited = false;
                    }
                }
                if(ele.equals(stack.peek())){
                    //at bottom, visit and drop it.
                    System.out.println(ele);
                    stack.pop();
                }
            } else {
                System.out.println(ele);
                stack.pop();
            }

        }
//        System.out.println(result);
//        result.forEach(System.out::println);
    }

}
