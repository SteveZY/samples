package zyz.steve.lchashtable;

import org.junit.Test;
import zyz.steve.datastruct.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class LCHashTableTest {

    @Test
    public void isIsomorphic() {
        String s = "badc";
        String t = "baba";
        boolean ans = LCHashTable.isIsomorphic(s, t) && LCHashTable.isIsomorphic(t,s);
        System.out.println(ans);

        ans = LCHashTable.isIsomorphicII(s,t);
        System.out.println(ans);
    }
    @Test
    public void testFindRestaurant(){
        String[] list1 = {"happy","sad","good"};
        String[] list2 = {"sad","happy","good"};

        String[] ans = LCHashTable.findRestaurant(list1, list2);
        System.out.println(Arrays.toString(ans));
    }
    @Test
    public void testFindUniqChar(){
        String a = "hhaappyy";
        System.out.println(LCHashTable.firstUniqChar(a));
    }
    @Test
    public void testIntersect(){
        int[]a ={1,2,2,1},
                b={2,2,2,3,3,1};
        int[] ans = LCHashTable.intersect(a, b);
        System.out.println(Arrays.toString(ans));
    }
    @Test
    public void testNerbyDups(){
        int[]a ={1,2,3,4,5,2,1};
        assertFalse(LCHashTable.containsNearbyDuplicate(a, 1));
        assertTrue(LCHashTable.containsNearbyDuplicate(a, 4));

        a= new int[]{1,2,3,1,2,3};
        assertFalse(LCHashTable.containsNearbyDuplicate(a, 2));
    }
    @Test
    public void testValidSodoku(){
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] b = {{'8','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(LCHashTable.isValidSudoku(b));
    }

    @Test
    public void testGroupAnagrams(){
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ret = LCHashTable.groupAnagrams(strs);
        System.out.println(ret);
    }

    @Test
    public void testDuplicateSubTrees(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);
        HashMap<String, Integer> map = new HashMap<>();
        List<TreeNode> nodes = new ArrayList<>();
        LCHashTable.findDuplicateSubtrees(root,map,nodes);
        System.out.println(map);
    }

    @Test
    public void testLengthOfLongestSubstring(){
        String str = "abbac";
//        System.out.println
//        (LCHashTable.lengthOfLongestSubstring(str));

        System.out.println
                (LCHashTable.lengthOfLongestSubstringDP(str));
    }

    @Test
    public void testTopK(){
        int [ ] a = {1,1,1,2,2,3,6,8,3,2,3,4,5,6,7};
        System.out.println
        (Arrays.toString(LCHashTable.topKFrequent(a, 3)));
    }
}