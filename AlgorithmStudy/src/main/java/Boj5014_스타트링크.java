import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5014_스타트링크 {
    static int F,S,G,U,D;
    static int[] dp;
    static int[] dFloor = new int[2];
    static int minAnswer = Integer.MAX_VALUE;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dp = new int[F+1];
        Arrays.fill(dp,-1);
        dFloor[0] = U;
        dFloor[1] = -D;

        queue.add(S);
        dp[S] = 0;

        while(!queue.isEmpty()){
            int now = queue.poll();
            //System.out.println(now+" "+dp[now]);
            if(now == G){
                break;
            }
            for(int i=0;i<2;i++){
                int next = now + dFloor[i];
                if(1<=next && next<=F && dp[next] == -1){
                    dp[next] = dp[now] + 1;
                    queue.add(next);
                }
            }
        }
        if(dp[G]==-1){
            System.out.println("use the stairs");
        }
        else{
            System.out.println(dp[G]);
        }
    }

    //dfs는 오답
    public static int dfs(int floor,int click){

        //1. 체크인
        dp[floor] = click;
        //System.out.println(Arrays.toString(dp));
        //System.out.println(floor+" "+click);
        //2. 목적지인가?
        if(floor == G){
            return Math.min(minAnswer,click);
        }
        //3. 인접한 점
        for(int i=0;i<2;i++){
            int next = floor + dFloor[i];
            //4. 갈수잇는가?
            if(1<=next && next<=F && dp[next] == -1){
                //5. 간다
                //System.out.println("U?D? - "+i + " now "+floor+" to next "+next);
                return dfs(next,click+1);
            }
        }
        //6. 체크아웃
        return -1;
    }
}
