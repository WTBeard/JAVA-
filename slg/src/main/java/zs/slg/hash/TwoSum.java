package zs.slg.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两数之和
 * https://leetcode.cn/problems/two-sum/
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]),i};
            }
            int diff = target - nums[i];
            map.put(diff,i);
        }
        return new int[]{-1,-1};
    }


    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{2, 7, 11, 15}, 9);
    }
}
