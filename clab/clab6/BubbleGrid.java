public class BubbleGrid {
    private int[][] grid;
    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) { this.grid = grid; }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int row = grid.length;
        int column = grid[0].length;
        int[][] gridCopy = new int[row][column];
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        for (int i = 0; i < row; i++) {
            gridCopy[i] = grid[i].clone();
        }
        // 提前把命中的格子变成0
        for (int i = 0; i < darts.length; i++) {
            gridCopy[darts[i][0]][darts[i][1]] = 0;
        }

        // 假设所有不掉落的气泡都与row*column+1连通
        UnionFind uf = new UnionFind(row * column + 1);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (gridCopy[i][j] == 1) {
                    int index = i * column + j;
                    if (i == 0) {
                        uf.union(index, row * column);
                    }
                    if (i > 1 && gridCopy[i-1][j] == 1) {
                        uf.union(index, (i-1)*column + j);
                    }
                    if (j > 1 && gridCopy[i][j-1] == 1) {
                        uf.union(index, i*column + j - 1);
                    }
                }
            }
        }
        int[] result = new int[darts.length];
        for (int t = darts.length-1; t >= 0; t--) {
            int x = darts[t][0];
            int y = darts[t][1];
            if (grid[x][y] == 0) {  // 没有命中
                continue;
            } else {
                int prevSize = uf.sizeOf(row * column);
                int index = x * column + y;
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if (newX >= 0 && newX < row && newY >= 0 && newY < column && gridCopy[newX][newY] == 1) {
                        uf.union(index, newX*column+newY);
                    }
                }
                if (x == 0) {
                    uf.union(index, row*column);
                }
                gridCopy[x][y] = 1;
                result[t] = Math.max(0, uf.sizeOf(row*column) - prevSize - 1);
            }
        }
        return result;
    }
}
