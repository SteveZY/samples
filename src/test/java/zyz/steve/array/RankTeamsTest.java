package zyz.steve.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankTeamsTest {

    @Test
    public void testRankTeams() throws ExecutionException, InterruptedException {
        int numReqs = 3;
        ExecutorService executors = Executors.newFixedThreadPool(numReqs);
        int loops = 100;

        RankTeams.RankDataStore ds = new RankTeams.RankDataStore();
        List<String> mlist = mock(List.class);

        RankTeams.VotesForATeam votesTeam = new RankTeams.VotesForATeam(4, 'A');
//        votesTeam.getData()[1]++;
        while (loops>0) {
            when(mlist.get(0)).thenReturn("WXYZ","XYZW","ZYXW");
            CompletableFuture<?>[] fs = IntStream.range(0, numReqs).mapToObj((i) -> CompletableFuture.runAsync(() ->
                            ds.receiveVote(mlist.get(0)),executors))
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(fs).get();

            String ranking = RankTeams.rankTeamsFromDataStore(ds);

            Assert.assertEquals("XZWY",ranking);
            loops--;
        }

    }

}