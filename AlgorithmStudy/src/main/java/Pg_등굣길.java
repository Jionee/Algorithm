import java.io.*;
import java.util.*;

public class Pg_등굣길 {
    static int m = 4;
    static int n = 3;
    static int[][] puddles = {{2,2},{2,3}};

    static int[][] dp;

    public static void main(String[] args) throws Exception{
        int answer = 0;
        //(1,1)에서 시작해서 (n,m)까지 가는 최단 경로 중 물을 피해야 함
        //Water = new boolean[n+1][m+1];

        dp = new int[n+1][m+1]; //i,j가 true면 도달할 수 있는 것
        for(int i=0;i<puddles.length;i++){
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }

        //경로를 방문했으면 dp값 갱신하기 + 어차피 오른쪽,아래 한 방향으로밖에 못가니까
        //dp[row][col] = dp[row-1][col] + dp[row][col-1]; 해서 n,m까지
        dp[1][1] = 1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(i==1 && j==1){
                    continue;
                }
                if(dp[i][j] == -1) continue;
                if(dp[i - 1][j] > 0) dp[i][j] += dp[i - 1][j] % 1000000007;
                if(dp[i][j - 1] > 0) dp[i][j] += dp[i][j - 1] % 1000000007;

            }
        }

        for(int[] dpp:dp){
            System.out.println(Arrays.toString(dpp));
        }

        answer = dp[n][m] % 1000000007;
        System.out.println(answer);
    }
}
