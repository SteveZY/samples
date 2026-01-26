package zyz.steve.backtrace;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SubsetsFinder {
    static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> getAllSubsets(int[] nums) {
        backtrack(0, ans, new ArrayList<>(), nums);

        // backtracking 的办法
        return ans;
    }
// LC78

    private static void backtrack(int start, List<List<Integer>> ans, List<Integer> cur, int[] nums) {
        // 相当于 把当前 元素 加到 其后续元素所能构成的 子集 中，再构成新的 子集。
        // 当递归返回到最后，就到 了 第一个 （0 号位子的元素） ，空集是在第一次调用的时候 就加入了
        ans.add(new ArrayList<>(cur)); // 将当前集合 放入结果集， 然后 开始操作
        for (int i = start; i < nums.length; i++) {
            // 从start 制定的index 开始，枚举 到最后
            // 第一次进入的时候， 从0 位置开始，cur 为 【】
            // 然后 cur 获得 0 号元素
            // 递归 第二次 的时候，cur as [0], 被保存为一个 子集中的一个， 然后从1 号位置开始，枚举 到最后
            // 第三次 时， cur as [0, 1]， 保存之，继续从 2号 位置开始
            cur.add(nums[i]);

            backtrack(i + 1, ans, cur, nums); // i+1 令我们总是从下一个元素开始 实验
            cur.remove(cur.size() - 1);
        }
    }
// LC78

    /**
     * 基本就是 当前的所有子集 附加上 当前的 元素 ， 形成 一堆新的 子集，然后 再加入结果集
     * 时间复杂度， n * 2 ^n  ,
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newSets = new ArrayList<>();

            for (List<Integer> set : res) { // 这个 loop  O(2^n)  ,
                // 细说的话 就是 一开始 只有一个空集 2^0 , 然后 变成 2^1 , 2^2 直到 2 ^ n-1
                // 1 + 2 + 2^2 + ... + 2^n-1
                List<Integer> newSet = new ArrayList<>(set);
                newSet.add(num);
                newSets.add(newSet);
            }
            res.addAll(newSets); // 每次都要复制 所有 子集
        }
        return res;
    }
// LC78

    /**
     * 一组元素的所有子集 就是  是 C(n,0) + C(n,1) + ... + C(n,n)
     * 或者 这么理解，n个数， 每个 都 有两种可能 选，或不选， 然后 用乘法原理， 2 x 2 x ... x 2 = 2 ^ n
     * 也就是 n个数，咱们就有 2^n  个 子集， 那 0 ～ 2 ^n -1 ,这些个数，对应的 2 进制表达 ，就正好可以用来 表示 某个 元素 是否 要选
     * 例如 n 为 3 时
     * 0 ～ 7 用于 表示 各个 子集的 代号， 他们二进制
     * 0  --    000b  一个都没选中
     * 1 -- 001b。选 最后一个数
     * 2 -- 010b  表示 只选 第二个数
     * ..
     * 7 -- 111b  表示所有三个数全选
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsByBitmask(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int totalSets = 1 << nums.length;
        for (int mask = 0; mask < totalSets; mask++) {
            List<Integer> set = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if ((mask & 1 << i) != 0) { // 使用 位与 得到  当前位 置 选还是不选
                    // the cur nums[i] is selected
                    set.add(nums[i]);
                }
            }
            res.add(set);
        }
        return res;
    }

    public static List<List<Integer>> subsetsII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 排序， 方便排除 重复 数字
        helper(res, new ArrayList<Integer>(), 0, nums);
        return res;
    }

    // backtrack
    private static void helper(List<List<Integer>> res, List<Integer> cur, int start, int[] nums) {
        res.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; i++) {
            // 先将重复数字 排除
            // 非第一次， 而且 跟前面的元素 相同
            if (i != start && nums[i] == nums[i - 1]) continue;
            cur.add(nums[i]);
            helper(res, cur, i + 1, nums);
            cur.remove(cur.size() - 1);

        }
    }

    // LC 46 https://leetcode.com/problems/permutations/description/
    public static List<Set<Integer>> permutations(int[] nums) {
        // 还是backtrack 但是 ， 咱们每次都得从头开始 遍历数组
        List<Set<Integer>> res = new ArrayList<>();

        permuHelper(res, new LinkedHashSet<>(), nums);
        return res;
    }

    private static void permuHelper(List<Set<Integer>> res, Set<Integer> cur, int[] nums) {
        if (cur.size() == nums.length) {
            res.add(new LinkedHashSet<>(cur));
            // got one then return to save the following for loop
            return;
        };
        // 遍历 ， 并 将每个数字 都加入 当前 递归层对应的 位置， 例如 第一次 就时对应 0号元素
        // 后续的递归层， 就 一次 加入 未使用过的数字 到 相应 层 的位置
        for (int i = 0; i < nums.length; i++) {
            if (cur.contains(nums[i])) continue;
            cur.add(nums[i]);
            permuHelper(res, cur, nums);
            cur.remove(nums[i] ); // remove the cur num to try next node or element
        }
    }
// LC 47 https://leetcode.com/problems/permutations/description/
    // 输入数组 有 重复元素 时， 跟之前组合 借鉴 一下， 排序，使相同的元素 挨着彼此，当发现给前方元素 相同时 需要做一些过滤 提前结束 循环
    public static List<List<Integer>> permutationsII(int[] nums) {
        // 还是backtrack 但是 ， 咱们每次都得从头开始 遍历数组
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort( nums);
        permu2Helper(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private static void permu2Helper(List<List<Integer>> res, List<Integer> cur, int[] nums, boolean[] used) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            // got one then return to save the following for loop
            return;
        };
        // 遍历 ， 并 将每个数字 都加入 当前 递归层对应的 位置， 例如 第一次 就时对应 0号元素
        // 后续的递归层， 就 一次 加入 未使用过的数字 到 相应 层 的位置
        for (int i = 0; i < nums.length; i++) {
            // if (cur.contains(nums[i])) continue;  因为 输入有 重复元素， 不能用 Set 了， 所以需要 使用额外 bool 数组 记录 当前 排列中是否 已经含有当前元素
            // 跳过的 逻辑 为两种可能，
            // 1: 每次都是从数组开头开始循环，所以 某个元素 可能已 由上层 的递归 加入 了，不能 再用。 使用used 数组 来标识之， 然后跳过。
            // 这跟组合中 使用 hashset 判断是否已存在 是一个意思
            // 2: 另外一种可能 是， 当不是首次进入 该 for loop 时， 当前元素 和其前方元素相等， 而且 前一个 元素 没有 加入 当前排列，就不能 使用当前元素 了。
            // 例子： 输入 1，1，2； 当以第一个 1 打头 的 所有 排列被 找到后， 第二次 进入 该for ， 又碰到了 1， 如果还拿这个 1 再构成排列， 则必然重复了
            // 也 就是说， 排好序后，重复元素中， 只取 第一个  构成新排列，剩下的 全部 跳过.
            // 针对这种情况的 一个特例是， 如果前方元素 已经在 排列中了， 这个新的重复的元素 可以在 后面跟着 没问题， 也就是 为啥 需要 确定 第一个 在不在当前排列中 !used[i-1] ,
            //  第一个 1 开头， 第二个 1  看到 前一个 1 在 排列中， 那它 就可以 进入 排列中
            if (used[i] || i>0 && nums[i] == nums[i-1] && !used[i-1] ) continue;

            used[i] = true;
            cur.add(nums[i]);

            permu2Helper(res, cur, nums, used);
            used[i] = false;
            cur.remove(cur.size()-1 ); // remove the cur num to try next node or element
        }
    }

    //LC 77, return all combs k out of n numbers
    public static List<List<Integer>> combs(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        backtrackComs(res, new ArrayList<>(), n, k, 1);

        return res;
    }

    private static void backtrackComs(List<List<Integer>> res, List<Integer> cur, int n, int k, int start){
        if(cur.size() == k) {
            // 加入结果集， 返回
            res.add(new ArrayList<>(cur)); return;
        }; // already go k numbers , return

        for (int i = start; i <= n; i++) {
            cur.add(i);
            backtrackComs(res, cur, n, k, i+1);
            cur.remove(cur.size()-1);
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(getAllSubsets(new int[]{1, 2, 3}).toArray()));
        System.out.println(Arrays.deepToString(subsetsIterative(new int[]{1, 2, 3}).toArray()));
        System.out.println(Arrays.deepToString(subsetsByBitmask(new int[]{1, 2, 3}).toArray()));

        System.out.println(Arrays.deepToString(subsetsII(new int[]{1, 2, 2}).toArray()));
        System.out.println(Arrays.deepToString(permutations(new int[]{1, 3, 2, 6}).toArray()));
        System.out.println(Arrays.deepToString(permutationsII(new int[]{1, 3, 3, 6}).toArray()));

        System.out.println(Arrays.deepToString(combs(4, 2).toArray()));
    }
}
