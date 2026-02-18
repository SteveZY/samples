package zyz.steve.generalquestions.lastlap;

public class LapStats {
    private int totalTime, laps, lastLapTime;
    public void addLap( int laptime, boolean isPitStop )
    {
        if( !isPitStop)
        {
            laps++;
            totalTime += laptime;
        }
        lastLapTime = laptime;
//        addLap( laptime );
    }
    public void addLap( int laptime ){
        laps++;
        totalTime += laptime;
        lastLapTime = laptime;
    }
    public int getTotalTime() {
        return totalTime;
    }
    public int lastLapTime() {
        return laps;
    }
    public int getAvgLapTime() {
        return totalTime / laps;
    }
    public int getLastLapImprovement() {
        return lastLapTime - getAvgLapTime();
    }

}
