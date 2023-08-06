package zyz.steve.union;
//搞成forest， 元素的id 为其父元素 id，每组的根元素的id 就是它自己
public class UnionFindUF {
    //slow as well
    //find - 检查看 p q是否有同样的root
    //union - 将p的root 的 id 设置成 q 的root的id
    private int[] id;

    public UnionFindUF(int n) {
        //初始化，每个元素都初始化成自己的id
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private int getRoot(int q) {
        int temp = q;
        while (id[temp] != temp) {
            temp = id[temp];
        }
        return temp;
    }

    public boolean connected(int p, int q) {
        //check they have the same root.
        return getRoot(p) == getRoot(q);
    }
    public void union(int p, int q){
        int pr = getRoot(p);
        int qr = getRoot(q);
        if(pr == qr) return;
        id[pr] = qr;
    }
}
