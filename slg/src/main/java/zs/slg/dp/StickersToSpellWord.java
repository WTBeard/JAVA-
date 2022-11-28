package zs.slg.dp;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        if (stickers == null || target == null || stickers.length == 0 || target.length() == 0) return 0;
        int i = process1(stickers, target);
        return i == Integer.MAX_VALUE ? 0 : i;
    }

    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(sticker, target);
            if (rest.length() != target.length()) {
                int i = process1(stickers, rest);
                min = Math.min(i, min);
            }
        }
        return min == Integer.MAX_VALUE ? min : min + 1;
    }

    public static String minus(String sticker, String target) {
        int[] dp = new int[26];
        char[] c1 = sticker.toCharArray();
        char[] c2 = target.toCharArray();
        for (char c : c2) {
            dp[c - 'a']++;
        }
        for (char c : c1) {
            dp[c - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > 0) {
                builder.append((char) (i + 'a'));
            }
        }
        return builder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        if (stickers == null || target == null || stickers.length == 0 || target.length() == 0) return 0;

        int[][] stickerCounts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                stickerCounts[i][c - 'a']++;
            }
        }
        int i = process2(stickerCounts, target);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    public static int process2(int[][] stickerCounts, String target) {
        if (target.length() == 0) return 0;
        char[] chars = target.toCharArray();
        int[] targetCount = new int[26];
        for (char cha : chars) {
            targetCount[cha - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickerCounts.length; i++) {
            int[] sticker = stickerCounts[i];
            if (sticker[chars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (targetCount[j] > 0) {
                        int num = targetCount[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(process2(stickerCounts, builder.toString()), min);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : 1 + min;
    }

    public static void main(String[] args) {
        int i = minStickers1(new String[]{"ba", "c", "abcd"}, "babac");
        System.out.println(i);
        int a = minStickers2(new String[]{"ba", "c", "abcd"}, "babac");
        System.out.println(a);

    }
}
