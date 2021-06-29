import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        ArrayList<Integer> result = new ArrayList<Integer> ();

        for(int i=0;i<arr.length;i++){
            if(arr[i] % divisor==0)
                result.add(arr[i]);
        }

        //오름차순 정렬
        for(int i=0;i<result.size()-1;i++){
            for(int k=i+1;k<result.size();k++){
                if(result.get(i)>result.get(k)){
                    Collections.swap(result,i,k);
                }
            }
        }

        answer = new int[result.size()];
        for(int i=0;i<answer.length;i++){
            answer[i] = result.get(i);
        }
        if(answer.length==0){
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }
}
