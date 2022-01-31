import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1932_정수삼각형 {
    static int N;
    static int[][] tree;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new int[N+1][N+1];
        dp = new int[N+1+1][N+1+1];

        //tree 입력받기
        for(int i=1;i<N+1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k=1;
            while(st.hasMoreTokens()){
                tree[i][k] = Integer.parseInt(st.nextToken());
                k++;
            }
        }

        //dp 구성
        //dp[i][j] = min(dp[i][j], dp[i+A][j+B] + tree[i][j])
        //초기값
        dp[1][1] = tree[1][1];
        for(int i=2;i<N+1;i++){ //y
            for(int j=1;j<=i;j++){ //x
                dp[i][j] = tree[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
            }
        }

        int Answer = 0;
        for(int i:dp[N]){
           Answer = Math.max(Answer,i);
        }
        System.out.println(Answer);
    }

}
