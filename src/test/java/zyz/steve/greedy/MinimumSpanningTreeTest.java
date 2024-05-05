package zyz.steve.greedy;

import org.junit.Test;


public class MinimumSpanningTreeTest {

    @Test
    public void testMST() {
        int[][] arr = new int[][] {
                // 0  1  2  3  4  5  6  7  8
                {-0, 4, 0, 0, 0, 0, 0, 7, 0},
                {4, -0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, -0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, -0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, -0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, -0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, -0, 1, 6},
                {7, 11, 0, 0, 0, 0, 1, -0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, -0}
        };
        MinimumSpanningTree.findMinSpaningTree(arr);
        //        作者：feburary
        //        链接：https://juejin.cn/post/7035842708786642981
        //        来源：稀土掘金
        //        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    }
    @Test
    public void testDijikstra(){
        MinimumSpanningTree.findShortestPathD(new int[][]{
                {0,2,3,4},
                {2,0,8,0},
                {3,8,0,0},
                {4,0,0,0},
        },3);
    }
    @Test
    public void testBellmanFord(){
        System.out.println(MinimumSpanningTree.bellmanFord(new int[][] {
                {0, 2, 3, 4},
                {2, 0, 8, 0},
                {3, 8, 0, 0},
                {4, 0, 0, 0},
        }, 1));
    }

}