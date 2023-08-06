package zyz.steve.dividcqr;

public class PowerCalc {
    static int qpowerRecursively(int x, int n){
        //x to the power of n
        if(n==0)
            return 1;
        if((n&1) != 0)//odd
            return qpowerRecursively(x,n-1)*x;
        int t = qpowerRecursively(x, n / 2);//n is even
        return t*t;
    }
    static int qpower(int x, int n) {
//非递归
        int ans = 1;
        while (n > 0) {
            if ((n & 1) != 0)//odd
            {
                ans = ans * x;
            }
            x *= x;
            n >>= 1;
        }
        return ans;
        //举个例子x^7写成二进制形式
        //x^111 = x^110 * x
        //在 7 里面 有一个 4 是 2的整数次幂 ((x^2)^2), 就可以用 21 行 乘法得到
        //再比如 在 9里面 有 8 是 (((x^2)^2)^2), 也是 x * x 后得 X ， 继续 X*X
        //对于不能是2的整数次幂的情况， 每次就单独乘
        //2^9 = 2^8 * 2 ; 2^10 (1010b) = 2^8(1000b) * 2^2(010b)
        //a^12(1100b)=a^8(1000b)*a^4(100b);
    }
    private static int[][] multi_matrix(int[][]a, int[][]b){
        int [][] tmp = new int[2][2];
        for(int i =0; i<2;i++) {
            for (int j = 0; j < 2; j++) {
                tmp[i][j] =0;
                for (int k = 0; k < 2; k++){
                    tmp[i][j] = tmp[i][j] + a[i][k]*b[k][j];
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
}
