package zyz.steve.tree;

import zyz.steve.datastruct.AVLTNode;
import zyz.steve.datastruct.TreeNode;

public class AVL {
    /**
     * AVL insert
     * 1. 就简单插入
     * 2. fix AVL 以便于符合 要求的AVL property
     *
     *      O
     *     /
     *    O
     *   /
     *  O
     */

    public AVLTNode rightRotate(AVLTNode node){
        AVLTNode left = node.left;//
        node.left = left.right;// 将原左节点的 右子树 挂到 node的 左侧
        left.right = node;//将 旋转轴节点 挂到 原 left 的右侧
        updateHeight(node);
        updateHeight(left);
        return left;
    }
    public AVLTNode leftRotate(AVLTNode node){
        AVLTNode right = node.right;//
        node.right = right.left;// 将原左节点的 右子树 挂到 node的 左侧
        right.left = node;//将 旋转轴节点 挂到 原 left 的右侧
        updateHeight(node);
        updateHeight(right);
        return right;
    }
    public static void inorderTraversal(AVLTNode root){
        if(root == null) return;
        inorderTraversal(root.left);
        System.out.println(root.data);
        inorderTraversal(root.right);

    }
    public void updateHeight(AVLTNode node){
//        if(node!=null) return node.getHeight();
        int left = node.left == null ? -1 : node.left.getHeight();
        int right
                = node.right == null ? -1 : node.right.getHeight();
        node.height = Math.max(left,right)+1;
    }
    public  int height(AVLTNode node){
        return node == null? -1:node.getHeight();
    }
    public int getBalanceFactor(AVLTNode node){
        int lHeight = height(node.left);// == null ? -1 : node.left.getHeight();
        int rHeight = height(node.right);// ==null?-1: node.right.getHeight();
        return lHeight - rHeight;
    }
    public AVLTNode insert(int key, AVLTNode root){
        if(root==null){
            AVLTNode node = new AVLTNode(key);
            node.height = 0;
            return node;
        }
        if(key < root.data){
            root.left = insert(key,root.left);
             updateHeight(root);
        }else if(key> root.data){
            root.right = insert(key,root.right);
            updateHeight(root);
        }else{
            System.out.println("key exists");
            return root;
        }
        return reBalance(root);
    }

    public AVLTNode reBalance(AVLTNode node){
        int bf = getBalanceFactor(node);
        if (bf > 1) {
            //左侧太高，不平衡，需要处理; right rotation
            if (getBalanceFactor(node.left) < 0) {//插入了左树的右侧 LR
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (bf < -1) {
            //右侧太高，left rotate
            if (getBalanceFactor(node.right) > 0) {//插入了右树的左侧 RL
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);

        }
        //if balanced, return directly
        return node;
    }
    public AVLTNode deleteKey( int key, AVLTNode root){
        return  deleteNode(key, root);
//        if(node == null) return null;
//        return
//                node;
//        updateHeight(node);
//        return reBalance(node);
    }
    private AVLTNode deleteNode(int key, AVLTNode root){

        if(root==null) return null;

        if (key > root.data) {
            root.right = deleteNode(key, root.right);
//            return root;
        } else if (key < root.data) {
            root.left = deleteNode(key, root.left);
            //            return root;
        }

        //找到，开始删除
        //左右子树至少有一个为空的情况
        else if (root.left == null) {
            root = root.right;
        } else if (root.right == null) {
            root = root.left;
        } else {
            //左右子树都不为空
            //使用后继进行替换
            //1.查找后继 - 即右子树的最小值
            AVLTNode rightMin = root.right;

            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            //2. 替换待删除节点
            root.data = rightMin.data;

            //到右侧，删除 rightMin 并更新待删除节点 的右子树
            root.right = deleteMin(root.right);
        }
        if(root != null) {
            updateHeight(root);
            root = reBalance(root);
        }
        return root;
    }
    private AVLTNode deleteMin(AVLTNode root){
        if (root.left == null) {//找到了最小值
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }
}
