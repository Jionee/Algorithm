import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pg_다단계칫솔판매 {

    public static void main(String[] args) throws Exception{
        //input
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        Solution.solution(enroll,referral,seller,amount);
    }

    class Solution {
        //static ArrayList<Person> Array = new ArrayList<>();
        static HashMap<String,Person> Map = new HashMap();

        public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            int[] answer = new int[enroll.length];
            for(String enr:enroll){ //사람 등록
                Map.put(enr,new Person());
            }
            for(int i=0;i<referral.length;i++){ //parent 설정
                Map.get(enroll[i]).parent = referral[i];
            }

            for(int i=0;i<seller.length;i++){
                calculate(seller[i],amount[i]*100);
            }

            for(int i=0;i<enroll.length;i++){
                answer[i] = Map.get(enroll[i]).sum;
            }
            //System.out.println(Arrays.toString(answer));
            return answer;
        }

        static void calculate(String whom, int amount){
            if(whom.equals("-")){
                return;
            }
            if(amount==0){
                return;
            }
            int two = (int) (amount * 0.1);
            int nine = amount - two;
            Map.get(whom).sum += nine;
            calculate(Map.get(whom).parent, two);
        }

        static class Person{
            String parent;
            int sum;
        }
    }
}
