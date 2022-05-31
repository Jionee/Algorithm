import java.util.PriorityQueue;

public class Pg_시험장나누기 {

    public static void main(String[] args) throws Exception{
        //input
        int k = 3;
        int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
        int[][] links = {{-1,-1},{-1,-1},{-1,-1},{-1,-1},{8,5},{2,10},{3,0},{6,1},{11,-1},{7,4},{-1,-1},{-1,-1}};

        int answer = Solution.solution(k,num,links);
    }

    static class Solution {
        /**/
        public static int solution(int k, int[] num, int[][] links) {
            int answer = 0;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(6);
            queue.add(2);
            queue.add(3);
            System.out.println(queue); //큐는 힙구조라서 자식은 정렬되어 존재하지 않음. 뺄 때 정렬되어서 나오는 것,,,
            while(!queue.isEmpty()){
                System.out.println(queue.poll());
            }

            return answer;
        }
        /**/
    }
}
