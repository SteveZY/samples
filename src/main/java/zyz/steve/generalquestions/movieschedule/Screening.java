package zyz.steve.generalquestions.movieschedule;

public record Screening(Movie movie, int startTime
) {
    int endTime() {
        return startTime + movie.duration();
    }
}
