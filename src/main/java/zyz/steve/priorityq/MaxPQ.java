package zyz.steve.priorityq;

//Find largest M items in a stream of N items
//Elementary impl, linked list can be used.
//complete binary tree - perfectly balanced except for bottom level
//Binary heaps  - complete binary tree ; Array representation of a heap ordered complete binary tree
// 数组 最大值 放在 位置1， 位置0 不用, 父的index 就总是 子的 index/2
//特性： keys in nodes; parent's key no smaller than children's keys
//Promotion in a heap:  子比父的key 大时，要promote；HOWTO：交换父子，重复直到 violation 被消除
//Demotion in a heap: 当 P key 小于 任何一个 子的 key，violation 发生。交换 父与较大的 子，重复直到没有violation
public class MaxPQ {
    //based on binary heap
    //remove the largest / smallest item
    int [] keys;
    int N;
    int capacity;
    public MaxPQ(int n){keys = new int[n + 1]; N =0; capacity = n;}

    private void swim(int k){
        while (k>1 && less(k/2, k)){
            //父的index 总是 子的index /2
            exch(k, k/2);
            k = k/2;
        }
    }
    public int peek(){
        return keys[1];
    }
    public int delMax(){
        int max = keys[1];
        exch(1, N--);//把最大的和最后的交换
        sink(1);//看有没冲突，往下一层送
        keys[N+1]=0;//清除最后一个
        return max;

    }
    private void sink(int k){

        while ((k<<1) <N){
            int j = k<<1;//指向一个子
            if(j < N && less(j,j+1)) j++;//调整为指向较大的子
            if(!less(k,j)) break;
            exch(k,j);
            k =j;
        }
    }
    public void insert(int x){
        //总是加到最数组的最后，然后swim 上来
        if (N + 1 > capacity) {
            keys[N] = x; //replace the last one
        } else //添到最后
        {
            keys[++N] = x;
        }
        swim(N);
    }

    private boolean less(int i, int k) {
        return keys[i] < keys[k];
    }

    private void exch(int k, int i) {
        int t = keys[k];
        keys[k] = keys[i];
        keys[i] = t;
    }

}

//for k-ary heap （https://www.geeksforgeeks.org/k-ary-heap/）
//节点之间index的关系，假设 第一个 node 的 index 是 I0， 则任意节点 X 的第一个子节点的index  Ch0(Ix) 可以表示如下
// Ch0（Ix） = (Ix - I0)*k +I0 + 1； 详细说明：
// Ix - I0 即为 位于X 节点 之前的节点数量(不含 X 本身)，记为n；每个节点会挂 k 个子节点， 所以 Ix 的第一个子节点 Ch0(Ix) 之前的 节点总数为 n*k + 1 (第一个节点亦为根节点本身)
// 则 Ch0(Ix) 的 前一个节点 index 即为， n*k + 1 + I0 - 1 = n*k + I0， 故前述 Ix 可推得
