import java.io.*;
import java.util.StringTokenizer;

public class Boj12865_평범한배낭 {
    static int N,K;
    static Bag[] Bags;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Bags = new Bag[N+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            Bags[i] = new Bag(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        dp = new int[N+1][K+1];

        //쪼갤 수 없는 배낭문제
        //dp[i][j] > j까지 무게에 i번째 물건을 넣었을 때 최대 가치
        //dp[i][j] = Max(dp[i-1][j], dp[i][j-i무게] + i가치)
        //현재 물건을 사용하지 않는 경우 / 현재 물건을 사용하는 경우 두 가지 중 최대 값을 이용한다.
        for(int i=1;i<N+1;i++){
            for(int j=0;j<K+1;j++){
                if(j>=Bags[i].weight){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-Bags[i].weight] + Bags[i].value);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][K]);
    }

    static class Bag{
        int weight;
        int value;

        public Bag(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
