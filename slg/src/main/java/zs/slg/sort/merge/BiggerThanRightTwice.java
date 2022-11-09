package zs.slg.sort.merge;

/**
 * 在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
 * <p>
 * https://leetcode.com/problems/reverse-pairs/
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class BiggerThanRightTwice {

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int res = 0;
        int re = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (re <= r && arr[i] > arr[re] * 2){
                res++;
                re++;
            }
            re = mid + 1;
        }


        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int ls = mid;
        int rs = r;
        while (ls >= l && rs >= mid + 1) {
            help[i--] = arr[ls] > arr[rs] ? arr[ls--] : arr[rs--];
        }
        while (ls >= l) {
            help[i--] = arr[ls--];
        }
        while (rs >= mid + 1) {
            help[i--] = arr[rs--];
        }
        for (i = 0; i <= help.length - 1; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }


    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePairs(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
