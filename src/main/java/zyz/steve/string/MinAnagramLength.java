package zyz.steve.string;

import java.util.*;

public class MinAnagramLength {

//    https://leetcode.com/problems/minimum-length-of-anagram-concatenation/
    //#3138

    private static boolean isValidFactor(String s, int f ){
        int [] cnt = new int [26];
        int [] cnt2 = new int [26];

        int n = s.length();
        if( f == n) return true;
        String sub = s.substring(0,f);
        for(int i=0;i<f;i++){
            //count chars in the pattern
            cnt[sub.charAt(i) - 'a']++;

        }
        for(int i=f;i<n;i+=f) {
            for (int j=i;j<i+f;j++){
                cnt2[s.charAt(j) - 'a']++;
            }
            if(!Arrays.equals(cnt2,cnt)) return false;
            Arrays.fill(cnt2,0);
        }
        return true;
    }
    public static int minAnagramLength(String s) {
//        int [] cnt = new int [26];
        int n = s.length();
        List<Integer> fs = new ArrayList<>();
        //get all the factors
        for(int i =1; i*i<=s.length();i++){
            if(n%i ==0) {
                fs.add(i);
                if(i!=n/i)
                fs.add(n/i);
            }
        }
        int ans =n;
        //check all the factors
        for(Integer f: fs){
            if(isValidFactor(s,f)){
                ans = Math.min(ans,f);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minAnagramLength("abba"));
    }
}
