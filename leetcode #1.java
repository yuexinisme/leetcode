import java.util.ArrayList;
import java.util.HashMap;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

class Solution {
    public int reverse(int x) {
        boolean flag=x>=0;
        x=Math.abs(x);
        int newNum=0;
        int count=0;
        List<Integer>list=new ArrayList<>();
        while(x!=0){
            list.add(x%10);
            count++;
            x=x/10;
        }
        for(int i=0;i<list.size()&&count>0;i++){
            newNum+=Math.pow(10, count--)*list.get(i);
        }
        return flag?newNum:-newNum;
    }
}