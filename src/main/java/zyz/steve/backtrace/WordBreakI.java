package zyz.steve.backtrace;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordBreakI {
    public static boolean wordBreak(String s, List<String> wordDict) {

        //        if(words.size()==0)return false;
        Set<String> words = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()+1];
//        return search(s, 0,s.length(),words,memo);
        return searchDictResursively(s,0,wordDict,memo);
    }

    /**
     * 先在找到一个子串，然后递归剩余的子串（需要记下递归的结果，否则会有大量重复计算，最后超时），如果剩余的子串可以到最后，就是true。
     * 如果到不了，则增加prefix的长度，继续下一轮循环，
     * 也可以循环 字典
     * @param s
     * @param b
     * @param e
     * @param words
     * @param memo
     * @return
     */
    private static boolean search(String s, int b, int e, Set<String> words,Boolean[] memo ){
        //naive 回溯 (没有memoization)，太慢，超时，O(n!)
        //加了 memoization， 就可以通过了
        if (b == e) {
            return memo[b]= true;
        }
        if(memo[b]!=null){
            return memo[b];
        }
        //        int
        for (int i = b + 1; i <= e; i++) {
            if (words.contains(s.substring(b, i))) {
                boolean res = search(s, i, e, words,memo);
                if (res) {

                    return memo[b]= true;
                }
            }
        }
        return memo[b]=false;
    }
    private static boolean searchDictResursively(String s, int b,List<String> wordDict,Boolean[] memo){
        if(b==s.length()) return true;
        if(memo[b] != null) return memo[b];

        for (int i = 0; i < wordDict.size(); i++) {
            String w = wordDict.get(i);
            if(s.startsWith(w,b)){
                if(searchDictResursively(s, b+w.length(),wordDict,memo)){
                    return memo[b]=true;
                }
            }
        }
        return memo[b]=false;

    }
    public static boolean wordBreakBFS(String s, List<String> wordDict) {

        Set<String> words = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] seen = new boolean[s.length() + 1];
        queue.add(0);

        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (start == s.length()) {
                return true;
            }

            for (int end = start + 1; end <= s.length(); end++) {
                if (seen[end]) {
                    continue;
                }

                if (words.contains(s.substring(start, end))) {
                    queue.add(end);
                    seen[end] = true;
                }
            }
        }

        return false;
    }

    /**
     * dp[i] = dp[i-word.len]==true && s(i-word.len+1,i) in dict
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreakDP(String s, List<String> wordDict){
        Set<String> words = new HashSet<>(wordDict);
        boolean[] reachable = new boolean[s.length()+1];
        reachable[0] =true;//初始， 设定 空字符串 总是可以构造的 ，reachable 的 index 1 指明 字符串 的 0号字符能否到达，依此类推
        for (int i = 1; i <= s.length(); i++) {
            //欲查看 可否到达 i ，需要判断 是否在字典的约束下 是否可到达 i 之前的 某个位置
            //然后判断，之前的某个可到达位置， 能否再通过字典中的 某个词到达 i
            for (int j = 0; j < i; j++) {
                if(reachable[j] && words.contains(s.substring(j,i))){
                    reachable[i]=true;
                    break;
                }
            }
        }
        return reachable[s.length()];
    }
}
