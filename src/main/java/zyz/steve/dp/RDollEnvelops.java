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
        //一个新的数组存放 当前位置 的 最后 一对宽高值，以及 以当前位置为起始， 有多少个 宽高对 可符合 严格递增
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

    static int bsearch2(int[] p, int key, int right) {
        int low = 0;
        int high = right;
        while (low < high) {
            int mid = (low + high) / 2;
            if (key <= p[mid]) high = mid;
            else low = mid + 1;

        }
        return low;
    }

    public static void maxEnv2(int[][] p) {
        Arrays.sort(p, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int len = 1; //初始最大长度 为1
        int[] g = new int[p.length + 1];//记录 长度 为 下标所代表的长度，末尾元算值
        Arrays.fill(g, Integer.MAX_VALUE);
        g[0] = 0;
        g[1] = p[0][1];
        boolean increased = true;//初值为 true，因为第一个元素必令长度 增加到1

        //从第二个元素开始检查
        for (int i = 1; i < p.length; i++) {

            if (p[i][0] > p[i - 1][0]) {//宽度变大
                int insertPos = bsearch2(g, p[i][1], len + 1);//长度可能增1，比较高度并留够空间
                g[insertPos] = Math.min(p[i][1], g[insertPos]);
                if (insertPos > len) {
                    len = insertPos;
                    increased = true;//标记该宽度 已增 1
                }else {
                    // 同宽度的信封，高度分量 乱序的情况下，第一个元素 的高 不足以 令长度的增加的时候
                    // 若宽度改变的本次 迭代， 长度未增，标记之。万一后续同宽度的 序列 的高 可能会有更大的， 亦可使长度得以扩展
                    increased = false;//若宽度改变那次 迭代 长度未变，标记之。万一后续同宽度的 序列 的高 可能会有更大的， 亦可使长度得以扩展
                }
            } else {
                // 每次宽度的增加 都有可能 使得长度增加。
                // 如果宽度初变的时候 序列长度没有增加，后续还可能增加，则需要留出空间
                int nextLen = increased ? len : len + 1;
                int insertPos = bsearch2(g, p[i][1], nextLen);
                g[insertPos] = Math.min(p[i][1], g[insertPos]);
                if (!increased && insertPos > len) {
                    len++;
                    increased = true;//标记 已经增 1， 避免后续的 同宽度的较大高度 继续 增加长度
                }
            }
        }
        System.out.println(Arrays.toString(g));
    }

    /**
     * https://www.acoier.com/2021/03/04/354.%20%E4%BF%84%E7%BD%97%E6%96%AF%E5%A5%97%E5%A8%83%E4%BF%A1%E5%B0%81%E9%97%AE%E9%A2%98%EF%BC%88%E5%9B%B0%E9%9A%BE%EF%BC%89/#%E6%A0%91%E7%8A%B6%E6%95%B0%E7%BB%84-%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92
     * 基本细想：
     * 遍历列表：  当宽度有增加 就可能 使 IS 进一步变长，并检查前一宽度的所有元素，确定 是哪些 可能确保了 IS长度的增加(通过 fi?==len),
     * 若确认则 令 len 增1。 检查（二分法） 其高度 是否 大于 所有已 可构成 IS 的 元素高度（靠g 中存放着的高度值）
     *       宽度没有增加，则 继续 看当前元素 在 g 中位置（二分法），以确定 其 所能构成的 IS 的长度 并用 f 记录下来，
     * 便于碰到宽度增加的时候 用于 确定 前一宽度 的所有元素中，是哪些 可能确保了 IS长度的增加
     *
     *
     * step 1： 只是宽度变大的话， 长度 只是 有可能 增加，故增加 潜在 能达到的 长度 len， 是否能真增加，还要看下一步
     * Step 2: 同宽度的元素：遍历一遍 记录下能每个能构造的 单增序列， 其中那个高度 大于 所有已知高度， 就会把长度 加上去
     *      但由于 当前的最大可能长度 len 的限制，就算有多个都很大，也不会导致某个元素 f[x] 比 g 当前的最长值，增长 超过 2
     * 3: 一旦高度 确定加上去了， 就可以回到 step 1 继续
     *
     *
     * 例如：
     * 给定 如下序列 已经按宽度排好序
     * {{2, 2}, {2, 1}, {2, 3}, {3, 1},{3,2}}
     *
     * 初始化
     * 由于每个元素至少可以构成 长度 为1 的序列，故 令 潜在能到达的 长度 len 设为1
     * f  数组 长度 为 n， 记录 每个 位置 能构成的 IS 的长度， 初值为都为 0 设为1最准确，但是并不必须，因为只有最大值是需要的，而且每次迭代一个位置
     * 自然会更新 对应的 f。f 真的有用的地方，只是对于当前相同宽度 的元素， 用于判断是不是其中某个真的 令 LIS 增1
     * g 数组 存储 相应长度 IS 目前最后一个元素的高度值，
     * 长度应大于n，最好是 n+1，由于 序列的长度可能是n, 而零号位置仅为 哨兵位。 长度为 Idx(可理解为一个普通的数字) 的 IS，内容 为该IS 最后一个数字
     *
     * 第一次迭代
     * 由于 i，j 均为0
     * g不会被更新
     * f【0】 为 1， 0号元素 可构成IS 最大长度为 1
     *
     * 第二次
     * i 等于1
     * f【1】 为1 可构造的IS 最大长度 为 1. 这是由于其高度 1 ，在g中的位置必然小于 上个元素2，而且不能超过最大的潜在长度 len
     * 第三次
     * i 等于 2
     * f[1] = 1,
     *
     * i = 3 时
     * 宽度 增加， 故需要 向前 查看 前一宽度 2 的所有元素 是否有令 len 确实 增1，
     * 发现 f0 ～ f2 全都 为一，即他们都可以 确认了 len。只要其中任意一个 可确认 len， 即可 使len 继续增 1，意思时说，本次的宽度增大可
     * 令 潜在长度 len 增 1， 因此也更新 g[1]  位置.
     *  内层循环细节
     *   j=0 ， f0 == len ， 令 g【1】= 2， 并 len 增一 变成 2
     *   j=1，  f1 != len    g1 = min(g1, e[j][1])
     *   j=2,  f2 != len  g1 = min(g1, e[j][1])
     *
     *   循环结束时， j =3， 指向 最新宽度的开始元素 {3,1} g1 = 1， 即可令IS 长度为1 的元素 中 高度的最小值
     * 内层循环结束， 查看 元素 {3,1 } 的高度 1 在 g 中 所处的位置，发现只能在1 故 f3=1
     *
     * i=4 时，
     * 宽度不变，查看 {3,2} 在g中 的 位置，此时由于 len=2， 而且 高度 2 大于 g1=1， 所以 f4 = 2;
     *
     *  所有元素遍历完成，得 g = {0,1,2, NA ...} 找到 g 中 有效数字所在的最大 index 即为 LIS 的长度 ，完成
     * @param es
     * @return
     */
    public static int maxEnvelopes(int[][] es) {
        int n = es.length, ans = 1;
        if (n == 0) return n;
        // 由于我们使用了 g 记录高度，因此这里只需将 w 从小到达排序即可
        Arrays.sort(es, (a, b) -> a[0] - b[0]);
        // f(i) 为考虑前 i 个物品，并以第 i 个物品为结尾的最大值
        int[] f = new int[n];
        // g(i) 记录的是长度为 i 的最长上升子序列的最小「信封高度」
        int[] g = new int[n];
        // 因为要取 min，用一个足够大（不可能）的高度初始化
        Arrays.fill(g, Integer.MAX_VALUE);
        g[0] = 0;
        for (int i = 0, j = 0, len = 1; i < n; i++) {
            // 对于 w 相同的数据，不更新 g 数组
            if (es[i][0] != es[j][0]) {
//                新的宽度出现，j 目前 指向 旧宽度的第一个元素
//               while 核心逻辑， 从旧宽度的第一个元素，向 当前 i 元素 推进，查看是否某个 确实令长度增加了，若有，则 潜在最大长度len 可以继续增1
                //若没有，则看是否需要更新 g 对应长度 上最后元素， g[f[j]] 和 j 位置高度的最小值
                //while 结束后，j 就会指向 新宽度的 第一个元素

                // 限制 j 不能越过 i，确保 g 数组中只会出现第 i 个信封前的「历史信封」
                while (j < i) {
                    int prev = f[j], cur = es[j][1];
                    if (prev == len) {
                        // 与当前长度一致了，说明上升序列多增加一位
                        g[len++] = cur;
                    } else {
                        // 始终保留最小的「信封高度」，这样可以确保有更多的信封可以与其行程上升序列
                        // 举例：同样是上升长度为 5 的序列，保留最小高度为 5 记录（而不是保留任意的，比如 10），这样之后高度为 7 8 9 的信封都能形成序列；
                        g[prev] = Math.min(g[prev], cur);
                    }
                    j++;
                }
            }
            //最开始的初始化，也从这里开始

            //根据当前 i 位置的 元素，高度，定位其在 g 中 位置， 即其所能达成 的 最长长度， g 数组的 下标 pos 表示 长度， 其值为该长度序列 最后一个元素的 值，
            //比如 g 中 当 pos =1 ，即为 在1号位置记录长度为 1的 单增序列，其最后一个元素 目前见到的 最小 高度 为 g[pos] 即为 g[1]
            // pos =2， 在2号位置，记录长度为2的单增序列，其最后一个元素，目前见到的最小 高度 为g[pos] 亦为 g[2]
            // 二分过程
            // g[i] 代表的是上升子序列长度为 i 的「最小信封高度」
            int l = 0, r = len;
            while (l < r) {
                int mid = (l + r) >> 1;
                // 令 check 条件为 es[i][1] <= g[mid]（代表 w 和 h 都严格小于当前信封）
                // 这样我们找到的就是满足条件，最靠近数组中心点的数据（也就是满足 check 条件的最大下标）
                // 对应回 g[] 数组的含义，其实就是找到 w 和 h 都满足条件的最大上升长度
                if (es[i][1] <= g[mid]) r = mid;
                else l = mid + 1;
            }
            // 确定当前元素 能构成多长的递增序列，更新 f[i] 与答案
            f[i] = r;
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] p = {{2, 2}, {3, 4}, {3, 7}, {3, 1}};
        int[][] b = {{5, 4}, {3, 4}, {3, 7}, {4, 3}};
        int[][] c = {{2, 1}, {2, 2}, {2, 4}, {4, 1}, {4, 2}, {5, 1}, {6, 1}};//测试 func maxEnvelopes

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

        System.out.println(Arrays.deepToString(c));
        System.out.println(maxEnvelopes(c));


        int[][] d = new int[][]{{2, 2}, {2, 1}, {2, 3}, {3, 1},{3,2}};
        maxEnv2(c);
        //3d box sorting
        int[][] xxx = {{5, 5, 6}, {5, 6, 7}, {1, 2, 3}, {2, 2, 4}, {1, 6, 3}};

        sort3D(xxx);
        System.out.println(xxx);


    }
}
