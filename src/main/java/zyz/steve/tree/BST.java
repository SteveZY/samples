package zyz.steve.tree;

import zyz.steve.datastruct.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Stream;

public class BST {
    static TreeNode root;
    public  static void initTree(int[] a){
        //                put(root, e)
        Arrays.stream(a).forEach(e->put(root, e));
    }
    private static void put(TreeNode r, int a){
        if(r == null) {
            root = new TreeNode(a);
            return;
        }

        if(a>r.val){
            if(r.right ==null)r.right=new TreeNode(a);
            else put(r.right,a);
        }
        else if (a<r.val) {
            if(r.left == null) r.left = new TreeNode(a);
            else put(r.left, a);
        }

    }
    //先左节点，然后是父节点，然后再是右节点，中序遍历
    public static void inOrder(TreeNode r){
        if(null == r) return;
        inOrder(r.left);
        System.out.println(r.val);
        inOrder(r.right);
    }


    public static void inOrderNonRecursive(TreeNode r){
        //DFS
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
//        stack.push(r);
        TreeNode cur  = r;

        while (true){
//            cur = stack.pop();
//            System.out.println(cur.val);
            if(null != cur) {
                stack.push(cur);
                cur = cur.left;//先深入左侧
            }
            else  if (!stack.isEmpty()){
                //左侧已经到底了
                cur = stack.pop();
                //visit cur
                System.out.println(cur.val);
                cur = cur.right;

            }else { break;
            }
        }
    }
    public static void inOrderReversed(TreeNode r){
        if(null == r) return;
        inOrderReversed(r.right);
        System.out.println(r.val);
        inOrderReversed(r.left);
    }
    //父节点先于子节点，那么就是先序遍历
    public static void preOrder(TreeNode r){
        if(null == r) return;
        System.out.println(r.val);
        preOrder(r.left);
        preOrder(r.right);
    }
    public static void preOrderNonRecursive(TreeNode r){
        //DFS
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(r);
        TreeNode cur ;

        while (!stack.isEmpty()){
            cur = stack.pop();
            System.out.println(cur.val);
            if(null != cur.right) stack.push(cur.right);//right先入栈
            if(null != cur.left) stack.push(cur.left);//这样左侧先弹出
        }
    }
    //先左后右 再 父。 父节点在最后，后序遍历
    public static void postOrder(TreeNode r){
        if(null == r) return;
        postOrder(r.left);
        postOrder(r.right);
        System.out.println(r.val);
    }
    public static void levelOrder(TreeNode r){
        //BFS 从上到下，从左到有
        ArrayDeque<TreeNode> fifo = new ArrayDeque<>();

        fifo.add(r);
        while (!fifo.isEmpty()) {
            TreeNode cur = fifo.remove();
            System.out.println(cur.val);//visit current
            if (null != cur.left)
                fifo.add(cur.left);
            if (null != cur.right)
                fifo.add(cur.right);
        }
    }
}
