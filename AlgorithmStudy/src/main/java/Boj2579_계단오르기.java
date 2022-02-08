import java.io.*;

public class Boj2579_계단오르기 {
    static int N;
    static int[] stairs;
    static int[][] dp; //[i번째 계단을 밟을 때] [j번째 연속으로 밟고있음] 일 때의 최댓값

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N+1];
        dp = new int[N+1][2+1];

        for(int i=1;i<N+1;i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        //계단 오를건데 1,1,1 로는 못오름
        //초기화
        dp[1][1] = stairs[1];
        for(int i=2;i<N+1;i++){
            //연속으로 3번을 못 밟는 것이 조건이므로 이를 사용하자. (3번을 봐야 함)
            //-> 연속으로 1,2번만 밟을 수 있음을 이용
            dp[i][1] = Math.max(dp[i][1], dp[i-2][1] + stairs[i]); //연속1번 > 2번
            dp[i][1] = Math.max(dp[i][1], dp[i-2][2] + stairs[i]); //연속2번 > 2번
            dp[i][2] = Math.max(dp[i][2], dp[i-1][1] + stairs[i]); //연속1번 > 1번
        }
        bw.write(Math.max(dp[N][1],dp[N][2])+"\n");

        bw.flush();
        bw.close();
    }
}
