import java.util.*;

public class Main {

    static int N = 100010, M = 2 * N, INF = 1 << 30;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] w = new int[M];
    static StringBuilder sb = new StringBuilder();
    static int idx, ret;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Arrays.fill(h, -1);
        idx = 0;
        ret = 0;
        for (int i = 0; i < n - 1; ++i) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        dfs(1, -1);
        System.out.println(ret);
        System.out.println(sb.toString());

    }

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int dfs(int u, int fa) {
        int total = 0;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            int c = w[i];
            if (j == fa)
                continue;
            int cur = dfs(j, u);
            total += cur;
            if (c == 2) {
                total++;
                if (cur == 0) {
                    ret++;
                    sb.append(j + " ");
                }
            }
        }
        return total;
    }

}