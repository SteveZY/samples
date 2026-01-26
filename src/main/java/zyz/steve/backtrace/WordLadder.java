package zyz.steve.backtrace;

/**
 * LC 127
 * https://leetcode.com/problems/word-ladder/description/
 * A transformation sequence from word beginWord to word endWord using a dictionary
 * wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 */
import java.util.*;
public class WordLadder {
    private final static List<List<String>> ans = new ArrayList<>();
    private final static Set<String> marker = new HashSet<>();
    public static int LadderLength(String b, String e, List<String> dict){

        // BFS 解法， 基本上就是 将当前 单词 的每个字母都 用剩下的25个字母 替换后 产生 新的 词，看在不在字典里
        // 变成 图的话 因为一次只换一个字母， 看起来象这样
        //  hit 的邻接 列表就是这样， adj(hit)= [ait,bit,...,zit, hat, hbt, ..., hzt, hia,hib,...,hiz]
        Set<String> words = new HashSet<>(dict); //保存 字典
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> fifo = new ArrayDeque<>();

        fifo.offer(b);
        int count=1;

        if(!words.contains(e)) return 0;
        while (!fifo.isEmpty()){
            int size = fifo.size();
            for (int c = 0; c < size; c++) {

                String curWord = fifo.remove();
                if(curWord.equals(e))return count;
                char[] bChars = curWord.toCharArray();
//                if(numMatched==0) newMatched = 0;
                for (int i = 0; i < bChars.length; i++) {
                    for (char sub = 'a'; sub <='z'; sub++) {
                        //construct a new word and see if it's in the dict
                        if( sub==bChars[i]) continue;
                        String nextWord= getWord(bChars,i,sub);
                        if(words. contains(nextWord)&& !visited.contains(nextWord)) {
                            //                        newMatched ++;
                            fifo.offer(nextWord);
                            visited.add(nextWord);
                        }
//                        if(nextWord.equals(e)) return ++count;
                    }
                }
            }
            count++;

        }
        return 0;
    }
    private static String getWord(char[] word, int pos, char sub){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length; i++) {
            if(i==pos) sb.append(sub);
            else {
                sb.append(word[i]);
            }
        }
        return sb.toString();
    }
    //https://leetcode.com/problems/word-ladder-ii/

    /**
     * pure BFS but TLE for the given test case
     * https://leetcode.com/problems/word-ladder-ii/solutions/40434/c-solution-using-standard-bfs-method-no-dfs-or-backtracking/
     *
     * Running ones
     * C++: https://leetcode.com/problems/word-ladder-ii/solutions/3239872/c-dfs-detailed-explanation/
     * Java: https://leetcode.com/problems/word-ladder-ii/solutions/3477340/java-solution-containing-bfs-and-dfs-passing-all-test-cases/
     * https://leetcode.com/problems/word-ladder-ii/solutions/2367587/python-bfs-dfs-with-explanation-why-optimization-is-needed-to-not-tle/
     * @param b
     * @param e
     * @param dict
     * @return
     */
    public static List<List<String>> findLaddersBFSOnly(String b, String e, List<String> dict){
        Map<String, Set<String>> adj = new HashMap<>();
//        adj.put(b,new HashSet<>());
        dict.forEach(ele->adj.put(ele,new HashSet<>()));
        HashSet<String> words = new HashSet<>(dict);

        List<List<String>> ans = new ArrayList<>();
        //build adj list; with this ， 超时了
//        buildAdj(adj);

        //        List<String> path = new ArrayList<>();
        Queue<List<String>> fifo = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();//marker
        visited.add(b);
        words.remove(b);
        fifo.offer(Collections.singletonList(b));
        while (!fifo.isEmpty()){
            int sz = fifo.size();
            while (sz>0){

                List<String> cur = fifo.remove();
                String curWord = cur.get(cur.size() - 1);
                List<String> nexts = neighbours(curWord, words);
                for (String next: nexts){
                    if(!words.contains(next)) continue;
                    List<String> newPath = new ArrayList<>(cur);
                    newPath.add(next);
                    visited.add(next);
                    if(next.equals(e)) {
                        //一旦找到，就stay on the same level,因为往下的话 只能变得更长
                        ans.add(newPath);
                    }
//                  没找到则储存，准备继续向下
                    else fifo.offer(newPath);
                }
                sz --;
            }
            visited.forEach(words::remove);
            visited.clear();
        }
        return ans;
    }

    /**
     * 单源最短路径问题，只不过所有路线的 weight 都是1
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>>  findLaddersBFSThenDFS(String beginWord, String endWord, List<String> wordList){
        HashSet<String> words = new HashSet<>(wordList);

        ArrayDeque<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Map<String, Integer> nodeDistance= new HashMap<>();
        nodeDistance.put(beginWord,1);

        while (!q.isEmpty()) {
            //Dijkstra 算法
            //由于所有路径的 长度都是1， 就不需要额外维护 pq 选最小的了
//            BFS 填充 途中遇到的节点 到 beginWord 的距离
            String curWord = q.poll();
            //            for (int i = 0; i < curWord.length(); i++) {
            if(curWord.equals(endWord)) {
                System.out.println(nodeDistance);
                break;
            }
            Integer distOfCur = nodeDistance.get(curWord);
            //访问 下一跳，
            for (String next : neighbours(curWord, words)) {
                if (visited.contains(next)) {
                    continue;
                }

                visited.add(next);
                q.offer(next);
                nodeDistance.put(next, distOfCur+1); //relaxation
            }
        }
        //DFS + back tracking to get paths

        ArrayDeque<String> path = new ArrayDeque<>();

        path.offer(beginWord);
        marker.add(beginWord);
        dfsFromBegin(beginWord,endWord,path,nodeDistance.keySet(),nodeDistance);
        
//        path.push(endWord);
//
//        dfs(beginWord,endWord,path,nodeDistance.keySet(),nodeDistance);
        return ans;

    }
    private static void dfs(String s,String end,ArrayDeque<String> path,Set<String> words,Map<String,Integer> nodeDist){
        if(end.equals(s)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        List<String> successors = neighbours(end, words);
        for(String pre: successors){
            if( nodeDist.get(pre) < nodeDist.get(end)){
                path.push(pre);
                dfs(s,pre,path,words,nodeDist);
                path.pop();//backtracking
            }
        }
    }
    private static void dfsFromBegin(String s,String end, ArrayDeque<String> path, Set<String> words, Map<String, Integer> nodeDist){
        if(s.equals(end)){
            ans.add(new ArrayList<>(path));
            return;
        }
        List<String> successors = getNexts(s, words);
        for (String suc: successors){
            if( nodeDist.get(suc).compareTo(nodeDist.get(s))>0 ){
                marker.add(suc);
                path.offer(suc);
                dfsFromBegin(suc,end,path,words,nodeDist);
                //返回前将路径 复位
                path.removeLast();//backtracking
            }
        }

    }
    private static List<String> getNexts(String word,Set<String> words){

        ArrayList<String> nexts = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if(c==word.charAt(i))continue;
                char[] warr = word.toCharArray();
                warr[i] = c;
                String next = new String(warr);
                if (!marker.contains(next)&&words.contains(next)) {
                    nexts.add(next);
                }
            }
        }
        return nexts;
    }
    private static List<String> neighbours(String word,Set<String> words) {
        //        char[] warr = null;
        ArrayList<String> nexts = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if(c==word.charAt(i))continue;
                char[] warr = word.toCharArray();
                warr[i] = c;
                String next = new String(warr);
                if (words.contains(next)) {
                    nexts.add(next);
                }
            }
        }
        return nexts;
    }
    private static void buildAdj(Map<String, Set<String>> adj) {
        for(String w: adj.keySet()){
            for (int i = 0; i < w.length(); i++) {
                char[] chars = w.toCharArray();
                chars[i] = '.';
                String pattern = new String(chars);
                for(String next: adj.keySet()){
                    if(next.equals(w)) continue;
                    if(next.matches(pattern)) {
                        adj.get(w).add(next);
                        adj.get(next).add(w);
                    }
                }
            }
        }
        System.out.println(adj.entrySet().stream().map(e -> e.getValue().size()).reduce(0, Integer::sum));
    }
}
