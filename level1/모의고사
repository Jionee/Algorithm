import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] score = new int[3];

        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {2,1,2,3,2,4,2,5};
        int[] arr3 = {3,3,1,1,2,2,4,4,5,5};
        int[] lengths = new int[3];
        lengths[0]=arr1.length;
        lengths[1]=arr2.length;
        lengths[2]=arr3.length;

        for(int i=0;i<answers.length;i++){
            if(answers[i]==arr1[i%lengths[0]]) score[0]++;
            if(answers[i]==arr2[i%lengths[1]]) score[1]++;
            if(answers[i]==arr3[i%lengths[2]]) score[2]++;
        }

        //Find maxPoint
        int max = 0;
        for(int i=0;i<score.length;i++){
            if(score[i]>max)
                max = score[i];
        }

        ArrayList<Integer> winner = new ArrayList<Integer>();
        for(int i=0;i<score.length;i++){
            if(score[i]==max)
                winner.add(i+1);
        }


        //정답 완성하기
        answer = new int[winner.size()];
        for(int i=0;i<winner.size();i++){
            answer[i] = winner.get(i);
        }

        return answer;
    }
}
