package codes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nick Yuan
 * @date 2019/3/15
 * @mood shitty
 */
public class Solution {
    public int lengthOfLastWord(String s) {
        int length=0;
        boolean flag=false;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' '){
                length++;
                if(!flag)
                    flag=true;
            }
            else if(flag)
                return length;
        }
        return length;
    }
}
