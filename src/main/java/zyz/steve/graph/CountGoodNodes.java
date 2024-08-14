package zyz.steve.graph;
//https://leetcode.cn/problems/count-the-number-of-good-nodes/
public class CountGoodNodes {

//    static int[] numofnodes = new int[13];
    static int total;
    public static int countNodes(int [][] g , int start){

        int nn=1;//至少 有一个节点
        int curNum;
        int startNum =0;
        boolean good = true;
        if (g[start].length > 0) {
            startNum = countNodes(g, g[start][0]);
            nn += startNum;
        }
        for (int i = 1; i < g[start].length; i++) {
            curNum = countNodes(g, g[start][i]);
            //当任意子节点 的 子树 所含 节点数 跟第一个节点不同时，标记位 不 good
            if(curNum!= startNum && good) good=false;
            nn += curNum;

        }
        if(good) total++;
        return nn;
    }

    public static void main(String[] args) {
        int [][] g = {{9,5,1},{2,3,4},{},{},{},{6},{7},{8},{},{12,10},{11},{},{}};

        countNodes(g,0);
        System.out.println(total);

    }

}
