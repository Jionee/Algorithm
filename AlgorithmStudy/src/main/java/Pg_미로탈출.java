import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Pg_미로탈출 {

    public static void main(String[] args) throws Exception{
        //input
        int n=3;
        int start=1;
        int end=3;
        int[][] roads={{1,2,2},{3,2,3}};
        int[] traps={2};

        int answer = Solution.solution(n,start,end,roads,traps);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static ArrayList<Node>[] lList,rList;
        static int N,End;
        static int answer = Integer.MAX_VALUE;
        static HashMap<Integer,Integer> TrapHashMap = new HashMap<>();
        static int[][] dist;
        static int INF = Integer.MAX_VALUE;

        public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
            N = n;
            End = end;

            lList = new ArrayList[n+1];
            rList = new ArrayList[n+1];
            for(int i=1;i<n+1;i++){
                lList[i] = new ArrayList<>();
                rList[i] = new ArrayList<>();
            }

            for(int i=0;i<roads.length;i++){
                int P = roads[i][0];
                int Q = roads[i][1];
                int S = roads[i][2];
                lList[P].add(new Node(Q,S,0)); //P->Q, S만큼 가중치
                rList[Q].add(new Node(P,S,0));
            }
            for(int i=0;i<traps.length;i++){
                int trap = traps[i];
                TrapHashMap.put(trap,1<<(i+1));
            }

            dist = new int[n+1][1<<TrapHashMap.size()+1]; //node이면서, trap이 어디가 켜져있는지 봅시다
            for(int i=1;i<n+1;i++){
                Arrays.fill(dist[i],INF);
            }
            dijkstra(start);

            for(int val:dist[End]){
                answer = Math.min(answer,val);
            }

            return answer;
        }

        static void dijkstra(int start){
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.add(new Node(start,0,0));
            dist[start][0] = 0;

            while(!queue.isEmpty()){
                Node now = queue.poll();
                int middle = now.end;
                int w = now.weight;
                int mapStatus = now.status;

                if(middle==End){ //도착지에 도착
                    return;
                }

                boolean currTrapped = false;
                if(TrapHashMap.containsKey(middle)){ //지금 밟은게 trap인지 확인
                    //전체적인 상황이랑 & 지금 밟고 있는게 trap인지 -> 지금 밟고 있는게 trap이 켜져있음
                    if(((mapStatus) & TrapHashMap.get(middle)) != 0){
                        currTrapped = true;
                    }
                }

                //정방향 -> currTrapped ^ nextTrapped == false
                //lList 돌면서 정방향인 애들 큐에 넣기
                boolean nextTrapped = false;
                boolean canForward = false;
                for(Node next: lList[middle]){
                    int end = next.end;
                    nextTrapped = false; //초기화
                    int newMapStatus = mapStatus;
                    if(TrapHashMap.containsKey(end)){ //다음꺼가 trap인지 확인
                        //전체적인 상황이랑 & 다음이 trap인지 -> 다음 trap이 켜져있음
                        if(((mapStatus) & TrapHashMap.get(end)) != 0){
                            nextTrapped = true;

                        }
                        newMapStatus ^= TrapHashMap.get(end); //다음이 trap이면 밟은 상태로 업데이트
                    }
                    canForward = currTrapped ^ nextTrapped;
                    if(!canForward){
                        //다익스트라 진행
                        int startToMiddleToEnd = w + next.weight;
                        int startToEnd = dist[end][mapStatus];
                        if(startToMiddleToEnd<startToEnd){
                            dist[end][mapStatus] = startToMiddleToEnd;
                            queue.add(new Node(end,dist[end][mapStatus],newMapStatus)); //원래는 dist[새로운거] 가 들어가지만 여기서는 새로운 노드 + 아직 다음꺼가 안 밟힌 상태를 전달해줘야해서 이렇게 전달
                        }
                    }
                }

                //역방향
                //rList 돌면서 역방향인 애들 큐에 넣기
                nextTrapped = false;
                canForward = false;
                for(Node next: rList[middle]){
                    int end = next.end;
                    nextTrapped = false; //초기화
                    int newMapStatus = mapStatus;
                    if(TrapHashMap.containsKey(end)){ //다음꺼가 trap인지 확인
                        //전체적인 상황이랑 & 다음이 trap인지 -> 다음 trap이 켜져있음
                        if(((mapStatus) & TrapHashMap.get(end)) != 0){
                            nextTrapped = true;
                        }
                        newMapStatus ^= TrapHashMap.get(end); //다음이 trap이면 밟은 상태로 업데이트
                    }
                    canForward = currTrapped ^ nextTrapped;

                    if(canForward){
                        //다익스트라 진행
//                        int startToMiddleToEnd = dist[middle][mapStatus] + next.weight;
//                        int startToEnd = dist[end][mapStatus];
//                        if(startToMiddleToEnd<startToEnd){
//                            dist[end][newMapStatus] = startToMiddleToEnd;
//                            queue.add(new Node(end,dist[end][newMapStatus],newMapStatus));
//                        }
                        if(dist[end][mapStatus] > w + next.weight) {
                            dist[end][mapStatus] = w + next.weight;
                            queue.add(new Node(end, dist[end][mapStatus], newMapStatus));
                        }
                    }
                }
            }
        }


       static class Node implements Comparable<Node>{
            int end;
            int weight;
            int status;

           public Node(int end, int weight, int status) {
               this.end = end;
               this.weight = weight;
               this.status = status;
           }

           @Override
           public String toString() {
               return "Node{" +
                       "end=" + end +
                       ", weight=" + weight +
                       ", status=" + status +
                       '}';
           }

           @Override
           public int compareTo(Node o){
               return Integer.compare(weight,o.weight);
           }
       }

    }
}
