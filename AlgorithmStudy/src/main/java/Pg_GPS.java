import java.io.*;
import java.util.*;

public class Pg_GPS {
    static StringTokenizer st;
    static int N,M,K;
    static int[][] edge_list;
    static int[] gps_log;
    static int answer;
    static ArrayList<Integer>[] Graph;
    static int[][] dp;
    static int MAX = 54321;
    
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edge_list = new int[M][2];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            edge_list[i][0] = Integer.parseInt(st.nextToken());
            edge_list[i][1] = Integer.parseInt(st.nextToken());
        }
        K = Integer.parseInt(br.readLine());
        gps_log = new int[K+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            gps_log[i] = Integer.parseInt(st.nextToken());
        }

        //=====
        answer = MAX;
        Graph = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            Graph[i] = new ArrayList<>();
            Graph[i].add(i);
        }
        for(int i=0;i<M;i++){
            Graph[edge_list[i][0]].add(edge_list[i][1]);
            Graph[edge_list[i][1]].add(edge_list[i][0]);
        }

        answer = MAX;
        //bfs로 해보자구
        //bfs();

        //dp로 하자 ㅠㅠ
        dp = new int[K][N+1]; //i번째까지 했을 때 j번 노드인 경우 최소 수정 횟수
        for(int i=0;i<K;i++){
            Arrays.fill(dp[i],MAX);
        }
        answer = dfs(0,gps_log[0],0);
        //System.out.println(answer);

        if(answer == MAX){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }
        //return answer;
    }

    static int dfs(int index, int node, int changeCnt){
        if(index>=K-1){
            if(node==gps_log[K-1]){
                //System.out.println("@@"+index+ " "+changeCnt);
                answer = Math.min(answer,changeCnt);
                return dp[index][node] = 0;
            }
            else{
                //System.out.println("XX");
                return dp[index][node] = MAX;
            }
        }
        if(dp[index][node]!=MAX){
            return dp[index][node];
        }
        //System.out.println(node+"->"+" "+changeCnt);
        int ret = MAX;
        dp[index][node] = 0;

        for(int next:Graph[node]){ //갈수있는 모든 노드들에 대해 탐색할거임
            ret = Math.min(ret,dfs(index+1,next,0));
            //next가 gps[index+1]이랑 같은 경우, 다른 경우
            //비교해놓고, dp[index+1][next] = 0 or 1 넣어두기
//            if(next==gps_log[index+1] || next==gps_log[K-1]){
//                //System.out.println(index+" : "+node+"->"+next+" "+changeCnt);
//                dp[index][node] = Math.min(dp[index][node],dfs(index+1,next,changeCnt));
//            }
//            else{
//                dp[index][node] = Math.min(dp[index][node],dfs(index+1,next,changeCnt+1));
//            }
        }
        return dp[index][node] = (gps_log[index]==node)? ret : ret + 1;
    }

    static void bfs(){
        int answer = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(gps_log[0], 0,0));
        while(!queue.isEmpty()){
            Point now = queue.poll();
            System.out.println(now);
            if(now.level>=K-1 && now.node == gps_log[K-1]){
                answer = Math.min(answer,now.changeCnt);
                continue;
            }
            else if(now.level>=K-1){
                continue;
            }
            int nowNode = now.node;
            if(!Graph[nowNode].contains(gps_log[now.level+1])){ //원래 길이 없음 -> 무조건 고쳐야 함
                for(int next: Graph[nowNode]){
                    queue.add(new Point(next,now.level+1,now.changeCnt+1));
                }
            }
            else{ //길이 있음 -> 안고치고 가는 것, 그냥 굳이 고치고 가는 것
                for(int next: Graph[nowNode]){
                    queue.add(new Point(next,now.level+1,now.changeCnt+1));
                }
                queue.add(new Point(gps_log[now.level+1],now.level+1,now.changeCnt));
            }
        }
        System.out.println(answer);
    }

    static class Point{
        int node;
        int level;
        int changeCnt;

        public Point(int node, int level, int changeCnt) {
            this.node = node;
            this.level = level;
            this.changeCnt = changeCnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "node=" + node +
                    ", level=" + level +
                    ", changeCnt=" + changeCnt +
                    '}';
        }
    }

//    static void dfs(int level, int change){
//        if(level>=K){
//            answer = Math.min(answer,change);
//        }
//        int
//    }
}
