package zyz.steve.backtrace;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
// LC 51
public class NQueens {
    public static List<List<String>> solve(int n ){
        // init the board
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
//        backtrack(board,0, res);

        backtrackII(board,res);
        return res;
    }
    private static void backtrack(char[][] board, int col, List<List<String>> res){
        if(col == board.length) {
            // go one solution
            return;
        }

        for (int i = 0; i < board.length; i++) { // rows
            // 从 第一行 第一列 开始 填入 Q
            if(validate(i, col, board)){
                board[i][col] = 'Q';
                backtrack(board, col+1, res);
                board[i][col] = '.';
            }
        }
//        return false;
    }
    private static boolean validate(int row, int col, char[][] board){
        for (int i = 0; i < board.length; i++) { // row
            for (int j = 0; j < board.length; j++) { // cols
                if(board[i][j] == 'Q') {
                    if(row==i) return false;
                    if(col == j) return  false;
                    if(i+j == row+col) return false;
                    if(row-i == col -j) return false;
                }
            }
        }
        return true;
    }

    // 最慢的 解决办法， 产生 大量重复， backtrack 做了优化
    private static  void backtrackII(char[][] board,  List<List<String>> res){
        // check if we go a solution
        int qcount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 'Q') qcount++;
            }
        }
        if ( qcount == board.length) {

//            String firstline = new String(board[0]);
            boolean duplicate = false;
            for(List s : res){
                // when equal , the solution was found already
//                boolean equal = false;
                duplicate = true;
                for (int i = 0; i < s.size(); i++) {
                    if (!s.get(i).equals(new String(board[i]))){
                        duplicate = false;
                        break;
                    }
                }
                if(duplicate) break;
            }
            if (!duplicate) {
                System.out.println("got one solution");
                List<String> sol = new ArrayList<>();
//                sol.add(firstline);
                for (int i = 0; i <board.length; i++) {
                    sol.add(new String(board[i]));
                }
                System.out.println(sol);
                res.add(sol);
                return;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(validate(i, j,board)) {
                    board[i][j] = 'Q';
                    backtrackII(board, res);
                    board[i][j] = '.';
                }
            }
        }

    }
    public static  void main (String[] args){
        solve(5);
    }
}
