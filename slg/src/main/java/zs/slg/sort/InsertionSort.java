package zs.slg.sort;

/**
 * 插入排序
 */
public class InsertionSort extends Logarithmic {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // 1 ~ 0 相邻比较交换
        // 2 ~ 0 相邻比较交换
        // ....
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr,j,j-1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        doAction(InsertionSort.class);
    }
}
