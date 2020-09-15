package Common;

import java.util.*;

public class Solution51 {
    public static void main(String[] args) {
        Solution51 solution = new Solution51();
        solution.solveNQueens(8);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] isValid = new boolean[n][n];
        int[][] positions = new int[n][2];
        dfs(isValid, positions, 0, ans);
        return ans;
    }

    private void dfs(boolean[][] isValid, int[][] positions, int num, List<List<String>> ans) {
        int i, j;
        if (num == 0) {
            i = 0;
            j = 0;
        }
        else {
            i = positions[num - 1][0];
            ++i;
            j = 0;
        }
        while (i < isValid.length) {
            while (j < isValid.length) {
                if (!isValid[i][j]) {
                    List<int[]> temp = modifyValid(isValid, new int[]{i, j});
                    positions[num] = new int[]{i, j};
                    if (num + 1 == isValid.length) {
                        printAns(positions, ans);
                    }
                    else dfs(isValid, positions, num + 1, ans);
                    for (int[] index : temp) {
                        isValid[index[0]][index[1]] = false;
                    }
                }
                ++j;
            }
            ++i;
            j = 0;
        }
    }

    private List<int[]> modifyValid(boolean[][] isValid, int[] indexs) {
        List<int[]> ans = new ArrayList<>();
        ans.add(indexs);
        isValid[indexs[0]][indexs[1]] = true;
        int i = indexs[0] + 1, j = indexs[1];
        while (i < isValid.length) {
            if (!isValid[i][j]) {
                isValid[i][j] = true;
                ans.add(new int[]{i, j});
            }
            ++i;
        }
        i = indexs[0];
        ++j;
        while (j < isValid.length) {
            if (!isValid[i][j]) {
                isValid[i][j] = true;
                ans.add(new int[]{i, j});
            }
            ++j;
        }
        j = indexs[1] - 1;
        ++i;
        while (i < isValid.length && j >= 0) {
            if (!isValid[i][j]) {
                isValid[i][j] = true;
                ans.add(new int[]{i, j});
            }
            --j;
            ++i;
        }
        j = indexs[1] + 1;
        i = indexs[0] + 1;
        while (i < isValid.length && j < isValid.length) {
            if (!isValid[i][j]) {
                isValid[i][j] = true;
                ans.add(new int[]{i, j});
            }
            ++j;
            ++i;
        }
        return ans;
    }

    private void printAns(int[][] positions, List<List<String>> ans) {
        List<String> thisAns = new ArrayList<>();
        int n = positions.length;
        for (int i = 0; i < n; ++i) {
            String s = "";
            for (int j = 0; j < n; ++j) {
                if (j == positions[i][1]) s += 'Q';
                else s += '.';
            }
            thisAns.add(s);
        }
        ans.add(thisAns);
    }
}
