package zyz.steve.slidingwin;

import java.util.HashMap;
import java.util.Map;

// LC   395
public class LongestSubStrWithAtleastKRepChars {
    private static int lenOfLongestSubStrWithAllCharsInItRepeatedKTimes(String s, int k) {
        int ans = 0;
        int numOfUniqueChars = 0; // should from 1 ～ 26
        Map<Character, Integer> map = new HashMap<>(); // 存放 字符的出现次数
        int left = 0, validCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            map.put(curChar, map.getOrDefault(curChar, 0) + 1);
            if (map.get(curChar) == k) validCount++; // 有一个字符在当前window 达到 了 k 个
            // 一旦相等说明 我们找到了一个window ，其中的所有字符的 各自的数量都 》k 了，可以开始缩小window了
            // 但这样的问题是， 如果 输入 是 aaabbb ， k 为2 的话，前两个a 就满足了条件，然后后面的 while 循环就开始缩小window了，
            // 这样就找不到最长的了哦
            // 所以需要 固定 咱们 要找 的window 到底 有 几个 unique 字符
            while (validCount == numOfUniqueChars/*map.size()*/) {
                char leftCh = s.charAt(left);

                if (map.get(leftCh) == k) validCount--; // 左字符影响到了 有效字符计数
                map.put(leftCh, map.get(leftCh) - 1);

                if (map.get(leftCh) == 0) map.remove(leftCh);
                left++;
            }

        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
