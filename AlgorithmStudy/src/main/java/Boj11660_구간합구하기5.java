import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11660_구간합구하기5 {
    static int N,M;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            int j=1;
            while(st.hasMoreTokens()){
                arr[i][j++] = Integer.parseInt(st.nextToken());
            }
        }

        //구간합 구하기 (1,1)부터 (r,c)까지
        dp[1][1] = arr[1][1];
        for(int r=1;r<N+1;r++){
            for(int c=1;c<N+1;c++){
                dp[r][c] = arr[r][c] + dp[r-1][c] + dp[r][c-1] - dp[r-1][c-1];
            }
        }

        for(int m=0;m<M;m++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int answer = dp[r2][c2] - dp[r1-1][c2] - dp[r2][c1-1] + dp[r1-1][c1-1];
            System.out.println(answer);
        }
    }
}
