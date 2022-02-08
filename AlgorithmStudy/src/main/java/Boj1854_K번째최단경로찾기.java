import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1854_K번째최단경로찾기 {
    //distance 배열에 우선순위 큐 넣기
    static int N,M,K;
    static ArrayList<info>[] Map;
    static PriorityQueue<Integer>[] Distance;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //노드개수
        M = Integer.parseInt(st.nextToken()); //간선개수
        K = Integer.parseInt(st.nextToken()); //k번째 최단경로

        Map = new ArrayList[N+1];
        Distance = new PriorityQueue[N+1]; //각 노드마다 K개까지 원소를 가지고 있는다.
        for(int i=1;i<N+1;i++){
            Map[i] = new ArrayList<>();
            Distance[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Map[a].add(new info(b,c));
        }

        //K번째 최단경로를 구해야한다.
        //기본 다익스트라는 i에서 k로 갈 때 최소를 갱신해가면서 구하는건데, 음 몇번째 최단경로는 어케 구하지?
        //다익스트라 수행 시 특정 노드까지 도달하는 모든 간선(경로)를 다 지나면서 최단 경로를 구한다.
        //따라서 K개까지만 허용하는 최대힙을 이용하여 새로 갱신된 거리가 맨 위꺼보다 작으면 하나 뽑고 넣는다.
        //다익스트라 수행
        int root = 1;
        PriorityQueue<info> queue = new PriorityQueue<>();
        Distance[root].add(0);
        queue.add(new info(root,0));

        while(!queue.isEmpty()){
            info now = queue.poll();
            int middle = now.end;
            int rootToMiddle = now.weight;
            if(rootToMiddle > Distance[middle].peek()){ //root->middle  root->@#@#@#->middle의 최댓값
                continue;
            }
            for(info next:Map[middle]){ //root->middle->end   root->end
                int end = next.end;
                int middleToEnd = next.weight;
                if(Distance[end].size() < K){
                    Distance[end].add(rootToMiddle + middleToEnd); //root->middle->end 새 경로 탐색 추가
                    queue.add(new info(end,rootToMiddle + middleToEnd));
                }
                else{
                    int rootToMiddleToEnd = rootToMiddle + middleToEnd;
                    if(rootToMiddleToEnd < Distance[end].peek()) { //기존에 존재하는 것보다 작으면 넣기
                        Distance[end].poll();
                        Distance[end].add(rootToMiddleToEnd);
                        queue.add(new info(end,rootToMiddleToEnd));
                    }
                }
            }
        }

        for(int i=1;i<N+1;i++){
            if(Distance[i].size() == K){
                bw.write(Distance[i].peek() +"\n");
            }
            else{
                bw.write("-1" +"\n");
            }
        }

        bw.flush();
        bw.close();

    }
    static class info implements Comparable<info>{
        int end;
        int weight;

        public info(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(info o) {
            return Integer.compare(weight,o.weight);
        }
    }
}

