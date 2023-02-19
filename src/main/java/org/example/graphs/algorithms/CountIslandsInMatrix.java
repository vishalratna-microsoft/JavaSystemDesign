package org.example.graphs.algorithms;

import java.util.Stack;

public class CountIslandsInMatrix {
    private final int[][] mInput;
    private final int mRows;
    private final int mCols;

    public CountIslandsInMatrix(int[][] matrix, int rows, int cols) {
        this.mInput = matrix;
        this.mRows = rows;
        this.mCols = cols;
    }

    public int countIslands() {
        boolean[][] visited = new boolean[mRows][mCols];
        init(visited);
        int count = 0;
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                if (!visited[i][j]) {
                    Stack<Integer> s = new Stack<>();
                    dfs(mInput, i, j, visited, s);
                    if (s.size() > 0) {
                        count++;
                    }

                }
            }
        }
        return count;
    }

    private void init(boolean[][] visited) {
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                visited[i][j] = false;
            }
        }
    }

    private void dfs(int[][] mInput, int i, int j, boolean[][] visited, Stack<Integer> stack) {
        if (!isValid(i, j)) return;
        if (visited[i][j]) return;

        visited[i][j] = true;
        if (mInput[i][j] != 1) return;

        stack.push(mInput[i][j]);

        // Try in all directions.
        dfs(mInput, i, j + 1, visited, stack);
        dfs(mInput, i, j - 1, visited, stack);
        dfs(mInput, i + 1, j, visited, stack);
        dfs(mInput, i - 1, j, visited, stack);
    }

    private boolean isValid(int m, int n) {
        return m >= 0 && m < mRows && n >= 0 && n < mCols;
    }
}
