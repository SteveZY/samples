package zyz.steve.union;
//该树的最大高度，为 lgN
public class WeightedQuickUnion {
    //track 每个tree 的尺寸
    //balance 通过将小的树挂到较大的树上实现
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int n) {
        //初始化，每个元素都初始化成自己的id
        id = new int[n];
        sz = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] =1;
        }
    }

    private int getRoot(int q) {
        //q此处 为元素的位置index
        int temp = q;
        while (id[temp] != temp) {
            id[temp]=id[id[temp]];//flatten the tree more by pointing to its grandparent
            //节点的Id 等于节点的index时，就找到root了
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

        if (sz[qr] > sz[pr]) {
            id[pr] = qr;//p root tree 较小
            sz[qr]+=sz[pr];//加上p树，调整q树的尺寸
        }
        else {
            id[qr] = pr;//q树较小
            sz[pr] += sz[qr];//调整p树尺寸
        }
    }
}
