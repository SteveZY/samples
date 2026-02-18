package zyz.steve.slidingwin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringNoDupChar {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthBruteForce(str));
        System.out.println(lenLongSubstring(str));
        System.out.println(lenLongSubstrUsingMap(str));
        str = "aabc";
        System.out.println(lenTwoDupCharAtMax(str));
    }

    private static int lengthBruteForce(String str) {
        int len = str.length();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (noDupChar(str, i, j)) ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    private static boolean noDupChar(String str, int i, int j) {
        Set<Character> set = new HashSet<>();
        for (int k = i; k < j; k++) {
            if (!set.add(str.charAt(k))) return false;
        }
        return true;
    }

    private static int lenLongSubstring(String str) {
        Set<Character> curChs = new HashSet<>();
        int ans = 0, left = 0;
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            while (!curChs.add(ch)) curChs.remove(str.charAt(left++));// 只要不能加入，从set 中一个个的移除左侧的字符, 并移动左指针

            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    private static int lenLongSubstrUsingMap(String s) {
        int ans = 0, left = 0;
        Map<Character, Integer> mapxx = new HashMap<>();// 存放 char - index
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // abcac
            if (mapxx.containsKey(ch)) left = Math.max(mapxx.get(ch), left);// 移动left 到 该重复字符的位置， 这比一个一个移动left要快
            mapxx.put(ch, i);
            ans = Math.max(ans, i - left);
        }
        return ans;
    }

    private static int lenTwoDupCharAtMax(String s){
        int ans = 0, left =0;
        Map<Character, Integer> map = new HashMap<>(); // 用于 计数 当前加入的字符的数量， Char - counter
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            map.put(ch, map.getOrDefault(ch,0)+1); //计数
            while (map.size()>2) {
                // 一旦大于2说明 有不一样的 字符进来

                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar)-1); // 减小left的计数
                if(map.get(leftChar) == 0){
                    map.remove(leftChar);
                }
                left++;
            }
//            if(!map.containsKey(ch))map.put(ch,i);
            ans = Math.max(ans, i-left+1);

        }

        return ans;

    }
}
