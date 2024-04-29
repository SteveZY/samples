package zyz.steve.graph;

import org.junit.Assert;
import org.junit.Test;

public class GridTest {

    @Test
    public void testGridDFS() {
        int[][] g = {{1, 1, 0,0}, {0, 1, 0,0}, {0, 0, 1,0},{0,1,1,1}};

        Assert.assertEquals(4,Grid.maxAreaOfIsland(g));
//        System.out.println(g[1][2]);
//        System.out.println(g)

    }
    @Test
    public void testArrray(){
        int [][]grid = {{0,1,2,3}};
        System.out.println(grid[0][3]);
    }
    @Test
    public void testPerimeter(){
        int [][]grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};

        int[][]gg= new int[3][];
        gg[1] = new int[]{4,5,6};
        gg[0] = new int[]{4,0,6};
        Assert.assertEquals(16, Grid.islandPerimeterDFS(grid));
        grid = new int[][]{{1}};
        Assert.assertEquals(4, Grid.islandPerimeter(grid));
        grid = new int[][]{{1,0}};
        Assert.assertEquals(4, Grid.islandPerimeterDFS(grid));
    }
    @Test
    public void testLargestIsland(){
        int [][]grid = {{0,1,0},{1,0,1},{0,1,0}};
        Assert.assertEquals(5,Grid.largestIsland(grid));
        grid = new int[][] {{1,0},{0,1}};
        Assert.assertEquals(3,Grid.largestIsland(grid));
        grid = new int[][] {{1,1},{1,1}};
        Assert.assertEquals(4,Grid.largestIsland(grid));
        grid = new int[][] {{0,0},{0,0}};
        Assert.assertEquals(1,Grid.largestIsland(grid));
    }
}