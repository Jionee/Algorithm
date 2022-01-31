import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11049_행렬곱셈순서 {
    static int N;
    static Matrix matrix[];
    static StringTokenizer st;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new Matrix[N+1];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = new Matrix(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        dp = new int[N+1+1][N+1+1];
        for(int i=1;i<N+1;i++){
            for(int j=i+1;j<N+1;j++){
                dp[i][j] = -1;
            }
        }
        //다하면 물론 가능하겠지만 시간초과가 나겠지?
        //왜지? -> O(N2)만큼의 시간복잡도를 가지니까
        //-> dp로 해결해보자
        //dp 초기화
//        for(int i=1;i<N;i++){ //두개씩은 계산해서 미리 넣어두자
//            dp[i][i+1] = matrix[i].r * matrix[i].c * matrix[i+1].c;
//        }

        //두개씩 계산한거를 이용해서 세개, 4개, ...N개까지의 값들을 계산하자
        //min으로
        //재귀로도 구현해보자 (top-down)
        System.out.println(dfs(1,N));
        for(int[] d :dp){
            System.out.println(Arrays.toString(d));
        }
        //구간이 작은 것 부터 채워줘야 한다. (bottom-up)
        //Dse = min((D(sk) + D(k+1 e) + Ms.r * Mk.c * Me.c) , Dse)
        //k는 s<= k <= e 로 반복한다.
//        for(int s=N-1;s>=1;s--){
//            for(int e=s+1;e<=N;e++){
//                dp[s][e] = INF; //min값을 찾기 위해 무한대로 초기화
//                for(int k=s;k<=e;k++){
//                    dp[s][e] = Math.min(dp[s][e], dp[s][k] + dp[k+1][e] + matrix[s].r * matrix[k].c * matrix[e].c);
//                }
//            }
//        }
        //System.out.println(dp[1][N]); //1부터 N까지의 행렬 곱 경우의 수 중 가장 최소가 되는 경우를 출력한다.
    }
    static int dfs(int s,int e){
        if(s+1 == e){
            return matrix[s].r * matrix[s].c * matrix[e].c;
        }
        if(dp[s][e] != 0 && dp[s][e]!=-1 && dp[s][e] !=INF){ //dp에 값이 들어있으면(갱신이 끝났으면)
            return dp[s][e];
        }
        dp[s][e] = INF;
        for(int k=s;k<=e;k++){
            dp[s][e] = Math.min(dp[s][e],dfs(s,k)+dfs(k+1,e)+matrix[s].r * matrix[k].c * matrix[e].c);
        }
        return dp[s][e];
    }

    static class Matrix{
        int r;
        int c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
