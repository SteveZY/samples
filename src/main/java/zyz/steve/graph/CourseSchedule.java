package zyz.steve.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {

    boolean isValid = true;

    int numNodesCompleted = 0;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] marker = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {//建图
            graph[p[1]].add(p[0]);
            inDegree[p[0]]++;

        }
        //使用DFS
        //        for (int i = 0; i < numCourses && isValid ; i++) {
        //            if (marker[i] == 0) {
        //                dfs(graph, i, marker);
        //            }
        //        }
        //        return isValid;

        //或者使用BFS
        bfs(graph, inDegree);
        return numCourses == numNodesCompleted;

    }
//https://leetcode.com/problems/course-schedule-ii/
    //#210
    public int [] findOrder(int numCourses, int[][]pres){
        int[] res = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] p : pres) {//建图
            graph[p[1]].add(p[0]);
            inDegree[p[0]]++;

        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                //将indgree 为0 的进q， 准备从他们开始广度搜索
                q.offer(i);
            }
        }
        int pos =0;
        while (!q.isEmpty()){
            Integer cur = q.poll();
            res[pos++] = cur;
            for(int v:graph[cur]){
                inDegree[v]--;
                if(inDegree[v]==0){
                    q.offer(v);
                }
            }
        }
        return  pos==numCourses? res:new int[0];
    }
    private void dfs(List<Integer>[] graph, int i, int[] marker) {
        marker[i] = 1;
        for (int v : graph[i]) {
            if (marker[v] == 0) {
                dfs(graph, v, marker);
                //发现环，终止
                if (!isValid) {
                    return;
                }
            } else if (marker[v] == 1) {
                //有环
                isValid = false;
                return;
            }
        }
        marker[i] = 2;
    }

    private void bfs(List<Integer>[] graph, int[] inDgree) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < inDgree.length; i++) {
            if (inDgree[i] == 0) {
                //将indgree 为0 的进q， 准备从他们开始广度搜索
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (int v : graph[cur]) {
                inDgree[v]--;
                if (inDgree[v] == 0) {
                    q.offer(v);
                }
            }
            numNodesCompleted++;
        }
    }
}
