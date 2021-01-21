class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] student = new int[n+1];
        for(int i=0;i<student.length;i++){
            student[i]=0;
        }
        for(int i:lost){
            student[i-1]=-1;
        }
        for(int i:reserve){
            System.out.println(student[i-1]);
            student[i-1]=student[i-1]+1;
         }

        for(int i=0;i<student.length;i++){
            if(student[i]==1){
                if(i==0){
                    if(student[i+1]==-1){
                        student[i+1]++;
                    }
                }
                else if(i==n){
                    if(student[i-1]==-1){
                        student[i-1]++;
                    }
                }
                else{
                    if(student[i-1]==-1){
                        student[i-1]++;
                    }
                    else if(student[i+1]==-1){
                        student[i+1]++;
                    }
                }
            }
        }

        answer = n;
        for(int i=0;i<student.length;i++){
            if(student[i]==-1){
                answer--;
            }
        }
        return answer;
    }
}
