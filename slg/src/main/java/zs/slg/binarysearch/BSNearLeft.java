package zs.slg.binarysearch;

import java.util.Arrays;

/**
 * 在有序数组中，找到 >= 某个数最左侧的位置
 */
public class BSNearLeft{

    public int search(int[] arr, int num) {
        if (arr == null || arr.length == 0) return -1;

        int l = 0;
        int r = arr.length - 1;

        int index = -1;
        while (l <= r){
            int mid = l + ((r-l)>>1);
            if (arr[mid] >= num){
                index = mid;
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) throws Exception {
        doAction(BSNearLeft.class);
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

            int res = logarithmic(array,num);

            // 自定义算法排序
            int res2 = search(array,num);

            if (res != res2) { // 比较数组
                success = false;
                System.err.println("运行失败");
                System.out.print("原数组: ");
                printArray(array);
                System.out.print("需要找的数: "+num);
                System.out.println("默认查找: "+res);
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


    public int logarithmic(int[] arr,int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) return i;
        }
        return -1;
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
        ((BSNearLeft) clazz.newInstance()).doLogarithmic();
    }
}
