package zyz.steve.sorting;

public class SortMethod {

    public static void merge(int[] a, int[] aux, int lo, int mid, int hi){
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
}
