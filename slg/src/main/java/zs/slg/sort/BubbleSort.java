package zs.slg.sort;

/**
 * 冒泡排序
 */
public class BubbleSort extends Logarithmic {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // n - 1 ~ 0 确定 n - 1 最大值
        // n - 2 ~ 0 确定 n - 2 最大值
        // ....
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        doAction(BubbleSort.class);
    }
}
