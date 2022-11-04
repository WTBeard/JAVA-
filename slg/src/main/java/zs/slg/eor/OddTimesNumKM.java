package zs.slg.eor;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，已知M > 1，K < M，找到出现了K次的数
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class OddTimesNumKM {

    public static int km(int[] arr, int k, int m) {
        int[] table = new int[32];  // 二进制 32 位数组

        for (int value : arr) {
            for (int j = 0; j < 32; j++) {
                // 某个数的二进制32位中 哪位值是 1,就将哪位累加
                table[j] += 1 & (value >> j);
            }
        }

        int res = 0;
        for (int i = 0; i < table.length; i++) {
            // 除余数m ,不为 0 的那肯定就是 k
            if (table[i] == 0 || table[i] % m == 0) continue;
            // 1 向左移动 | 进去
            res |= 1 << i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,2,3,3};
        int km = km(arr, 2, 3);
        System.out.println(km);
    }
}
