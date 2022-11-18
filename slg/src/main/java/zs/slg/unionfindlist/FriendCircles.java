package zs.slg.unionfindlist;

/**
 * 一群朋友中，有几个不相交的朋友圈
 * Leetcode题目：https://leetcode.com/problems/friend-circles/
 */
public class FriendCircles {
    public static int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    public static class UnionFind {
        int[] parents;
        int[] sizes;
        int[] help;
        // 一共有多少个集合
        private int sets;

        UnionFind(int len) {
            parents = new int[len];
            sizes = new int[len];
            help = new int[len];
            sets = len;
            for (int i = 0; i < len; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public void union(int i, int j) {
            int i1 = find(i);
            int j1 = find(j);
            if (i1 != j1) {
                int is = sizes[i1];
                int js = sizes[j1];
                int big = is >= js ? i1 : j1;
                int small = big == i1 ? j1 : i1;
                parents[small] = big;
                sizes[big] += sizes[small];
                sets--;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (parents[i] != i) {
                help[hi++] = i;
                i = parents[i];
            }
            hi--;
            while (hi >= 0) {
                parents[help[hi--]] = i;
            }
            return i;
        }
    }
}
