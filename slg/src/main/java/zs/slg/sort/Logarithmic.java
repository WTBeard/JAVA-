package zs.slg.sort;

import java.util.Arrays;

/**
 * 对数器
 */
public abstract class Logarithmic {

    protected int maxLength = 500; // 长度
    protected int maxValue = 500; // 值范围 [-500,500]
    protected int time = 500; // 次数

    public void doLogarithmic() {
        boolean success = true;
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            int[] array = generateRandomArray(); // 生成随机数组

            // 对数器默认排序
            int[] array1 = copyArray(array);
            logarithmic(array1); // 默认排序

            // 自定义算法排序
            int[] array2 = copyArray(array);
            sort(array2); // 自定义排序算法

            if (!judge(array1, array2)) { // 比较数组
                success = false;
                System.err.println("运行失败");
                System.out.print("原数组: ");
                printArray(array);
                System.out.print("默认排序: ");
                printArray(array1);
                System.out.print("自定义排序: ");
                printArray(array2);
                break;
            }
        }
        if (success) {
            System.out.println("运行成功...");
            System.out.println("耗时:" + (System.currentTimeMillis() - beginTime));;
        }
    }

    public void printArray(int[] arr) {
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public boolean judge(int[] s1, int[] s2) {
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) return false;
        }
        return true;
    }

    public void logarithmic(int[] arr) {
        if (arr == null || arr.length < 2) return;
        Arrays.sort(arr);
    }

    public int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    /**
     * 生成随机数组
     *
     * @return
     */
    public int[] generateRandomArray() {
        int length = (int) (Math.random() * (maxLength + 1)); // 随机长度
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue); // 随机值 有正负
        }
        return arr;
    }

    public abstract void sort(int[] arr);

    public void swap(int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 执行对数器
    public static void doAction(Class clazz) throws Exception {
        ((Logarithmic) clazz.newInstance()).doLogarithmic();
    }
}
