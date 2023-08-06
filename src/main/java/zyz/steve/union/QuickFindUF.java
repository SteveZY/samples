package zyz.steve.union;
//简单示例,但这中数据结构 union时太慢
//元素 0  1  2 3  4 5  6  7 8  9 10
//Id ｜1｜2｜1｜1｜1｜3｜3｜2｜2｜3｜4｜
public class QuickFindUF {
    //因为无需寻找路径，所以union 本质上就是数据分组
    //用一个数组 id[] 存放所有元素的 id，相同id的元素为一组
    private int [] id;
    public QuickFindUF(int n ){
        //初始化，每个元素都初始化成自己的id
        id = new int[n];
        for(int i = 0;i <n;i++){
            id[i] =i;
        }
    }
    public boolean connected(int p, int q){
        return id[p] == id[q];
    }
    public void union(int p, int q){
        //将p放入 q所在分组，即将 p以及p一组元素的id都改变成q的需要遍历，当然反过来也行
        int pid = id[p];
        int qid = id[q];
        for(int i =0; i<id.length;i++){
            if(id[i] == pid) id[i] = qid;
        }
    }

}
