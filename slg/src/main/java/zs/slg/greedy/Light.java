package zs.slg.greedy;

/**
 * 给定一个字符串str，只由'X'和'.'两种字符构成
 * 'X' 表示墙，不能放灯，也不需要点亮；
 * '.' 表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让 i-1，i 和 i+1 三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Light {

    public static int minLight(String road) {
        if (road == null || road.length() == 0) return 0;

        char[] chars = road.toCharArray();
        int i = 0;
        int res = 0;
        while (i < chars.length){
            if (chars[i] == 'X'){
                i++;
                continue;
            }
            res++;
            if (i + 1 == chars.length) {
                break;
            }
            if (chars[i + 1] == 'X') { // . x
                i = i + 2;
            } else {
                i = i + 3; // . . .
            }
        }
        return res;
    }
}
