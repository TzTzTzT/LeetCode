package Common;

public class Solution37 {
    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution37 solution = new Solution37();
        solution.solveSudoku(board);
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 8; ++j) System.out.print(board[i][j] + ",");
            System.out.println(board[i][8]);
        }
    }

    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    private boolean dfs(char[][] board) { // 此处dfs未经优化，为穷举回溯
        int[] index = new int[]{-1, -1};
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
            if (index[0] != -1) break;
        }
        if (index[0] == -1) return true;
        else {
            boolean[] isExist = new boolean[9];
            for (int i = 0; i < 9; ++i) {
                if (board[i][index[1]] != '.') {
                    isExist[board[i][index[1]] - '1'] = true;
                }
                if (board[index[0]][i] != '.') {
                    isExist[board[index[0]][i] - '1'] = true;
                }
            }
            if (index[0] < 3) {
                if (index[1] < 3) help(3, 3, board, isExist);
                else if (index[1] < 6) help(3, 6, board, isExist);
                else help(3, 9, board, isExist);
            }
            else if (index[0] < 6) {
                if (index[1] < 3) help(6, 3, board, isExist);
                else if (index[1] < 6) help(6, 6, board, isExist);
                else help(6, 9, board, isExist);
            }
            else {
                if (index[1] < 3) help(9, 3, board, isExist);
                else if (index[1] < 6) help(9, 6, board, isExist);
                else help(9, 9, board, isExist);
            }
            for (int i = 0; i < 9; ++i) {
                if (!isExist[i]) {
                    board[index[0]][index[1]] = (char)(i + '1');
                    if (dfs(board)) return true;
                    else board[index[0]][index[1]] = '.';
                }
            }
            return false;
        }
    }

    private void help(int iEnd, int jEnd, char[][] board, boolean[] isExist) {
        for (int i = iEnd - 3; i < iEnd; ++i) {
            for (int j = jEnd - 3; j < jEnd; ++j) {
                if (board[i][j] != '.') {
                    isExist[board[i][j] - '1'] = true;
                }
            }
        }
    }
}
