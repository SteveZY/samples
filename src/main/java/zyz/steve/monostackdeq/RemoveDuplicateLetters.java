package zyz.steve.monostackdeq;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicateLetters {
// LC 316 字典序最小 去重。 那么就总是希望较小的元素出现在最前面

    private static String removeDups(String s) {
        StringBuilder res = new StringBuilder();

        // 栈中 存放的即 为 要返回的元素
        Deque<Integer> stack = new ArrayDeque<>();
        int[] lastIdxSeen = new int[128];
        int[] alreadyIn = new int[128];
        for (int i = 0; i < s.length(); i++) {
            lastIdxSeen[s.charAt(i)] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (alreadyIn[c] == 1) continue;
            // 维持 单调 增 栈，
            while (!stack.isEmpty()
                    && c <=  s.charAt(stack.peek()) // 发现一个更小的，就要看能不能 替换
//                    && lastIdxSeen[s.charAt(stack.peek())] > stack.peek() // wrong, 要看 i 的后面 还有没有改元素
                    && i< lastIdxSeen[s.charAt(stack.peek())] // 该栈顶元素 后面还会出现的话，就可以替换， 否则就得保留之
//                    && alreadyIn[c] !=1 // 还未 被使用过
            ) {
                Integer top = stack.pop();
                alreadyIn[s.charAt(top)] = 0;
            }
            if(alreadyIn[c] !=1) {
                stack.push(i);
                alreadyIn[c]=1;
            }
        }
        while (!stack.isEmpty()){
            res.append(s.charAt(stack.pollLast()));
        }

        return res.toString();
    }

    // 简化一下 只是移除 重复元素 但是要保留 原来的 顺序，丢弃后出现的重复元素
    private static String dedupSimply(String s){
        StringBuilder sb = new StringBuilder();
        int []flag = new int[128];
        for (int i = 0; i < s.length(); i++) {
            if(flag[s.charAt(i)] == 1) continue;
            flag[s.charAt(i)] = 1;
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String ss = "bcabc";
        System.out.println(removeDups(ss));
//        ss = "cbacdcbc";
//        System.out.println(removeDups(ss));
//        ss = "bbcaac";
//        System.out.println(removeDups(ss));
//        ss = "abacb";
//        System.out.println(removeDups(ss));
//
//        System.out.println(dedupSimply(ss));
    }
}

