package zyz.steve.backtrace;

/**
 * another one is
 * word in a list of words
 * list of words = {"cat","dog","hello"}
 * note = "weraewsdfsdf"
 *
 * 判断 note 中是否包含 单词列表中的任何 一个单词
 *
 * From Atlassian Karat interview
 */

    import java.util.*;

class FindWordAnother {

    static int N = 17, M = 65;
    static int[] dx = { 0, 1 };
    static int[] dy = { 1, 0 };
    static int n, m;
    static String s;
    static List<int[]> ret;
    static char[][] grid;
    static Boolean[][] f = new Boolean[1 << N][M];//开大数组
    static int[][] steps = new int[1 << N][M];

    public static void main(String[] args) {
        grid = new char[][] {
                { 'a', 'b', 'c' },
                { 'e', 'b', 'd' },
                { 'x', 'y', 'z' }
        };
        s = "xyz";
        ret = new ArrayList<>();
        n = grid.length;
        m = grid[0].length;
        for (int i = 0; i < N; ++i) {
            Arrays.fill(f[i], null);
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == s.charAt(0)) {
                    int id = getId(i, j);
                    int state = 1 << id;
                    if (dfs(state, id)) {
                        System.out.println(id / m + " " + (id % m));
                        while (Integer.bitCount(state) != s.length()) {
                            int next = steps[state][id];
                            System.out.println(next / m + " " + (next % m));
                            id = next;
                            state |= 1 << id;
                        }
                        break;
                    }
                }
            }
        }
    }

    static boolean dfs(int state, int idx) {
        int cur = Integer.bitCount(state);
        if (cur >= s.length()) {
            return true;
        }
        if (f[state][idx] != null) {
            return f[state][idx];
        }
        int x = idx / m, y = idx % m;
        for (int i = 0; i < dx.length; ++i) {
            int a = x + dx[i];
            int b = y + dy[i];
            int id = getId(a, b);
            if (a < 0 || a >= n || b < 0 || b >= m || (state >> id & 1) == 1)
                continue;
            if (grid[a][b] != s.charAt(cur))
                continue;
            if (dfs(state | 1 << id, id)) {
                steps[state][idx] = id;
                return f[state][id] = true;
            }
        }
        return f[state][idx] = false;
    }

    static int getId(int x, int y) {
        return x * m + y;
    }

}





