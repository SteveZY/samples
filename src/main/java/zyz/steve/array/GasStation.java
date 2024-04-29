package zyz.steve.array;

//#134: https://leetcode.com/problems/gas-station/
public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int numOfStations = gas.length;
        int i =0,startStartion =0;
        int totalGas=0, totalCost=0;
        int counter =0;
        while (i<numOfStations){
            totalGas +=gas[i];
            totalCost +=cost[i];
            if(totalGas>=totalCost){
                //too many mod calculation, slow
                if((i+1) % numOfStations == startStartion) return startStartion;
                i = (++i)%numOfStations;

            }else {
                //reset
                if(counter >= numOfStations ) return -1;
                i=++i%numOfStations;
                startStartion = i;
                totalGas = 0;
                totalCost = 0;
                counter++;
            }
        }
        return -1;
    }
    public static int canCompleteCircuitOp(int[] gas, int[] cost) {
        int numOfStations = gas.length;
        int i =0;//i is where we start
        while (i<numOfStations){
            int totalGas=0, totalCost=0;
            int counter =0;

            while (counter < numOfStations){
                int j = (i+counter)%numOfStations;
                totalCost+=cost[j];
                totalGas += gas[j];
                if(totalGas<totalCost) break;

                counter++;
            }
            if(counter ==numOfStations) return i;
            else i=i+counter+1;
        }
        return -1;
    }
}
