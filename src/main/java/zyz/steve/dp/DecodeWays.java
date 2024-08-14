package zyz.steve.dp;

public class DecodeWays {

    public int numDecodings(String s ){
        int[] dp = new int[s.length()];
        if(s.charAt(0) == '0') return 0;
        dp[0] = 1;
        if(s.length()==1) return dp[0];
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i)!='0') {
                //当前字符不为0 则可以直接 将新字符追加到 最后，构成新的解码，故简单的设置当前总数到 上一步的值就行
                dp[i] = dp[i - 1];
            }
            if(s.charAt(i-1)=='0') continue; //如前一个字符 为 ‘0’ 则 无法合并解码，继续看下一个字符
            //判断能否跟前一个字符 合并 解码，若 小于 27说明可以，就可以跟 上上一步的 总数 再构成 一组解码总数
            int twoD = Integer.parseInt(s.substring(i - 1, i + 1));
            if( twoD <27){
                //如果 越界，说明目前只有两个字符，就加个 1 ，
                dp[i] += i-2>=0? dp[i-2]:1;
            }

        }
        return dp[s.length()-1];
    }

    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();

        System.out.println(dw.numDecodings("20"));
    }

}
