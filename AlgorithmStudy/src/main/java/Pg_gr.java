import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Pg_gr {

    public static void main(String[] args) throws Exception{
        //input
        int[] bricks = {1, 2, 5, 3, 1, 0, 2, 3};
        int n = 6;
        int k = 2;

        int answer = Solution.solution(bricks, n, k);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static int B,N,K;
        static int[] Bricks,newBricks;
        static int answer = Integer.MAX_VALUE;
        static ArrayList<Integer> Arr = new ArrayList<>();
        public static int solution(int[] bricks, int n, int k) {

            N = n;
            B = bricks.length;
            K = k;
            Bricks = new int[B];
            newBricks = new int[B];
            for(int i=0;i<B;i++){
                Bricks[i] = bricks[i];
                newBricks[i] = bricks[i];
            }

            //k-1개의 n높이를 만들고, 이게 물웅덩이 K개를 만족하는지
            //이후에 벽돌 갯수 해서 최소값
            dfs2(0,-1);


            return answer;
        }
        static void dfs2(int count,int idx){
            if(count>=K-1){
                //K개 웅덩이를 만족하는지
                //answer 갱신하기
                int cnt = 0;
                int sub = -1;
                for(int index:Arr){
                    if(index - sub > 1){
                        cnt++;
                    }
                    sub = index;
                }
                if((B-1) - sub >= 1){
                    cnt++;
                }
                System.out.println(Arr);
                System.out.println(cnt);
                if(cnt==K){
                    //벽돌 갯수 구하기
                    int brickNum = 0;
                    for(int index:Arr){
                        brickNum += N - Bricks[index];
                    }
                    answer = Math.min(answer,brickNum);
                }
                return;
            }
            for(int i=idx+1;i<B;i++){
                Arr.add(i);
                dfs2(count+1,i);
                Arr.remove(Arr.size()-1);
            }

        }

        /**/
    }
}
