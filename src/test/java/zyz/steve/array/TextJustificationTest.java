package zyz.steve.array;

import org.junit.Test;
import zyz.steve.aaaa.TypingTest;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.*;

public class TextJustificationTest {
    int ans = 0;

    @Test
    public void testKCloset(){
        int[][] aa = new int[][]{{1, 3}, {-2, 2}};
        kClosest(aa,1);
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int []> pq = new PriorityQueue<>(k, (a,b)

                ->a[0]*a[0]+a[1]*a[1]-b[0]*b[0] -b[1]*b[1]
        );
        for(int[] p : points ){
            pq.offer(p);
        }
        int [][] ans = new int[k][];
//        int sz = pq.size();
        for(int i =0; i< k; i++ ){
            int [] a = pq.poll();
            ans[i]=a;
        }

        return ans;
    }
    @Test
    public void testJump(){
        //target is 0,
        System.out.println((jumpUpOrDown(1, false, 0, 2) )
//                +jumpUpOrDown(1+Math.pow(2,))
        );
    }    private int comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        long res = 1;
        for (int i = 0; i < k; ++i)
            res = res * (n - i) / (i + 1);
        return (int)res;
    }

    @Test
    public void waysToReachStair() {
        int k = 0;
        int res = 0;
        for (int j = 0; j < 31; j++)
            res += comb(j + 1, (1 << j) - k);
//        return res;
        System.out.println(res);
    }
    private int jumpUpOrDown(int i, boolean down, int jump, int target){
        if(i<0) return 0; // not reachable
        if(i>target+1) return 0; //not reachable
        if(i==target ) ans+=1;
        if(i>0 && !down) jumpUpOrDown(i-1,true,jump,target);
        //上一次操作 是 down,只能up

        jumpUpOrDown(i + (int) Math.pow(2, jump), false, jump + 1, target);
        return ans;

    }
    @Test
    public void testDiff(){
        int[] nums = {13, 23, 12};
        int l = Integer.toString(nums[0]).length();
        long total =0;
        int[] cnt = new  int [10];
        for(int i=0;i<l;i++){

            for(int j=0;j< nums.length; j++){
                int di = nums[j]/((int)Math.pow(10,i))%10;
                cnt[di]++;

            }
            for(int m =0; m<10;m++){
                if(cnt[m] ==0) continue;
                for(int n =m+1; n<10;n++){
                    total+= (long) cnt[m] *cnt[n];
                }
            }
            Arrays.fill(cnt,0);
        }
        System.out.println(total);
    }
    @Test
    public void testFullJustify() {
//        System.out.println(TextJustification.fullJustify(new String[] {
//                        "The","day","began","as","still","as","the","night","abruptly","lighted","with","brilliant","flame"
//                },
//                26));
        int a = 123;
        System.out.println((int)Math.pow(a,2));
        System.out.println(TextJustification.fullJustify(new String[] {
                        //                        "This", "is", "an", "example", "of", "text", "justification."

                        "a","words.length","b","aaaaaaaaaaaaaaaaaaa","c"
                },
                20));
    }
    @Test
    public void testReflowandJust(){
        String [] lines = {"The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame" };
        String[] lines6 = {"a b c d"};
        char[] ccc = new char[2];
        Arrays.fill(ccc,'-');
        System.out.println();

        System.out.println(TextJustification.reflowOp(lines, 24));
        System.out.println(TextJustification.reflowOp(lines, 13));
        System.out.println(TextJustification.reflowOp(lines, 12));

        System.out.println(TextJustification.reflowOp(lines6, 4));

        System.out.println(TypingTest.eeeee(lines, 24));

    }
    @Test public void testLeftJustify(){
        StringBuilder sb = new StringBuilder();
        ArrayDeque<String> q = new ArrayDeque<>();
        q.push("a");
        q.push("b");
        TextJustification.leftJustify(sb, q,20);
        System.out.println(sb);
    }

    @Test
    public void testWordWrap(){
        String[] words = new String[] {
                "The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame"
        };
        String[]words2={"Hello"};
        String[]words3={"Hello","Hello"};
        String[]words4={"Well","Hello","world"};
        String[]words5={"Hello","HelloWorld","Hello","Hello"};
        String[]words6={"a","b","c","d"};
        System.out.println(TextJustification.wordWrap(words, 13));
        System.out.println(TextJustification.wordWrap(words, 12));
        System.out.println(TextJustification.wordWrap(words, 20));
        System.out.println(TextJustification.wordWrap(words2, 5));
        System.out.println(TextJustification.wordWrap(words2, 30));
        System.out.println(TextJustification.wordWrap(words3, 5));
        System.out.println(TextJustification.wordWrap(words4, 5));
        System.out.println(TextJustification.wordWrap(words5, 20));
        System.out.println(TextJustification.wordWrap(words6, 20));
        System.out.println(TextJustification.wordWrap(words6, 3));
        System.out.println(TextJustification.wordWrap(words6, 4));
        System.out.println(TextJustification.wordWrap(words6, 1));

        System.out.println(TypingTest.wordWrap(words, 13));

    }

    @Test
    public void testAddThouSep(){
        System.out.println(TextJustification.addThousandSep("19876234.56789"));
        String [][] badge_times = {
                                {"Paul", "1355"},
                                {"Jennifer", "1910"},
                                {"John", "835"},
                                {"John", "830"},
                                {"Paul", "1315"},
                                {"John", "1615"},
                                {"John", "1640"},
                                {"Paul", "1405"},
                                {"John", "855"},
                                {"John", "930"},
                                {"John", "915"},
                                {"John", "730"},
                                {"John", "940"},
                                {"Jennifer", "1335"},
                                {"Jennifer", "730"},
                                {"John", "1630"},
                                {"Jennifer", "5"}
                        };
        timeChecker(badge_times);
//            timeChecker();
        TypingTest.timeChecker(badge_times);

    }
    public void timeChecker(String [][]badgeTimes){

        Map<String, List<Integer>> pt = new HashMap<>();
        for(String[] bt:badgeTimes){
            List<Integer> en ;//= pt.getOrDefault(bt[0],new ArrayList<>());
            if(!pt.containsKey(bt[0])){
                en = new ArrayList<>();
                pt.put(bt[0],en);
            }else{
                en = pt.get(bt[0]);
            }
            en.add(getTime(bt[1]));
        }

        for(String key: pt.keySet()){
            if(checkWoQ(pt.get(key))){
                System.out.println(key);
            }
        }

    }
    boolean check(List<Integer> ts){
        Collections.sort(ts);
//        int counter=0;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i< ts.size();i++){
            while(!q.isEmpty() && ts.get(i)-q.peek()+1>60){
                q.poll();
            }
            q.offer(ts.get(i));
            if(q.size()>2) return true;
        }

        return false;
    }
    int getTime(String t){
        StringBuilder sb = new StringBuilder();
        while(4-t.length()-sb.length()>0)
            sb.append("0");
        String        ts = sb.append(t).toString();

        return Integer.parseInt(ts.substring(0,2)) *60
                + Integer.parseInt(ts.substring(2,4));
    }

    boolean checkWoQ(List<Integer> ts){
        Collections.sort(ts);
        int startIdx = 0
                ;
        for(int i =1; i< ts.size();i++){
            while (startIdx<i&& ts.get(i) - ts.get(startIdx) + 1>60){
                startIdx++;
            }
            if(i-startIdx+1>2){
                return true;
            }
        }
        return false;
    }
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        boolean [] ans = new boolean[queries.length];
        TreeSet<Integer> curSE = new TreeSet<>();
        for(int i=0;i<queries.length;i++ ){
            if(curSE.size()==0) {
                int s = queries[i][0];
                int e = queries[i][1];
                ans[i] = is(nums, s, e);
                curSE.add(s);
                curSE.add(e);
            }else {
                int s = queries[i][0];
                int e = queries[i][1];
                Integer ss = curSE.ceiling(s);
                Integer ee = curSE.floor(e);
            }

        }

        return ans;

    }
    public boolean is(int[] nums,int s, int e) {

        if(e-s < 1) return true;
        int r = nums[s]&1;
        for(int i =s+2; i<= e;i+=2){
            // int newr = nums[i]&1;
            if((nums[i]&1) !=r ) return false;
            // if( r!= newr){
            //     r=newr;
            // }else{
            //     return false;
            // }

        }
        for(int i=s+1;i<=e;i+=2){
            if((nums[i]&1) ==r ) return false;


        }
        return true;
    }
    /**
     * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
     *
     * Aging 有一个整数数组 nums。如果 nums 是一个 特殊数组 ，返回 true，否则返回 false。
     * @param nums
     * @return
     */
    public boolean isArraySpecial(int[] nums) {

        if(nums.length == 1) return true;
        int r = nums[0]%2;
        for(int i =1; i< nums.length;i++){
            int newr = nums[i]%2;
            if( r!= newr){
                r=newr;
            }else{
                return false;
            }

        }
        return true;
    }
@Test
    public void testLongestPalindrome(){
    System.out.println(TypingTest.longestPalindrome("aacbdef"));

    int[] aa = new int[10];
//    aa.length
}


//    public void timeChecker(){
//        String [][] badge_times = {
//                {"Paul", "1355"},
//                {"Jennifer", "1910"},
//                {"John", "835"},
//                {"John", "830"},
//                {"Paul", "1315"},
//                {"John", "1615"},
//                {"John", "1640"},
//                {"Paul", "1405"},
//                {"John", "855"},
//                {"John", "930"},
//                {"John", "915"},
//                {"John", "730"},
//                {"John", "940"},
//                {"Jennifer", "1335"},
//                {"Jennifer", "730"},
//                {"John", "1630"},
//                {"Jennifer", "5"}
//        };
////        int m = gettime();
//
//        Map<String, List<Integer>> ptmap = new HashMap<>();
//        for (String[] time: badge_times) {
//            List<Integer> en;
//            if (!ptmap.containsKey(time[0])) {
//                en = new ArrayList<>();
//            } else {
//                en = ptmap.get(time[0]);
//            }
//            en.add(gettime(time[1]));
//            ptmap.put(time[0], en);
//
//        }
//        List<String> ans = new ArrayList<>();
//        for(String key:ptmap.keySet()){
//            List<Integer> times = ptmap.get(key);
//            if(check(times)){
//                ans.add(key);
//            }
//
//        }
//        System.out.println(ans);
//
//    }
//
//    private static int gettime(String t) {
//        StringBuilder sb = new StringBuilder();
//        while (t.length() + sb.length() < 4) {
//            sb.append("0");
//        }
//        sb.append(t);
//
//        t = sb.toString();
//        return Integer.parseInt(t.substring(0, 2)) * 60
//                + Integer.parseInt(t.substring(2, 4));
//    }
//
//    private boolean check(List<Integer> t){
//        Collections.sort(t);
//
//        Queue<Integer> q = new ArrayDeque<>();//fifo
////        int counter =0;
//        for (int i = 0; i < t.size(); i++) {
////            }
//             while (!q.isEmpty() && (t.get(i)-q.peek()+1) >60){
//                q.poll();
//            }
//            q.offer(t.get(i));
//            if(q.size()>2) return true;
//        }
//        return false;
//    }

}