package zs.slg.sort.merge;

import zs.slg.sort.Logarithmic;

/**
 * 归并排序
 */
public class MergeSort extends Logarithmic {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int L, int H) {
        if (L == H) return;

        int mid = L + ((H - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, H);
        merge(arr, L, mid, H);
    }

    public void merge(int[] arr, int L, int mid, int H) {
        int[] help = new int[H - L + 1];

        int i = 0;
        int ls = L;
        int rs = mid + 1;
        // 两个数组依次比较,谁小放谁
        while (ls <= mid && rs <= H) {
            help[i++] = arr[ls] <= arr[rs] ? arr[ls++] : arr[rs++];
        }
        while (ls <= mid) {
            help[i++] = arr[ls++];
        }
        while (rs <= H) {
            help[i++] = arr[rs++];
        }
        for (i = 0; i < help.length; i++) {
            arr[i + L] = help[i];
        }
    }

    public static void main(String[] args) throws Exception{
        doAction(MergeSort.class);
    }
}
