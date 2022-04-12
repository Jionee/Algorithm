import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cd_streemy2 {

    public static void main(String[] args) throws Exception{
        //input
        int[] A = new int[100000];
        int[] B = new int[100000];
        for(int i=0;i<100000;i++){
            A[i] = i;
            B[i] = 100000-i;
        }
//        int[] A = {1,2,4,3};
//        int [] B = {2,1,2,3};
        Solution.solution(A,B);
    }

    class Solution {
        static int answer = 0;

        public static int solution(int[] A, int[] B){
            //System.out.println(Arrays.toString(A));
            // write your code in Java SE 8
            int L = A.length;
            Set<Integer> set = new HashSet<>();
            for(int i=0;i<L;i++){
                set.add(Math.max(A[i],B[i]));
            }
            //System.out.println(set);

            for(int i=1;i<100001;i++){
                if(!set.contains(i)){
                    answer = i;
                    break;
                }
            }

            System.out.println(answer);
            return answer;
        }
    }
}
