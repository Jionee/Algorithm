import java.io.*;
import java.util.*;

public class Boj1516_게임개발 {
    static int N;
    static int[] indegree;
    static int[] time;
    static int[] prevTime; //각 노드마다 최소 건물 짓는 시간 갱신
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        time = new int[N+1];
        prevTime = new int[N+1];

        graph = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            graph[i] = new ArrayList<>();
        }

        for(int n=1;n<N+1;n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[n] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int input = Integer.parseInt(st.nextToken());
                if(input == -1){
                    break;
                }
                indegree[n] += 1; //들어오는 곳에 indegree 증가
                graph[input].add(n); //input->n 연결
            }
        }

        //indegree가 0인 것 부터 bfs 수행 -> 순서가 나옴
        //특정 노드 이전 단계까지 최소 값을 알아야 하기 때문에 이를 저장하는 배열을 만든다. cost[i] = max(cost[i],newCost)
        //cost[i-1] +  time[i]

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<N+1;i++){
            if(indegree[i]==0){
                queue.add(i);
                prevTime[i] = time[i];
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next:graph[now]){
                indegree[next]-=1;
                prevTime[next] = Math.max(prevTime[next],prevTime[now]+time[next]); //중간에 지나오는게 있을 수 있으므로 MAX를 사용해야 함
                if(indegree[next]==0){
                    queue.add(next);
                }
            }
        }

        for(int i=1;i<N+1;i++){
            bw.write(prevTime[i]+"\n");
        }

        bw.flush();
        bw.close();
    }
}

