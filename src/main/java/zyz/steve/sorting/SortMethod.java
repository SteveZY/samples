package zyz.steve.sorting;

public class SortMethod {

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        //copy

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //init https://www.coursera.org/lecture/algorithms-part1/mergesort-ARWDq
        int i = lo, j = mid + 1;//i指向 低的部分，j指向较后面的的部分
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];//前半部分已经全部以正确的顺序放入 aux，故从后半部分取值
            } else if (j > hi) {
                a[k] = aux[i++];//when done with j part, 将前半部分放入
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];//优先放 i 的部分
            }
        }
    }

    /**
     *
     * @param a
     * @param aux
     * @param lo  index pointing to 左侧元素
     * @param hi  index 指向本 iteration右侧 元素
     */
    public static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = (hi - lo) / 2 + lo;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    //Invariant
    //A <- |p| | | | | | | | | | | | |q|
    //            ｜      ｜
    //       <=p  i  >p   j
    //i 记录小于等于 pivot 的部分最后的index；j记录大于pivot 分区的最后index
    public static void quickSort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;//必须含有至少两个元素
        }
        //partition
        int m = partition(a, lo, hi);
        quickSort(a, lo, m - 1);
        quickSort(a, m + 1, hi);

    }

    //a 整数数组，但最大最小值不能差太大，避免无法分配到足够大的内存
    //有负数的情况需要找到min，每个数都要减去 min 获得 其对应的 数组下标
    private static int[] getMax(int[] a) {
        int[] maxAndMin = new int[2];
        maxAndMin[0] = a[0];
        maxAndMin[1] = a[0];
        for (int e : a) {
            if (e > maxAndMin[0]) {
                maxAndMin[0] = e;
            } else if (e < maxAndMin[1]) {
                maxAndMin[1] = e;
            }

        }
        return maxAndMin;
    }

    public static void countingSort(int[] a) {
        int[] maxmin = getMax(a);

        int[] bucket = new int[maxmin[0] - maxmin[1] + 1];
        for (int v : a) {
            bucket[v - maxmin[1]]++;
        }
        int curPos = 0;//指向原数组 可以填充的位置
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                a[curPos++] = i + maxmin[1];
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
                //一旦在后半部分找到小于等于pivot 的，就将前半部分加大 1，
                i++;
                //然后把发现的元素放倒 前半部分 最后位置
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        //将pivot 元素和smaller group 的
        //最后一个元素 交换，确保pivot 位于位置i，所以其位于两个分区的相对中部，即为其最终位置
        // 并且不再参与后续的分区
        a[lo] = a[i];
        a[i] = pivot;
        return i;
    }

    /**
     * https://www.coursera.org/learn/algorithms-part1/lecture/1hYlN/insertion-sort
     * 确保前半部分 总是排序好的.把当前探测到的 i 位置元素 放到前半部分正确的位置
     *
     * 将新 拿进来的元素 插入到前半部分的正确位置，交换次数较多，
     * @param a
     */
    public static void insertionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    //exchange
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                } else {
                    break;
                }

            }

        }
    }

    /**
     * 前半部分 维持总是排序好的，并且都比 指针 右侧的元素小，
     * 一旦向后移动 其尾部指针 则invariant 不成立，就需要 找到最小的，然后跟当前指针位置的元素交换
     * 以维持invariant
     * https://www.coursera.org/learn/algorithms-part1/lecture/VE0sv/selection-sort
     *
     * 选择最小的 放到 前半部分 最后一个位置
     */
    public static void seletionSort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            //i 指向前半部分的最后元素，其应该比后面的都小
            //但是 循环一开始，这个invariant 就无法维持，不能保证i指向的元素小于其后所有的，
            //故向后遍历，如果找到更小的，就修改min 指向，找到最小的之后 跟 i 位置交换，这样invariant 得到维持
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                //从i的下一个位置开始
                if (a[j] < a[min]) {
                    min = j;//记录min的位置
                }
            }
            if (min != i) {
                int tmp = a[min];
                a[min] = a[i];
                a[i] = tmp;
            }

        }
    }
}
