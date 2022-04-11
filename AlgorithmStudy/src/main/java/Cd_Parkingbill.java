public class Cd_Parkingbill {

    public static void main(String[] args) throws Exception{
        //input

        Solution.solution("10:50","11:10");
    }

    class Solution {
        static int answer = 0;


        public static int solution(String E, String L){
            // write your code in Java SE 8

            int firstHour = Integer.parseInt(E.split(":")[0]);
            int firstMinute = Integer.parseInt(E.split(":")[1]);
            int secondHour = Integer.parseInt(L.split(":")[0]);
            int secondMinute = Integer.parseInt(L.split(":")[1]);

            int fullHour = secondHour - firstHour;
            int minute = 0;
            if(firstMinute<=secondMinute){
                minute = secondMinute - firstMinute;
            }
            else{
                minute = 60 + secondMinute - firstMinute;
                fullHour--;
            }

            answer+=2;//entrance
            if(minute>0){
                fullHour++;
            }
            if(fullHour>0){
                answer+=3; //첫 1시간
                answer+=(4*(fullHour-1));
            }
            //System.out.println(answer);
            return answer;
        }


    }
}
