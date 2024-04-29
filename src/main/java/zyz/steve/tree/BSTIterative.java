package zyz.steve.tree;

import zyz.steve.datastruct.TreeNode;

public class BSTIterative {
    TreeNode root;
    public BSTIterative(){
        root = null;
    }
    public TreeNode searchNode(int key){
        TreeNode node = root;
        while (node !=null){
            if (key == node.val) {
                return node;
            } else if (key > node.val) {
                node = node.right;//go to right
            } else {
                node = node.left;//go to left
            }
        }
        return null;
    }
    public void insertNode(int key) {
        TreeNode newNode = new TreeNode(key);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode node = root;

        while (true) {
            if (key > node.val) {
                if (node.right != null) {
                    node = node.right;//go to right
                } else {
                    node.right = newNode;
                    return;
                }
            } else if (key < node.val) {
                if (node.left != null) {
                    node = node.left;//go to left
                } else {
                    node.left = newNode;
                    return;
                }
            } else {
                System.out.println("key exists already");
            }
        }
    }
    //TODO
    //https://cloud.tencent.com/developer/article/1708563 - 递归删除
    //iteratively: https://github.com/SvenWoltmann/binary-tree/blob/main/src/main/java/eu/happycoders/binarytree/BinarySearchTreeIterative.java
    public void deleteNode(int key){
        TreeNode node = root;
        TreeNode parent = null;
        //不为空，并不是要找节点时
        while (node!=null && node.val!=key){
            /**
             * 特殊的 只有一个节点的情况下，父节点会跟当前节点一样
             * 但会进入没有找到的情形 Line#69
             */
            parent = node;//设置父节点，为继续向下搜索做准备

            if(node.val > key){//向左
                node = node.left;
            }else {//向右
                node = node.right;
            }
        }

        if(node == null) return;//没有找到

        //found
        if(node.left == null || node.right == null){//没有或只有一个子树的情况
            TreeNode child = node.left == null ? node.right : node.left;
            if(node == root) root=child;//当待删者 就是 root时，直接用其中一个子替换
            else if(key>parent.val) parent.right=child;//被删除节点在父母节点右侧，将新的child也挂到右侧 替换之
            else parent.left=child; //被删除节点在左侧，挂到左侧。
        }else{
            //两个子树都有
            //要么用左子树的最大值 替换被删者，要么用右子树的最小值，替换被删除者
            //此处用前者
            TreeNode predecessor = node.left;
            TreeNode predecessorParent = node;
            while(predecessor.right!=null){
                predecessorParent = predecessor;
                predecessor = predecessor.right;
            }
            //找到了左子树的最大值
            //然后进行值替换
            node.val = predecessor.val;
            //删除 predecessor , 其最多只有一个左子树
            //由于替换节点predecessor位于其父节点右侧（除非predecessor 前驱，就是 待删除节点的左节点 ），需要把 它的左子树挂到其父节点的右侧
            if (node.left == predecessor) {
                //前驱节点就是 待删除节点的左节点, 将前驱节点的left 挂到 待删节点的左侧
                node.left = predecessor.left;
            }else {
                //前驱节点 is further down， 将其左子树挂到 其父的右侧
                predecessorParent.right = predecessor.left;
            }
//            predecessor.left = null; 这步不需要，只要没人引用predencessor， 会被回收掉，其左子树不受影响
        }
    }
    //递归删除
    public TreeNode deleteNodeRecursively(int key, TreeNode root){
        if(root == null) return null;
        //递归部分
        if(key>root.val){//比当前大，继续向右搜索
            //一旦找到，就更新父节点相应子树的指针
            //本分支走向右侧，故用返回的节点更新右子树
            root.right = deleteNodeRecursively(key,root.right);//
            return root;
        }else if(key < root.val) {
            root.left = deleteNodeRecursively(key, root.left);
            return root;
        }
        //找到了，开始核心逻辑
        //1.若没有或只有单一子树,返回任意一个空/非空子树
        if(root.right == null) return root.left;
        if(root.left == null) return root.right;
//        if( root.right == null || root.left == null) {
//            return root.left == null ? root.right : root.left;
//        }
        //2. 待删除节点有两个子树
        //这里选择删除后继（successor）
        TreeNode minNode = root.right;//向右找到后继
        //2.1 [[迭代]] 找右子树最小, 即后继
        if(minNode.left != null){
            minNode = minNode.left;
        }
        //2.2 用后继的值替换带删除节点
        root.val = minNode.val;
        //2.3 递归  删除后继节点, 并将更新后的右子树挂到更新后节点的右侧
        root.right =
                deleteMin(root.right);
        return root;
    }

    private TreeNode deleteMin(TreeNode minRoot) {
        if(minRoot.left == null){
            //找到最小
            //返回Successor之右子树
            //            minRoot.right = null;
            return minRoot.right;
        }
        minRoot.left = deleteMin(minRoot.left);
        return minRoot;
    }

    public static void inorderTraversal(TreeNode root){
        if(root == null) return;
        inorderTraversal(root.left);
        System.out.println(root.val);
        inorderTraversal(root.right);

    }
    //Wrong
    //[5,4,6,null,null,3,7] - one failed case
    //右子树必须严格大于左子树
    public boolean isValidBst(TreeNode root){
        boolean ret;
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        //         root.val > root.left.val && root.val < root.right.val
        if(root.left == null){
            ret = root.val < root.right.val;
        }else if(root.right ==null) {
            ret = root.val > root.left.val;
        }
        else {
            ret = root.val < root.right.val && root.val > root.left.val;
        }
        return  isValidBst(root.left) && isValidBst(root.right) && ret;
    }
    public boolean isValidBstWithSpecifiedMinMax(TreeNode root){
        return bstValidator( root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean bstValidator(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.val < min || root.val > max) return false;
        //转换成long 已处理边界情况
        return bstValidator(root.left,min,(long)root.val-1 ) && bstValidator(root.right,(long)root.val+1, max);
    }

    //Not a good one O(n^2)
    public boolean isValidBstMinMax(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null
                && maxValue(root.left) > root.val) {
            return false;
        }
        if (root.right != null
                && minValue(root.right) < root.val) {
            return false;
        }
        return isValidBstMinMax(root.left)
                && isValidBstMinMax(root.right);
    }

    private int minValue(TreeNode root) {
        if(root == null) return Integer.MIN_VALUE;
        int minLeft = minValue(root.left);
        int minRight = minValue(root.right);
        return Math.min(root.val, Math.min(minLeft,minRight));
    }

    private int maxValue(TreeNode root) {
        if(root == null) return Integer.MAX_VALUE;
        int maxLeft = maxValue(root.left);
        int maxRight = maxValue(root.right);
        return Math.max(root.val, Math.max(maxLeft,maxRight));
    }
}
