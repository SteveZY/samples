package zyz.steve.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//questions is here : https://leetcode.com/problems/russian-doll-envelopes/
public class RDollEnvelops {
    static int bsearch(List<Integer> list, int key) {
        int high = list.size(), low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;

    }

    static boolean compareArray(int[] a, int b[]) {
        //要 a，  b 长度相等
        //不做参数检查
        //b 严格 大于 a 返回 1， 否则返回0
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= b[i]) {
                return false;
            }
        }
        return true;
    }

    //         ddd = {{1, 2, 3}, {2, 3, 4}};
    static int dp(int[][] p) {
        //一个新的数组存放 当前位置 的 最后 一对宽高值，以及 以当前位置为起始， 有多少个 数组 可符合 严格递增
        //先排序
        Arrays.sort(p, (o1, o2) -> o1[0] != o2[0] ?
                Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

        int[] dpMemo = new int[p.length];
        Arrays.fill(dpMemo, 1);
//        int[][] dpEndEle = Arrays.copyOfRange(p, 0, p.length);

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < i; j++) {
                if (compareArray(p[j], p[i])) {
                    dpMemo[j] = dpMemo[j] + 1;
                    p[j] = p[i];
                }
            }
        }
        System.out.println(Arrays.deepToString(p));

        return Arrays.stream(dpMemo).max().orElse(1);
    }

    static void sort3D(int[][] p) {
        Arrays.sort(p, new Comparator<int[]>() {


            @Override
            public int compare(int[] o1, int[] o2) {
                if ((o1[0] == o2[0] || o1[1] == o2[1]) ||
                        (o1[0] > o2[0] && o1[1] < o2[1]) ||
                        (o1[0] < o2[0] && o1[1] > o2[1])
                ) {
                    return Integer.compare(o2[2], o1[2]);
                } else {
                    return Integer.compare(o1[0], o2[0]);
                }
            }
        });
    }


    public static void main(String[] args) {
        int[][] p = {{2, 2}, {3, 4}, {3, 7}, {3, 1}};
        int[][] b = {{5, 4}, {3, 4}, {3, 7}, {4, 3}};

        Arrays.sort(p, (o1, o2) -> o1[0] != o2[0] ?
                Integer.compare(o1[0], o2[0]) : Integer.compare(o2[1], o1[1]));

        System.out.println(p);


        int[] heights = new int[p.length];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = p[i][1];
        }

        List<Integer> ll = new ArrayList<>();
//        ll.add(2);
//        ll.add(3);
//        ll.add(4);
//        System.out.println(bsearch(ll, 5));

        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            int idx = bsearch(ll, h);
            if (idx < ll.size()) {
                //由于 height 是从大到小排列，所以小的总是会替换掉之前较大的值。这个办法相当快
                ll.set(idx, h);
            } else {
                ll.add(h);
            }
        }
        System.out.println((dp(b)));

        //3d box sorting
        int[][] xxx = {{5, 5, 6}, {5, 6, 7}, {1, 2, 3},{2, 2, 4},{1,6,3}};

        sort3D(xxx);
        System.out.println(xxx);

    }
}
