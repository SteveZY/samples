package zyz.steve.tree;

import org.junit.Test;
import zyz.steve.datastruct.AVLTNode;

import static org.junit.Assert.*;

public class AVLTest {
    int[] initData = {9, 8, 4, 3, 2, 57, 10, 11};

    //    void setup() {
    //
    //    }
    @Test
    public void testInsertNode(){
        initData = new int[]{8,5,10,3,7,6};

        AVL tree = new AVL();
        AVLTNode root = null;
        for(int ele:initData){
            root=tree.insert(ele,root);
        }
        AVL.inorderTraversal(root);
        assertEquals(2, root.getHeight());
        assertEquals(7, root.data);
        //        System.out.println( tree);
    }
    @Test
    public void testDeleteNode(){
        initData = new int[]{8,5,10,6,9,11,12};
        AVL tree = new AVL();
        AVLTNode root = null;
        for(int ele:initData){
            root=tree.insert(ele,root);
        }
        AVLTNode newRoot = tree.deleteKey(6, root);
        AVL.inorderTraversal(newRoot);
    }

}