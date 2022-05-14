import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pg_nav2 {

    public static void main(String[] args) throws Exception{
        //input
        int[][] recruits = {{1,50},{1,60},{3,70},{0,65},{2,50},{1,90}};
        int s1 = 2;
        int s2 = 70;
        int[] answer = Solution.solution(recruits,s1,s2);
        System.out.println(answer[0]+" "+answer[1]);
    }

    static class Solution {
        /**/
        static ArrayList<Integer> e1e2 = new ArrayList<>();
        static ArrayList<Point> answerArr = new ArrayList<>();
        public static int[] solution(int[][] recruits, int s1, int s2) {
            int[] answer = new int[2];
            //System.out.println("asdf");

            //e1,e2 100까지
            dfs(0,recruits,s1,s2);
            //System.out.println("CEHCK");
            //check(2,50,recruits,s1,s2);
            //System.out.println("CEHCK");

            Collections.sort(answerArr);
            //System.out.println(answerArr);
            answer[0] = answerArr.get(0).e1;
            answer[1] = answerArr.get(0).e2;

            return answer;
        }
        static void dfs(int count,int[][] recruits, int s1, int s2){
            if(count>=2){
                int e1 = e1e2.get(0);
                int e2 = e1e2.get(1);
                //검사 고고
                boolean result = check(e1,e2,recruits,s1,s2);
                if(result){
                    //System.out.println(e1e2);

                    answerArr.add(new Point(e1,e2));
                }
                return;
            }
            for(int i=1;i<=100;i++){
                e1e2.add(i);
                dfs(count+1,recruits,s1,s2);
                e1e2.remove(e1e2.size()-1);
            }
        }
        static boolean check(int e1,int e2, int[][] recruits, int s1, int s2){
            boolean[] check = new boolean[recruits.length];
            int eCount = 0;
            int sCount = 0;
            int jCount = 0;

            for(int i=0;i< recruits.length;i++){
                if(!check[i]){
                    if(recruits[i][0]>=e1 && recruits[i][1]>=e2){

                        check[i] = true;
                        eCount++;
                    }
                }
            }
            //System.out.println(Arrays.toString(check));
            for(int i=0;i< recruits.length;i++){
                if(!check[i]){
                    if(recruits[i][0]>=s1 || recruits[i][1]>=s2){
                        check[i] = true;
                        sCount++;
                    }
                }
            }
            jCount = check.length - eCount - sCount;
            //System.out.println(eCount +" "+ sCount +" "+ jCount);
            if(0<eCount && eCount<sCount && sCount<jCount){
                return true;
            }
            return false;
        }

        /**/
        static class Point implements Comparable<Point>{
            int e1;
            int e2;

            public Point(int e1, int e2) {
                this.e1 = e1;
                this.e2 = e2;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "e1=" + e1 +
                        ", e2=" + e2 +
                        '}';
            }

            @Override
            public int compareTo(Point o) {
                int cmp = Integer.compare(o.e1+o.e2, e1+e2);
                if(cmp==0){
                    cmp = Integer.compare(o.e1,e1);
                }
                return cmp;
            }
        }
    }

}
