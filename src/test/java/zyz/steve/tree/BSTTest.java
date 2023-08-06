package zyz.steve.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;

public class BSTTest {
//    BST.
    @Before
    public void setup(){
        BST.root = null;
        BST.initTree(new int[] {5, 4, 2, 6, 3, 7,1});
    }
    @Test
    public void testInit(){
        BST.initTree(new int[] {5, 1, 2, 6, 3, 7});
        Assert.assertEquals(2, BST.root.left.right.val);
    }
    @Test
    public void testInOrder(){
        BST.inOrder(BST.root);
    }
    @Test
    public void testInOrderReversed(){
        BST.inOrderReversed(BST.root);
    }
    @Test
    public void testPreOrder(){

        BST.preOrder(BST.root);
    }
    @Test
    public void testPostOrder(){
        BST.postOrder(BST.root);
    }
    @Test
    public void testJavaStack(){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(2);
//        stack.push(null);
    }
    @Test
    public void testPreOrderNonRecr(){
        BST.preOrder(BST.root);
        BST.preOrderNonRecursive(BST.root);
    }
    @Test
    public void testInOrderNonRecr(){
//        BST.preOrder(BST.root);
        BST.inOrderNonRecursive(BST.root);
    }
    @Test
    public void testLevelOrderTraversal(){
        BST.levelOrder(BST.root);
    }

}