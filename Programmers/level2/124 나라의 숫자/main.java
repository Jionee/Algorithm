import java.util.*;

class Solution {
    public String solution(int n) {
        String answer = "";
        ArrayList<Integer> nums = new ArrayList<>();
        while(n >= 1){
            int r = n % 3;
            n = n / 3;
            if(r == 0){
                n--;
                r = 4;
            }
            nums.add(r);
        }

        for(int i=nums.size()-1;i>-1;i--){
            answer += String.valueOf(nums.get(i));
        }

        return answer;
    }
}
