import java.util.ArrayList;

public class Cd_binarygap {

    public static void main(String[] args) throws Exception{
        //input
        int N = 32;
        Solution.solution(N);
    }

    class Solution {
        static int answer = 0;
        public static int solution(int N) {
            // write your code in Java SE 8

            String binary = getBinary(N);
            ArrayList<Long> index = new ArrayList<>();
            for(long i=0;i<binary.length();i++){
                if(binary.charAt((int) i)=='1'){
                    index.add(i);
                }
            }

            //System.out.println(index);
            for(long i=0;i<index.size()-1;i++){
                long count = 0;
                long first = index.get((int) i);
                long second = index.get((int) (i+1));

                for(long j = first+1;j<second;j++){
                    if(binary.charAt((int)j) == '0'){
                        count++;
                    }
                    else{
                        break;
                    }
                }
                answer = (int) Math.max(answer,count);
            }
            // System.out.println(answer);
            return answer;
        }
        static String getBinary(int N){
            StringBuilder sb = new StringBuilder();
            while(true){
                int divide = N/2;
                int mod = N%2;
                //System.out.println(divide+" "+mod);
                sb.append(mod);
                N = divide;
                if(divide==1){
                    sb.append(1);
                    sb.reverse();
                    return sb.toString();
                }
            }
        }
    }
}
