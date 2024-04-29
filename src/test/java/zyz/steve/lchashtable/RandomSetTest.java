package zyz.steve.lchashtable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomSetTest {
    @Test
    public void testRandomSet(){
        RandomisedSet rs = new RandomisedSet();
        System.out.println(rs.remove(0));
        System.out.println(rs.remove(0));
        System.out.println(rs.insert(0));
        System.out.println(rs.getRandom());
        System.out.println(rs.insert(10));
        System.out.println(rs.insert(11));
        System.out.println(rs.insert(15));
        System.out.println(rs.insert(16));
        System.out.println(rs.insert(11));

        System.out.println(rs.remove(15));
        //        System.out.println(rs.insert(2));

        //        System.out.println(rs.getRandom());
        //        System.out.println(rs.remove(-1));
        //        System.out.println(rs.remove(-2));

    }

    @Test
    public void testRandom(){
        System.out.println((int) (Math.random()*2));
    }
//    @Test
//    public void testList(){
//       RandomisedSet rss = new RandomisedSet();
//        for (int i = 0; i < action.length; i++) {
//            if (action[i].equals("insert")) {
//                rss.insert(nums[i]);
//            } else if ("remove".equals(action[i])) {
//                rss.remove(nums[i]);
//            } else {
//                //getRandom
//                rss.getRandom();
//            }
//        }
//    }

}