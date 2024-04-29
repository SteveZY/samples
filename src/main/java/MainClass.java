import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//https://www.youtube.com/watch?v=yJUpe1pb3TU
public class MainClass {
    static List<Integer> pref = new ArrayList<>();
    static List<Integer> suffix = new ArrayList<>();
    public static int[] fillItem(String str) {
        int[] item = new int[2];
//        int count = 0;
        str.chars().forEach(c -> {
            if (c == '0') {
                item[0]++;
//                count++;
            } else {
                item[1]++;
                pref.add(item[0]);
            }
        });
        return item;
    }
    static  void initSuffix(String str){
        int count =0;
        for (int i = str.length()-1; i >=0; i--) {
            if(str.charAt(i) == '0'){
                count++;
            }else {
                suffix.add(count);
            }
        }
    }
    public static int binarySearchToDetermineMinCost(String str){
        pref.clear();
        suffix.clear();
        int[] onesZeros = fillItem(str);
//        int zeros = onesZeros[0];
//        int ones = onesZeros[1];
        initSuffix(str);
        int low = 0;
        int high = onesZeros[1];
        int ans =0;
        if(high==0) return ans;
        while (low<=high){//试着删除一半数量的 1s
            boolean foundOne = false;
            int mid = (low + high) >> 1;
            for (int i = 0; i <= mid; i++) {
                int zerosRemain = onesZeros[0];
                zerosRemain -= pref.get(i);
                zerosRemain -= suffix.get(mid - i);
                if (zerosRemain <= mid) {
                    //找到一个可能方案
                    foundOne = true;
                    break;
                }
            }
            if(foundOne){
                //继续看还有没有更小的可能
                ans = mid;
                high = mid-1;
            }else low = mid +1;//当前mid 不可能达成目标，试着删除更多的 1 继续循环
        }
        return ans;
    }
    //This one not working
    public static int costOfRemovalZeros(String str){
        int[] onesZeros = fillItem(str);
        int cost0 = onesZeros[0];
        int cost1= 0;
                int curCost = cost0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                cost1++;
            } else {
                cost0--;
            }
            if (cost1 >= cost0) {
                //继续从左侧删除1，没有收益了，故回退，跳出
                cost1--;
                break;
            }
            //左侧的cost
            curCost = Math.max(cost1,cost0);
        }
        //reset cost zero
        cost0 = onesZeros[0];
        //从右侧再测试
        for (int i = str.length()-1; i >=0 ; i--) {
            if(str.charAt(i) == '1'){
                cost1++;

            }else {
                cost0--;
            }
            if(cost1>= cost0) {
                cost1--;
                break;
            }
        }
        return Math.max(cost0,cost1);
    }
    static public int beautifulArray(int [] a){
//        int lb = Math.max(a[0], a[2]); //lower bound
        if(Math.max(a[0], a[2]) == a[0]){
            //最小值范围，含有较大的lower bound
            if( a[0]<=a[3]){
                //落入最大值的区间，不需要处理
                return a[0];
            }
//            else {
//                return a[0]+a[2];
//            }
        }else{
            //最大值范围，含有较大的lower bound
            if(a[2] <=a[1]){
                return a[2];
            }
        }
        //需要将lb 和 up 的数字加入

        return a[2]+a[0];

    }
    static public void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        List<String> strs = new ArrayList<>();
        int numOfStrs = Integer.parseInt(s);
        for (int i = 0; i < numOfStrs; i++) {
            strs.add(in.nextLine());
        }
        strs.forEach(ss ->
                System.out.println(binarySearchToDetermineMinCost(ss))

        );
    }
    static private int[] str2IntArr(String str){
//        List<Integer> xxx = Arrays.stream(str.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
//        xxx.t;
        int [] r = new int[4];
//        ArrayList l = new ArrayList()
        String[] sp = str.split(" ");
        for (int i = 0; i < sp.length; i++) {
            r[i] = Integer.parseInt(sp[i]);
        }
//        for (String e:str.split(" ")) {
//            rInteger.parseInt(e)
//        }
        return r;
    }
    static public void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        List<int[]> strs = new ArrayList<>();

        int numOfStrs = Integer.parseInt(s);
        for (int i = 0; i < numOfStrs; i++) {
            strs.add(
                    str2IntArr(
                    in.nextLine())
            );
        }
        strs.forEach(ss ->
                System.out.println(beautifulArray(ss))

        );
    }
    public static List<Integer[]> ans;
    private static boolean [] elected;
    public static List<Integer []>[] adj;
    public static void buildTree(int numOfNodes, int[][] edges){
        ans = new ArrayList<>();
        elected = new boolean[numOfNodes+1];
        adj = new LinkedList[numOfNodes+1];
        for (int i = 1; i <= numOfNodes; i++) {
            adj[i] = new LinkedList<>();
        }
        //        Arrays.fill(adj,new ArrayList<>());

        for (int i = 0; i < numOfNodes-1 ; i++) {
            int d1 = edges[i][0];
            int d2 = edges[i][1];
            int type = edges[i][2];
            Integer [] pairD1 = new Integer[2];
            Integer [] pairD2 = new Integer[2];

            pairD1[0] = d2;
            pairD2[1]  = pairD1[1] = type;
            pairD2[0] = d1;
            //            pairD2[1] = type;
            adj[d1].add(pairD1);
            adj[d2].add(pairD2);
        }
    }
    public static void dfs(Integer v, Integer parent){
        List<Integer[]> adjs = adj[v];

        for (Integer[] u : adjs) {
            //遍历所有相邻节点
            if (u[0].equals(parent)) {
                //确保不要检测父节点
                continue;
            }
            //深度优先搜索
            dfs(u[0], v);
            int type = u[1];
            if (type == 2) {//需要维修
                if (!elected[u[0]]) {
                    //如果该远端点还没有被选中，其即为一个要选的节点，更新状态，并将其加入 ans 列表
                    ans.add(u);
                    elected[u[0]] = true;
                }

            }
            //子节点 对应的子树 如果有节点被选中，置其父节点为选中状态
            elected[v] = elected[v] || elected[u[0]];
        }
    }
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numOfStrs = in.nextInt();
//        List<int[]> strs = new ArrayList<>();
//        int numOfStrs = Integer.parseInt(s);
        int [][] edgs= new int[numOfStrs+1][];
        for (int i = 0; i < numOfStrs - 1; i++) {
            int [] e = new int[3];
            e[0] = in.nextInt();
            e[1] = in.nextInt();
            e[2] = in.nextInt();
            edgs[i] = e;
        }
        buildTree(numOfStrs,edgs);
        dfs(1,0);

        System.out.println(ans.size());
        StringBuilder sb = new StringBuilder();
        for (Integer[] a:ans){
            sb.append(a[0]).append(" ");

        }
        System.out.println(sb.toString().trim());
//        strs.forEach(ss ->
//                System.out.println(beautifulArray(ss))
//
//        );
    }

}
