package zs.slg.sort;

/**
 * 计数排序
 */
public class CountSort extends Logarithmic {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int min = Integer.MAX_VALUE;
        for (int e : arr) {
            min = Math.min(min, e);
        }

        int max = Integer.MIN_VALUE;
        for (int e : arr) {
            max = Math.max(max, min < 0 ? e + Math.abs(min) : e); // 需要判断是 证书还是负数
        }

        int[] buckets = new int[max + 1];
        for (int j : arr) {
            buckets[min < 0 ? j + Math.abs(min) : j]++; // 需要判断是 证书还是负数
        }

        int i = 0;
        for (int j = 0; j < buckets.length; j++) {
            while (buckets[j] > 0) {
                arr[i++] = min < 0 ? j + min : j; // 需要判断是 证书还是负数
                buckets[j]--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        doAction(CountSort.class);
    }
}
