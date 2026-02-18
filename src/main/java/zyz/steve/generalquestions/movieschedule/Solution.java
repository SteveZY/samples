package zyz.steve.generalquestions.movieschedule;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    final static int EARLIST_TIME = 600;
    final static int LATEST_TIME = 60 * 23;

    public boolean canSchedule(Movie movie, List<Screening> screenings) {
        if (screenings == null)
            throw new RuntimeException("illegal screenings");
        int endOfLastScreening = EARLIST_TIME;
//        int startOfNextScreening = LATEST_TIME;
        for (int i = 0; i < screenings.size(); i++) {
            Screening screening = screenings.get(i);
            int startOfNextScreening = screening.startTime();
            if (isFit(movie, endOfLastScreening, startOfNextScreening)) {
                return true;
            }
            endOfLastScreening = screening.endTime();
        }

        return isFit(movie, endOfLastScreening, LATEST_TIME);

    }

    private boolean isFit(Movie movie, int start, int end) {
        return start + movie.duration() <= end;
    }

    public int totalRevenue(List<Screening> screenings) {
        int revenue = 0;
        for (Screening screening : screenings) {
            revenue += screening.movie().revenue();
        }
        return revenue;
    }

    // planScreening returns a movie to remove with the least impact to the revenue
    public Screening planScreening(Movie newMovie, List<Screening> screenings) {
        Screening screeningToRemove = null;
        int curRevenue = 0;
        for (Screening screening : screenings) {
            List<Screening> alist = removeOne(screening, screenings);
            if (canSchedule(newMovie, alist)) {
                int revenue = totalRevenue(alist);
                if (revenue > curRevenue) {
                    curRevenue = revenue; // update max revenue after one removal
                    screeningToRemove = screening;
                }
                ;
            }
            ;
        }
        return screeningToRemove;
    }

    private List<Screening> removeOne(Screening screening, List<Screening> screenings) {
        return screenings.stream().filter(screening1 -> !screening1.equals(screening))
                .collect(Collectors.toList());
    }

    void showAvailableScreening(
            List<Screening> screenings) {
        int endOfLast = EARLIST_TIME;
        for (int i = 0; i < screenings.size(); i++) {
            Screening screening = screenings.get(i);
            System.out.printf("start: %4d, end:%4d, duration: %3d\n", endOfLast,
                    screening.startTime(),
                    screening.startTime() - endOfLast);
            endOfLast = screening.endTime();
        }
        System.out.printf("start: %4d, end:%4d, duration: %3d\n", endOfLast,
                LATEST_TIME,
                LATEST_TIME - endOfLast);
    }
}
