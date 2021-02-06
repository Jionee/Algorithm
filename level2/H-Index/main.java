import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        for(int i=citations[citations.length-1];i>-1;i--){
            int up = 0;
            int down = 0;

            for(int k:citations){
                if(i <= k) up++;
                else down++;
            }
            //System.out.println(i+" - up : "+up+", down : "+down);

            if(i <= up && i >= down){
                answer = i;
                break;
            }
        }

        return answer;
    }
}
