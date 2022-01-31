import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj7579_앱 {
    static int N,M;
    static int[] memory;
    static int[] cost;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = 0;
        for(int i=1;i<N+1;i++){
            maxCost += cost[i];
        }

        dp = new int[N+1][maxCost+1];
        //M 메모리를 확보하기 위한 최소 cost 를 계산해보자
        //dp[i][j] -> i번째 앱까지를 사용할 때, j의 비용으로 확보할 수 있는 메모리
        //i번째 앱을 끄면 cost[i]가 든다. 이전에 i-1번째까지 사용할 때 j의 cost로 확보 가능한 메모리에서
        //dp[i][j-cost[i]] (i번째 앱을 사용하려면 cost[i]가 필요하므로 이 앱을 사용한 경우 vs 사용하지 않은 경우를 비교한다.)
        for(int i=1;i<N+1;i++){ //i번째까지 앱 사용 시
            for(int j=0;j<=maxCost;j++){
                //memory[i] + dp[i-1][j-cost[i]] 랑 dp[i-1][j]를 비교, max를 선택한다.
                if(j-cost[i]>=0){ //지금 사용하고 있는 cost가 i번째 앱의 cost보다 커야 비교를 진행할 수 있다.
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-cost[i]] + memory[i]);
                }
            }
        }
        //System.out.println(Arrays.toString(dp[N]));

        for(int i=0;i<=maxCost;i++){
            if(dp[N][i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
