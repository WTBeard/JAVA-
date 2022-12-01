package zs.slg.windows;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w) {
        if(arr == null || arr.length == 0) return null;

        int n = arr.length;
        int[] res = new int[n - w + 1];
        int index = 0;

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // 队列不为空,并且 双端队列的尾巴 小于当前值 就需要 排除
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] <= arr[i]){
                linkedList.pollLast();
            }
            linkedList.addLast(i);
            // 判断是否超出指定个数,超出的那个有正好头结点,就排除
            if (i - w == linkedList.peekFirst()){
                linkedList.pollFirst();
            }
            // 是否正好满足,满足加入结果集
            if (i >= w - 1){
                res[index++] = arr[linkedList.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] maxWindow = getMaxWindow(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3);
        for (int e:maxWindow) {
            System.out.println(e);
        }
    }
}
