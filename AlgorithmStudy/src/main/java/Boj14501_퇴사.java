import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//10:00 ~
public class Boj14501_퇴사 {
    static StringTokenizer st;
    static int N;
    static Point[] point;
    static int answer = 0;
    static int[] dp;
    static int max = 0;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        point = new Point[N+1];
        dp = new int[N+1+5];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            point[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        //dp[i] : i일까지 수행했을 때의 최대 수익
        for(int i=1;i<N+1;i++){ //1일부터 N일까지 바로 다음 day를 모두 한 번씩 훑고 지나갈 것임
            dp[i] = Math.max(dp[i],max); //dp는 최대 수익을 나타내는 것인데 지금 1일부터 수행을 하고 있으니까 최대 수익 max가 순서대로 증가함
            dp[i+point[i].time] = Math.max(dp[i+point[i].time],dp[i] + point[i].pay); //dp식
            max = Math.max(max,dp[i]); //i까지의 최대 수익을 max에 기록
            //System.out.println(Arrays.toString(dp));
        }
        max = Math.max(max,dp[N+1]); //N의 day가 1인 경우
        System.out.println(max);
    }

    static class Point{
        int time;
        int pay;

        public Point(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
}
