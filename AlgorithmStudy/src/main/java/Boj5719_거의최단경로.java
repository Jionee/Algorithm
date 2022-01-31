import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj5719_거의최단경로 {
    static int N,M;
    static StringTokenizer st;
    static ArrayList<Info>[] AdjacentList; //인접리스트
    static int[] Dikjkstra; //각 노드별 최단경로 저장
    static PriorityQueue<Info> queue = new PriorityQueue<>(); //다익스트라 수행
    static ArrayList<Integer>[] Tracking; //직전 정점들을 저장
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N==0 && M==0){
                break;
            }
            AdjacentList = new ArrayList[N];
            Tracking = new ArrayList[N]; //최초 다익스트라 후 두번째에서는 사용하지 않으므로 1회만 초기화해주면 됨
            for(int i=0;i<N;i++){
                AdjacentList[i] = new ArrayList<>();
                Tracking[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); //시작점
            int D = Integer.parseInt(st.nextToken()); //도착점

            //인접리스트 구성
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                AdjacentList[a].add(new Info(b,c,false));
            }

            //S -> D로 가는 거의 최단 경로 구하고, 출력하기
            //1. 다익스트라 1회 수행,
                //큐에 넣으면서 다익스트라 수행
                //갱신이 일어날 때는 Tracking[next] = now로 기록해준다. (최단경로로 오기 위해 직전에 온 노드 저장하기)
            //2. 그 후 Tracking[D]부터 재귀적으로 호출하여

            //1. 첫번째 다익스트라
            findShortestPath(S);
            if(Dikjkstra[D] == INF){
                System.out.println("-1");
                continue;
            }
            //2.Tracking 되돌아가면서 flag처리하기
            flagUsedEdge(D,S);
            //3. 2번째 다익스트라 수행
            findShortestPath(S);

            //출력
            if(Dikjkstra[D] == INF){
                System.out.println("-1");
            }
            else{
                System.out.println(Dikjkstra[D]);
            }

        }
    }

    //다익스트라
    private static void findShortestPath(int s) {
        //초기화
        Dikjkstra = new int[N]; //다익스트라를 사용할 때마다 초기화해줘야 함.
        Arrays.fill(Dikjkstra,INF);
        Dikjkstra[s] = 0;
        queue.add(new Info(s,0,false));

        while(!queue.isEmpty()){
            Info now = queue.poll();
            int middle = now.end;
            if(now.weight > Dikjkstra[middle]){
                continue;
            }

            for(Info next:AdjacentList[middle]){
                if(next.flag == true){ //최단거리로 썼으면 없는걸로 간주, 안씀
                    continue;
                }
                int nextEnd = next.end;
                int startToMiddleToEnd = Dikjkstra[middle] + next.weight;
                int startToEnd = Dikjkstra[nextEnd];
                if(startToMiddleToEnd < startToEnd){ //갱신
                    Dikjkstra[nextEnd] = startToMiddleToEnd;
                    queue.add(new Info(nextEnd,Dikjkstra[nextEnd],false));
                    Tracking[nextEnd].clear(); //갱신이 되므로 그 전에 가지고 있던 (더이상 최단이 아닌) 직전 점은 삭제하기
                    Tracking[nextEnd].add(middle); //그 직전 점 저장
                }
                //최단경로가 여러개일수있으므로 같은 경우 고려하기
                if(startToMiddleToEnd == startToEnd){
                    Tracking[nextEnd].add(middle);
                }
            }
        }
    }

    private static void flagUsedEdge(int now,int end) {
        if(now==end){
            return;
        }
        for(int next: Tracking[now]){ //직전에 마킹된 애들 flag처리하기
            //next -> now 를 flag 하면 됨
            for(Info info:AdjacentList[next]){
                if(info.end == now && info.flag == false){
                    info.flag = true;
                    flagUsedEdge(next,end); //재귀호출
                }
            }
        }
    }

    static class Info implements Comparable<Info>{
        int end;
        int weight;
        boolean flag; //이 간선이 사용되었는지 flag

        public Info(int end, int weight,boolean flag) {
            this.end = end;
            this.weight = weight;
            this.flag = flag;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(weight, o.weight);
        }
    }
}

