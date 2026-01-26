package zyz.steve.twopointers;

import zyz.steve.Utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TwoPointers {
    //leetcode 209
    //https://leetcode.com/problems/minimum-size-subarray-sum/
    public static int minSubArrayLen(int target, int[]nums){
        int left=0;
        int right=0;
        int s =0;
        int len = Integer.MAX_VALUE ;
        for (; left < nums.length; left++) {
             s = s+ nums[left];
             while(s>= target) {
                 //更新 len，保存较小的那个
                 len = Math.min(len,left - right + 1) ;
                 s = s - nums[right++];
//                 right++;

             }

        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }

    //sample from leetcode but it does have bug
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int start = 0;
        int end = numbers.length-1;
        while(numbers[start]+numbers[end]!=target){
            if(numbers[start]+numbers[end]<target){
                start++;
            }else {
                end--;
            }
        }
        result[0]=start+1;
        result[1]=end+1;
        return result;
    }
    public static int countOnes(int []a){
        int num1 =0;
        int maxNumOfOnes = 0;
        for (int j : a) {
            if (j == 1) {
                num1++;
            } else {
                maxNumOfOnes = Math.max(maxNumOfOnes, num1);
                num1 = 0;
            }
        }
        return Math.max(maxNumOfOnes,num1);
    }

    public static int countOnesWithPointers(int[] a) {
        // 快慢 指针
        int i = 0, k = 0;
        int maxNum = 0;
        for (; i < a.length; i++) {
            //i 代表end
            if (0 == a[i]) {
                if (k != i) {
                    maxNum = Math.max(maxNum, i - k);
                    //碰到零就重置 慢指针，即start
                    k = i;
                }
                k++;
            }
        }
        return Math.max(maxNum, i - k);
    }
    //https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1153/
    // for sorted arrays
    public static int[] twoSumII(int[] a, int sum) {
        int i = 0, j = a.length - 1;
        int[] ret = new int[2];
        while (i < j) {
            if ((a[i] + a[j]) < sum) {
                i++;
            } else if ((a[i] + a[j]) > sum) {
                j--;
            } else {
                //found
                ret[0] = i+1;
                ret[1] = j+1; // add one before return per requirement
                return ret;
            }
        }

        return ret;
    }
    public static void reverseString(char[] s) {
        int left=0;
        int right=s.length-1;
        for(int i=0; i< s.length >>1; i++){
            if(left >= right) return;
            char c = s[left];
            s[left++] = s[right];
            s[right--]=c;
        }
    }

    public static void rotateArray(int[] a, int k){
        if (k <= 0 || k == a.length) {
            return;
        }
        k %= a.length;
        int end = a.length - 1;

        //reverse the whole array
        reverseArray(a, 0, end);

        //reverse the second part
        reverseArray(a,k,end);
        //reserve the first part
        reverseArray(a, 0, k-1);
    }
    public static void reverseArray(int [] a,int start, int end){
        while (start<end){
            int tmp = a[start];
            a[start++] = a[end];
            a[end--] = tmp;

        }
    }

    /**
     * 将一个数组的0 都移动到尾部
     * [0,1,4,0,3,4]
     * step by step like this
     * [1,0,4,0,3,4]
     * [1,4,0,0,3,4]
     * [1,4,3,0,0,4]
     * 每找到一个非零就跟 zerostart 交换位置，然后改变zerostart指向位置
     * @param nums
     */
    public static void moveZeros(int[] nums) {
        int zeroStart = 0; // 假定开始循环是，zeros 从 第0 index 开始 就可以。循环中发现有不是0 的，就调整该值 使其成为 所有zero的起点
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (zeroStart != i) {
                    //初始的时候 二者相等，无需操作
                    //不是同一个位置，交换
                    int tmp = nums[zeroStart];
                    nums[zeroStart] = nums[i];
                    nums[i] = tmp; // 可以直接写 0 吧
                }
                //当发现不为零的数，就把zero start后移，否则维持不变
                zeroStart++;
            }
            //            else if(nums[i] == 0 && zeroStart == -1) {
            //                zeroStart = i;
            //            }
        }

    }
    //#11 water container

    /**
     * 从左右同时开始，对比左右数值，较小的一侧移动到下一个 index，获取下一个值；更新max
     * 最后当指针相遇时，循环结束，返回最大值。
     * 首先，所围区域的面积总是由较低那一侧的数值决定，
     * 当指针移动时，必导致我所围区域的长度减少，故而希望移动较低的一侧，希望寻找到较大面积
     * @param arr
     * @return
     */
    public static int getMostWater2Contain(int[] arr) {
        int i = 0;//左侧指针
        int j = arr.length - 1;//右侧指针
        int max = 0;
        while (i < j) {

            int min;
            int distance = j - i;
            if (arr[i] <= arr[j]) {
                min = arr[i];
                i++;
            } else {//move to right
                min = arr[j];
                j--;
            }
            int curContainVol = min * distance;
            max = Math.max(max, curContainVol);
        }

        return max;
    }

    public void testFindAnagrams() {
        String s = "bbbcdacb";
        String p = "abc";
        List<Integer> res = findAnagrams(s, p);

        System.out.println("" + res);
    }
    //#438
    private List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length()) {
            return result;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    counter--;
                }
            }
            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) == 1) {
                        counter++;
                    }
                }
                if (end - begin == t.length()) {
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }
    public static int[] fillItem(String str) {
        int[] item = new int[2];

        str.chars().forEach(c -> {
            if (c == '0') {
                item[0]++;
            } else {
                item[1]++;
            }
        });
        return item;
    }
    public static int costOfRemovalZeros(String str){
        int[] onesZeros = fillItem(str);
        int cost0 = onesZeros[0];
        int cost1= 0;
//        int curCost = cost0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                cost1++;
                if (cost1 >= cost0) {
                    cost1--;
                    break;
                }
            } else {
                cost0--;
            }
        }
        //从右侧再测试
        for (int i = str.length()-1; i >=0 ; i--) {
            if(str.charAt(i) == '1'){
                cost1++;
                if(cost1>= cost0) {
                    cost1--;
                    break;
                }
            }else {
                cost0--;
            }
        }
        return Math.max(cost0,cost1);
    }

    public static int removeDupsFromSortedArr(int [] a){
        int k =0;//save the last copied index, the next position should be k+1;
        int pre=Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            //            pre = a[i];
            if(a[i] != pre){
                a[k] = a[i];
                k++;
                pre = a[i];
            }
        }
        return k;
    }

}
