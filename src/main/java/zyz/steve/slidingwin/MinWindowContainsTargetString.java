package zyz.steve.slidingwin;

import java.util.HashMap;
import java.util.Map;

public class MinWindowContainsTargetString {

    // https://leetcode.cn/problems/minimum-window-substring/
    public static String minWindowWithTargetS(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();// 记录 目标 串的 字符 统计
        int left = 0;
        int minStart = 0, minLen = Integer.MAX_VALUE; //
        int count = 0; // 记录 当前 window 中 匹配了 多少个 字符
        for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        for (int i = 0; i < s.length(); i++) {
            char curCh = s.charAt(i);
            if (map.containsKey(curCh)) {
                if (map.get(curCh) > 0) count++; // s 的当前window 匹配到一个 t 的字符。若t 中该字符 已经 全部匹配完成，则不增加已匹配到的计数
                // 减小 t 中 待匹配的字符
                map.put(curCh, map.get(curCh) - 1);
            }
            // 已经 匹配到 t ，从左缩小 窗口，看能否找到更小的
            while (t.length() == count) {
                if (i - left + 1 < minLen) { // 更新 minlen

                    minStart = left;
                    minLen = i - left + 1;
                }
                // 准备缩小窗口
                char leftCh = s.charAt(left);
                if (map.containsKey(leftCh)) {
                    map.put(leftCh, map.get(leftCh) + 1); // 待匹配字符 增加
                    if (map.get(leftCh) > 0) count--;
                }
                left++;
            }
        }

        if (minLen == Integer.MAX_VALUE) return "";
        return s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        String s = "abababacdd";
        String t = "add";
        System.out.println(minWindowWithTargetS(s, t));
    }
}
