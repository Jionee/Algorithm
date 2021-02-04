import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> day = new LinkedList<>();
        
        //남은 날 Queue 생성
        for(int i=0;i<progresses.length;i++){
            int leftDay = (100-progresses[i])/speeds[i];
            if((100-progresses[i])%speeds[i]!=0){
                leftDay++;
            }
            day.offer(leftDay);
        }
        
        for(int i:day){
            System.out.println(i);
        }
        
        ArrayList<Integer> nums = new ArrayList<>();
        
        while(day.size()>0){
            int num=1;
            int tmp = day.poll();
            while(day.size()>0){ //비교
                int tmp2 = day.peek();
                if(tmp >= tmp2){
                    num++;   
                    day.remove();
                }
                else{    
                    break;
                }            
            }
            nums.add(num);
        }
        
        answer = new int[nums.size()];
        for(int i=0;i<nums.size();i++){
            answer[i] = nums.get(i); 
        }
        
        return answer;
    }
}
