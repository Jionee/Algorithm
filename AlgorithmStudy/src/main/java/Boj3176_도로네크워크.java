import java.io.*;
import java.util.*;

public class Boj3176_도로네크워크 {
    static int N,K;
    static ArrayList<Info>[] AdjacentList;
    static StringTokenizer st;
    static int logN;
    static int[][] SparseTable;
    static int[][] MaxDistance;
    static int[][] MinDistance;
    static int[] depth;
    static int min,max;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        AdjacentList = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            AdjacentList[i] = new ArrayList<>();
        }
        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            AdjacentList[a].add(new Info(b,w)); //양방향
            AdjacentList[b].add(new Info(a,w));
        }
        //LCA 이용 -> 두 노드의 공통조상을 찾는다는 것은 결국 둘 사이에 이어지는 길을 찾는다는 것을 말한다.
        //그 중 최대 간선, 최소 간선을 저장해 놓는다면 구할 수 있음
        //0. logN구해서 SparseTable 초기 세팅
        //1. bfs 돌려서 depth 구하기
        //2. SparseTable 만들기
        //3. D,E의 높이를 맞추면서 공통 조상 구하기 (질의)

        //0. logN구해서 SparseTable 초기 세팅
        logN = getLogN();
        SparseTable = new int[logN+1][N+1];
        MaxDistance = new int[logN+1][N+1];
        MinDistance = new int[logN+1][N+1];
        depth = new int[N+1];

        //1. bfs 돌려서 depth 구하기, MaxDistance, minDistance도 함께 구하기
        doBfs(1); //root를 1로 설정

        //2. SparseTable 만들기
        makeSparseTable();

        //3. D,E의 높이를 맞추면서 공통 조상 구하기 (질의)
        K = Integer.parseInt(br.readLine());
        for(int i=1;i<K+1;i++){
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            //D와 E를 연결하는 경로에서 가장 짧은 도로의 길이, 가장 긴 도로의 길이 출력하기

            getMinMax(D,E);
            System.out.println(min+" "+max);
        }
    }

    private static void makeSparseTable() {
        for(int i=1;i<=logN;i++){
            for(int j=1;j<N+1;j++){
                int parentIdx = SparseTable[i-1][j]; //바로 위 logN층의 값이 부모의 인덱스이다.
                SparseTable[i][j] = SparseTable[i-1][parentIdx];
                MinDistance[i][j] = Math.min(MinDistance[i-1][j],MinDistance[i-1][parentIdx]);
                MaxDistance[i][j] = Math.max(MaxDistance[i-1][j],MaxDistance[i-1][parentIdx]);
            }
        }
    }

    //k(logN)=0 N=1개, k=1 N=3개, k=2 N=7개 ...
    static int getLogN(){
        int logN = 0;
        for(int i=1;i<N;i*=2){
            logN++;
        }
        return logN;
    }

    static void doBfs(int start){
        Queue<Integer> queue = new LinkedList<>(); //노드의 Index를 가지고 있음
        depth[start] = 1;
        queue.add(start);

        while(!queue.isEmpty()){
            int now = queue.poll();
            for(Info next:AdjacentList[now]){
                if(depth[next.end]==0){ //갈 수 있는가?
                    depth[next.end] = depth[now] + 1; //Depth 초기화
                    SparseTable[0][next.end] = now; //SparseTable 초기화
                    MaxDistance[0][next.end] = next.weight;
                    MinDistance[0][next.end] = next.weight;
                    queue.add(next.end);
                }
            }
        }
    }

    static int getMinMax(int a, int b){
        min = Integer.MAX_VALUE;
        max = 0;

        //0. a가 더 깊이 있다고 가정
        if(depth[a] < depth[b]){
            return getMinMax(b,a);
        }
        //1. a를 b높이까지 맞추기
        for(int i=0;i<logN+1;i++){
            if(((depth[a]-depth[b]) & (1<<i)) >= 1){
                min = Math.min(min,MinDistance[i][a]);
                max = Math.max(max,MaxDistance[i][a]);
                a = SparseTable[i][a];
            }
        }
        if(a == b){
            return a;
        }

        //2. a,b를 올리기
        for(int i=logN;i>=0;i--){
            if(SparseTable[i][a] != SparseTable[i][b]){
                min = Math.min(min,Math.min(MinDistance[i][a],MinDistance[i][b]));
                max = Math.max(max,Math.max(MaxDistance[i][a],MaxDistance[i][b]));
                a = SparseTable[i][a];
                b = SparseTable[i][b];
            }
        }

        min = Math.min(min,Math.min(MinDistance[0][a],MinDistance[0][b]));
        max = Math.max(max,Math.max(MaxDistance[0][a],MaxDistance[0][b]));
        return SparseTable[0][a];
    }

    static class Info{
        int end;
        int weight;

        public Info(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
