import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11438 {
    static StringTokenizer st;
    static int N,M;
    static ArrayList<Integer>[] adjacentList;
    static int K; //SparseTable의 K값
    static int[][] SparseTable;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        depth = new int[N+1];
        adjacentList = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            adjacentList[i] = new ArrayList<>();
        }

        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjacentList[a].add(b);
            adjacentList[b].add(a);
        }

        //1. dfs돌려서 depth 구하기
        depth[1] = 1;
        dfsDepth(1);
        System.out.println(Arrays.toString(depth));
        // 2.전체 트리의 SparseTable 만들기

        //K = 2를 계속 곱해서 <N이 될 때까지
        K = 1;
        while(K < N-1){
            K *= 2;
        }
        SparseTable = new int[K][N];




        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //질의를 해서 둘의 공통 조상 구하기
            //System.out.println(function(a,b));
        }
    }
    static void dfsDepth(int node){
        //체크인 -> depth[node]++;

        //목적지인가?(생략)
        //인접노드 탐색 -> adjacentList[node] 다 돌기
        for(int next:adjacentList[node]){
            System.out.println("**"+next);
            //갈수있는가? -> depth[next]!=0 이면 갈수있음
            if(depth[next]==0){
                //간다 ->dfs(next)
                depth[next] = depth[node] + 1;
                dfsDepth(next);
            }
        }//체크아웃
    }


}
