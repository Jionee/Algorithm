import java.io.*;
import java.util.StringTokenizer;

public class Boj2342_DanceDanceRevolution {
    static int[] question;
    static int[][][] dp; //dp[i][j][k] = i번째 질의를 해결했을 때, 왼발은 j 오른발은 k에 있을 때 최소 비용
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        question = new int[100000+1];
        int index=1;
        while(st.hasMoreTokens()){
            question[index++] = Integer.parseInt(st.nextToken());
        }

        //dp는 초기화가 중요하다.
        dp = new int[index+1][5][5];
        for(int i=1;i<index+1;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    dp[i][j][k] = INF;
                }
            }
        }
        dp[1][question[1]][0] = 2; //처음 왼발로 움직였을 때
        dp[1][0][question[1]] = 2; //처음 오른발로 움직였을 때

    }
}
