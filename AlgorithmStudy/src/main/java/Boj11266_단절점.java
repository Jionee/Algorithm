import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj11266_단절점 {
    static int V,E;
    static ArrayList<Integer>[] adjacentList; //한 노드에서 인접한 노드들의 정보를 가져와야 하므로 -> 인접리스트 사용
    static boolean[] isCutVertex; //각 노드별 단절점 여부 기록
    static int[] searchOrder; //각 노드별 order 저장 배열
    static int Order;
    static int Answer;
    static StringBuffer AnswerList;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjacentList = new ArrayList[V+1];
        for(int i=1;i<V+1;i++){
            adjacentList[i] = new ArrayList<>();
        }
        isCutVertex = new boolean[V+1];
        searchOrder = new int[V+1];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjacentList[start].add(end);
            adjacentList[end].add(start); //양방향
        }
        //연결그래프가 아닐 수도 있음
        //DFS를 수행하면서 구하기
        //시작정점의 경우 -> 자식 트리 수 >= 2 -> 단절점
        //시작정점이 아닌 경우 -> low >= order -> 단절점

        Order = 0;
        for(int i=1;i<V+1;i++){
            if(searchOrder[i] == 0){ //방문하지 않았을 경우 시작노드로 해서 dfs돌리기
                dfs(i,true);
            }
        }

        //출력
        Answer = 0;
        AnswerList = new StringBuffer();
        for(int i=1;i<V+1;i++){
            if(isCutVertex[i]){
                Answer++;
                AnswerList.append(i+" ");
            }
        }
        bw.write(Answer +"\n");
        if(Answer > 0){
            bw.write(AnswerList + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int dfs(int now, boolean isRoot){
        //1. 체크인 -> visit[i] = true
        //2. 목적지인가?
        //3. 가지치기 -> adjacentList[i] 에 존재하는 곳 다 돌기
        //4. 갈수있는가? -> 방문배열 확인(false면 감), low -> 건들여봤으면 그것 order 중에 min값 리턴
        //5. 간다 -> order[new] 갱신
        //6. 체크아웃 -> visit[i] = false

        //1. 체크인 -> searchOrder[i] 갱신
        Order++;
        searchOrder[now] = Order;

        //low : 지금 정점 이후에 도달할 수 있는 모든 정점들의 탐색순서 중 가장 작은 값
        int rtn = Order; //low 초기화
        int child = 0; //root일 경우 자식 트리의 수

        //2. 목적지인가? (생략)
        //3. 가지치기 -> adjacentList[i] 에 존재하는 곳 다 돌기
        for(int next: adjacentList[now]){
            if(searchOrder[next]==0){ //방문한적 없으면
                child++;
                int low = dfs(next,false);

                if(isRoot == false && low >= searchOrder[now]){
                    isCutVertex[now] = true;
                }
                rtn = Math.min(rtn,low);
            }
            else{ //자식정점을 방문한적 있으면
                rtn = Math.min(rtn,searchOrder[next]);
            }
        }

        if(isRoot == true && child >=2){
            isCutVertex[now] = true;
        }
        return rtn;
    }
}
