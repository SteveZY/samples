package zyz.steve.generalquestions.movieschedule;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ScheduleTest {
    Movie m = new Movie("a movie",90, 200);
    Movie mLOT = new Movie("Lord Of The Rings",120,600);
    Movie mBTF = new Movie("Back To The Future",120,500);
    Screening s1 = new Screening(mLOT,660);
    Screening s2 = new Screening(mLOT,840);
    Screening s3 = new Screening(mBTF,1020);
    Screening s4 = new Screening(mLOT,1200);
    List<Screening> screenings = Arrays.asList(s1,s2,s3,s4);

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void test() {
//        Movie m = new Movie("a movie",90, 200);
//        Movie mLOT = new Movie("Lord Of The Rings",120,600);
//        Movie mBTF = new Movie("Back To The Future",120,500);
//        Screening s1 = new Screening(mLOT,660);
//        Screening s2 = new Screening(mLOT,840);
//        Screening s3 = new Screening(mBTF,1020);
//        Screening s4 = new Screening(mLOT,1200);
//        List<Screening> screenings = Arrays.asList(s1,s2,s3,s4);

        Solution solution = new Solution();
        System.out.println(solution.canSchedule(m, screenings));

        solution.showAvailableScreening(screenings);
        System.out.println(solution.totalRevenue(screenings));

        System.out.println(solution.planScreening(m, screenings));
    }
    @Test
    public void testCanScheduleWhenMovieLongerThan13H() {
        Movie m13h = new Movie("13h movie",3000,600);
        // sut
        Solution sut = new Solution();
        System.out.println(sut.canSchedule(m13h, screenings));

        System.out.println(sut.canSchedule(m, Lists.newArrayList()));
    }
}

