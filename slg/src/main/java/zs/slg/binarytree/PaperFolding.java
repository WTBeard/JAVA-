package zs.slg.binarytree;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开
 * 此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次
 * 请从上到下打印所有折痕的方向。
 * N=1时，打印: down
 * N=2时，打印: down down up
 */
public class PaperFolding {

    public static void printAllFolds(int N) {
        if (N == 0) return;

        process(1, N, 0);
    }

    public static void process(int i, int n, int v) {
        if (i > n) return;
        process(i + 1, n, 0);
        System.out.println(v == 0 ? "down" : "up");
        process(i + 1, n, 1);
    }

    public static void main(String[] args) {
        printAllFolds(2);
    }
}
