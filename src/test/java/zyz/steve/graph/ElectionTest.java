package zyz.steve.graph;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ElectionTest {
    @Test
    public void testBuildTree(){
        int [][] adj ={{1,2,2},{2,3,1}};
        Election.buildTree(3,adj);
        Arrays.stream(Election.adj).forEach(e-> System.out.println(System.identityHashCode(e)));
    }
    @Test
    public void testGetMinElectedNodes(){
        int [][] adj ={{1,2,2},{2,3,1},{1,4,2},{4,5,1},{4,6,2}};
        Election.buildTree(6,adj);

//        Election.buildTree();
        Election.dfs(1,0);
        Integer[] xxx = Election.ans.get(1);
        assertEquals(6L, (long)xxx[0]);
//        System.out.println(Election.ans);
    }

}