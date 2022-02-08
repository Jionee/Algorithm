import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11438_LCA2 {
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

        //K = 2를 계속 곱해서 <N이 될 때까지
//        K = 1;
//        while(K < N-1){
//            K *= 2;
//        }
        K = getLogN(); //SparseTable의 K값
        int K2 = getLogN2();
        System.out.println("111 "+K);
        System.out.println("222 "+K2);

        SparseTable = new int[K+1][N+1];

        //1. bfs돌려서 depth 구하기
        bfsDepth(1);

        // 2.전체 트리의 SparseTable 만들기
        makeSparseTable();

        //질의
        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //질의를 해서 둘의 공통 조상 구하기
            System.out.println(getLCA(a,b));
        }
    }
    //최대N개의 노드들이 들어갈 수 있는 트리의 최대 높이 구하기
    //k(logN)=0 N=1개, k=1 N=3개, k=2 N=7개 ...
    static int getLogN(){ //tree의 높이 구하기(sparseTable의 i 크기)
        int logN = 0;
        for(int i=1;i<N;i*=2){
            logN++;
        }
        return logN;
    }

    static int getLogN2(){
        int k=1;
        int logN = 0;

        while(k < N){
            k *= 2;
            logN++;
        }
        return logN;
    }

    static void bfsDepth(int start){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        depth[start] = 1; //루트 노드 depth = 1 초기화
        dq.add(start);

        while(!dq.isEmpty()){
            int now = dq.poll();
            for(int next:adjacentList[now]){
                if(depth[next] == 0){ //탐색하지 않은 곳만 탐색한다.
                    depth[next] = depth[now] + 1;
                    SparseTable[0][next] = now; //1번째 조상을 기록한다.
                    dq.add(next);
                }
            }
        }
    }

    static void makeSparseTable(){
        for(int i=1;i<=K;i++){ //2의 지수승만큼 돌면서
            for(int j=1;j<=N;j++){ //모든 노드들에 대해
                int parentIdx = SparseTable[i-1][j];
                SparseTable[i][j] = SparseTable[i-1][parentIdx];
            }
        }
    }

    static int getLCA(int a,int b){
        //a가 더 깊이 있음을 가정한 로직
        if(depth[a] < depth[b]){
            return getLCA(b,a);
        }
        //1. a를 b높이까지 맞추기
        for(int i=0;i<=K;i++){ //logN(K)번 돌면서 높이 맞추기
            //높이 차이가 5이라고 하면 0b101, i가 0일 때 0b101 & 0b1 = 1 이므로 올려야 한다.
            //그러면 a가 2의i승(2의0승 = 1)만큼 올라가고, 높이차이는 4가 됨
            //0b100, i가 1일 때 0b100 & 0b10 = 0 이므로 올라가지 않음
            //       i가 2일 때 ob100 & 0b100 = 1 이므로 올린다. 2의i승(2의2승 = 4)만큼 올라가고, 둘의 높이가 같아짐
            if(((depth[a]-depth[b]) & (1<<i)) >= 1){
                a = SparseTable[i][a];
            }
        }
        //2. 높이를 맞췄으면, 공통 조상을 찾을 때가지 같이 올려주기
        if(a == b){ //높이를 맞췄는데 둘이 같아짐 ==> 공통조상으로 옴 (끝내기)
            return a;
        }

        //공통조상이 아닐 때 부모를 찾아 올라가자
        for(int i=K;i>=0;i--){
            if(SparseTable[i][a] != SparseTable[i][b]){ //올렸는데 조상이 같지 않으면 그 바로 위가 공통조상임
                a = SparseTable[i][a];
                b = SparseTable[i][b];
            }
        }
        return SparseTable[0][a]; //바로 밑에까지 왔으므로 바로 위에가 공통조상이다. (리턴)
    }

}
