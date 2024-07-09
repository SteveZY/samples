package zyz.steve.array;
import java.util.*;
public class weekly405 {
    static List<String> ret = new ArrayList<>();

    static String  solution(String s, int k){

        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i=0; i< len; i++){
            int replace = (i+k) % len ;
            sb.append(s.charAt(replace));

        }
        return sb.toString();
    }
    public static List<String> validStrings(int n) {
        genString(1,"0",n);
        genString(1,"1",n);
        return ret;
    }
    private static void genString(int k, String prefix, int max){
        if(k>=max){
            ret.add(prefix);
            return;
        }
        String prefix_next1 = prefix + "1";
        String prefix_next0;
        genString(k+1, prefix_next1,max);
        if(prefix.charAt(prefix.length()-1) == '1') {
            //是 1 的情况下 可以 追加一个 0
            prefix_next0 = prefix + "0";
            genString(k + 1, prefix_next0, max);
        }
    }
    public static int numberOfSubmatrices(char[][] grid) {
        //init memoization data


        int xt = grid[0][0] == 'X' ? 1 : 0;
        int yt = grid[0][0] == 'Y' ? 1 : 0;
        int w = grid[0].length;
        int h = grid.length;
        HashMap<String, int[]> map = new HashMap<>();
        map.put("00",new int[]{xt,yt});
        int total = 0;
        //填充第一行
        for (int i = 1; i < w; i++) {

            int xxx=(map.get("0" + (i-1)))[0], yyy=(map.get("0" + (i-1)))[1];
            if (grid[0][i] == 'X') {
                xxx += 1;
            }else if (grid[0][i] == 'Y'){
                yyy += 1;
            }

            map.put("0"+i,new int[]{xxx,yyy});
            if(xxx>0 && xxx == yyy) total++;
        }
        //填充第一列
        for (int i = 1; i < h; i++) {
            int xxx=(map.get((i-1)+"0"))[0], yyy=(map.get((i-1)+"0"))[1];
            if (grid[i][0] == 'X') {
                xxx   += 1;
            }else if (grid[i][0] == 'Y'){
                yyy   += 1;
            }

            map.put(i+"0",new int[]{xxx,yyy});
            if(xxx == yyy) total++;
        }
        for (int i = 1; i <w ; i++) {// 横着的
            for (int j = 1; j < h; j++) {//竖的
                String key = j + String.valueOf(i);
                String upkey = (j - 1) + String.valueOf(i);
                String leftkey = (j) + String.valueOf(i - 1);

                int xxx = map.get(upkey)[0] + map.get(leftkey)[0] + grid[j][i] == 'X' ? 1 : 0;
                int yyy = map.get(upkey)[1] + map.get(leftkey)[1] + grid[j][i] == 'Y' ? 1 : 0;
                if(xxx == yyy) total++;
                map.put(key,new int[]{xxx,yyy});

            }
        }
        return total;
    }
    public static void main(String[] args) {
        char[][] grid = new char[][] {{'X','Y','.'},{'Y','.','.'}};
//        System.out.println(validStrings(4));

        System.out.println(numberOfSubmatrices(grid));

    }

}
