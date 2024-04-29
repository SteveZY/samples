package zyz.steve.lchashtable;

/**
 * hash function 跟 key 的取值范围 以及 bucket的数量有直接关系
 * integer - 0 ～ 100k, bucket: 1k, 可以 用 y = x%1000
 * char: a~z , bucket 26, y = x-'a'
 * <p>
 * 主要原则 就是 要把 key 一致的 map 到 bucket里面；最好能 1:1 mapping ，但是一般情况下
 * key的数量都会大于bucket的数量，就会有冲突
 * Chaining, 例如可以用array，linked list， balanced tree 解决冲突
 * <p>
 * 如果 bucket 数量>= key 也可以用open addressing 例如 linear probing,quadratic probing
 * details here https://learn.saylor.org/mod/book/view.php?id=32990&chapterid=12838#:~:text=When%20two%20items%20hash%20to,process%20is%20called%20collision%20resolution.
 */

import zyz.steve.datastruct.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class LCHashTable {

    //https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1176/
    //每个位 的和 除 2 的余数 即为 那个 loner 的对应位
    public int singleNumber(int[] nums) {
        // Loner
        int loner = 0;

        // Iterate over all bits
        for (int shift = 0; shift < 32; shift++) {
            int bitSum = 0;

            // For this bit, iterate over all integers
            for (int num : nums) {

                // Compute the bit of num, and add it to bitSum
                bitSum += (num >> shift) & 1;
            }

            // Compute the bit of loner and place it
            int lonerBit = bitSum % 2;
            loner = loner | (lonerBit << shift);
        }
        return loner;
    }

    /*
     * Template for using hash map to find duplicates.
     * Replace ReturnType with the actual type of your return value.
     */
    /*
     模版
    ReturnType aggregateByKey_hashmap(List<Type>& keys) {
        // Replace Type and InfoType with actual type of your key and value
        Map<Type, InfoType> hashmap = new HashMap<>();
        for (Type key : keys) {
            if (hashmap.containsKey(key)) {
                if (hashmap.get(key) satisfies the requirement) {
                    return needed_information;
                }
            }
            // Value can be any information you needed (e.g. index)
            hashmap.put(key, value);
        }
        return needed_information;
    }*/
    public int[] twoSum(int[] a, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[2];
        for (int i = 0; i < a.length; i++) {
            Integer b = map.get(sum - a[i]);
            if (null != b) {
                ret[0] = i;
                ret[1] = b;
                break;
            }
            map.put(a[i], i);
        }
        return ret;
    }

    //Isomorphic Strings
    //https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1117/
    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.putIfAbsent(s.charAt(i), t.charAt(i));
            if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    public static boolean isIsomorphicII(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }
            map1[s.charAt(i)] = i + 1;
            map2[t.charAt(i)] = i + 1;
        }
        return true;

    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            String[] tmp = list1;
            list1 = list2;
            list2 = tmp;
        }
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            map2.put(list2[i], i);
        }
        int curMin = Integer.MAX_VALUE;
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < list1.length; i++) {
            Integer idxIn2nd = map2.get(list1[i]);
            if (idxIn2nd == null) {
                continue;
            }
            int curValue = map2.get(list1[i]) + i;
            if (curValue < curMin) {//找到更小的，移除前值
                res.clear();
                curMin = curValue;
                res.add(i);
            } else if (curValue == curMin) {
                //找到一个同样小的
                res.add(i);
            }
        }
        String[] ret = new String[res.size()];
        int count = 0;
        for (Integer ee : res) {
            ret[count++] = list1[ee];
        }
        return ret;
    }

    public static int firstUniqChar(String s) {
        int[][] map = new int[129][2];//第一列 为 出现次数，第二列存储 第一次出现的位置
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)][0]++;
            if (map[s.charAt(i)][1] == 0) {
                map[s.charAt(i)][1] = i;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int[] ints : map) {
            if (ints[0] == 1) {
                min = Math.min(min, ints[1]);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int firstUCharImproved(String s) {
        int[] freq = new int[26];
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            freq[ch - 'a']++;
        }

        int i = 0;
        for (char ch : chars) {
            if (freq[ch - 'a'] == 1) {
                return i;
            }
            i++;
        }

        return -1;
    }
    //如果是已经排序好的两个数组
    //可以 从头 开始搜索，一个一个比对，遇到较小的值，就向后移动，找更大的值；相等的话，就加入结果集，并且两个指针都向后移动

    //https://leetcode.com/submissions/detail/1132884662/?from=explore&item_id=1178
    //把较长的数组 作为第二个参数
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        //可以使用数组 当成hashmap，节省内存，运行速度更快。如下方法，跟计数排序的做法差不多
        /*int[] count = new int[1001];
        for(int n : nums1) count[n]++;

        //结果集必然 短于 较小者，所以分配较小的内存
        int res[] = new int[Math.min(nums1.length, nums2.length)];
        int idx = 0;
        for(int n : nums2){
            if(count[n] > 0){
                count[n]--;
                res[idx++] = n;
            }
        }
        //返回的长度 由 idx 的决定，不会大于 res 的长度
        return Arrays.copyOf(res,idx);*/

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int e : nums1) {
            map1.put(e, map1.getOrDefault(e, 0) + 1);
        }
        for (int e : nums2) {
            map2.put(e, map2.getOrDefault(e, 0) + 1);
        }
        for (int e : map1.keySet()) {
            Integer c = map2.getOrDefault(e, 0);
            if (!c.equals(0)) {
                int r = Math.min(c, map1.get(e));
                for (int i = 0; i < r; i++) {
                    result.add(e);
                }
            }
        }
        int[] toReturn = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            toReturn[i] = result.get(i);
        }
        return toReturn;
        /**
         * improved
         *
         *
         * Map<Integer,Integer> map = new HashMap<>();
         *         for(int i:nums1) {
         *             int freq = map.getOrDefault(i,0);
         *             map.put(i,freq+1);
         *         }
         *         List<Integer> al = new ArrayList<>();
         *         for(int i:nums2) {
         *         //在第二个数组里面找到的话，就加入list，并将Freq 减少
         *         //本法也可以处理 当第二个数组 无法全部加载进内存的情形
         *             if(map.get(i) !=null && map.get(i)>0) {
         *                 al.add(i);
         *                 map.put(i,map.get(i)-1);
         *             }
         *         }
         *         int i=0;
         *         int op[] = new int[al.size()];
         *         while(i<al.size()) {
         *             op[i] = al.get(i);
         *             i++;
         *         }
         *         return op;
         */
    }

    //https://leetcode.com/submissions/detail/1132966572/?from=explore&item_id=1121
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer preIdx = map.get(nums[i]);
            if (null != preIdx && i - preIdx <= k) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
        /**
         *  if(nums == null || nums.length < 2 || k == 0)
         *             return false;
         *         int i = 0;
         *         HashSet<Integer> hset = new HashSet<Integer>();
         *         for(int j = 0; j < nums.length; j++) {
         *             if(!hset.add(nums[j])){
         *                 return true;
         *             }
         *             if(hset.size() >= k+1){
         *                 hset.remove(nums[i++]);
         *             }
         *         }
         *         return false;
         *     }
         */
    }

    public static boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer> m = new HashMap<>();

        Set<Character> s = new HashSet<>();
        //验正行
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!s.add(board[i][j])) {
                        return false;
                    }
                }
            }
            s.clear();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    if (!s.add(board[j][i])) {
                        return false;
                    }
                }
            }
            s.clear();
        }

        //验证sub boxes
        //        int startx=0, starty=0;
        for (int starty = 0; starty < 9; starty += 3) {
            for (int startx = 0; startx < 9; startx += 3) {
                for (int i = startx; i < startx + 3; i++) {
                    for (int j = starty; j < starty + 3; j++) {
                        if (board[j][i] != '.') {
                            if (!s.add(board[j][i])) {
                                return false;
                            }
                        }
                    }
                }
                s.clear();
            }
        }

        return true;

    }

    public static boolean isValid(char[][] board, int row, int col, int num) {
        //check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }

        }
        //check colm
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }

        }
        //check 3X3 matrix in which that num lie
        int srow = (row / 3) * 3;
        int scol = (col / 3) * 3;
        for (int i = srow; i < srow + 3; i++) {
            for (int j = scol; j < scol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;

    }

    //该方法，重复计算了
    public boolean isValidSudokuII(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                char num = board[i][j];
                board[i][j] = '.';
                if (!isValid(board, i, j, num)) {
                    return false;
                }
                board[i][j] = num;
            }
        }
        return true;
    }

    /** 这个跟用Hashset 没有本质区别。这种计数法，速度较快
     * public boolean isValidSudoku(char[][] board)
     *     {
     *         int count[]=new int[10];
     *         // check rows;
     *         for(int i=0;i<9;i++)
     *         {
     *             Arrays.fill(count, 0);
     *             for(int j=0;j<9;j++)
     *             {
     *                 if(board[i][j]!='.')
     *                 {
     *                     count[board[i][j]-'0']++;
     *                     if(count[board[i][j]-'0']>1)
     *                         return false;
     *                 }
     *             }
     *         }
     *
     *
     *         // check cols;
     *         for(int i=0;i<9;i++)
     *         {
     *             Arrays.fill(count, 0);
     *             for(int j=0;j<9;j++)
     *             {
     *                 if(board[j][i]!='.')
     *                 {
     *                     count[board[j][i]-'0']++;
     *                     if(count[board[j][i]-'0']>1)
     *                         return false;
     *                 }
     *             }
     *         }
     *
     *         // check cube
     *         for(int i=0;i<9;i+=3)
     *         {
     *             for(int j=0;j<9;j+=3)
     *             {
     *                 Arrays.fill(count, 0);
     *                 for(int r=i;r<i+3;r++)
     *                 {
     *                     for(int c=j;c<j+3;c++)
     *                     {
     *
     *                         if(board[r][c]!='.')
     *                         {
     *                             count[board[r][c]-'0']++;
     *                             if(count[board[r][c]-'0']>1)
     *                                 return false;
     *                         }
     *                     }
     *                 }
     *             }
     *         }
     *         return true;
     *     }
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();

        for (String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String key = new String(cs);
            if (group.containsKey(key)) {
                group.get(key).add(s);
            } else {
                List<String> curGrp = new ArrayList<>();
                curGrp.add(s);
                group.put(key, curGrp);

            }
            //            group.getOrDefault(key, new ArrayList<>()).add(s);

        }
        return new ArrayList<>(group.values());
        //        return ret;
    }

    public static String findDuplicateSubtrees(TreeNode node, Map<String, Integer> map, List<TreeNode> nodes) {
        if (null == node) {
            return "";
        }
//in order 遍历 获取所有子树，存储
        String str = "["
                + findDuplicateSubtrees(node.left, map, nodes) + node.val
                + findDuplicateSubtrees(node.right, map, nodes)
                + "]";
        Integer cnt = map.getOrDefault(str, 0);
        if (cnt.compareTo(1) >= 0) {
            //同样的子树已经存在
            //found duplicates
            nodes.add(node);
            System.out.println(node.val);
        }
        map.put(str, cnt + 1);

        return str;
    }

    //https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1135/
    //最长 的子字符串 substring  not subsequence

    //某网页的详细分析 https://liweiwei1419.github.io/leetcode-solution-blog/leetcode-problemset/dynamic-programming/0003-longest-substring-without-repeating-characters.html#%E6%96%B9%E6%B3%95%E4%BA%8C%EF%BC%9A%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92
    public static int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        char[] chars = s.toCharArray();
        int counter;
        int max = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if(map.containsKey(c)) {
                //发现重复
                Integer idx = map.get(c);
                if(start<=idx) {
                    //只有当重复字符位于当前的start ～ end范围之内 才需更新
                    //起始位置调整为重复字符的 后一个位置
                    start = idx + 1;
                }
                //更新 c 字符 位置

            }
            counter = i-start+1;
            map.put(c,i);//记录当前字符的位置信息
            if(max<counter){
                max=counter;
            }
        }
        return max;

    }
    public static int lengthOfLongestSubstringDP(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return len;
        }

        // dp[i] 表示以 s[i] 结尾的最长不重复子串的长度
        // 因为自己肯定是不重复子串，所以初始值设置为 1
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        // 因为要考虑 dp[i - 1]，索引得从 1 开始，故 d[s[0]] = 0 得先写上
        for (int i = 1; i < len; i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c) && dp[i - 1] >= i - map.get(c)) {
                //这里的DP 不像是别的DP 每一步都是存储最优解
                //而是总是存储 包含 当前字符的情况下，所能获得的最长子串长度
                //这样以来就不用保存，上一步 最长子串的左侧 开始 位置号了
                //因此，没遇到重复字符，最长值就会被 reset 成较小值，循环
                //最终，在整个数组中找到最大值，即为答案
//                if (dp[i - 1] >= i - map.get(c)) {
                    //当重复字符 即（pre position） 跟 当前位置 的 距离 ，不大于上一步的 长度
                    //那么含有当前字符的 最长长度 只能如下
                    dp[i] = i - map.get(c);
//                } else {
                    //如果最大长度 都无法touch之前的重复字符，就可以直接 +1
//                    dp[i] = dp[i - 1] + 1;
//                }
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            // 设置字符与索引键值对
            map.put(c, i);
        }
        // 最后拉通看一遍最大值
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> j = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            j.add(jewels.charAt(i));
        }
        int ret =0;
        for (int i = 0; i < stones.length(); i++) {
            if(j.contains(stones.charAt(i))){
                ret++;
            }
        }
        return ret;
    }

    //map 存储给定元素的出现次数，第二部 使用 PQ 根据 value 存储map
    public static Integer[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(k, Map.Entry.comparingByValue());
        Map<Integer,Integer> map = new HashMap<>();
        for(int e:nums){
            map.put(e,map.getOrDefault(e,0)+1);
        }
        for(Map.Entry<Integer,Integer> e:map.entrySet()){
            pq.add(e);
            if(pq.size()>k)pq.poll();
        }
        Integer[] xxx = pq.stream().map(Map.Entry::getKey).toArray(Integer []::new);
        return xxx;
    }

}
