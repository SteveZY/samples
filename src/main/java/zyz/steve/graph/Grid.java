package zyz.steve.graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.cn/problems/number-of-islands/?company_slug=microsoft
//网格 DFS ，简化版的 图DFS
//https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/?company_slug=microsoft
public class Grid {

    public static void traverse(int[][] g, int x, int y) {
        //遍历模版
        //y 代表 行(纵)坐标 x为 列(横)坐标
        int m = g[0].length;//m * n 网格  width
        int n = g.length; // height
        if (!inArea(g, y, x)) {
            return;//越界就返回
        }

        if (g[y][x] != 1) {
            return;//不是陆地 或已访问 返回
        }

        g[y][x] = 2;//标记 已访问过
        //Adjacency list - 一共四个，
        traverse(g, x, y + 1);//上
        traverse(g, x, y - 1);//下
        traverse(g, x - 1, y);//左
        traverse(g, x + 1, y);//右
    }

    private static boolean inArea(int[][] g, int row, int col) {
        int m = g[0].length;//m * n 网格  width
        int n = g.length; // height
        // 行(纵)坐标不能大于 height n；列（横）坐标不能大于 width m；
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    public static int maxAreaOfIsland(int[][] g) {
        int ans = 0;
        for (int i = 0; i < g[0].length; i++) {
            for (int j = 0; j < g.length; j++) {
                if (g[j][i] == 1) {
                    int area = areaOfIsland(g, j, i);
                    ans = Math.max(ans, area);
                }
            }

        }
        return ans;
    }

    //x - col, y- row
    private static int areaOfIsland(int[][] g, int x, int y) {
        //        int m = g[0].length;//m * n 网格  width
        //        int n = g.length; // height
        return areaOfIslandWithMarker(g, x, y, 1, 2);
        //        if (!inArea(g, y, x)) {
        //            return 0;//越界就返回
        //        }
        //
        //        if (g[y][x] != 1) {
        //            return 0;//不是陆地 或已访问 返回
        //        }
        //
        //        g[y][x] = 2;//标记 已访问过
        //        return 1 + areaOfIsland(g, x, y + 1)//上
        //                + areaOfIsland(g, x, y - 1)//下
        //                + areaOfIsland(g, x - 1, y)//左
        //                + areaOfIsland(g, x + 1, y);//右
    }

    //typeToLook： 指明啥类型是需要查找的
    private static int areaOfIslandWithMarker(int[][] g, int x, int y, int typeToLook, int flag) {
        //        int m = g[0].length;//m * n 网格  width
        //        int n = g.length; // height
        if (!inArea(g, y, x)) {
            return 0;//越界就返回
        }

        if (g[y][x] != typeToLook) {
            return 0;//不是陆地 或已访问 返回
        }

        g[y][x] = flag;//标记 已访问过
        return 1 + areaOfIslandWithMarker(g, x, y + 1, typeToLook, flag)//上
                + areaOfIslandWithMarker(g, x, y - 1, typeToLook, flag)//下
                + areaOfIslandWithMarker(g, x - 1, y, typeToLook, flag)//左
                + areaOfIslandWithMarker(g, x + 1, y, typeToLook, flag);//右
    }

    //https://leetcode.cn/problems/island-perimeter/description/
    public static int islandPerimeter(int[][] g) {
        //从左向右，从上向下 扫描
        //遇到上或左面已经是一的情况，就 加 4  然后 -2
        int perimeter = 0;
        for (int i = 0; i < g.length; i++) {//row
            for (int j = 0; j < g[0].length; j++) {//col
                if (g[i][j] == 1) {
                    //陆地，就先加4
                    perimeter += 4;
                    //然后检测 上 及左方邻居 是否 陆地，是的话，由于 重边， 就要从原来以及新增的 1 型cell各减1，总共减 2，
                    if (inArea(g, i - 1, j) && g[i - 1][j] == 1) {
                        //check left
                        perimeter -= 2;
                    }
                    if (inArea(g, i, j - 1) && g[i][j - 1] == 1) {
                        //check upper one
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }

    //DFS 有点儿 小题大作了，但是可以看下 网格 DFS
    public static int islandPerimeterDFS(int[][] g) {
        //ajecency list of a given cell 上下左右
//        int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if(g[i][j]!=0) {
                    return gridDfs(g, i, j);
                }

            }
        }
        return 0;
    }

    private static int gridDfs(int[][] g, int r, int c) {
        if (!inArea(g, r, c)) {
            return 1;//界外 遇到一个边
        }
        if(g[r][c] == 2){
            return 0;//explored， 不贡献边长 直接返回
        }
        if (g[r][c] == 0) {
            return 1;//岛外，是一个边
        }
        g[r][c] = 2;// mark it as explored
        int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int p = 0;
        for (int i = 0; i < 4; i++) {
            p += gridDfs(g, r + adj[i][0], c + adj[i][1]);
        }

        return p;
    }

    //https://leetcode.com/problems/making-a-large-island/description/
    //No.827
    public static int largestIsland(int[][] g) {
        int h = g.length;
        int w = g[0].length;
        List<Integer> areaList = new ArrayList<>();//记录各个岛屿的面积
        areaList.add(0, 0);
        areaList.add(1, 0);
        int idx = 2;
        int ans = 0;
        //遍历陆地 计算面积 填充数组
        for (int i = 0; i < h; i++) {
            //row
            for (int j = 0; j < w; j++) {
                //col
                if (g[i][j] == 1) {
                    //找到一个入口计算面积
                    int curArea = areaOfIslandWithMarker(g, j, i, 1, idx);
                    areaList.add(idx++, curArea);
                }
            }
        }
        if (areaList.size() == 3) {

        }
        for (int i = 0; i < h; i++) {
            //row
            for (int j = 0; j < w; j++) {
                //col
                if (g[i][j] == 0) {
                    Set<Integer> lands = new HashSet<>();
                    //找到一个海洋，测试其附近来自不同岛屿的陆地 上下左右
                    if (inArea(g, i - 1, j) && g[i - 1][j] != 0) {
                        //上面是陆地
                        lands.add(g[i - 1][j]);
                    }
                    if (inArea(g, i + 1, j) && g[i + 1][j] != 0) {
                        //下面是陆地
                        lands.add(g[i + 1][j]);
                    }
                    if (inArea(g, i, j - 1) && g[i][j - 1] != 0) {
                        //左面是陆地
                        lands.add(g[i][j - 1]);
                    }
                    if (inArea(g, i, j + 1) && g[i][j + 1] != 0) {
                        //右面是陆地
                        lands.add(g[i][j + 1]);
                    }
                    int area = 1;
                    for (Integer ii : lands) {
                        area += areaList.get(ii);
                    }
                    ans = Math.max(area, ans);
                }
            }
        }
        return ans == 0 && areaList.size() == 3 ? areaList.get(2) : ans;
    }
}
