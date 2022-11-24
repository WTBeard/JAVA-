package zs.slg.dp;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */
public class CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int first = first(arr, 0, arr.length - 1);// 我第一个拿
        int second = second(arr, 0, arr.length - 1);// 我第二个拿
        return Math.max(first, second);
    }

    // 从两边拿
    public static int first(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];    // 先拿,只剩一张,那就只能拿一张
        }
        int leftMax = arr[l] + second(arr, l + 1, r); // 拿左边最大值
        int rightMax = arr[r] + second(arr, l, r - 1);// 拿右边最大值
        return Math.max(leftMax, rightMax); // 取最大
    }

    public static int second(int[] arr, int l, int r) {
        if (l == r) {
            return 0;   // 还剩一张   因为一会要被别人拿走,所以就没有了,只剩下 0
        }
        int p1 = first(arr, l + 1, r); // 第二个人 拿(第一个人之前拿左边)
        int p2 = first(arr, l, r - 1); // 第二个人 拿(第一个人之前拿右边)
        return Math.min(p1, p2); // 第二个人把 最大的拿了 所以我只能拿 最小的
    }


    public static int win(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[][] f = new int[arr.length][arr.length];
        int[][] g = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            f[i][i] = arr[i];   // 先拿,只剩一张,那就只能拿一张
            g[i][i] = 0;   // 还剩一张   因为一会要被别人拿走,所以就没有了,只剩下 0
        }
        for (int l = 1; l < arr.length; l++) {   // l
            int L = 0;
            int R = l;
            while (R < arr.length) {   // 对角线的填法
                f[L][R] = Math.max(arr[L] + g[L + 1][R], arr[R] + g[L][R - 1]);
                g[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(f[0][arr.length - 1],g[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win(arr));
//        System.out.println(win3(arr));

    }


}
