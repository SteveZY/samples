package zyz.steve.backtrace;

public class Sodoku {
    // LC 37 https://leetcode.com/problems/sudoku-solver/
    public static void solveSudoku(char[][] board) {
        // 暴力 在每个位置 尝试所有 可能 的 数字，如果不对 就回退
        solve(board);

    }

    private static boolean  solve(char[][] board ){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9 ; j++) {
                if ( '.' != board[i][j]) continue;
                for (char k = '1'; k <='9' ; k++) {
                    if (isValid(board, i, j, k)) {
                        // 检测 当前位置 能否 放 给定元素
                        board[i][j] = k;
                        if (solve(board)) return true;

                        board[i][j] = '.'; // 不行 就 reset
                    }
                }
                return false;
            }
        }
        return true;
    }
    private static boolean  isValid( char[][] board, int i, int j, char c){
        for (int row  = 0; row < 9; row++) { // 查看行
            if (board[row][j] == c) return  false;
        }
        for (int col = 0; col < 9; col++) {
            if(board[i][col]== c) return  false;
        }
        for (int row = i/3*3; row < i/3*3+3; row++) {
            for (int col =j/3*3 ; col < j/3*3+3; col++) {
                if(board[row][col] == c) return  false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku( board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + ", ")  ;
            }
            System.out.println(";");
        }
    }

}
