import java.util.Arrays;

public class Pg_dev3 {

    public static void main(String[] args) throws Exception{
        //input
        int n = 8;
        int[][] edges = {{0,1},{1,2},{2,3},{4,0},{5,1},{6,1},{7,2},{7,3},{4,5},{5,6},{6,7}};
        int k= 4; int a= 0; int b = 3;

        Solution.solution(n,edges,k,a,b);
    }

    static class Solution {
        static int[][] Matrix;
        static int N,K,A,B;

        public static int solution(int n, int[][] edges, int k, int a, int b) {
            int answer = -1;
            N = n; K = k; A = a; B = b;

            Matrix = new int[n][n];
            for(int i=0;i< edges.length;i++){
                Matrix[edges[i][0]][edges[i][1]] = 1;
                Matrix[edges[i][1]][edges[i][0]] = 1; //양방향
            }

            for(int i=0;i< Matrix.length;i++){
                System.out.println(Arrays.toString(Matrix[i]));
            }

            //a에서 b로 가는 경로 구하기
            dfs(a,0);

            return answer;
        }

        static void dfs(int node,int count){
            if(count>=K){
                return;
            }

            for(int i=0;i<N;i++){
                if(Matrix[node][i] == 1){ //갈수잇으면

                    dfs(i,count+1);
                }
            }
        }
    }
}
