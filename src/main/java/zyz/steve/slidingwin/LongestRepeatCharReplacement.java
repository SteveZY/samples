package zyz.steve.slidingwin;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//LC 424
public class LongestRepeatCharReplacement {

    private static int findMax(Map<Character, Integer> map){
        return map.entrySet().stream().map(Map.Entry::getValue)
                .max(Comparator.naturalOrder()).orElse(0);
    }
    public static int getLongestSubstring(String s, int k) {
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        int ans =0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
            while ((i-left+1 - findMax(map)) >k) {
                char leftCh = s.charAt(left);
                map.put(leftCh, map.get(leftCh)-1);
                if (map.get(leftCh)<=0) map.remove(leftCh);
                left++;
            }
            ans = Math.max(ans, i-left+1);
        }

        return ans;

    }

    public static void main(String[] args) {
        String s = "ABAB";
        System.out.println(getLongestSubstring(s,2));
    }

}
