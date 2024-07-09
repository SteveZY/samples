package zyz.steve.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//https://leetcode.com/problems/rank-teams-by-votes/submissions/1279989102/
//#1366
public class RankTeams {
    static class VotesForATeam{

        public int[] getData() {
            return data;
        }

        int[] data;
        Character team;
        public VotesForATeam(int len, char t){
            data = new int[len];
            team = t;
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            VotesForATeam that = (VotesForATeam) o;
            return Arrays.equals(data, that.data) && team==that.team;
        }

        @Override public int hashCode() {
            int result = Objects.hash(team);
            result = 31 * result + Arrays.hashCode(data);
            return result;
        }
    }
    public static void sortVotesObject(){
        TreeSet<VotesForATeam> votesInOrder = new TreeSet<>(new Comparator<VotesForATeam>() {

            @Override public int compare(VotesForATeam o1, VotesForATeam o2) {
                for (int i = 0; i < o1.data.length; i++) {

                    if(o1.data[i]!=o2.data[i]){
                        return o2.data[i]-o1.data[i];
                    }
                    return o1.team.compareTo(o2.team);
                }
                return 0;
            }
        });
    }
    static class RankData{
        Map<Character,int[]> teamRecs = new HashMap<>();

        //最后存放 队伍 名字
        TreeSet<int []> sorted = new TreeSet<>((o1, o2) -> {
            for (int i = 0; i < o1.length-1; i++) {
                if(o1[i]!=o2[i])
                    return o2[i]-o1[i];
            }
            return o1[o1.length-1]-o2[o2.length-1];
        });
        void recVote(String vote){
            for (int i = 0; i < vote.length(); i++) {

                char team = vote.charAt(i);
                teamRecs.putIfAbsent(team,new int[vote.length()]);
                int[] score = teamRecs.get(team);

                int[] key =
                        Arrays.copyOf(score, score.length + 1);//this one for tree set with team id CHAR

                key[score.length] = team;

                sorted.remove(key);
                score[i]++;
                key[i]++;
                sorted.add(key);

            }
        }
        String getRanking(){
            StringBuilder sb = new StringBuilder();
            for(int[] item:sorted){
                sb.append((char)item[item.length-1]);
            }
            return sb.toString();
        }
    }
    static class  RankDataStore{

        public Map<Character, int[]> getData() {
            return data;
        }

        private final Map<Character, int[]> data ;
        public  RankDataStore(){
            data  = new ConcurrentHashMap<>();
        }
//        synchronized
        void receiveVote(String vote){
            for (int i = 0; i < vote.length(); i++) {
                int finalI = i;
                data.compute(vote.charAt(i),(k,v)->{
                   if(v==null){
                       int[] newv = new int[vote.length()];
                       newv[finalI]= 1;
                       return newv;
                   }else {
                       v[finalI]++;
                   }
                   return v;
                });
//                data.computeIfAbsent(vote.charAt(i),k->{
//                    int[] ret = new int[vote.length()];
//                    ret[finalI]++;
//                    return ret;
//                });
//                if(!data.containsKey(vote.charAt(i))){
//                    data.put(vote.charAt(i),new int[vote.length()]);
//                }
//                data.get(vote.charAt(i))[i]++;
            }
        }
    }
    public static String rankTeamsFromDataStore(RankDataStore ds){
        final Map<Character, int[]> data = ds.getData();

        List<Character> candidates = new ArrayList<>(data.keySet());

        Collections.sort(candidates, (o1, o2) -> {
            int[] votes1 = data.get(o1);
            int[] votes2 = data.get(o2);
            for (int i = 0; i < votes2.length; i++) {
                if (votes1[i] != votes2[i]) {
                    return votes2[i] - votes1[i];
                }
            }
            return o1.compareTo(o2);
        });
        StringBuilder sb = new StringBuilder();
        for(Character c: candidates){
            sb.append(c);
        }
        return sb.toString();
    }

//    public static String rankTeamsViaTreeMap(String[] votes){
//
//        TreeMap<String, int []> vs = new TreeMap<>(new COm);
//
//
//    }

    public static String rankTeams(String[] votes) {

        int[][] cnt = new int[26][votes[0].length() + 1];
        for (int[] cc : cnt) {
            cc[0] = 200;
        }
//        Arrays.fill();
        for (char c : votes[0].toCharArray()) {
            cnt[c - 'A'][0] = c;
        }
        for (String v : votes) {
            for (int i = 0; i < v.length(); i++) {
                cnt[v.charAt(i) - 'A'][i + 1]++;
            }
        }
        Arrays.sort(cnt, (a, b) ->
                {
                    for (int i = 1; i <= votes[0].length(); i++) {
                        if (b[i] != a[i]) {
                            return b[i] - a[i];
                        }
                    }
                    return a[0] - b[0];
                }

        );
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < votes[0].length(); i++) {
            sb.append((char) (cnt[i][0]));
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String[] vs = {"ABC", "ACB", "ABC", "ACB", "ACB"};
        //        System.out.println(rankTeams(vs));

        vs = new String[] {"WXYZ", "XYZW"};
        System.out.println(rankTeams(vs));

        RankDataStore dataStore = new RankDataStore();

        dataStore.receiveVote("WXYZ");
        dataStore.receiveVote("XYZW");

//        System.out.println(dataStore);

        System.out.println(rankTeamsFromDataStore(dataStore));
        dataStore.receiveVote("ZYXW");
        System.out.println(rankTeamsFromDataStore(dataStore));

        RankData rk = new RankData();
        rk.recVote("WXYZ");
        rk.recVote("XYZW");

        System.out.println(rk.getRanking());

        int [] a = new int[]{1,2,3};
        int [] b = new int[]{1,2,3};

        System.out.println(Arrays.hashCode(a));
        System.out.println(Arrays.hashCode(b));

    }

}
