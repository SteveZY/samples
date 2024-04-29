package zyz.steve.tree;

import org.junit.Test;
import zyz.steve.datastruct.TreeNode;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BSTIterativeTest {

    int[] initData = {9, 8, 4, 3, 2, 7, 10, 11, 56, 57};

//    void setup() {
//
//    }
    @Test
    public void testInsertNode(){
        BSTIterative tree = new BSTIterative();
        Arrays.stream(initData).forEach(
                tree::insertNode
        );
        tree.inorderTraversal(tree.root);
//        System.out.println( tree);
    }
    @Test
    public void testDelete(){
        BSTIterative tree = new BSTIterative();
        Arrays.stream(initData).forEach(
                tree::insertNode
        );
        tree.inorderTraversal(tree.root);
        tree.deleteNode(11);
        tree.inorderTraversal(tree.root);

        assertTrue(tree.isValidBst(null));
    }
    @Test
    public void testValidBst(){
        TreeNode root = new TreeNode(-2147483648);
        root.left = new TreeNode(-2147483648);
        BSTIterative tree = new BSTIterative();
        assertFalse(tree.isValidBstWithSpecifiedMinMax(root));

        assertTrue(tree.isValidBstWithSpecifiedMinMax(root.left));

        assertTrue(tree.isValidBstWithSpecifiedMinMax(new TreeNode(2147483647)));
    }
    @Test
    public void testInsertRecursive(){
        BSTRecursive bstRecursive = new BSTRecursive();
        TreeNode root = null;
        for(int ele:initData){
            root=bstRecursive.insertNode(ele,root);
        }
        BSTIterative tree = new BSTIterative();
        tree.inorderTraversal(root);
//        assertTrue(tree.isValidBstWithSpecifiedMinMax(root));
//        Arrays.stream(initData).forEach(e->bstRecursive.insertNode(e,null));
        assertEquals(10,
                bstRecursive.search(10, root).val);

        assertNull(bstRecursive.search(90,root));

        bstRecursive.deleteNode(2, root);
        bstRecursive.deleteNode(3, root);
        tree.inorderTraversal(root);

    }

}