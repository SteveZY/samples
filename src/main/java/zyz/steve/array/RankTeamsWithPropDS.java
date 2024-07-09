package zyz.steve.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class RankTeamsWithPropDS {
    class TeamScore implements Comparable<TeamScore>{

        private final  int[] score;
        private final Character team;

//        byte []xx

        TeamScore(int len, char t) {
            score = new int[len];
            team = t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TeamScore teamScore = (TeamScore) o;
            if (!Arrays.equals(score, teamScore.score)) {
                return false;
            }
            return team.equals(teamScore.team);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(score);
            result = 31 * result + team.hashCode();
            return result;
        }

        @Override
        public int compareTo(TeamScore o) {
            for (int i = 0; i < o.score.length; i++) {
                if (score[i]!= o.score[i]) {
                    return o.score[i] - score[i];
                }
            }
            return team.compareTo(o.team);
        }
    }

    class VotesForTeam {

        private final List<Integer> data;

        private final Character team;

        public List<Integer> getData() {
            return data;
        }

        public VotesForTeam(int len, char t) {
            Integer[] aaa = new Integer[len];
            Arrays.fill(aaa, 0);
            data = Arrays.asList(aaa);
            team = t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            VotesForTeam that = (VotesForTeam) o;

            if (!data.equals(that.data)) {
                return false;
            }
            return team.equals(that.team);
        }

        @Override
        public int hashCode() {
            int result = data.hashCode();
            result = 31 * result + team.hashCode();
            return result;
        }
    }

    final private Map<Character, VotesForTeam> teamScoreMap;

    public RankTeamsWithPropDS() {
        this.teamScoreMap = new HashMap<>();
//        Comparator<VotesForTeam> xxxx = new Comparator<VotesForTeam>() {
//
//            @Override public int compare(VotesForTeam o1, VotesForTeam o2) {
//                return 0;
//            }
//        };
        comparator = new Comparator<VotesForTeam>() {

            @Override public int compare(VotesForTeam o1, VotesForTeam o2) {
                VotesForTeam vt1 = (VotesForTeam) o1;
                VotesForTeam vt2 = (VotesForTeam) o2;
                for (int i = 0; i < vt1.data.size(); i++) {
                    if (!vt1.data.get(i).equals(vt2.data.get(i))) {
                        return vt2.data.get(i).compareTo(vt1.data.get(i));
                    }
                }
                return vt1.team.compareTo(vt2.team);
            }
        };
    }

    public void receiveVote(String s) {//O(V*N)
        //仅仅加入 到 hashmap ，排序留到 getRanking的 时候
        for (int i = 0; i < s.length(); i++) {
            char t = s.charAt(i);
            //非原子修改
//            teamScoreMap.putIfAbsent(t, new VotesForTeam(s.length(), t));
//            List<Integer> score = teamScoreMap.get(t).data;
//            score.set(i, score.get(i) + 1);

            //原子的修改，这两种都可以
            int finalI = i;
            teamScoreMap.compute(t, (k,v)->{
                VotesForTeam sc = null==v? new VotesForTeam(s.length(),t):v;
                List<Integer> votes = sc.data;
                votes.set(finalI,votes.get(finalI)+1);
                return sc;
            });
        }

    }
    Comparator<VotesForTeam> comparator;

    public String getRanking() {
        StringBuilder sb = new StringBuilder();

//        List<TeamScore> teamScores = new ArrayList<>();
        List<VotesForTeam> scores = new ArrayList<>(teamScoreMap.values());
//        Collections.sort(scores);
        scores.sort(comparator);
        //或者加入到 TreeSet
        Set<VotesForTeam> vSet = new TreeSet<>(comparator);
        vSet.addAll(teamScoreMap.values());
        //或者PQ
        PriorityQueue<VotesForTeam> pq = new PriorityQueue<>(comparator);
//        PriorityQueue<TeamScore> pq1 = new PriorityQueue<>();
        pq.addAll(teamScoreMap.values());
//        for (VotesForTeam s : scores) {
//            sb.append(s.team);
//        }

//        for (VotesForTeam votesForTeam : pq) {
//            sb.append(votesForTeam.team);
//        }
        for(VotesForTeam s:vSet){
            sb.append(s.team);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Integer> l2 = Arrays.asList(1, 2, 3);
        System.out.println(l1.hashCode());
        System.out.println(l2.hashCode());

        System.out.println(l1.equals(l2));

        Integer[] iii = new Integer[10];
        Stream.of(iii).forEach(System.out::println);
    }

}
