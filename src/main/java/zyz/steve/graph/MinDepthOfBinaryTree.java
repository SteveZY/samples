package zyz.steve.graph;

//LC 111 https://leetcode.cn/problems/minimum-depth-of-binary-tree/


import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

// Definition for a binary tree node.
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
 // BFS
public class MinDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        // BFS 使用 Q 来保存 adj 元素
        Set<TreeNode> visited = new HashSet<>();
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        if(null != root) q.offer(root);
        int depth = 1;
        while (!q.isEmpty()){
            int ss = q.size();
            for (int i = 0; i < ss; i++) {
                TreeNode ele = q.poll();
                if(visited.contains(ele))continue;
                visited.add(ele);
                if(null == ele.left && null == ele.right) {
                    return depth;
                }
                if(null != ele.left) q.offer(ele.left);
                if(null != ele.right) q.offer(ele.right);
            }
            depth++;
        }
        return 0;
    }
    public int minDepthRecursivelyDFS(TreeNode root){
        // 这个性能 有可能不如上面的好，特别是当 不平衡的树，有很深的子树的情况下
        // 基本逻辑：分别查看 左右 子树的 最小 深度，然后取最小 再加上当前 节点的深度 1 后，返回

        if (null == root) return 0;// 退出条件
//        if (null == root.left) return minDepthRecursivelyDFS(root.right) +1; // check right
//        if (null == root.right) return minDepthRecursivelyDFS(root.left) +1;// no left sub tree, check left
        return Math.min(minDepthRecursivelyDFS(root.left), minDepthRecursivelyDFS(root.right)) + 1;
    }

    public static void main(String[] args){
        MinDepthOfBinaryTree sol = new MinDepthOfBinaryTree();
        TreeNode l15 = new TreeNode(15);
        TreeNode r7 = new TreeNode(7);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20,l15, r7);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(sol.minDepth(root));

        System.out.println(sol.minDepthRecursivelyDFS(root));
    }
}
