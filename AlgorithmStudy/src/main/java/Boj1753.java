import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1753 {
    static int V,E,START;
    static ArrayList<Info>[] AdjacentList;
    static int[] Dikjkstra;
    static PriorityQueue<Info> queue = new PriorityQueue<>();
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(br.readLine());

        AdjacentList = new ArrayList[V+1]; //인접리스트 생성 -> 특정 노드에서 인접한 노드들을 탐색할것이므로 인접리스트 이용
        Dikjkstra = new int[V+1]; //최단거리 배열 생성
        for(int i=1;i<V+1;i++){
            AdjacentList[i] = new ArrayList<>();
            Dikjkstra[i] = INF;
        }

        //START에서 각 정점으로 가는 최단거리 구하기 -> 다익스트라 알고리즘
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            AdjacentList[start].add(new Info(end,weight));
        }

        //Queue에 start 넣기 (init)
        Dikjkstra[START] = 0;
        queue.add(new Info(START,0));

        //queue에서 하나 뽑아오고, 현재 최단 거리랑 비교해서 작으면 수행, 아니면 continue
        while(!queue.isEmpty()){
            Info now = queue.poll();
            int middle = now.end; //중간점
            if(now.weight > Dikjkstra[now.end]){
                continue;
            }
            //middle이랑 연결된 애들 다 갱신확인 진행
            //1->middle->end 랑 1->end(Dikjstra[end])랑 비교, 새로운게 작다면 갱신
            //갱신되면 queue에 추가
            for(Info next:AdjacentList[now.end]){
                int nextEnd = next.end; //갱신할 끝점
                int initToMiddleToEnd = Dikjkstra[middle] + next.weight; //1->middle->end (새로운것)
                int initToEnd = Dikjkstra[nextEnd]; //1->end (기존것)

                if(initToMiddleToEnd < initToEnd){ //새로운게 더 작으면 갱신
                    Dikjkstra[nextEnd] = initToMiddleToEnd;
                    queue.add(new Info(nextEnd,Dikjkstra[nextEnd]));
                }
            }
        }

        //System.out.println(Arrays.toString(Dikjkstra));
        for(int i=1;i<V+1;i++){
            if(Dikjkstra[i] == INF){
                System.out.println("INF");
            }
            else{
                System.out.println(Dikjkstra[i]);
            }
        }
    }
}

class Info implements Comparable<Info>{
    int end;
    int weight;

    public Info(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Info o) {
        return Integer.compare(weight,o.weight);
    }
}