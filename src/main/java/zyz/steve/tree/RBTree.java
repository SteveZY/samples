package zyz.steve.tree;

import zyz.steve.datastruct.RBTNode;


//https://www.youtube.com/watch?v=5IBxA-bZZH8
public class RBTree {
    RBTNode root;
    void rotateRight(RBTNode node){
        RBTNode left = node.left;

        node.left = left.right;//挂左侧 右子树 到 给定 node
        if (left.right != null) {
            //更新改右子树的 parent
            left.right.parent = node;
        }
        left.right = node;
        updateParentPointer(node, left);
    }
    void rotateLeft(RBTNode node){
        RBTNode right = node.right;
        RBTNode parent = node.parent;

        node.right = right.left;//挂左侧 右子树 到 给定 node
        if (right.left != null) {
            //更新改右子树的 parent
            right.right.parent = node;
        }
        right.left = node;
        updateParentPointer(node, right);
    }

    /**
     * 使 node 的parent 变为 newNode的parent
     * 若parent为空说明
     * @param node
     * @param newNode
     */
    private void updateParentPointer(RBTNode node, RBTNode newNode) {
        RBTNode parent = node.parent;

        node.parent = newNode;

        //更新父节点的指向
        if(parent ==null) root = newNode;
        else if(parent.left== node){
            parent.left = newNode;
        } else if (parent.right == node) {
            parent.right = newNode;
        }
        else {
            throw new RuntimeException("Node does not have the given parent.");
        }
        newNode.parent = parent;
    }
    public void insertNode(int key){
        RBTNode node = root;
        RBTNode newNode = new RBTNode(key);
        newNode.color = false; //false means RED
        if (root == null) {
            root = newNode;
//            fixRBProps4Insertion(newNode);
            return;
        }
        while (true){
            if(key>node.data){
                //go right
                if (node.right != null) {
                    node = node.right;
                } else {
                    newNode.parent = node;
                    node.right = newNode;
                    break;
                }

            }else if (key < node.data){
                if(node.left !=null)
                node = node.left;
                else {
                    newNode.parent = node;
                    node.left = newNode;
                    break;
                }
            }
            else {
                throw new RuntimeException("The given key exists");
            }
        }
        //Found a position
        fixRBProps4Insertion(newNode);

    }

    private void fixRBProps4Insertion(RBTNode node) {
        RBTNode parent = node.parent;
        if(parent == null || parent.color) return;

        //parent 是红色的
        RBTNode grandParent = parent.parent;
        if(grandParent == null){
            parent.color = true;//染成黑色
            return;
        }
        RBTNode uncle = getUncle(node);
        if(uncle!= null && !uncle.color){
            //uncle 为红色 recolor
            parent.color = true;
            grandParent.color=false;
            uncle.color = true;
//递归修正
            fixRBProps4Insertion(grandParent);
        }
        //uncle 是黑的， 空的时候 也认为是黑色的
        else if(parent == grandParent.left){
            //父在祖父的左侧
            if(node == parent.right){
                //zig zag - case 2
                node = parent;
                rotateLeft(node);
            }
            node.parent.color=true; //一条线上 case 3
            node.parent.parent.color=false;
            rotateRight(grandParent);
        }else{
            //父在祖父的右侧
            if(node == parent.left){
                //zig zag case 2
                node= parent;
                rotateRight(node);
            }
            node.parent.color=true; //一条线上 case 3
            node.parent.parent.color=false;
            rotateLeft(grandParent);
        }
    }

    private RBTNode getUncle(RBTNode parent) {
        RBTNode gPa = parent.parent;
        return gPa.left == parent ? gPa.right:gPa.left;
    }
    public void deleteNode(int key){
        RBTNode node = root;
        while(node != null && node.data != key){
            if(node.data>key){
                node = node.left;
            }else  {
                node = node.right;
            }
        }
        if(node == null) return; //没找到

        //found the node to be deleted
        boolean color;
        RBTNode substitute;
        if(node.left == null || node.right==null){
            //保存用于替换带删除节点 的 子孙节点， 以及 待删除节点的 颜色
            substitute = deleteNodeWithZeroOrSingle(node);
            color = node.color;
        }
        else{//有两个孩子
            RBTNode inorderSuccessor = findMin(node);
            node.data = inorderSuccessor.data;
            color = inorderSuccessor.color;
            substitute = deleteNodeWithZeroOrSingle(inorderSuccessor);
        }
        if(color){//被删除的为黑色
            fixRBProps4Deletion(substitute);

        }
    }

    private void fixRBProps4Deletion(RBTNode substitute) {
    }

    private RBTNode findMin(RBTNode node) {
        RBTNode minRoot = node.right;
        while(minRoot != null){
            minRoot = minRoot.left;
        }
        return minRoot;
    }
    private RBTNode searchMinRecursively(RBTNode node){
        if (node.left != null) {
            //BST 的最下值 就是 其左子树的最小值，所以一直向左搜索
            return searchMinRecursively(node.left);
        }
        return node;
    }

    private RBTNode deleteNodeWithZeroOrSingle(RBTNode node) {
        if(node.left!= null){
            //TODO: update parent and child
            return node.left;
        }else if (node.right != null){
            return node.right;
        }
        else{//TODO
            return new NilNode();
        }

    }

    static class NilNode extends RBTNode{

        private NilNode() {
            super(0);
            this.color = true;
        }
    }

}
