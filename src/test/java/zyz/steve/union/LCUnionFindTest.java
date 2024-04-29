package zyz.steve.union;

import org.junit.Test;


public class LCUnionFindTest {
    @Test
    public void testEq() {
        String[] e = {"a==b","b==c","a==c"};

        System.out.println(LCUnionFind.equationsPossible(e));
        e = new String[] {"a==b","b!=c","c==a"};
        System.out.println(LCUnionFind.equationsPossible(e));

        int[][] isConnected = {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};

        System.out.println(LCUnionFind.findCircleNum(isConnected));
        System.out.println(LCUnionFind.findCircleNumDfs(isConnected));


    }

}