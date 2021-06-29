class Solution {
    public int solution(int num) {
        int answer = 0;
        long numm = num;
        while(numm>1){
            if(answer>=500){
                answer=-1;
                break;
            }
            
            if(numm%2==0){
                numm=numm/2;
            }
            else if(numm%2!=0) {
                numm=numm*3+1;
            }
            answer++;
            System.out.println(answer+" : "+numm);
        }
        
        return answer;
    }
}
