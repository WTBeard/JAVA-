package zs.slg.sort;

/**
 * 选择排序
 */
public class SelectionSort extends Logarithmic {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // 0 ~ n-1 上获取最小值 放 0 位
        // 1 ~ n-1 上获取最小值 放 1 位
        // 2 ~ n-1 上获取最小值 放 2 位
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[min] > arr[j]) min = j;
            }
            swap(arr, i, min);
        }
    }

    public static void main(String[] args) throws Exception {
        doAction(SelectionSort.class);
    }
}
