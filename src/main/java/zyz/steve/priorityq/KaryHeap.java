package zyz.steve.priorityq;

public class KaryHeap {
    int N;
    int [] keys;
    int K;
    public KaryHeap(int [] a, int k ){
        N = a.length;
        keys = a;
        K=k;
    }
    public void buildHeap(){

        int len = N;
        int lastNonLeafNodeIndex  = ((len -1 ) - 1)/K;
//        = (len - 1 - (len - 1) % k) / k;
        for (int i = lastNonLeafNodeIndex; i >=0; i--) {
            sink(i);
        }
    }
    void sink(int i){
        while ((i*K)+1 < N){
            //节点的子,没有越界就继续
//            int idxOfAChild = (i * K + 1);
            int idxOfAChild = max(i*K +1);
            if(less(i,idxOfAChild)) {
                exch(i, idxOfAChild);
                i = idxOfAChild;
            }
            else break;
        }
    }
    private void exch(int k, int i) {
        int t = keys[k];
        keys[k] = keys[i];
        keys[i] = t;
    }
    boolean less(int i, int j){
        return keys[i] < keys[j];
    }
    int max(int i){
        int max = i;
        for (int j = i+1; j < i+K; j++) {
            if(j>N-1) return max;
            max = less(max,j)? j:max;
        }
        return max;
    }

}
