package zyz.steve.tree;

import zyz.steve.datastruct.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;

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
            System.out.println(cur.val);//探查本节点
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

    static int countNodes(TreeNode root) {
        if(root == null) return 0;
        if( root.left == null  && root.right ==null) return 1;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    //invert a binary tree 翻转二叉树
    // https://leetcode.com/problems/invert-binary-tree/description/
    //https://www.baeldung.com/java-reversing-a-binary-tree
    //递归 recursively
    static TreeNode invertTree(TreeNode node){
        if(node == null) {
            //when it's a leaf, return directly
            return null;
        }
        TreeNode temp = node.right;
        node.right = invertTree(node.left);
        node.left = invertTree(temp);
        return node;
    }
    //迭代方式
    //从顶向下
    static void invertTreeIteratively(TreeNode root){
        //递归的方法改成迭代，就需要手动维护一个栈，或者 Q 之类的
//        List<TreeNode> queue = new LinkedList<>();

        LinkedList<TreeNode> q=new LinkedList<>();
        if(null != root){
            q.add(root);

        }
        while (!q.isEmpty()){
            //拿出在q 里面呆的最久的 节点
            TreeNode nodeTobeReversed = q.poll();
            if (nodeTobeReversed.left !=null) q.push(nodeTobeReversed.left);
            if (nodeTobeReversed.right !=null) q.push(nodeTobeReversed.right);
            TreeNode temp = nodeTobeReversed.left;
            nodeTobeReversed.left = nodeTobeReversed.right;
            nodeTobeReversed.right=temp;

        }
    }

    static void invertTreeLevelIndependently(TreeNode root){
        ArrayDeque<TreeNode>[] fifo = new ArrayDeque[] {new ArrayDeque<>(), new ArrayDeque<>()};
//        fifo[0] = new ArrayDeque<>();
//        fifo[0] = new ArrayDeque<>();
        fifo[0].add(root);

        //loop 非空的那个Q
        ArrayDeque<TreeNode> q = fifo[0];
        ArrayDeque<TreeNode> qtofill = fifo[1];
        while (!q.isEmpty()){
            TreeNode node = q.poll();
            if(node.left !=null) qtofill.add(node.left);
            if(node.right !=null) qtofill.add(node.right);
            if(q==fifo[0]) {//子 树需要对换
                //

                //reverse it's children
                TreeNode tmp = node.left;
                node.left=node.right;
                node.right=tmp;
            }
            if(q.isEmpty()) {
                if (fifo[0].isEmpty()) {
                    q = fifo[1];
                    qtofill = fifo[0];
                } else {
                    q = fifo[0];
                    qtofill = fifo[1];
                }
            }
        }

    }
    static boolean find(int a, TreeNode r){
        if (r == null) {
            return false;
        }
        if (a > r.val) {
            return find(a, r.right);
        } else if (a < r.val) {
            return find(a, r.left);
        } else {
            return true;
        }
//        return false;
    }

    //MSFT 面试题
    static TreeNode findInOrderSuccessor(int a, TreeNode r) {
        if (r == null) {
            return null;
        }
        //在左子树中查找
        TreeNode scInLeft = findInOrderSuccessor(a, r.left);
        if (scInLeft != null) {//找到
            return scInLeft;
        } else if (r.val > a) {//不在左侧，看当前root是不是
            //这里是关键，中序后继 是大于某给节点 的 所有节点的最小值
            //一旦 左子树没有，那么root 就可能成为 后继
            return r;
        }
        //当前root也不是大于它，那么说明 待找节点比较大 需要从 右子树找答案了
        return findInOrderSuccessor(a, r.right);
    }

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
    //Entry
    static public String serialize(TreeNode r){
        StringBuilder sb = new StringBuilder();
        serialize(r, sb);
        return sb.toString();
    }
    static void serialize(TreeNode r, StringBuilder sb){
        //前序遍历 pre order
        if(r==null) {
            sb.append("n,");
            return;
        }
        sb.append(r.val).append(",");
        serialize(r.left,sb);
        serialize(r.right, sb);


    }

    public static  TreeNode deserialize(String s){
        String[] a = s.split(",",0);
        int[] index = new int[] {0};
        return deserialize(a,index);
    }
    static TreeNode deserialize(String[] a, int[] c){
        if(c[0] == a.length || a[c[0]].equals("n")){
            c[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(a[c[0]++]));
        node.left=deserialize(a,c);
        node.right=deserialize(a,c);
        return node;
    }
}
