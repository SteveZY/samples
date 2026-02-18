package zyz.steve.aaaa;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

class TimeInterval <T> {
    private  T interval;
    public TimeInterval(T t){
        this.interval = t;
    }
    public T getInterval(){
        return interval;
    }
}
class AgentScore {
    String name;
    String id;
    int count;
    long totalScore;

    AgentScore(String name) {
        this.name = name;
    }

    public void updateTotalScore(long score) {
        totalScore += score;
        count++;

    }

    public float getAverageScore() {
        return (float) totalScore / count;
    }

    @Override
    public String toString() {
        return String.format("%s:%f", name, getAverageScore());
    }
}

public class RatingService {
    private Map<String, AgentScore> mapAgentScore = Maps.newHashMap();
    private int maxScore = 5;
    private int minScore;
    final double THRESHOLD = .0001;
    private Map<YearMonth, Map<String,AgentScore>> mapYearMonthScore = Maps.newHashMap();
    public RatingService(int max, int min) {
        this.maxScore = max;
        this.minScore = min;
    }

    public void getRating(int year, int num, TemporalUnit temporalUnit) {
        String key = String.format("%d%d", year, num);
        LocalDateTime dt = LocalDateTime.now();
        dt.plus(2, ChronoUnit.DAYS);
    }
    public void receiveRating(YearMonth yearMonth, String name, long score) {
        if (score < minScore || score > maxScore) return;
        Map<String, AgentScore> agentScores = mapYearMonthScore.computeIfAbsent(yearMonth, k -> Maps.newHashMap());
        agentScores.computeIfAbsent(name, k -> new AgentScore(name)).updateTotalScore(score);

    }
    public List<AgentScore> getRatings(YearMonth yearMonth) {
        Map<String, AgentScore> agentScores = mapYearMonthScore.get(yearMonth);
        if (agentScores == null) {
            return null;
        }
        ArrayList<AgentScore> list = new ArrayList<>(agentScores.values());
        list.sort((o1, o2) -> {
            float diff = o2.getAverageScore() - o1.getAverageScore();
            return Math.abs(diff) < THRESHOLD ?
                    o1.name.compareTo(o2.name) :
                    diff > 0 ? 1 : -1;
        });
        return list;
    }
    public List<AgentScore> getRatings() {

        List<AgentScore> list = Lists.newArrayList(mapAgentScore.values());
        list.sort((o1, o2) -> {
            float diff = o2.getAverageScore() - o1.getAverageScore();
            return Math.abs(diff) < THRESHOLD ?
                    o1.name.compareTo(o2.name) :
                    diff > 0 ? 1 : -1;
        });
//    list.sort()
        return list;
    }

    public RatingService() {
    }

    public void receiveRating(String name, long score) {
        if (score < minScore || score > maxScore) return;
        mapAgentScore.computeIfAbsent(name, AgentScore::new).updateTotalScore(score);

    }

    public static void main(String[] args) {

        long l = 0x8l;
        System.out.println(String.format("0x%x",l));
        System.out.println(Long.numberOfTrailingZeros(0x6L | 0x800000000L));
        System.out.println(String.format("%x",(long)Math.pow(2, 56)));
        RatingService ratingService = new RatingService();
        ratingService.receiveRating("aaa", 5);
        ratingService.receiveRating("aaa", 2);
        ratingService.receiveRating("aaa", 3);

        ratingService.receiveRating("1bbb", 5);
        ratingService.receiveRating("1bbb", 2);
        ratingService.receiveRating("1bbb", 3);


        System.out.println(ratingService.getRatings());


        YearMonth ym = YearMonth.of(2024,12);
        ratingService.receiveRating(YearMonth.now(),"1bc", 5);
        ratingService.receiveRating(YearMonth.now(),"1bc", 5);
        ratingService.receiveRating(YearMonth.now(),"1bc", 4);

        ratingService.receiveRating(YearMonth.now(),"2bc", 5);
        ratingService.receiveRating(YearMonth.now(),"2bc", 5);
        ratingService.receiveRating(YearMonth.now(),"2bc", 5);

        System.out.println(ratingService.getRatings(YearMonth.now()));

        ratingService.receiveRating(ym,"1bc", 5);
        ratingService.receiveRating(ym,"1bc", 5);
        ratingService.receiveRating(ym,"1bc", 4);

        ratingService.receiveRating(ym,"2bc", 5);
        ratingService.receiveRating(ym,"2bc", 5);
        ratingService.receiveRating(ym,"2bc", 2);

        System.out.println(ratingService.getRatings(ym));


    }
}
