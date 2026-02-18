package zyz.steve.generalquestions.lastlap;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class Solution
{
//    DriverLap
    Map<String, LapStats> driverStats = new HashMap<>();
    public void lapForDriver ( String driver, int time){

        LapStats stats =
        driverStats.computeIfAbsent(driver, k -> new LapStats());
        stats.addLap(time);
    }
    public void lapForDriverWithPitStop ( String driver, int time, boolean pitStop){

        LapStats stats =
                driverStats.computeIfAbsent(driver, k -> new LapStats());
        stats.addLap(time, pitStop);
    }
    public String getLastLapWinner(){
        String winner = null;
        int improvement = 0;
        for(Map.Entry<String, LapStats> e: driverStats.entrySet())
        {
            int curImprovement = e.getValue().getLastLapImprovement();
            if (curImprovement < improvement){
                improvement = curImprovement;
                winner=e.getKey();
            }
        }
        return winner;
    }
}
