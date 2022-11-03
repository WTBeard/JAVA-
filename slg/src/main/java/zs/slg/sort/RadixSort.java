package zs.slg.sort;

/**
 * 基数排序
 */
public class RadixSort extends Logarithmic {

    private int min;

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        min = dealNegative(arr); // 处理负数

        int maxBit = maxBits(arr);
        sort(arr, 0, arr.length - 1, maxBit);

        if (min < 0){  // 还原数组
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i] + min;
            }
        }
    }

    public void sort(int[] arr, int L, int R, int bit) {
        int[] help = new int[R - L + 1];
        for (int i = 0; i < bit; i++) {
            int[] count = new int[10];
            for (int j = L; j <= R; j++) {
                int digit = getDigit(arr[j], i);
                count[digit]++; // 在 0 ~ 9 槽位中累加
            }
            // 累加和
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j-1];
            }
            for (int j = R; j >= L; j--) {
                int digit = getDigit(arr[j], i);
                help[count[digit] - 1] = arr[j];
                count[digit]--;
            }
            for (int j = 0; j < help.length; j++) {
                arr[L + j] = help[j];
            }
        }
    }

    public int getDigit(int num, int bit) { // 获取进位上的值
        num = num / ((int) Math.pow(10, bit));
        return num % 10;
    }

    // 获取最大进位
    public int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE; // 获取最大值
        for (int e : arr) {
            max = Math.max(max, e);
        }

        int bit = 1;
        while (max >= 10) {
            max = max / 10;
            bit++;
        }
        return bit;
    }

    public int dealNegative(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int e : arr) {
            min = Math.min(min, e);
        }
        if (min < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += Math.abs(min);
            }
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
        doAction(RadixSort.class);
    }
}
