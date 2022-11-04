package zs.slg.binarysearch;

import java.util.Arrays;

/**
 * 在有序数组中，查找某数字是否存在
 */
public class BinarySearch {

    public boolean search(int[] arr, int num) {
        if (arr == null || arr.length == 0) return false;

        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return arr[l] == num;
    }

    public static void main(String[] args) throws Exception {
        doAction(BinarySearch.class);
    }

    protected int maxLength = 500; // 长度
    protected int maxValue = 500; // 值范围 [-500,500]
    protected int time = 500; // 次数

    public void doLogarithmic() {
        boolean success = true;
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            int[] array = generateRandomArray(); // 生成随机数组
            Arrays.sort(array);
            int num = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);

            boolean res = logarithmic(array,num);

            // 自定义算法排序
            boolean res2 = search(array,num);

            if (res != res2) { // 比较数组
                success = false;
                System.err.println("运行失败");
                System.out.print("原数组: ");
                printArray(array);
                System.out.print("需要找的数: "+num);
                System.out.println("默认查找是否存在: "+res);
                System.out.println("自定义查找: "+res2);
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


    public boolean logarithmic(int[] arr,int num) {
        for (int e:arr){
            if (e == num) return true;
        }
        return false;
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

    // 执行对数器
    public static void doAction(Class clazz) throws Exception {
        ((BinarySearch) clazz.newInstance()).doLogarithmic();
    }
}
