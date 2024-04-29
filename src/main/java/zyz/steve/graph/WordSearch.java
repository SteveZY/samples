package zyz.steve.graph;

//https://leetcode.com/problems/word-search/description/
//检测 word是否存在
public class WordSearch {
    private final static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static boolean exist(char[][] b, String word) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                int cur = 0;// word 的当前位置
                if(dfs(b, i, j, word, cur)) return true;
            }
        }
        return false;
    }
    private static boolean inArea(int rs, int cs, int c, int r) {
        return c >= 0 && c < cs && r >= 0 && r < rs;
    }
    private static boolean dfs(char[][] b, int r, int c, String s, int idx){
        if(!inArea(b.length,b[0].length,c,r)) return false;
        char ch = b[r][c];
        if(ch!=s.charAt(idx))
        {
            return false;
        }else {
            //当前board 上的字符 字符，跟 word 对应位置字符相等，并且已经到了word 的最后， 则返回 true
            if(idx == s.length()-1) return true;

        }
        if (ch == '#') {
            return false; //visited already
        }
        b[r][c] = '#';//mark as visited
        boolean ret = false;
        for (int[] ints : dir) {

           ret = ret || dfs(b, r + ints[0], c + ints[1], s, idx+1);
        }
        b[r][c] = ch;

        return ret;
    }

}
