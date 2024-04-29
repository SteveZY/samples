package zyz.steve.tree;

import org.junit.Test;
import zyz.steve.datastruct.RBTNode;

import static org.junit.Assert.*;

public class ChangeRootBT1666Test {

    @Test
    public void flipBinaryTree() {
        RBTNode n3 = new RBTNode(3);
        RBTNode n5 = new RBTNode(5);
        RBTNode n1 = new RBTNode(1);
        RBTNode n6 = new RBTNode(6);
        RBTNode n2 = new RBTNode(2);
        RBTNode n7 = new RBTNode(7);
        RBTNode n4 = new RBTNode(4);

        n3.left=n5;n3.right=n1;
        n5.left=n6;n5.right=n2;n5.parent=n3;
        n6.parent=n5;
        n2.left=n7;n2.right=n4;n2.parent=n5;
        n7.parent=n2;n4.parent = n2;

        n1.parent = n3;

        RBTNode ret = ChangeRootBT1666.flipBinaryTree(n3, n7);
        System.out.println(ret);


         n1 = new RBTNode(1);
//        RBTNode n6 = new RBTNode(6);
         n2 = new RBTNode(2);
         n1.right = n2;n2.parent=n1;

        System.out.println(ChangeRootBT1666.flipBinaryTree(n1, n2));

    }
}