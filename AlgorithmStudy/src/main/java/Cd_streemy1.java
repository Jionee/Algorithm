import java.util.Comparator;
import java.util.PriorityQueue;

public class Cd_streemy1 {

    public static void main(String[] args) throws Exception{
        //input
        String str = "aaa";
        int[] C = {10,1,2};

        Solution.solution(str,C);
    }

    class Solution {
        static int answer = 0;

        public static int solution(String S, int[] C){
            // write your code in Java SE 8
            int cost = C[0];
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2,o1);
                }
            });
            queue.add(C[0]);
            boolean flag = false;

            for(int i=0;i<S.length()-1;i++){
                if(S.charAt(i)==S.charAt(i+1)){ //i,i+1 같을때
                    cost += C[i+1];
                    queue.add(C[i+1]);
                    flag = true;
                }
                else{ //다를때
                    if(!queue.isEmpty()){
                        answer+=cost-queue.poll();
                    }
                    queue = new PriorityQueue<>(new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return Integer.compare(o2,o1);
                        }
                    });
                    queue.add(C[i+1]);
                    cost = C[i+1];
                    flag = false;
                }
            }
            if(flag){
                answer+=cost-queue.poll();
            }

            System.out.println(answer);
            return answer;
        }
    }
}
