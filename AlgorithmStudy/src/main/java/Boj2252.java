import java.io.*;
import java.util.*;

public class Boj2252 {
    static int N,M;
    static ArrayList<Integer>[] student; //인접한 점을 많이 찾는 행위(부속간선) -> 인접리스트가 이득
    static int[] link; //각 정점의 진입차수를 저장한 배열

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        student = new ArrayList[N+1]; //각 학생에 연결된 엣지 링크드리스트 형태로 구현
        for(int i=1;i<N+1;i++){
            student[i] = new ArrayList<>();
        }
        link = new int[N+1]; //각 학생에 몇 개의 in-degree가 존재하는가

        for(int m=0; m < M ;m++){
            st = new StringTokenizer(br.readLine()); //A -> B
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            student[a].add(b); //A->B
            link[b] += 1;
        }

        Queue<Integer> queue = new LinkedList();
        for(int i=1;i<N+1;i++){
            if(link[i]==0){
                queue.add(i);
            }
         } //link가 0인 스타트 넣기

        while(!queue.isEmpty()){
            int target = queue.poll();
            System.out.print(target + " ");
            for(int s :student[target]){
                link[s]--;
                if(link[s] == 0){
                    queue.add(s);
                }
            }
        }
    }
}