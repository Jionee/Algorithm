import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11659_구간합구하기4 {
    static int N,M;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        int i=1;
        while(st.hasMoreTokens()){
            arr[i++] = Integer.parseInt(st.nextToken());
        }
        //0부터 노드까지의 구간합 구하기
        dp[1] = arr[1];
        for(int j=2;j<N+1;j++){
            dp[j] = dp[j-1] + arr[j];
        }

        System.out.println(Arrays.toString(dp));
        //질의
        for(int m=0;m<M;m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(dp[b] - dp[a-1]);
        }
    }
}
