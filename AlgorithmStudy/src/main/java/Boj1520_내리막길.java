import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1520_내리막길 {
    static int M,N;
    static int[][] Map;
    static StringTokenizer st;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,1,-1};
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Map = new int[M+1][N+1];
        dp = new int[M+1][N+1];
        for (int[] ints : dp) {
            Arrays.fill(ints,-1);
        }

        for(int i=1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(1,1));
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }

    static int dfs(int row,int col){
        if(row == M && col == N){
            return 1;
        }
        if(dp[row][col]!=-1){
            return dp[row][col];
        }
        dp[row][col] = 0; //방문처리
        for(int i=0;i<4;i++){
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if(0<newRow && newRow<=M && 0<newCol && newCol <=N){
                if(Map[row][col] >Map[newRow][newCol]){
                    dp[row][col] += dfs(newRow,newCol);
                }
            }
        }
        return dp[row][col];
    }
}