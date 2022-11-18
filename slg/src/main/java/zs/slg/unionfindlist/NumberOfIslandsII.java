package zs.slg.unionfindlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛问题2
 * 返回每一步的 matrix中岛的数量
 * 测试链接：https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslandsII {

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (positions[i][j] == 1){
                    ans.add(uf.connect(i, j));
                }
            }
        }
        return ans;
    }

    public static class UnionFind1 {
        private int[] parent;
        private int[] size; // 用size来表示 是否被初始化过
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind1(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        // 合并 上下左右
        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,0,0,0,0},
                {0,1,1,0,0},
                {0,1,0,0,0},
                {0,0,0,1,0},
                {1,0,1,0,1},
        };
        List<Integer> list = numIslands21(4, 4, arr);
        for (Integer e:list) {
            System.out.println(e);
        }
    }
}
