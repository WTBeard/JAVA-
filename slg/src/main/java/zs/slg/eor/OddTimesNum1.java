package zs.slg.eor;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class OddTimesNum1 {

    public static void printOddTimesNum(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int eor = 0;
        for (int e:arr) {
            eor ^= e;
        }
        System.out.println(eor);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,2,3,4,3};
        printOddTimesNum(arr);
    }
}
