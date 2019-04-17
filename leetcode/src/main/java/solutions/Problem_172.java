package solutions;

/**
 * @author Nick Yuan
 * @date 2019/4/17
 * @mood shitty
 * Given an integer n, return the number of trailing zeroes in n!.
 */
public class Problem_172 {
    /**
     * Simply count the number of numbers ending with 5, as 10 can only be multiplied by
     * single digits 2 and 5, and a 2 must come before 5.
     * @param n Integer
     * @return the number of trailing zeroes in n!
     */
    public int trailingZeroes(int n) {
        int count=0;
        while(n>4){
            n/=5;
            count+=n;
        }
        return count;
    }
}
