import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1854 {
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
        for(int i=1;i<N+1;i++){
            Map[i] = new ArrayList<>();
            Distance[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i=0;i<M;i++){


            //Map[a].add(new info(b,c));
        }
    }
}

class info{
    int end;
    int weight;

    public info(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}