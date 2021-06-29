import java.util.*;

class Solution {
    public boolean solution(int x) { 
        boolean answer;

        String tmp = Integer.toString(x); 
        int[] num = new int[tmp.length()];

        for(int i=0;i<num.length;i++){
            num[i] = Integer.parseInt(Character.toString(tmp.charAt(i)));
            System.out.println("num["+i+"]"+num[i]);
        }

        int sum=0;
        for(int i=0;i<num.length;i++){
            sum += num[i];
        }

        System.out.println("sum : "+sum);
        if(x%sum==0)
            answer=true;
        else
            answer=false;

        return answer;
    }
}
