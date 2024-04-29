package zyz.steve.dp;

import java.util.Arrays;

//https://leetcode.com/problems/longest-increasing-subsequence/
// Binary Indexed Tree
//https://zhuanlan.zhihu.com/p/99167607#:~:text=%E6%A0%91%E7%8A%B6%E6%95%B0%E7%BB%84%E6%88%96%E4%BA%8C,%E7%9A%84%E5%89%8D%E7%BC%80%E5%92%8C%EF%BC%8C%20%E5%8C%BA%E9%97%B4%E5%92%8C%E3%80%82
//https://www.baeldung.com/cs/fenwick-tree
public class LengthOfLIS {
    public static int getLenOfLongestIncSubseq(int [] m){
        int[] len = new int[m.length];
        Arrays.fill(len,1);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                //查看 i 之前的所有元素，只要比i 小，再查看 其位置的 值 多上 i 这个数字后，是否比 当前 到i 的 LIS 更大
                if(m[j]<m[i] && len[i]<len[j]+1){
                    len[i] = len[j] + 1;
                }
            }
        }
        int max = 1;
        for (int j : len) {
            max = Math.max(max, j);
        }
        return max;
    }
}
