package zyz.steve.string;
import java.util.*;

//https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/
//#3170

public class ClearStarsLexiMin {
    public static String clearStars(String s) {
        int n = s.length();
        TreeMap<Character, List<Integer>> bst = new TreeMap<>();
        for (int i = 0; i < n ; ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                if (!bst.isEmpty()) {
                    char first = bst.firstKey();
                    List<Integer> cur = bst.firstEntry().getValue();
                    cur.remove(cur.size() - 1);
                    if (cur.size() == 0) {
                        bst.remove(first);
                    }
                }
            } else {
                if (!bst.containsKey(c)) {
                    bst.put(c, new ArrayList<>());
                }
                bst.get(c).add(i);
            }
        }
        char [] chs = new char[n];
        Arrays.fill(chs, '*');
        for (char  key : bst.keySet()) {
            List<Integer> cur = bst.get(key);
            for (int a : cur) {
                chs[a] = key;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n ; ++i) {
            if (chs[i] == '*') continue;
            sb.append(chs[i]);
        }
        return sb.toString();
    }
    public static String clearStarsPQ(String s){
        PriorityQueue<Integer> pq = new PriorityQueue<>((i, j) -> {
            if (s.charAt(i) == s.charAt(j))
                return j - i;
            return s.charAt(i) - s.charAt(j);
        });
        char[] arr = s.toCharArray();
        // PriorityQueue<Character> ans = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                pq.offer(i);
                // sb.append(s.charAt(i));
            } else {
                Integer top = pq.poll();
                arr[top] = '-';
                arr[i] = '-';

            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (arr[i] != '-') {
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

            @Override public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }
        );
        pq.add(5);
        pq.add(11);

        pq.add(9);
        pq.add(10);
        System.out.println(pq);
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
    }
}
