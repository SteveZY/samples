package zyz.steve.lchashtable;

import java.util.HashMap;
import java.util.Map;

//#13 https://leetcode.com/problems/roman-to-integer/
public class RomanToInt {
    public static int romanToInt(String s){
        Map<Character,Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        int ans = m.get(s.charAt(s.length() - 1));
        int curGreater = ans;
        for (int i = s.length()-2; i >=0; i--) {
            Integer cur = m.get(s.charAt(i));
            if(cur.compareTo(curGreater)<0){
                ans-=m.get(s.charAt(i));
            }else {
                curGreater=m.get(s.charAt(i));
                ans+=m.get(s.charAt(i));
            }

        }
        return ans;
    }

    public static int maxSubArray(int[] nums) {
        if(nums == null || nums.length < 1) {
            return 0;
        }
        int sum = 0;
        int result = Integer.MIN_VALUE;
        // slide window
        for(int num : nums) {
            // if sum < 0 and remove pre sum
            if(sum < 0) {
                sum = 0;
            }
            sum += num;
            result = Math.max(result, sum);
        }
        return result;
    }

}
