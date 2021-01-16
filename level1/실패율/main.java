import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        ArrayList<ArrayList<Float>> stageF = new ArrayList<>();
        ArrayList<Float> eachStage;

        int fix=0;

        for(int i=0;i<N;i++){ //N:5 > 0~4
            int total=0;
            int clear=0;
            eachStage = new ArrayList<>();
            fix=i+1;
            for(int k=0;k<stages.length;k++){
                if(stages[k]>=fix){//도전 완료
                    total++; 
                    if(stages[k]==fix)
                        clear++;
                }
            }
            eachStage.add((float)fix); //stage
            eachStage.add((float)clear/total); //실패율
            stageF.add(eachStage);
        }

        //For print
        for(int i=0;i<stageF.size();i++){
            System.out.println(stageF.get(i).get(0)+" stage - "+stageF.get(i).get(1));
        }

        //버블정렬사용
        for(int i=0;i<stageF.size();i++){
            for(int k=0;k<stageF.size()-i-1;k++){
                if(stageF.get(k).get(1)<stageF.get(k+1).get(1))
                    Collections.swap(stageF,k,k+1);
            }
        }

        answer = new int[stageF.size()];
        for(int i=0;i<stageF.size();i++){
            answer[i]=Math.round(stageF.get(i).get(0));
        }
        
        return answer;
    }
}
