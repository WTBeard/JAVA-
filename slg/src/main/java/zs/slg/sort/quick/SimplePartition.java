package zs.slg.sort.quick;

/**
 * 给定一个x，数组，<=x 放左边  >x放右边
 */
public class SimplePartition {

    public static int partition(int[] arr, int L, int R) {
        if (L > R) return -1;
        if (L == R) return L;

        int lb = L - 1;
        int index = L;
        while (index < R){
            if (arr[index] <= R){
                swap(arr,++lb,index++);
            }else {
                index++;
            }
        }
        swap(arr,++lb,R);
        return lb;
    }


    public static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
