import java.io.*;

public class Boj5582_공통부분문자열 {
    public static String strA,strB;
    public static int[][] dp; //dp[i][j] A의 i번째, B의 j번째까지 갔을 때 최대 부분 문자열 길이 저장
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        strA = br.readLine();
        strB = br.readLine();
        dp = new int[strA.length()+1][strB.length()+1];

        int Answer = 0;
        for(int i=1;i<strA.length()+1;i++){
            for(int j=1;j<strB.length()+1;j++){
                //모든 점을 다 돌건데, 같으면 이전 dp값에 1 더하면 됨
                if(strA.charAt(i-1) == strB.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    Answer = Math.max(Answer,dp[i][j]);
                }
            }
        }

        bw.write(Answer + "\n");
        bw.flush();
        bw.close();

    }
}
