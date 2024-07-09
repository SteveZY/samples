package zyz.steve.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class RankTeamsWithPropDSTest {
    @Test
    public void testGetRanking(){
        RankTeamsWithPropDS scoreboard = new RankTeamsWithPropDS();

        scoreboard.receiveVote("WXYZ");
        scoreboard.receiveVote("XYZW");

        System.out.println(scoreboard.getRanking());

    }

}