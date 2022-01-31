import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2098_외판원순회 {
    static StringTokenizer st;
    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    static int VISITALL;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost =  new int [N+1][N+1];
        VISITALL = (1<<N) - 1;
        dp = new int[N+1][VISITALL+1]; //minCost = [이번에방문노드][visited]

        for(int i=1;i<N+1;i++){ //min값을 구해야 하므로 INF로 dp배열을 초기화한다.
            Arrays.fill(dp[i],INF);
        }

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //외판원순회 문제
        //1~N번까지(<=16) 도시가 존재하고, 모든 도시를 거쳐 다시 시작점으로 돌아오는 최소비용 여행 계획을 세우자
        //다시 시작점으로 돌아오므로, cycle을 이룬다. 즉 어디서 시작을 하더라도 전체 최소비용은 동일하다. -> 1에서 시작하는 것으로 설정하자
        //이동한 경로 저장은 할 필요 없고, 방문 여부만 다루면 된다 -> 비트마스킹
        System.out.println(dfs(1,1)); //1을 방문했다는 것을 visited로 전달 2의 1-1승
    }

    static int dfs(int node,int visited){
        //System.out.println(node + " "+ visited);
        //중단조건 -> 모든 점을 방문했을 때 -> 시작인 1 도시로 돌아가기
        if(visited == VISITALL){
            if(cost[node][1] == 0){
                return INF;
            }
            return cost[node][1];
        }

        //dp테이블
        if(dp[node][visited]!=INF){
            return dp[node][visited];
        }

        //가지치기 (모든 노드를 다 탐색하긴 해야함)
        //방문했거나, -> visited에서 next가 1이면 pass
        for(int next=1; next<N+1; next++){
            //갈수있는가?
            if((visited & (1<<(next-1))) == 0 && cost[node][next]!=0){ //next를 방문 안했으면 방문 시작 ,길이 있어야 방문
                //간다 > dp갱신
                dp[node][visited] = Math.min(dp[node][visited], dfs(next, visited | (1<<(next-1))) + cost[node][next]) ;
            }
        }
        return dp[node][visited];
    }
}