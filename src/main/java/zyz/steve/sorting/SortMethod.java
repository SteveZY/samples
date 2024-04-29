package zyz.steve.sorting;


public class SortMethod {

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi){
        //copy

        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        //init https://www.coursera.org/lecture/algorithms-part1/mergesort-ARWDq
        int i = lo, j=mid+1;//i指向 低的部分，j指向较后面的的部分
        for(int k=lo;k<=hi;k++){
            if(i>mid) a[k]=aux[j++];//前半部分已经全部以正确的顺序放入 aux，故从后半部分取值
            else if (j>hi)  a[k]=aux[i++];//when done with j part, 将前半部分放入
            else if (aux[j]<aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void mergeSort (int[] a, int[] aux, int lo, int hi){
        if(hi<=lo) return;
        int mid = (hi - lo) / 2 + lo;
        mergeSort(a,aux,lo,mid);
        mergeSort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);
    }

    //Invariant
    //A <- |p| | | | | | | | | | | | |q|
    //            ｜      ｜
    //       <=p  i  >p   j
    //i 记录小于等于 pivot 的部分最后的index；j记录大于pivot 分区的最后index
    public static  void quickSort(int [] a, int lo, int hi){
        if(lo >= hi) return;//必须含有至少两个元素
        //partition
        int m = partition( a,  lo,  hi);
        quickSort(a,lo,m-1);
        quickSort(a,m+1,hi);

    }

    //a 整数数组，但最大最小值不能差太大，避免无法分配到足够大的内存
    //有负数的情况需要找到min，每个数都要减去 min 获得 其对应的 数组下标
    private static int[] getMax(int [] a){
        int[] maxAndMin = new int[2];
        maxAndMin[0] = a[0];
         maxAndMin[1] = a[0];
        for(int e:a){
            if(e>maxAndMin[0]){
                maxAndMin[0] =e;
            }else if(e<maxAndMin[1] ){
                maxAndMin[1] = e;
            }

        }
        return maxAndMin;
    }
    public static void countingSort(int [] a){
        int[] maxmin = getMax(a);

        int[] bucket = new int[maxmin[0]-maxmin[1] + 1];
        for (int v:a)
        {
            bucket[v-maxmin[1]]++;
        }
        int curPos = 0 ;//指向原数组 可以填充的位置
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]>0){
                a[curPos++]=i+maxmin[1];
                bucket[i]--;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi) {
//        int tmp;
        int pivot = a[lo];//取 第一个 作为 pivot
        int i = lo;//save the index for the smaller group
        for (int j = lo + 1; j <= hi; j++) {
            if (a[j] <= pivot) {
                i++;
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        a[lo] = a[i];
        //将pivot 元素和smaller group 的
        //最后一个元素 交换，确保pivot 位于位置i，所以其位于两个分区的相对中部，即为其最终位置
        // 并且不再参与后续的分区
        a[i] = pivot;
        return i;
    }
}
