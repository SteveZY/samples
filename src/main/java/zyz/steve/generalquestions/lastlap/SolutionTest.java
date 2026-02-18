package zyz.steve.generalquestions.lastlap;

import org.junit.Test;

public class SolutionTest {
    @Test
    public void testDriver3AsTheWinner() {
        // setup
        Solution solution = new Solution();
        solution.lapForDriver("driver1", 100);
        solution.lapForDriver("driver2", 90);
        solution.lapForDriver("driver3", 70);
        solution.lapForDriver("driver1", 110);
        solution.lapForDriver("driver2", 95);
        solution.lapForDriver("driver3", 50);

        // test
        System.out.println(solution.getLastLapWinner());
    }
    @Test
    public void testNoWinners() {
        // setup
        Solution solution = new Solution();
        solution.lapForDriver("driver1", 100);
        solution.lapForDriver("driver2", 90);
        solution.lapForDriver("driver3", 70);
        solution.lapForDriver("driver1", 110);
        solution.lapForDriver("driver2", 95);
        solution.lapForDriver("driver3", 150);

        // test
        System.out.println(solution.getLastLapWinner());
    }
    @Test
    public void testDriver2AsTheWinnerWithPitStop() {
        Solution solution = new Solution();
        solution.lapForDriverWithPitStop("driver1", 100,false);
        solution.lapForDriverWithPitStop("driver2", 80, true);
        solution.lapForDriverWithPitStop("driver1", 120,true);
        solution.lapForDriverWithPitStop("driver2", 100, false);
        solution.lapForDriverWithPitStop("driver1", 130,false);
        solution.lapForDriverWithPitStop("driver2", 100, false);
        solution.lapForDriverWithPitStop("driver1", 110,false);
        solution.lapForDriverWithPitStop("driver2", 90, true);
        System.out.println(solution.getLastLapWinner());
    }
}
