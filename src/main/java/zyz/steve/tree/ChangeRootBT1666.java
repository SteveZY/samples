package zyz.steve.tree;

//https://leetcode.com/problems/change-the-root-of-a-binary-tree/description/

import zyz.steve.datastruct.RBTNode;

/**
 * Given the root of a binary tree and a leaf node, reroot the tree so that the leaf is the new root.
 *
 * You can reroot the tree with the following steps for each node cur on the path starting from the leaf up to the root excluding the root:
 *
 * If cur has a left child, then that child becomes cur's right child.
 * cur's original parent becomes cur's left child. Note that in this process the original parent's pointer to cur becomes null, making it have at most one child.
 *
 *
 * Return the new root of the rerooted tree.
 *
 * Note: Ensure that your solution sets the Node.parent pointers correctly after rerooting or you will receive "Wrong Answer".
 */
public class ChangeRootBT1666 {
    public static RBTNode flipBinaryTree(RBTNode root, RBTNode leaf) {
//        RBTNode r = rearrange(leaf, root);
//        r.parent = null;
        RBTNode r = reroot(root,leaf);
        return  r;
    }

    private static RBTNode reroot(RBTNode root, RBTNode leaf){
        /**
         * 伪代码
         * ------------------------------------------
         * leaf is root ? return leaf; //到根了， 就返回
         * //由于leaf的某个子树 要成为 父母节点所以当 leaf 传进来的时候，至多只会有一个子节点
         * //故可执行以下操作
         * leaf.left? leaf.right = leaf.left // 如果左子存在，重新挂到右侧，以便于左指针可指向 当前父母
         *
         * leaf at left? leaf.parent.left=null //leaf在原父节的哪侧 就将 其对应侧 设置为空.因为该leaf 会变成 parent，故就不需要再指向它了
         * else leaf.parent.right =null;
         *
         * ret = reroot(root, leaf.parent);//已准备好当前父节点，递归调用
         *
         * leaf.left = ret; //原parent 挂到左侧
         * ret.parent = leaf; //返回时，原parent 的 子节点都已经 reroot完毕，此处仅将原parent的parent 设为 本 leaf
         *
         * leaf.parent = null;//置空，由上级调用者 设置 本级的 父节点，也就是👆的那行
         * return leaf;
         *
         */
        if (leaf == root) {
            return leaf;
        }

        if (leaf.left != null) {
            leaf.right = leaf.left;
        }
        //get the next node ready
        if (leaf == leaf.parent.left) {
            leaf.parent.left = null;
        } else {
            leaf.parent.right = null;
        }

        RBTNode ret = reroot(root, leaf.parent);

        leaf.left = ret;
        ret.parent = leaf;
        leaf.parent = null;
        return leaf;
    }
    private static RBTNode rearrange(RBTNode node, RBTNode root){
        RBTNode cur = node;
        RBTNode curPar = cur.parent;
        RBTNode leftChild = curPar.left;
//        cur.left = null;
//        RBTNode rightChild = cur.right;
        //检测当前节点 是否 在父母节点左侧
        if(leftChild==null||leftChild.data != cur.data){
            //左节点为空 或者左节点 不是当前节点 时
            //则当前节点不为左节点，说明是从右侧 回溯过来，右侧成了 新父母，那么需要将左孩子挂到 右侧
            //
            curPar.right = leftChild;
            //到根的时候调整原根节点的时候就调整
            if(curPar.data == root.data){
                cur.left = curPar;
                curPar.parent =cur;//根的parent 指向当前节点
                curPar.right = null;
                return cur;
            }
        }
        else {
            if(curPar.data == root.data){
                cur.left = curPar;
                curPar.parent =cur;
                curPar.left = null;
                return cur;
            }
        }

        cur.left = curPar;
//        cur.right = leftChild;
        //进入下一级 ,处理 其子节点
        RBTNode r = rearrange(curPar, root);
        //更新 当前节点 的 父母节点 的 父母节点为 当前节点
        r.parent = cur;
        return cur;
    }
}
