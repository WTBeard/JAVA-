package zs.slg.dp;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueens {

    public static int num1(int n) {
        if (n < 1) return 0;
        int[] record = new int[n];
        return process(record, 0, n);
    }

    public static int process(int[] record, int index, int n) {
        if (index == n) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (check(record,index,i)){
                record[index] = i;
                int process = process(record, index + 1, n);
                res += process;
            }
        }
        return res;
    }

    /**
     *
     * @param record
     * @param index 行
     * @param j 列
     * @return
     */
    public static boolean check(int[] record,int index,int j){
        for (int i = 0; i <= index ; i++) {
            // j == record[i] 同列       Math.abs(record[i] - j) == Math.abs(index - i) 同行
            if (j == record[i] || Math.abs(record[i] - j) == Math.abs(index - i)) {
                return false;
            }
        }
        return true;
    }


    public static int num2(int n) {
        if (n < 1 || n > 32) return 0;
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit,0,0,0);
    }

    public static int process2(int limit,int col,int left,int right){
        if (col == limit) return 1;

        int pos = limit & (~(col | left | right));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,col | mostRightOne,(left | mostRightOne) << 1,(right | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int i = num2(4);
        System.out.println(i);
    }

}
