package zyz.steve.union;

import zyz.steve.datastruct.ListNode;

import java.util.List;

public class PracticeUF {
    int N;

    //序号/指针/位置代表实际元素，其内容 为其父元素的 序号
    int [] p;

    public PracticeUF(int n){
        this.N = n;
        p = new int[N];
        for (int i = 1; i < p.length; i++) {
            //将自身设为parent
            p[i] = i;
        }
    }

    /**
     * 寻找 x 的 根节点
     * @param x
     * @return
     */
    public int findRootRecursively(int x) {
        //递归查找
        if (p[x] != x) {

            // 1. 递归 寻找 x 父节点的 根
            int rootOfXParent = findRootRecursively(p[x]);
            //2.  将x的 parent 更新 为 其 parent 的 根，即压缩路径
            p[x] = rootOfXParent;
        }
        //返回x 的 parent 即为 x 的 root， 因为 x 的 root 跟其 parent 的 root 必然 是 同一个的
        return p[x];
    }
    public int getRoot(int x){
        while (p[x] != x ){
            //with path  expression
            p[x] = p[p[x]];

            x=p[x];
        }
        return p[x];
    }
    public void union(int x, int y){
        //将b的root 指向 a的 root
        int rx = findRootRecursively(x);
        int ry = findRootRecursively(y);
        p[ry] = rx;
//        p[findRootRecursively(b)] = p[a];
    }

    public ListNode revList(ListNode curNode){
        if(curNode==null || curNode.next == null){
            return curNode;
        }
        ListNode prev = revList(curNode.next);

        curNode.next.next=curNode;
        curNode.next=null;

        return prev;
    }
    public void visitAList(ListNode node){
        if(null == node ) {
            System.out.print("null");
            return;
        }
        //类似pre order traversal
        System.out.print(node.data);
        System.out.print("->");
        visitAList(node.next);

    }


    public static void main(String[] args) {
        PracticeUF uf = new PracticeUF(10);

        uf.union(5,4);
        uf.union(2,5);
        uf.union(3,2);

//        System.out.println(uf.getRoot(5));

        System.out.println(uf.findRootRecursively(4));//shall be 3
        uf.union(7,5);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;

        uf.visitAList( node1);
        System.out.println("\n-----");
        ListNode hh = uf.revList(node1);

        uf.visitAList(hh);
//        while (hh!=null){
//            System.out.println(hh.data);
//            hh=hh.next;
//        }
    }
}
