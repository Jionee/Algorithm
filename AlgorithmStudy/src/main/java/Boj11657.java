import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11657 {
    static int N,M;
    static Bus[] bus;
    static long[] cost;
    static final int INF = Integer.MAX_VALUE; //무한대

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bus = new Bus[M];
        cost = new long[N+1];
        for(int n=1;n<N+1;n++){
            cost[n] = INF;
        }

        for(int m=0;m<M;m++) {
            st = new StringTokenizer(br.readLine());
            bus[m] = new Bus(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        //음의 사이클 -> -1출력
        //1번 도시에서 출발, 특정 도시로 가는 최소 시간 출력
        cost[1] = 0; //초기화
        for(int i=1;i<=N-1;i++){ //1~V-1번 돌기
            for(int m=0;m<M;m++){ //1~E까지 돌면서
                //선택되는 간선을 이용해서 최단거리 갱신하기
                Bus nowEdge = bus[m];
                //start, end 노드들이 존재하는데 그걸 갱신하자!
                // 1-> start 가 이전에 존재해야 하므로 그게 INF가 아닌 경우에 비교
                // 뭐로? min으로 -> min(1->start->end, 1->end) //새로운 경로, 기존 경로
                if(cost[nowEdge.start] != INF){
                    long initTostartToEnd = cost[nowEdge.start] + nowEdge.time; //1->start->end(새로운)
                    long initToEnd = cost[nowEdge.end]; //1->end(기존)
                    if(initTostartToEnd < initToEnd){ //새로운게 더 작으면
                        cost[nowEdge.end] = initTostartToEnd; //갱신
                    }
                }
            }
        }


        for(int m=0;m<M;m++){ //새롭게 갱신되는 점이 있다면, 음의 싸이클이 존재한다.
            Bus nowEdge = bus[m];
            if(cost[nowEdge.start] != INF){
                long initToStartToEnd = cost[nowEdge.start] + nowEdge.time;
                long initToEnd = cost[nowEdge.end];
                if(initToStartToEnd < initToEnd){ //갱신이 되면 negative cycle존재
                    System.out.println("-1");
                    return;
                }
            }
        }

        for(int n=2;n<N+1;n++){
            if(cost[n]==INF){
                System.out.println(-1);
            }
            else{
                System.out.println(cost[n]);
            }
        }
    }
}

class Bus{
    int start;
    int end;
    int time;

    public Bus(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}