import java.util.HashMap;

public class Pg_124나라의숫자 {

    public static void main(String[] args) throws Exception{
        //input
        int n = 5;
        Solution.solution(n);
    }

    static class Solution {
        static char[] newNumber = {'1','2','4'};
        static String answer;
        public static String solution(int n) {
            answer = "";

            StringBuilder sb = new StringBuilder();
            HashMap<Integer,Character> Map = new HashMap<>();
            Map.put(1,'1');
            Map.put(2,'2');
            Map.put(0,'4');
            int mock = n;
            int namuji = 0;
            while(true){
                mock = n / 3;
                namuji = n % 3;
                n = mock;
                sb.append(Map.get(namuji));
                if(mock==0){
                    break;
                }
            }
            answer = sb.reverse().toString();

            System.out.println(answer);
            return answer;
        }

    }
}
