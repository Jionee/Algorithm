import java.util.HashSet;
import java.util.Set;

public class Cd_streemy3 {

    public static void main(String[] args) throws Exception{
        //input
        int A = 10;
        int B = 21;

        Solution.solution(A,B);
    }

    class Solution {
        static int answer = 0;

        public static int solution(int A, int B){
            // write your code in Java SE 8

            int start = 1;
            int end = 1000000000;
            //int end = 1000000000;
            while(start<=end){
                int mid = (start+end)/2;
                //sum갯수 구하기
                int ADiv = A / mid;
                int BDiv = B / mid;
                int sum = ADiv + BDiv;

                if(sum < 4){
                    end = mid-1;
                }
                else{
                    answer = Math.max(answer,mid);
                    start = mid + 1;
                }
            }

            System.out.println(answer);
            return answer;
        }
    }
}
