package zyz.steve.dp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoinChange {
    //leet code 669 - LintCode?
//    int [] f = new int[100];
//video : https://www.youtube.com/watch?v=dHeBncN-dZo&t=2188s
    public static int coinChange(int [] coins, int S){
        int [] f = new int[S+1];
        f[0]=0;
        for (int j = 1; j <= S; j++) {
            f[j] = Integer.MAX_VALUE;
//            f[j] = min{f[j-c1} +1, f[j-c2]+1 ... f[j-cn]+1}
            for (int coin : coins) {
                int tmp;
                int remainder = j - coin;
                if (remainder >= 0 && f[remainder]<Integer.MAX_VALUE) {
                    //之前的面值存在，若也可被拼出，则更新f[j]
                    //小于无穷时，说明可以被拼出
                    tmp = f[remainder] + 1;
                    f[j] = Math.min(f[j], tmp);
                }
            }
        }
        if (f[S] < Integer.MAX_VALUE) {
            return f[S];
        } else {
            return -1;
        }
    }
    public static int uniquePaths(int w, int h){
        //w:width, h:height

        int [][] map = new int[w][h];
        for (int i = 0; i < w ; i++) {
            for (int j = 0; j < h; j++) {
                if (i==0 || j ==0){
                    map[i][j] = 1;
                }else {
                    map[i][j]=map[i-1][j]+map[i][j-1];
                }
            }
        }
        return map[w-1][h-1];
    }
    public static long uniquePathsOp(int w, int h){
        //w:width, h:height
        int min, max;
        if(w>h){
            min =h;
            max = w;
        }else {
            min = w;
            max = h;
        }
        long  [] map = new long[min];
        for (int i = 0; i < max ; i++) {
            for (int j = 0; j < min; j++) {
                //小边在内侧，这样就可以用较少的内存
                if (i==0 || j ==0){
                    map[j] = 1;
                }else {
                    //map[j-1] : top
                    //map[j]: left
                    map[j] = map[j] + map[j-1];
                }
            }
        }
        return map[min-1];
    }

    public static boolean canJump(int [] s){
//        https://www.youtube.com/watch?v=dHeBncN-dZo
        //假设有n个 数据点，那么能否到达最后一个数据点 n -1 的情况，就要求如下
        //若是从第 m （m<n-1）个 数据点跳过来的，那么就需要
        //        n - 1 - m <= s[m]
        //那么能否从m跳到 n-1 就 有两个条件决定
        //1. 得向能跳到 m
        //2. s[m]>= n - 1 -m
        //于是通用的转移方程就可以这么写，假设要从 i 跳到 j (j>i)， 闭关令 f[i] 为 能否从i之前跳到 i 的 状态
        //f[j] = ( f[i] == true  &&  s[i] >= j - i ) (只要对任意 0<i<j， 只要有一个i 满足该条件，则 f[j] 为 true
        boolean [] f = new boolean[s.length];
        f[0] = true;//起始点
        for (int j = 1; j < s.length; j++) {
            //从第一个点开始检查
            for (int i = 0; i < j; i++) {
                if (f[i] && (s[i] >= (j - i))) {
                    f[j] = true;
                    break;
                }
            }
        }
        for(boolean e:f){
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("-------");
        //        Stream.of(f).forEach(System.out::println);
        return f[s.length-1];
    }

    /**
     *
     * @param a 两个产线各个站点耗时 2* n
     * @param t 进行产线转换需要的时间 2* n-1
     * @param e 进入装配线所需时间 长度2
     * @param x 离开 ... 长度 2
     * @param n 装配站的个数
     */
    public static void fastestWay(int [][]a, int [][]t,int []e, int[]x, int n){
        int [] f0 = new int[n];
        int [] f1 = new int[n];
        int [] l0 = new int[n];
        int [] l1 = new int[n];
        f0[0] = a[0][0] + e[0];
        f1[0] = a[1][0] + e[1];
        //f0[j] = min(f0[j-1]+a[0][j]，f1[j-1] + t[1][j-1] + a[0][j]
        //f1[j] = min(f1[j-1]+a[1][j]，f0[j-1] + t[0][j-1] + a[1][j]
//        int currentLine=0;
//        int alternativeLine=0;
        for (int j = 1; j < n; j++) {
//            currentLine =
            if(f0[j-1]+a[0][j] < f1[j-1]+t[1][j-1]+a[0][j]){
                f0[j]= f0[j-1]+a[0][j];
                l0[j] = 0;
            } else {
                f0[j] = f1[j-1]+t[1][j-1]+a[0][j];
                l0[j] = 1;
            }
//            f0[j] = Math.min(f0[j-1]+a[0][1],f1[j-1]+t[1][0]+a[0][1]);
            if(f1[j-1] + a[1][j] < f0[j-1] + t[0][j-1] + a[1][j]){
                f1[j] = f1[j-1] + a[1][j];
                l1[j] = 1;
            }else{
                f1[j] = f0[j-1] + t[0][j-1] + a[1][j];
                l1[j] = 0;
            }
        }
        int fastestLine = -1;
        if(f1[n-1] + x[1] > f0[n-1] + x[0]){
            //Line 0 as the end is faster
            fastestLine = 0;
//            System.out.println("line 0, station:"+(n-1));

        }else {
            //line 1 as the end is faster
            fastestLine = 1;
        }
        System.out.printf("line %d station:%d%n",fastestLine,n-1);
        for (int i = n-1; i > 0; i--) {
            if (fastestLine == 0){
                if (l0[i] == 0)
                    System.out.println("line 0, station:" + (i-1));
                else {
                    System.out.println("line 1, station:" + (i -1) );
                }
                fastestLine= l0[i];
            }else {
                if (l1[i] == 0)
                    System.out.println("line 0, station:" + (i-1));
                else {
                    System.out.println("line 1, station:" + (i -1) );
                }
                fastestLine= l1[i];

            }
        }
    }

    public static int cutRodRecursively(int[]p,int n){
        //假设index 从 0 ～ n-1
        //转移方程 r[j] = max(p[j],r[1]+r[j-1],r[2]+r[j-2],...,r[j-1]+r[1])
        // 或者改写为 r[j] = max(p[1]+r[n-1],p[2]+r[n-2],...,p[n-1]+r1,p[n]+r[0])
        if(n<=0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, p[i]+cutRodRecursively(p,n-1-i));
        }
        return  max;
    }
    public static int cutRodMemo(int []p, int n ){
        int [] r = new int [n];
        Arrays.fill(r, -1);
        return cutRodRecursivelyMemoization(p,n, r);
    }
    private static int cutRodRecursivelyMemoization(int[]p,int n, int []r){
        //假设index 从 0 ～ n-1
        //转移方程 r[j] = max(p[j],r[1]+r[j-1],r[2]+r[j-2],...,r[j-1]+r[1])
        // 或者改写为 r[j] = max(p[1]+r[j-1],p[2]+r[j-2],...,p[j-1]+r1,p[j]+r[0])
        if(n<=0) return 0;//if 0,  返回零
        if(r[n-1]>=0) return r[n-1]; // 如果已经计算过了，直接返回
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, p[i] + cutRodRecursivelyMemoization(p, n - 1 - i, r));
        }
        r[n-1] = max;//记住当前 r[j] 的最优解
        return  max;
    }

    //典型的动态规划 做法
    public static int cutRodDPBottomUp(int []p, int n){
        int max=Integer.MIN_VALUE;
        int [] r = new int[n+1];
        Arrays.fill(r, -1);

        r[0]=0;
        r[1] = p[0];

        for (int j = 1; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                max = Math.max(max, p[i] + r[j-i]);
            }
            r[j+1] = max;

        }
        return r[n];
    }
    //转移方程： opt[i][w] = max(opt[i-1][w], opt[i-1][w-items[i].wt] + items[i].val)
    public  static int knapsackRecursive(int[][] items, int k, int n) {
        //        System.out.printf("current - n:%d k:%d %n",n,k);

        //根据上述转移方程，写成递归形式
        //k: bag limit.
        // items (i,2): contain size and value both,
        // n: number of items
        //base case
        if (k <= 0 || n <= 0) {
            //            System.out.printf("n:%d k:%d - value:0%n",n,k);
            return 0;
        }
        //        System.out.printf("n:%d k:%d - value:%d%n",n,k,max);
        return Math.max(
                knapsackRecursive(items, k, n - 1),
                k - items[n - 1][0] >= 0 ?
                        //当 k值 无效，不能取当前物品，故总是返回不取的结果
                        knapsackRecursive(items, k - items[n - 1][0], n - 1) + items[n - 1][1] : 0);
    }
    public static int knapSackMemo(int[][] items, int k, int n) {
        int[][] m = new int[n + 1][k + 1];
        IntStream.range(0, n + 1).forEach(i -> Arrays.fill(m[i], -1));
        int ret = knapSackRecsMemoization(items, k, n, m);
        System.out.println(ret);
        return ret;
    }
    static int knapSackRecsMemoization(int[][] items, int k, int n, int [][]m){
        System.out.printf("n is %d;k is %d%n",n,k);
        if(m[n][k] >=0) {
            System.out.println("Calculated. Returning...");
            return m[n][k];
        }
        int ret;
        if (k == 0 || n == 0) {
            ret = 0;
        }
        else {
            ret = Math.max(
                    knapSackRecsMemoization(items, k, n - 1, m),
                    k - items[n - 1][0] >= 0 ? knapSackRecsMemoization(items, k - items[n - 1][0], n - 1, m) + items[n - 1][1] : 0
            );
        }
        m[n][k]=ret;
        return ret;
    }
    //01 背包 DP
    public static int knapSackDP(int[][] items, int k) {
        //用1维数组记录当前的item 选与不选 所更新的价值； 因为毕竟 物品重量只有一个维度，一维数组应该足够了
        int[] maxValue = new int[k + 1];
        for (int i = 0; i < items.length; i++) {
            for (int j = k; j >= 0; j--) {
                //因为需要用到数组前面较小的值,不能覆盖之，所以背包尺寸需要从大到小遍历
                if (j - items[i][0] >= 0) {//可以放下
                    int newValue = maxValue[j - items[i][0]] + items[i][1];
                    maxValue[j] = Math.max(newValue, maxValue[j]);
                }//否则不做更新
            }
        }
        return maxValue[k];
    }
    //完全背包问题，
    //k：背包尺寸
    //https://www.youtube.com/watch?v=AFspvwnCcK4&t=1553s
    public static int knapSackFullDP(int[][] items, int k){
        //用1维数组记录当前的item 选与不选 所更新的价值； 因为毕竟 物品重量只有一个维度，一维数组应该足够了
        int[] maxValue = new int[k + 1];
        for (int[] item : items) {
            for (int j = 0; j <= k; j++) {
                //因为有无穷的同一物品，故而需从最优解数组的前面更新,正序更新
                if (j - item[0] >= 0) {//可以放下
                    int newValue = maxValue[j - item[0]] + item[1];
                    maxValue[j] = Math.max(newValue, maxValue[j]);//用较大值更新当前最优解
                }//否则不做更新
            }
        }
        return maxValue[k];
    }
    //474: https://leetcode.com/problems/ones-and-zeroes/description/
    //返回 最大的 subset of those items, 该子集中的总共的 0，1 数字的个数 分别为 m 和 n
    // items: container number of zeros and ones
    //
    public static int onesZerosRecursive(int[][] items, int m, int n, int count){
        //count is the count of items  - index from 0 ~ count -1
        //X(items, m, n, count) =
        // X(items, m, n, count-1) + 1 if (m-item.zeros) >= 0 && (n-items.ones) >=0
        // X(items, m, n, count-1)  otherwise
        //)
        if (count <= 0) {
            return 0;
        }
        if (m <= 0 && n <= 0) {
            return 0;
        }
        int curItemIdx = count - 1;
        if(((m - items[curItemIdx][0] >=0) && (n-items[curItemIdx][1] >= 0))){

            return Math.max(
                    onesZerosRecursive(items, m, n, count - 1), onesZerosRecursive(items, m - items[curItemIdx][0], n - items[curItemIdx][1], count - 1) + 1
            );
        }else {//无法容纳当前物品
            return onesZerosRecursive(items, m, n, count - 1);
        }
    }
    public static int onesZerosDP(int[][] items, int m, int n) {
        int [][] optTable = new int[m+1][n+1];
        for (int i = 0; i < items.length; i++) {//遍历物品

            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if ((j - items[i][0] >= 0) && (k - items[i][1] >= 0)) {
                        optTable[j][k] = Math.max(
                                optTable[j][k],
                                optTable[j - items[i][0]][k - items[i][1]]+1
                        );
                    }
                }
            }
        }
        return optTable[m][n];
    }

    /**
     * TODO: Not implement yet
     * https://www.youtube.com/watch?v=1BAsAgdx7Ac
     * 时间约束投资组合 最大收益
     * prev(i) 就是跟当前i 任务不冲突的 任务号
     * 递归式：
     * opt(i) = max(
     *  i.val + opt(prev(i))，
     *  opt(i-1)
     * )
     * task[0] - 启动时间
     * task[1] - 结束时间，任务需要按着结束时间先排序
     * task[2] - 是价值
     * @return
     */
    public static int maxTaskValue(
            int [][] task
    ){
        //遍历task 填充 prev(i)
        for (int i = 0; i < task.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < task.length; j++) {
                if(j == i) continue;
                int timeOverlap = task[i][0] - task[j][1];
                if (timeOverlap < 0) continue;//i 任务开始时间早于j的结束时间 故不是该j 继续
                min = Math.min(min, timeOverlap);
            }
        }
        return 0;
    }

}
