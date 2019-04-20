package codes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nick Yuan
 * @date 2019/4/13
 * @mood shitty as fuck but hopefully it's 'transient'
 * @problem leetcode#4 hard
 */
public class Solution {
    /**First create a third array to store the combined result. Use a counter to monitor
     * the number of elements inserted. The insertion
     * stops when one array has been successfully inserted. Then check whether
     * the one/two needed digit(s) is/are fullfilled. If not, retrive it/them from the other array.
     * This one is a bitch to crack due to its abundance of corner cases, so beware.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length==0){
            return nums2.length%2==0?(nums2[nums2.length/2]+nums2[nums2.length/2-1])/2.0:nums2[nums2.length/2];
        }
        if(nums2.length==0){
            return nums1.length%2==0?(nums1[nums1.length/2]+nums1[nums1.length/2-1])/2.0:nums1[nums1.length/2];
        }
        int[]nums3=new int[nums1.length+nums2.length];
        int left=0,right=0,index=0;
        while(left<nums1.length&&right<nums2.length){
            if(nums1[left]>nums2[right]){
                nums3[index]=nums2[right];
                right++;
            }else{
                nums3[index]=nums1[left];
                left++;
            }
            index++;
        }
        if(nums3.length/2>index-1){
            if(left>=nums1.length){
                nums3[nums3.length/2]=nums2[nums2.length-nums3.length+nums3.length/2];
            }else if(right>=nums2.length){
                nums3[nums3.length/2]=nums1[nums1.length-nums3.length+nums3.length/2];
            }
        }
        if(nums3.length/2-1>index-1){
            if(left>=nums1.length){
                nums3[nums3.length/2-1]=nums2[nums2.length-nums3.length+nums3.length/2+1];
            }else if(right>=nums2.length){
                nums3[nums3.length/2-1]=nums1[nums1.length-nums3.length+nums3.length/2+1];
            }
        }
        return nums3.length%2==0?(nums3[nums3.length/2]+nums3[nums3.length/2-1])/2.0:nums3[nums3.length/2];
    }
}
