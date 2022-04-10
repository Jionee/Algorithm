public class Cd_FrogJump {

    public static void main(String[] args) throws Exception{
        //input

        Solution.solution(10,85,30);
    }

    class Solution {
        static int answer = 0;


        public static int solution(int X, int Y, int D){
            // write your code in Java SE 8
            int divide = (Y-X) / D;
            int mod = (Y-X) % D;
            if(mod != 0){
                divide++;
            }
            //System.out.println(divide);
            return divide;
        }


    }
}
