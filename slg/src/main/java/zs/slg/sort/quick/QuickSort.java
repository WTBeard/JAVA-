package zs.slg.sort.quick;

import zs.slg.sort.Logarithmic;

/**
 * 快速排序
 */
public class QuickSort extends Logarithmic {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int L, int R) {
        if (L >= R) return; // base case

        int[] partition = partition(arr, L, R); // 获取 < v 的最大位置, >v 的最小位置
        process(arr, L, partition[0] - 1);
        process(arr, partition[1] + 1, R);
    }

    public int[] partition(int[] arr, int L, int R) {
        random(arr, L, R);  // 随机选择一个值,与末尾交换
        int randomNum = arr[R];

        int ls = L - 1; // 左边界
        int rs = R; // 右边界
        int cur = L; // 当前索引
        while (cur < rs) {
            if (arr[cur] < randomNum) {
                swap(arr, ++ls, cur++);
            } else if (arr[cur] > randomNum) {
                swap(arr, --rs, cur);
            } else {
                cur++;
            }
        }
        swap(arr, rs, R);
        return new int[] {ls + 1, rs};
    }

    public void random(int[] arr, int L, int R) {
        int randomIndex = L + (int) (Math.random() * (R - L) + 1);
        swap(arr, R, randomIndex);
    }

    public static void main(String[] args) throws Exception {
        doAction(QuickSort.class);
    }
}
