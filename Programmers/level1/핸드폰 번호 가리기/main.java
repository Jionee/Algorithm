import java.util.*;
class Solution {
    public String solution(String phone_number) {
        String answer = "";
        StringBuffer sb = new StringBuffer();
        sb.append(phone_number);
        
        for(int i=phone_number.length()-1-4;i>-1;i--){
            sb.setCharAt(i,'*');
        }
        answer = sb.toString();
        return answer;
    }
}
