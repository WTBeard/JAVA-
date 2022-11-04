package zs.slg.eor;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class OddTimesNum2 {

    public static void printOddTimesNum(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int eor = 0;
        for (int e : arr) {
            eor ^= e;      // eor 最后肯定为 eor = a^b
        }

        // a^b 因为 a 与 b 不是同一个,因为在二进制位上肯定有一个是 1,有一个是 0
        // 找出二进制最右边的 1
        int rightOne = eor & (-eor); // -eor = ~eor + 1 去反加 1
        int otherEor = 0;
        for (int e : arr) {
            if ((e & rightOne) == 1){
                otherEor ^= e; // a  因为其他书有偶数个,所以otherEor必定是 a 和 b 中的一个
            }
        }

        eor ^= otherEor; // b 异或得出另一个
        System.out.println(otherEor);
        System.out.println(eor);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,2,3,4,3,5};
        printOddTimesNum(arr);
    }
}
