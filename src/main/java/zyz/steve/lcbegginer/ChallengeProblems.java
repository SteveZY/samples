package zyz.steve.lcbegginer;

import zyz.steve.datastruct.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ChallengeProblems {
    public static int [] runningSum(int [ ] a){
        for (int i = 1; i < a.length; i++) {
            a[i] += a[i-1];
        }
        return a;
    }
    public static List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if(i%3==0 && i%5==0){
                ret.add("FizzBuzz");
            }else if(i%3==0){
                ret.add("Fizz" );
            }else if(i%5 ==0){
                ret.add("Buzz");

            }else {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }

    public static ListNode middleNode(ListNode head){
        ListNode fast = head;
        while (fast != null){
            if(fast.next==null){
                break;
            }else {
                fast = fast.next.next;
                head = head.next;
            }

        }
        return head;
    }
    public static boolean ransomNote(String n, String d){
        int[] dic = new int[128];
//        int [] m = new int[128];
        for(char c:d.toCharArray()){
            dic[c]++;
        }
        for(char c:n.toCharArray()){
            if(--dic[c] < 0){
                return false;
            }
        }

        return true;
    }
    //https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1176/
    public int singleNumber(int[] nums) {
        // Loner
        int loner = 0;

        // Iterate over all bits
        for (int shift = 0; shift < 32; shift++) {
            int bitSum = 0;

            // For this bit, iterate over all integers
            for (int num : nums) {

                // Compute the bit of num, and add it to bitSum
                bitSum += (num >> shift) & 1;
            }

            // Compute the bit of loner and place it
            int lonerBit = bitSum % 2;
            loner = loner | (lonerBit << shift);
        }
        return loner;
    }
}
