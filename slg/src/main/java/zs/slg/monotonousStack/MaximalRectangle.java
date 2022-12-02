package zs.slg.monotonousStack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 */
public class MaximalRectangle {

    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0){
            return 0;
        }

        int res = 0;
        int[] height = new int[map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
            }
            res = Math.max(largestRectangleArea(height),res);
        }
        return res;
    }


    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int[] stack = new int[height.length];
        int index = -1;

        for (int i = 0; i < height.length; i++) {
            while (index != -1 && height[stack[index]] >= height[i]){
                int cur = stack[index--];
                int left = index == -1 ? -1 : stack[index];
                int right = i;
                int count = right - left - 1;
                res = Math.max(res,count * height[cur]);
            }
            stack[++index] = i;
        }
        while (index != -1){
            int cur =  stack[index--];
            int left = index == -1 ? -1 : stack[index];
            int count = height.length - left - 1;
            res = Math.max(res,count * height[cur]);
        }
        return res;
    }

}
