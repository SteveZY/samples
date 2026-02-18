package zyz.steve.dividcqr;

public class PowerCalc {
    static int qpowerRecursively(int x, int n) {
        //x to the power of n
        if (n == 0)
            return 1;
        if ((n & 1) != 0)//odd
            return qpowerRecursively(x, n - 1) * x;
        int t = qpowerRecursively(x, n / 2);//n is even
        return t * t;
    }

    /**
     * 先从特例 开始 然后 推广 到 所有 幂次
     * 即， n 都是 2 的整数次方
     */
    static int qpowerSpecial(int x, int n) {
//    if (Integer.bitCount(n) != 1) return -1;
        int res = 1;
//    int i =1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res *= x; // 如果 n 是 2的整数次方的情况下，最后一个1 就 可以将 之前的 x^n  全部拿出到结果了
            }
//        res=x;
            n >>= 1; // 这里就是 根据 n 的二进制 来循环的， 一直到最高位的那个 1
            x *= x;
        }
        return res;
    }

    /**
     * 每个 整数 n 都 可以 表示成 二进制， 进而 根据 每个 数位 上 是 0 或者1 决定 怎么拆解成 加法
     * 比如 x 为3， n 为 7 ， 则 n为 111b ， 用2 为底， 加法形式 为 2^2 + 2^1 + 2^0
     * 3^(2^2) * 3 ^ (2 ^1)* 3 ^ (2^0) , 即 3^4 * 3^2 * 3^1
     * <p>
     * x^n 可以 写成
     * (x^2)^(n/2)                 当 n 为 偶数时 或
     * (x^2)^(floor(n/2)) * x , 当n 为奇数 时
     *
     * @param x
     * @param n
     * @return
     */
    static int qpower(int x, int n) {
//非递归
        int ans = 1;
        while (n > 0) {
            if ((n & 1) != 0)//odd
            {
                ans = ans * x;
            }
            n >>= 1; // 每次 n 都 会 变成 floor(n/2) ,  为了 使 等式成立，则 底 需要更新为 当前 x的 square  即 x^2
            x *= x;//  x 都会 更新为 它 自己 的 平方， 便于 下次使用
        }
        return ans;
        //举个例子x^7写成二进制形式
        //x^111b = x^110b * x
        //在 7 里面 有一个 4 是 2的整数次幂 ((x^2)^2), 就可以用 21 行 乘法得到
        //再比如 在 9里面 有 8 是 (((x^2)^2)^2), 也是 x * x 后得 X ， 继续 X*X
        //对于不能是2的整数次幂的情况， 每次就单独乘
        //2^9 = 2^8 * 2 ; 2^10 (1010b) = 2^8(1000b) * 2^2(010b)
        //a^12(1100b)=a^8(1000b)*a^4(100b);
    }

    private static int[][] multi_matrix(int[][] a, int[][] b) {
        int[][] tmp = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                tmp[i][j] = 0;
                for (int k = 0; k < 2; k++) {
                    tmp[i][j] = tmp[i][j] + a[i][k] * b[k][j];
                }

            }
        }
        return tmp;
    }

    public static int[][] qpower(int[][] m, int n) {
        int[][] ans = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) != 0) {
                ans = multi_matrix(ans, m);
            }
            n >>= 1;
            m = multi_matrix(m, m);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(qpowerSpecial(-3, 16));
    }
}
