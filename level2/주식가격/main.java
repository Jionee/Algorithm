class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        answer = new int[prices.length];
        int res=0;
        for(int i=0;i<prices.length;i++){
            res=0;
            for(int k=i+1;k<prices.length;k++){
                res++; 
                if(prices[i]>prices[k])
                    break;   
            }
            answer[i]=res;
        }
        
        return answer;
    }
}
