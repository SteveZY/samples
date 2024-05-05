package zyz.steve.tree;

import org.junit.Test;


public class SegmentTreeTest {
    @Test
    public void testSegTreeBuild(){
    int [] nn = {9,3,1,2};
        SegmentTree st = new SegmentTree(nn);

//        System.out.println(st);

        System.out.println(st.rangeQuery(0, 2, 0, 3, 0));
    }

}