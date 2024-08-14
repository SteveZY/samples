package zyz.steve.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class ACWing3115 {

    static int N = 10_000_010;
    static int [] uSet = new int[N];
    static int [] color = new int[N];

    static int rootOf(int x){
        while (x!=uSet[x]) uSet[x]=rootOf(uSet[x]);
        return uSet[x];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();

        for (int i = 0; i <=m; i++) {
            uSet[i] = i;

        }
        //倒着染色，则 前面的操作 就不能更改 i 较大时的颜色了，
        //即判断 有没有被染过色，一旦染过了，就 pass
        for (int i = m; i >= 1; i--) {
            int i1 = (i * p + q) % n + 1;
            int i2 = (i * q + p) % n + 1;

            int s = Math.min(i1, i2);
            int e = Math.max(i1,i2);
            Arrays.fill(uSet,s,e+1,i);
//            rootOf(s) =
//            for (int j = s; j <=e ; j++) {
//                rootOf(j)
//                uSet[j] = i;
//            }


        }
        for (int i = 1; i <= n; i++) {
            System.out.println(uSet[i]);
        }
    }

}
