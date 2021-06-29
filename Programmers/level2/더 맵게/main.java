import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> arr = new PriorityQueue<>();
        
        for(int i:scoville){
            arr.add(i);
        }
        
        while(arr.size() > 1){ //1개가 되면 멈추기
            int a = arr.poll();
            int b = arr.poll();
        
            arr.add(a+b*2);
            answer++;
            
            if(arr.peek() >= K)
                break;
        }
        
        //1일 때 
        if(arr.peek() < K)
            answer = -1;
        
        return answer;
    }
}
