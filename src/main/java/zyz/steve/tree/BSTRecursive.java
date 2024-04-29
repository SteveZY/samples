package zyz.steve.tree;

import zyz.steve.datastruct.TreeNode;

public class BSTRecursive {
    public TreeNode insertNode(int key, TreeNode root){
        if(root ==null) {
             return new TreeNode(key);
//            return;
        }
        if(key<root.val) {
            root.left = insertNode(key,root.left);
        }
        else if(key>root.val){
            root.right = insertNode(key,root.right);
        }else {
            System.out.println("the given key exists");
        }
        return root;
    }
    public TreeNode search(int key,TreeNode root){
        if(root==null) return null;
        if(key>root.val)
            return search(key, root.right);
        else if(key < root.val)
            return search(key, root.left);

        return root;
    }

    public TreeNode deleteNode(int key, TreeNode root){

        if(root==null) return null;

        if (key > root.val) {
            root.right = deleteNode(key, root.right);
            return root;
        } else if (key < root.val) {
            root.left = deleteNode(key, root.left);
            return root;
        }

        //找到，开始删除
        //左右子树至少有一个为空的情况
        if(root.left == null) return root.right;
        if(root.right==null) return root.left;

        //左右子树都不为空
        //使用后继进行替换
        //1.查找后继 - 即右子树的最小值
        TreeNode rightMin = root.right;

        while(rightMin.left != null){
            rightMin = rightMin.left;
        }
        //2. 替换待删除节点
        root.val = rightMin.val;

        //到右侧，删除 rightMin 并更新待删除节点 的右子树
        root.right = deleteMin(root.right);
        return root;
    }
    private TreeNode deleteMin(TreeNode root){
        if (root.left == null) {//找到了最小值
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }


}
