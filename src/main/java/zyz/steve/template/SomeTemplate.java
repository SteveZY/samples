package zyz.steve.template;

public class SomeTemplate {
    // 二分
    public static  int binSearch(int[] nums, int target){
        int left = 0,         right = nums.length-1;

        while (left <= right){
            int mid = (left + right) /2;
            if(nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid+1;
            else right = mid-1;
        }
        return -1;
    }
    public static int binSearchRecursive(int[] nums, int low, int high, int target) {

        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (nums[mid] > target) return binSearchRecursive(nums, low, mid - 1, target); // target 较小， 找左侧
        if (nums[mid] < target) return binSearchRecursive(nums, mid + 1, high, target);

        return mid;
    }
    public static void main(String[] args){

        int[] aa= {3,5,7,8,9};
        System.out.println(binSearch(aa, 9));
        System.out.println(binSearchRecursive(aa, 0,4,9));
    }
}
