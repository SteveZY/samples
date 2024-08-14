package zyz.steve.sorting;

//题目链接 - https://www.acwing.com/problem/content/5843/
//封印序列

import java.util.Scanner;

/**
 * 每删除一个元素
 * 求最大子段和
 * 可以使用并查集 参考 https://www.acwing.com/problem/content/3118/  疯狂的馒头
 *
 * 或者 245 但是解法更复杂 https://www.acwing.com/problem/content/246/
 */
public class ACWing5840 {
    static int N=100010;
    static int []a = new int[N];
    static int []b = new int[N];
    static int []p = new int[N];

    static boolean []st = new boolean[N];
    static long []ans = new long[N];
    static long []sum = new long[N];
    public static void main(String[] args) {
        //处理输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 1; i <= n  ; i++) {
            a[i] = in.nextInt();

        }
        for (int i = 1; i <= n  ; i++) {
            b[i] = in.nextInt();

        }

        //初始化 并查集
        for (int i = 1; i <=n; i++) {
            p[i] = i;
        }
        long max = 0;
        for (int i = n; i >0 ; i--) {
            int toAddIdx = b[i];
            int lroot = 0, rroot=0;
            st[toAddIdx] = true;
            sum[toAddIdx] = a[toAddIdx];
            if(st[toAddIdx-1]) {
                lroot = root(toAddIdx - 1);
                p[lroot] = root(toAddIdx);//connect to the left
                sum[toAddIdx]=sum[lroot] + sum[toAddIdx];
            }
            if(st[toAddIdx+1]){
                rroot = root(toAddIdx+1);
                p[toAddIdx] = rroot;
                sum[rroot] += sum[toAddIdx];
            }

            max = Math.max(max, sum[root(toAddIdx)]);
            ans[i-1] = max;
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);

        }
    }
    static int root(int x){
        if(p[x] != x) p[x] = root(p[x]);
        return p[x];
    }

}
