package zyz.steve.dp;

public class HouseRobber {
    public static int robberMoney(int [] m){
        return Math.max (rob(m,0),rob(m,1));
    }

    /**
     * 也可以bottom up 递归， 当前的价值 + max (从 下一跳(+2) 开始 能获得的 最大价值 ， 或者从下下一跳(+3) 能获得的最大值);
     * 此处实现这中方式
     *
     * top down ， 当前的可获得的最大 价值  = 当前价值 + max( 到 i-2处，能获得的最大价值 ， 到 i-3 处能获得的最大价值 )
     * @param m
     * @param i
     * @return
     */
    private static int rob(int[] m, int i) {
        if(i>=m.length) return 0;
        if(i==m.length -1) return m[i];
        return (m[i] + Math.max(rob(m,i+2),rob(m,i+3)));
    }
    public static int robDp(int []m){
        int [] d = new int[m.length];
        d[0] = m[0];
        if (m.length > 1) {
            d[1] = Math.max(m[1],d[0]);
        }
        if (m.length > 2) {
            d[2] = Math.max(d[1],d[0] + m[2]);
        }
//        d[3] = d[1]
        for (int i = 3; i <m.length; i++) {
            d[i] = Math.max(Math.max(
                    d[i-2]+m[i],d[i-3]+m[i]),d[i-1]

            );
        }
        return d[m.length-1];
    }

}
