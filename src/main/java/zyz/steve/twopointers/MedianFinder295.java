package zyz.steve.twopointers;

import java.util.Arrays;
import java.util.TreeMap;

//https://leetcode.cn/problems/find-median-from-data-stream/description/?company_slug=microsoft
public class MedianFinder295 {
    double median;
    int length;
    TreeMap<Integer,Integer> nums ;//= new TreeMap<>();
    int[] left, right;//左右指针
    public MedianFinder295() {
        left = new int[2];
        right = new int[2];
        nums = new TreeMap<>();
    }
    public void addNum(int num){
        /**
         * 可以维护两个 PQs，一个Min heap 一个 max heap 然后 min + max /2 就为median
         *
         *或者维护一个有序集合，例如 TreeMap， 输入的数字作为 key，value 为 该数字出现的次数；
         * 然后用两个指针指向median的左右两个值，在有重复值的时候，并且需要记录指向的是第几个
         *
         * length of nums ==0 ? left.val = right.val = num; left.idx = right.idx=1//初始化 left。 right
         * length is odd?
         *      num< left.val? left 向左移动一个；
         *      else right 向右移动一个
         *  else //even
         *      num between left and right？ left 向右移动，right 向左移动
         *      else num >= right.val? left 向右 移动一个 即增加 //右侧多了一个元素，故左指针向右，
         *      else： right 向左移动一个 即减小，并令 left = right //左侧多了一个元素，故右指针向左移动维持平衡。另外此时left right 应该指向同一个，故需要将left同步一下
         * left = {}, right={}
         *
         */
        nums.put(num,nums.getOrDefault(num,0)+1);
        //实现第二种
        if(0==length){
            left[0]=num;left[1]=1;
            right[0]=num;right[1]=1;
        }else if(length%2==1){//odd
            if(num<left[0]){//左侧变多一个元素
                moveLeft(left);
            }else {//右侧变多
                moveRight(right);
            }

        }else{//even
            // between
            if(num>left[0]&& num<right[0]){
                moveRight(left);
                moveLeft(right);
            }else if(num>=right[0]) {
                moveRight(left);

            }else {
                //左侧变多
                moveLeft(right);
                //左侧的新元素，如果正好等于 left，就会被插入left的右侧，故必须调整left 跟right 相同
                //当然如果再加if 单独处理 亦可，不过 就浪费时间了。
                synchronize(right, left);
            }
        }
        length++;

    }

    private void synchronize(int[] orig, int[] d) {
//        d = Arrays.copyOf()
        d[0]=orig[0];
        d[1]=orig[1];
    }

    private void moveRight(int[] p) {
        p[1]++;
        if(nums.get(p[0])<p[1]){//已没有更多当前元素
            Integer next = nums.ceilingKey(p[0] + 1);
            p[0]=next;
            p[1]=1;//指向第一个
        }
    }

    private void moveLeft(int[] p) {
        p[1]--;
        if(p[1]<1){//已经指向当前值的第一个，故需要拿到下一个较小值
            Integer next = nums.floorKey(p[0] - 1);
            p[0]= next;
            p[1] = nums.get(next);//设定序号 万一该值重复出现
        }
    }

    public double findMedian() {return (left[0]+right[0])/2.0;}
}
