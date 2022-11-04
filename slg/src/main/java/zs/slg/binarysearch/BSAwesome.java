package zs.slg.binarysearch;

import java.util.Arrays;

/**
 * 无序数组，任意两个相邻的数不相等，局部最小值问题 i-1 > i < i+1
 */
public class BSAwesome {

    public int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1 || arr[0] < arr[1]) return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;

        int l = 1;
        int r = arr.length - 2;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) return mid;
            else if (arr[mid] < arr[mid - 1]) l = mid + 1;
            else if (arr[mid] > arr[mid - 1]) r = mid - 1;
        }
        return -1;
    }

    public void logarithmic(int times, int maxLength, int maxValue, boolean negative) {
        boolean success = false;
        for (int i = 0; i < times; i++) {
            int[] array = generateRandomArray(maxLength, maxValue, negative);
            int index = getLessIndex(array);
            if (index == -1) continue;

            if (array.length == 1 || (index == 0 && array[index] < array[index + 1])) success = true;
            else if (index == array.length - 1 && array[index] < array[index - 1]) success = true;
            else if (array[index] < array[index - 1] && array[index] < array[index + 1]) success = true;

            if (!success) {
                System.err.println("算法失败");
                break;
            }
        }
        if (success) {
            System.out.println("算法成功");
        }
    }

    public int[] generateRandomArray(int maxLength, int maxValue, boolean negative) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    public static void main(String[] args) {
        new BSAwesome().logarithmic(500,500,500,true);
    }
}
