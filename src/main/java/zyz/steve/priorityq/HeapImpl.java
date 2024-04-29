package zyz.steve.priorityq;

public class HeapImpl {
    int capacity;
    int [] keys;
    int N;
    int lastParentIdx;
    public HeapImpl(int [] arr){
        keys = arr; N =arr.length; capacity = arr.length+1;
        lastParentIdx = (N - 2) / 2; // last one with children = ( idx of the last one (N -1)  -1) /2
        buildHeap();
    }
    private boolean less(int i, int k) {
        if(k>=N) return false;
        return keys[i] < keys[k];
    }
    private boolean less(int []a,int i, int k) {
        if(k>=N) return false;
        return a[i] < a[k];
    }
    /**
     * 递归 sink
     */
    private void heapifyRecursively( int i){

        if(i > ((N>>1)-1)) return;
        int left = i*2+1;
        int right = left + 1;

        int max = left;
        if(less(left,right)) max= right;//找到较大的子
        if(less(i,max)) {
            exch(i, max);
            heapifyRecursively(max);
        }
//        if(right<N-1) heapify_rec(right);


    }
    private void buildHeap(){
        //从最后一个有子的父节点开始
        //这样，可以确保较大的值 从最底部上移一层，而较小的值都在底部
        //下次循环时，sink 才可以将更高一层的较小值下移，最终 形成一个heap
        for (int i = lastParentIdx; i >=0 ; i--) {
            //递归版本
//            heapifyRecursively(i);//
            //调用 迭代版本
            heapifyIter(i);

        }
    }

    private void exch(int i, int max) {
        int tmp = keys[i];
        keys[i] = keys[max];
        keys[max] = tmp;
    }
    private void exch(int [] a, int i, int max) {
        int tmp = a[i];
        a[i] = a[max];
        a[max] = tmp;
    }

    //sink 供build heap时调用
    //将较小的值一直往下送，直到找不到更小的，返回。
    //调用前，要保证下层的子树已经 是heap，这是需要 build heap 从倒数第二层开始 sink各个元素
    private void heapifyIter(int i){
        int aChild ;
        while((aChild = i*2+1) < N){ //左侧的
            if(less(aChild, aChild+1)) aChild++;//若右侧的大，就指向右侧
            if(less(i,aChild)) {
                exch(i, aChild);
                i = aChild;
            }else
                break;
        }

    }
    public void sort(){
        //heap sort
        for (int i = N-1; i >=0; i--) {

            exch(0,i);
            N--;
            heapifyRecursively(0);
//            heapifyIter(0); //下沉 0
        }
//        int i=N-1;
    }
//https://www.youtube.com/watch?v=j-DqQcNPGbE
    public void heapifyCopied(int [] a, int i){
        int left = i*2+1;
        int right = left + 1;
        int max = i;
        if(left<a.length &&less(a,max,left)) max = left;
        if(right<a.length &&less(a, max,right)) max = right;
        if(max!=i){
            exch(a ,i, max);
            heapifyCopied(a,max);
        }

    }


}
