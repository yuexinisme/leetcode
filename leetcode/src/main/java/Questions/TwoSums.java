package Questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nick
 * @date 2018/12/25
 */
public class TwoSums {
    public static int[] twoSum(int[] nums, int target){
        int[] ints=new int[2];
        Map<Integer,Integer> temp=new HashMap<>();
        for(int a=0;a<nums.length;a++){
            if(temp.containsKey(target-nums[a])){
                ints[0]=temp.get(target-nums[a]);
                ints[1]=a;
                return ints;
            }
            temp.put(nums[a],a);
        }
        return ints;
    }

    public static void main(String[] args) {
        int[] nums={2,35,6,3,7,13,25,33,33};
        int[] ints = TwoSums.twoSum(nums, 8);
        System.out.println(Arrays.toString(ints));

    }
}
