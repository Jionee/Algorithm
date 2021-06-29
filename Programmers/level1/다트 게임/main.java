import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int num=0;
        int sNum=0;
        int[] score = new int[3];


        for(int i=0;i<dartResult.length();i++){

            char tmp = dartResult.charAt(i);
            if(String.valueOf(tmp).matches("-?\\d+") && num<3){ //Integer인지 확인
                if(tmp=='1' && dartResult.charAt(i+1)=='0'){ //10
                    score[num]=10;
                    StringBuilder builder = new StringBuilder(dartResult);
                    builder.setCharAt(i+1,' ');
                    dartResult = builder.toString(); 
                }
                else{
                    score[num]=Integer.parseInt(String.valueOf(tmp));
                }

                if(num!=2){
                    num++;
                    sNum=num-1;
                }
                else if(num==2){
                    sNum=num;
                }
                continue;
            }

            else if(tmp == 'S' || tmp == 'D' || tmp == 'T'){
                switch(tmp){
                    case 'S':
                        score[sNum]=(int)Math.pow(score[sNum],1);
                        break;
                    case 'D':
                        score[sNum]=(int)Math.pow(score[sNum],2);
                        break;
                    case 'T':
                        score[sNum]=(int)Math.pow(score[sNum],3);
                        break;
                }
            }
            else if(tmp == '*' || tmp == '#'){
                switch(tmp){
                    case '*':
                        if(sNum==0){
                            score[sNum]=score[sNum]*2;
                        }
                        else{
                            score[sNum-1]=score[sNum-1]*2;
                            score[sNum]=score[sNum]*2;
                        }
                        break;
                    case '#':
                            score[sNum]=score[sNum]*(-1);
                        break;
                }    
            }
        }

        for(int i=0;i<score.length;i++){
            answer+=score[i];
        }
        return answer;
    }
}
