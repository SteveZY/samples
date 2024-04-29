package zyz.steve.array;

import zyz.steve.sorting.SortMethod;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class ArrayLCCourse {

    //Given an array of integers nums, calculate the pivot index of this array.
    //
    //The pivot index is the index where the sum of all the numbers strictly to
    // the left of the index is equal to the sum of all the numbers strictly to
    // the index's right.
    //https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
    public static int findPivot(int[] a) {
        //        int pivot = -1;
        if (a == null || a.length == 0) {
            return -1;
        }
        if (a.length < 2) {
            return 0;
        }
        int sumLeft = 0, sumRight = 0;
        for (int i = 1; i < a.length; i++) {
            //计算出 右侧的 和， 即位于 第一个元素 右侧的所有元素的和，也为其最大的范围
            //此时其左侧没有元素，故 sumLeft 为 0
            sumRight += a[i];
        }
        for (int i = 0; i <= a.length - 1; i++) {
            //从左到右遍历
            sumLeft += i == 0 ? 0 : a[i - 1];//注意边界
            if (sumRight == sumLeft) {
                return i;
            }
            sumRight -= i == a.length - 1 ? 0 : a[i + 1];//在开始下一个循环前更新 右侧和，注意边界
        }
        return -1;
    }

    public static int dominantIndex(int[] a) {
        int max = Integer.MIN_VALUE, maxIdx = -1, theSecondMax = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                theSecondMax = max;
                max = a[i];
                maxIdx = i;

            } else if (theSecondMax < a[i]) {
                theSecondMax = a[i];
            }
        }
        if (max == Integer.MIN_VALUE || theSecondMax == Integer.MIN_VALUE) {
            return -1;
        }
        System.out.println("sec is:" + theSecondMax + ":" + max);
        if (max >= theSecondMax << 1) {
            return maxIdx;
        }
        return -1;
    }

    public static int[] plusOne(int[] a) {
        int[] carrier = {1};

        for (int i = a.length - 1; i >= 0; i--) {
            int sum = a[i] + carrier[0];
            if (sum > 9) {
                a[i] = sum % 10;
                carrier[0] = sum / 10;
            } else {
                a[i] = sum;
                carrier[0] = 0;
                break;
            }
        }
        //        IntStream.concat()
        if (carrier[0] == 0) {
            return a;
        }
        return IntStream.concat(Arrays.stream(carrier), Arrays.stream(a)).toArray();
        //        return
    }

    public static int[] plusOneOptimised(int[] a) {

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] < 9) {
                a[i]++;
                return a;
            }
            a[i] = 0;
        }
        int[] ret = new int[a.length + 1];
        ret[0] = 1;
        //        int [] carrier = {1};
        //        return IntStream.concat(Arrays.stream(carrier), Arrays.stream(a)).toArray();

        return ret;
    }

    public static void findDiagonalOrder(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int row = 0, col = 0;
        boolean up = true;
        while (row < m && col < n) {
            if (up) {
                //斜向上
                while (row > 0 && col < n - 1) {
                    System.out.println(a[row][col]);
                    row--;
                    col++;
                }
                //当前对角线的最后一个
                System.out.println(a[row][col]);
                if (col == n - 1) {
                    row++;
                } else {
                    col++;
                }
            } else {
                //斜向下
                while (row < m - 1 && col > 0) {
                    System.out.println(a[row][col]);
                    row++;
                    col--;
                }
                System.out.println(a[row][col]);
                if (row == m - 1) {
                    col++;
                } else {
                    row++;
                }
            }
            up = !up;
        }
    }

    public static void findDiagonalOrderV2(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int sumIdx = m + n - 1;
        int row = 0, col = 0;

        for (int i = 0; i < sumIdx; i++) {
            if ((i & 1) == 0) {//斜向上
                while (row > 0 && col < n - 1) {
                    System.out.println(a[row][col]);
                    row--;
                    col++;
                }
                System.out.println(a[row][col]);
                //已到边界，准备调转方向
                if (col == n - 1) {//到了最后一列，向下移动
                    row++;
                } else {
                    //到了最后一列
                    col++;
                }

            } else {
                while (row < m - 1 && col > 0) {
                    System.out.println(a[row][col]);
                    row++;
                    col--;
                }
                System.out.println(a[row][col]);
                if (row >= m - 1) {
                    //到了最后一行
                    col++;
                } else {
                    row++;
                }
            }

        }
    }

    public static List<Integer> findSpiralOrder(int[][] a) {
        //记录边界，每到边缘一次，就更新相应的边界
        int top = 0, bottom = a.length, left = 0, right = a[0].length;
        List<Integer> ret = new ArrayList<>(bottom * right);
        while (true) {
            //重设其实坐标row, col，开始下一轮
            //from top-left corner
            //1.left->right
            int row = top, col = left;
            while (col < right) {
                System.out.println(a[row][col]);
                ret.add(a[row][col]);
                col++;
            }

            top++;//fix top
            if (top >= bottom) {
                break;
            }
            //重设其实坐标row, col，开始下一轮
            row = top;
            col = right - 1;
            //top -> bottom
            while (row < bottom) {
                System.out.println(a[row][col]);
                ret.add(a[row][col]);
                row++;
            }

            right--;//fix right
            if (right <= left) {
                break;
            }

            col = right - 1;
            row = bottom - 1;
            //right->left
            while (col >= left) {
                System.out.println(a[row][col]);
                ret.add(a[row][col]);
                col--;
            }
            bottom--;//fix bottom
            if (bottom <= top) {
                break;
            }
            col = left;
            row = bottom - 1;
            //bottom->top
            while (row >= top) {
                System.out.println(a[row][col]);
                ret.add(a[row][col]);
                row--;
            }
            left++;// fix left
            if (left >= right) {
                break;
            }
        }
        return ret;
    }

    //Given an integer numRows, return the first numRows of Pascal's triangle.
    public static List<List<Integer>> generatePascal(int numRows) {
        List<List<Integer>> ret = new ArrayList<>(numRows);
        ArrayList<Integer> line1 = new ArrayList<>(1);
        ArrayList<Integer> line2 = new ArrayList<>(2);
        line1.add(1);

        ret.add(line1);
        if (numRows == 1) {
            return ret;
        }
        line2.add(1);
        line2.add(1);
        ret.add(line2);
        if (numRows == 2) {
            return ret;
        }
        for (int i = 2; i < numRows; i++) {
            ArrayList<Integer> curline = new ArrayList<>(i + 1);
            List<Integer> prevLine = ret.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    curline.add(1);
                } else {
                    curline.add(prevLine.get(j - 1) + prevLine.get(j));
                }
            }
            ret.add(curline);
        }
        return ret;
    }

    //纯数学方法
    //二项式系数遵循 C(n,0), C(n,1), C(n,2)...C(n,n-1), C(n,n);从n个元素取 0个，1个，2个 等等
    //递归 计算 C
    // C(n,m) = C(n,m-1)*((n-(m-1))/m) //
    //行号从0开始
    public static List<List<Integer>> generatePascalMath(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        //第一行
        ArrayList<Integer> line1 = new ArrayList<>(1);
        line1.add(1);
        ans.add(line1);
        if (numRows == 1) {

            return ans;
        }
        //总是从第二行开始 即 那一行 仅包含两个数字 1 1
        for (int i = 1; i < numRows; i++) {//第二行，行号设为1，第一行行号 设置为0
            int prevEle = 1;//C(n,0)
            List<Integer> list = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                list.add(prevEle);
                //计算下一个系数
                prevEle = prevEle * (i - j) / (j + 1);//此处由于是计算下一个系数，而j 依旧指向当前位置，故需 先 加一 才符合上述通项公式

            }
            ans.add(list);

        }
        return ans;
    }

    //行号从 1开始
    public static List<List<Integer>> generatePascalFromLC(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            int n = 1;
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                list.add(n);
                n = n * (i - j) / j;
            }
            ans.add(list);
        }
        return ans;
    }

    //Pascal Triangle II
    //https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1171/
    public static List<Integer> getRowOfPascalTri(int rowIndex) {
        ArrayList<Integer> line1 = new ArrayList<>(rowIndex + 1);

        Long prevEle = 1L;//C(n,0)
        for (int j = 0; j <= rowIndex; j++) {
            line1.add(prevEle.intValue());
            //计算下一个系数
            prevEle = prevEle * (rowIndex - j) / (j + 1);//此处由于是计算下一个系数，而j 依旧指向当前位置，故需 先 加一 才符合上述通项公式
        }
        return line1;
    }

    public static int removeExtraSpaces(char[] a, int start, int end) {
        int k = start;//较慢指针，指向要复制的目的 index
        boolean isPreSpace = false;
        //i为普通指针，每次都向后移
        for (int i = start; i <= end; i++) {
            if ((a[i] == ' ' && !isPreSpace)) {
                isPreSpace = true;
                //                char tmp = a[i];
                //                a[i] = a[k];
                a[k] = a[i];
                k++;
            } else if (a[i] != ' ') {
                isPreSpace = false;
                a[k] = a[i];
                k++;
            }
        }
        return a[k - 1] == ' ' ? k - start - 1 : k - start;//如果最后一个为space，不计算到长度内
    }

    //https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1164/
    public static String reverseWordsInAString(String str) {
        char[] carr = str.toCharArray();
        //找到开始
        int start = 0;
        for (int i = 0; i < carr.length; i++) {
            if (carr[i] != ' ') {
                start = i;
                break;
            }
        }
        //删除无用字符, 更新的长度
        int newLen = removeExtraSpaces(carr, start, carr.length - 1);
        //反转 除空格后的部分
        reverseArray(carr, start, start + newLen - 1);

        //前后的多余空格 已经全部去除
        //开始定位各个word，并一个一个反转
        int j = start;//慢指针，指向一个word的开始
        for (int i = start; i <= start + newLen; i++) {
            if ((i == start + newLen) || carr[i] == ' ') {
                //空格分隔符 找到了,或者结束了
                reverseArray(carr, j, i - 1);
                j = i + 1;//重置 慢指针为空格/结束 的下一个字符
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + newLen; i++) {
            sb.append(carr[i]);
        }
        return sb.toString();
    }

    public static void reverseArray(char[] a, int start, int end) {
        while (start < end) {
            char tmp = a[start];
            a[start++] = a[end];
            a[end--] = tmp;
        }
    }

    //https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3240/
    public static int findMinNonNegative(int[] a) {
        //a 是非 递减 数组
        int i = 0;
        for (; i < a.length; i++) {
            if (a[i] >= 0) {
                break;
            }
        }
        return i - 1;
    }

    public static int[] squareSortedArray(int[] a) {
        //找到 最小非负值 的index
        int maxIdxOfNeg = findMinNonNegative(a);
        //a new array for result after merge
        int[] ret = new int[a.length];
        int i = maxIdxOfNeg, j = maxIdxOfNeg + 1;
        //        int k =0;//下一个存储位置
        for (int k = 0; k < a.length; k++) {
            if (i >= 0 && j < a.length) {

                if (Math.abs(a[i]) <= a[j]) {
                    ret[k] = a[i] * a[i];
                    i--;
                } else {
                    ret[k] = a[j] * a[j];
                    j++;
                }
                continue;
            }
            if (i < 0) {
                //negative part exhausted
                ret[k] = a[j] * a[j];
                j++;
            } else {
                //non-negative part exhausted
                ret[k] = a[i] * a[i];
                i--;
            }
        }
        return ret;
    }

    public static int[] squareSortedArrayII(int[] a) {
        int i = 0, j = a.length - 1, k = a.length - 1;
        int[] ret = new int[a.length];
        while (i <= j) {
            int left = Math.abs(a[i]);
            int right = Math.abs(a[j]);
            if (left >= right) {
                ret[k] = left * left;
                i++;
            } else {
                ret[k] = right * right;
                j--;
            }
            k--;

        }
        return ret;
    }

    //https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3253/
    //数组a的 后面部分 均为0 ，所以 就从后面开始存放较大的值，一直先前到最后
    public static void merge(int[] a, int m, int[] b, int n) {
        int i = m - 1, j = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if (i < 0) {
                a[k] = b[j--];
            } else if (j < 0) {
                a[k] = a[i--];
            } else if (a[i] > b[j]) {
                a[k] = a[i--];
            } else {
                a[k] = b[j--];
            }
        }
    }

    public static boolean checkIfDblExists(int[] a) {
        Set<Integer> setss = new HashSet<>(a.length);
        for (int j : a) {

            if (setss.contains(j * 2)) {
                //包含 double
                return true;
            }
            if (j % 2 == 0 && setss.contains(j >> 1)) {
                //已经包含half
                return true;
            }
            setss.add(j);
        }
        return false;
    }
//https://leetcode.com/explore/learn/card/fun-with-arrays/527/searching-for-items-in-an-array/3251/
    public static boolean validMountainArrII(int[] a) {
        int peakIdx =0;
        if (a.length < 3) {
            return false;
        }
        for (int i = 1; i < a.length; i++) {
            if (a[i] <= a[i - 1]) {
                peakIdx = i-1;
                break;
            }
        }
        //peak在开头或者结尾，就返回false
        if(peakIdx == 0 || peakIdx == a.length-1) return false;
        //peak 之后的元素需要是单调递减
        for (int i = peakIdx+1; i < a.length; i++) {
            if(a[i]>=a[i-1]){
                //没有单减
                return false;
            }
        }
        return true;
    }

    //TODO: 有bug 需要fix
    public static boolean validMountainArr(int[] a) {
        if (a.length < 3) {
            return false;
        }
        int[] b = new int[a.length];
        for (int i = 1; i < a.length; i++) {
            //求导数
            b[i] = a[i] - a[i - 1];
        }
        boolean numOfChange = false;
        for (int i = 1; i < a.length - 1
                ; i++) {
            if (b[i] == 0) {
                return false;
            } else if (b[i] > 0 && b[i + 1] < 0) {
                //找到一个山峰拐点
                if (!numOfChange) {
                    numOfChange = true;
                } else {
                    return false;
                }
            } else if (b[i] < 0 && b[i + 1] >= 0) {
                //山谷拐点
                return false;
            }
        }
        return numOfChange;
    }
    //https://leetcode.com/submissions/detail/1130475420/?from=explore&item_id=3259
    public static int [] replaceEleWithMaxFromRight(int []a){
        int curMax = Integer.MIN_VALUE;
        int newEle = a[a.length-1];
        //从后往前搜索
        for (int i = a.length-2; i >=0; i--) {
            curMax = Math.max(curMax,newEle);
            newEle = a[i];//更新 需要新计入的 元素，下次循环使用
            a[i] = curMax;
        }
        a[a.length-1] = -1;
        return a;
    }
    //https://leetcode.com/explore/learn/card/fun-with-arrays/511/in-place-operations/3260/
    public static int [] sortArrayByParity(int []a){
        int evenWriter = 0,oddW= a.length -1 ;
        while (evenWriter < oddW){
            if(a[oddW]%2 ==0 && a[evenWriter]%2!=0){
                //swap
                int tmp = a[evenWriter];
                a[evenWriter++] = a[oddW];
                a[oddW--] = tmp;
            }else if (a[evenWriter]%2==0){
                evenWriter++;//已经是偶数，移动指针
            }else {
                oddW--;//已经是奇数，移动指针
            }
        }
//        int oddEnd = oddWriter;
//        int evenS = evenW;
//        if(a[evenW]%2 == 0){
//            oddEnd--;
//        }else {
//            evenS++;
//        }
//        SortMethod.quickSort(a,0,oddEnd);
//        SortMethod.quickSort(a,evenS,a.length-1);
        return a;
    }
    //https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3228/
    public static int heightChecker(int [] heights){
        int[] b = //b is in the original order
                Arrays.copyOf(heights, heights.length);
        Arrays.sort(b);
        int count = 0;
        for (int i = 0; i < b.length; i++) {
            if (heights[i] != b[i]) {
                count++;
            }
        }
        return count;
    }
    static void countingSort(int [] a){
        int []m = new int[101];
        for(int e:a){
            m[e]++;//将对应的index 增一，记录该位置 e 的个数，即 在a中元素 e 的个数
        }
        int writerIdx = 0;
        for (int i = 0; i < m.length; i++) {
            //遍历所有位置
            while (m[i]>0){//计数不为零的位置，说明该位置在原始数组中，拿出所有元素，放入排序后的数组
                a[writerIdx++]=i;
                m[i]--;
            }
        }
    }
    //https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3231/
    static int thirdMax(int [] a){
        long [] maxs = new long[3];//任意max 都可以求出
        Arrays.fill(maxs,Long.MIN_VALUE);
        for (long e:a){
            for (int i = 0; i <3; i++) {
                //如果比当前大值大 则更新它，并用旧值去测试下一个较大值；
                // 若相等，退出循环；
                // 否则继续测试下一个较小的大值
                if (e > maxs[i]) {
                    long tmp = maxs[i];
                    maxs[i] = e;
                    e=tmp;
                }else if (e==maxs[i]){
                    break;
                }
            }
        }
        if(maxs[2] != Long.MIN_VALUE)return (int)maxs[2];
        return (int)maxs[0];
    }
    static int findMax(int[ ] a){
        int max = 0;
        for(int e:a){
            max=Math.max(e,max);
        }
        return max;
    }
    //https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3270/
    public static List<Integer> findDisappearedNumbers(int[] a){
        int [] b = new int[a.length+1];
        //counting first
        for(int e:a){
            b[e]++;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i < b.length; i++) {
            if(b[i] == 0 ){
                ret.add(i);
            }
        }
        return ret;
    }

    //https://leetcode.cn/problems/find-all-duplicates-in-an-array/description/?company_slug=microsoft
    public static List<Integer> findDuplicates(int[]a){
        int len = a.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if(a[i] > len) {
                //调整一下
                int trueAi = a[i] - len;
                int trueIdx = trueAi - 1;
                if (a[trueIdx] > len)
                    res.add(a[i]-len);
                else a[trueIdx]+=len;
            }
            else {
                //标记
                if(a[a[i] - 1] > len) {
//                    dups
                    res.add(a[i]);
                    continue;
                }
                a[a[i] - 1] += len;

            }
        }
        return res;

    }
    //https://leetcode.cn/problems/word-break-ii/description/?company_slug=microsoft
    //https://zhuanlan.zhihu.com/p/448969860


    //https://leetcode.com/problems/missing-number/description/
//    https://leetcode.cn/problems/missing-number/solutions/1/gong-shui-san-xie-yi-ti-wu-jie-pai-xu-ji-te3s/
    public static int missingNumber(int [] n){
        for (int i = 0; i < n.length; i++) {
            //idx to swap.
            int idx = i;

            while (idx < n.length && n[idx] < n.length && n[idx]!=idx){
                    exchange(n, idx, n[idx]);
                    idx=n[idx];

            }
        }
        int pos = 0;
        for (; pos < n.length; pos++) {
            if(pos!=n[pos]) break;
        }
        return pos;
//        return 0;
    }

    private static void exchange(int [] n, int i, int i1) {
        int tmp = n[i];
        n[i]=n[i1];
        n[i1]=tmp;

    }

    public static int binarySearch (int[] n, int low, int high, int target){
        if(low> high)
            return -low;
        int mid = ((high - low) >> 1) + low;
        if(n[mid]< target)
            return binarySearch(n, mid+1,high,target);
        else if(n[mid] > target){
            return  binarySearch(n,low, mid-1,target);
        }

        return mid;
    }
}
