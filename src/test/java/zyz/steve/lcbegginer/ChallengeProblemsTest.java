package zyz.steve.lcbegginer;

import org.junit.Test;
import zyz.steve.datastruct.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class ChallengeProblemsTest {
    @Test
    public void testRunningSum(){
        int [] a = {3,1,2,10,1};

        int[] b = ChallengeProblems.runningSum(a);
        Arrays.stream(b).forEach(e -> {
            System.out.print(e);
            System.out.print(",");
        });

    }
    @Test
    public void testMiddleNode(){
        ListNode head = new ListNode(10);
        ListNode nine = new ListNode(9);
        ListNode eight = new ListNode(8);
        ListNode svn = new ListNode(7);
        head.next = nine;
        nine.next=eight;
        eight.next = svn;
        ListNode ans = ChallengeProblems.middleNode(head);
        System.out.println(ans);
    }

    @Test
    public void testRansomNote(){
        boolean ans = ChallengeProblems.ransomNote("uuytaed", "uyyttaed");
//        int[] dic = new int[128];
//
//        Map<Integer,Integer> b = new HashMap<>();
//        for(int ee:dic){
//            b.put(ee,1);
//            b.getOrDefault(ee,0);
//        }
//        for(Map.Entry<Integer,Integer> entry: b.entrySet()){
//            if(entry.getValue()<2){
//                return;
//            }
//        }
        System.out.println(ans);
    }

}