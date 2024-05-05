package zyz.steve.backtrace;

import java.util.*;

public class FindLadders {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        HashMap<String, Integer> hm = new HashMap<>();
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(beginWord, 1));
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (q.size() > 0) {
            Pair rem = q.remove();
            String word = rem.word;
            int steps = rem.steps;
            hm.put(word, steps);
            if (endWord.equals(word)) {
                break;
            }

            for (String trav : wordList) {
                if (visited.contains(trav) == false && isDiffOne(word, trav)) {
                    visited.add(trav);
                    q.add(new Pair(trav, steps + 1));
                }
            }
        }

        List<String> arl = new ArrayList<>();
        ans = new ArrayList<>();

        System.out.println(hm);

        if (hm.containsKey(endWord) == false) {
            return ans;
        }

        dfs(endWord, beginWord, hm, arl);

        return ans;

    }

    List<List<String>> ans;

    public void dfs(String end, String begin, HashMap<String, Integer> hm, List<String> arl) {

        if (end.equals(begin)) {
            arl.add(0, begin);
            List<String> temp = new ArrayList<>(arl);
            ans.add(temp);
            arl.remove(0);
            return;
        }

        arl.add(0, end);
        for (String trav : hm.keySet()) {
            if (hm.get(trav) < hm.get(end) && isDiffOne(end, trav)) {
                dfs(trav, begin, hm, arl);
            }
        }
        arl.remove(0);
    }

    public boolean isDiffOne(String s1, String s2) {
        if (s1.equals(s2)) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
            if (count >= 2) {
                return false;
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }

    class Pair {

        String word;

        int steps;

        Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
}