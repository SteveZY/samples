package zyz.steve.string;

import java.util.HashMap;
import java.util.Map;
//https://leetcode.com/problems/permutation-difference-between-two-strings/submissions/1268399795/
//#  3146
public class PermutationDiff {
    public static  int findPermutationDifference(String s, String t) {
        //可使用counter 数组 进行优化
        Map<Character, Integer> sm = new HashMap<>();//记录 每个 字符的 index
        Map<Character, Integer> tm = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sm.put(s.charAt(i),i);
            tm.put(t.charAt(i),i);
        }

        int ans =0;
        for(Map.Entry<Character,Integer> c: sm.entrySet()){
            ans+=Math.abs((tm.get(c.getKey()) - c.getValue()));
        }
        return ans;

    }
    public static void main(String []aa){
        System.out.println(findPermutationDifference("abc", "bac"));
        System.out.println(findPermutationDifference("abcde", "edbac"));
    }
}
