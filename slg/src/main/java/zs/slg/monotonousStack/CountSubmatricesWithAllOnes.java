package zs.slg.monotonousStack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的子矩形数量
 */
public class CountSubmatricesWithAllOnes {

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0){
            return 0;
        }

        int[] height = new int[mat.length];
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            res +=max(height);
        }
        return res;
    }

    public static int max(int[] height){
        int[] stack = new int[height.length];
        int index = -1;

        int res = 0;

        for (int i = 0; i < height.length; i++) {
            while (index != -1 && height[stack[index]] >= height[i]){
                int cur = stack[index--];
                if (height[cur] > height[i]){
                    int left = index == -1 ? -1 : stack[index];
                    int right = i;
                    int largest = left == -1 ? height[right] : Math.max(height[right],height[left]);
                    int size = right - left - 1;
                    res += (((size + 1) * size) >> 1) * (height[cur] - largest);
                }
            }
            stack[++index] = i;
        }
        while (index != -1){
            int cur = stack[index--];
            int left = index == -1 ? -1 : stack[index];
            int largest = left == -1 ? 0 : height[left];
            int size = height.length - 1 - left;
            res +=  (((size + 1) * size) >> 1) * (height[cur] - largest);
        }
        return res;
    }
}
