package zyz.steve.dp;

//最长公共子序列

/**
 * 暴力解法：
 * 找到 a[m] 中所有 子串, 然后在b中查找是否存在 2^m 个子串， 太大，
 */
public class LCS {


    static int lcsRecursively(String a, String b, int i, int j) {
        /**
         * i and j： 是 a 和b 字串 的 末尾index。
         *      要求得的 这两个子串最长 公共子序列长度， 可以看是否可以从前一个位置递推出来
         *                     |- max (lcs(a[i],b[j-1]), lcs(a[i-1],b[j]))  当 a[i] != b[j]
         *  lcs(a[i],b[j]) =   | 或者
         *                     |- lcs(a[i-1],b[j-1]) +   1                  当 a[i] == b[j]
         * 递归的意思：
         *  一旦 a，b的 下一个字符相同， 说明该字符的加入 可以令 lcs的长度增加1
         *  若不同，
         */
        if (i < 0 || j < 0) return 0;
        if (a.charAt(i) == b.charAt(j))
            return lcsRecursively(a, b, i - 1, j - 1) + 1;
        else {
            return Math.max(lcsRecursively(a, b, i, j - 1), lcsRecursively(a, b, i - 1, j));
        }
    }

    //   将每个 i，j 都记下来，存在的话直接返回，
    static int lcsRecursivelyMemo(String a, String b, int i, int j, int[][] memo) {
        if (i < 0 || j < 0) return 0;
        int k = i + 1;
        int l = j + 1;
        if (a.charAt(i) == b.charAt(j)) {
            if (memo[k][l] != 0) return memo[k][l];
            else
                return memo[k][l] = lcsRecursivelyMemo(a, b, i - 1, j - 1, memo) + 1;
        } else {
            if (memo[k][l - 1] == 0 || memo[k - 1][l] == 0) {
                return Math.max(
                        memo[k][l - 1] == 0 ?
                                memo[k][l - 1] = lcsRecursivelyMemo(a, b, i, j - 1, memo) : memo[k][l - 1],
                        memo[k - 1][l] == 0 ?
                                memo[k - 1][l] = lcsRecursivelyMemo(a, b, i - 1, j, memo) : memo[k - 1][l]);

            } else {
                return Math.max(memo[k][l - 1], memo[k - 1][l]);
            }

        }
    }

    public static void main(String[] args) {
//        LCS lcs = new LCS();

        String a = "abcd09werewr094309540r34534543i9889oyuyurrfhfgfcv";
        String b = "abcdvvbhnhjmmjjuyjhjgjgyfghfhhtfhgfhttfh";

//        System.out.println(lcsRecursively(a, b, a.length() - 1, b.length() - 1));

        System.out.println(lcsRecursivelyMemo(a, b, a.length() - 1, b.length() - 1, new int[a.length() + 1][b.length() + 1]));
    }
}
