import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1922 {
    static int N,M;
    static int[] graph; //union-find
    static Edge edge[];
    static int cost;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edge = new Edge[M];
        graph = new int[N+1];
        for(int i=1;i<N+1;i++){
            graph[i] = i;
        }

        for(int m=0;m<M;m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edge[m] = new Edge(a,b,c);
        }

        //최소 신장 트리 사용
        //1. cost로 오름차순 정렬한다.
        //2. 노드들을 순서대로 돌아가며 cycle이 생기지 않도록 그래프를 구성한다.
            //2-1. find를 통해 부모를 찾음, 부모 비교하여 같은 그룹에 있는지 확인
            // 같은 그룹 -> 넘어감
            // 다른 그룹 -> union
        //3. 총 N-1개의 간선을 연결하면 종료한다.

        Arrays.sort(edge);
        //System.out.println(Arrays.toString(edge));
        int num = 0;
        for(Edge e :edge){
            int parentA = findParent(e.start);
            int parentB = findParent(e.end);
            if(parentA != parentB){ //Cycle 체크
                cost += e.cost;
                union(e.start,e.end); //그룹지어준다.
                num++;
            }
            if(num>=N-1){ //N-1만큼 돌면 종료
                break;
            }
        }
        System.out.println(cost);
    }
    public static int findParent(int a){
        if(graph[a] == a){
            return a;
        }
        else{
            return graph[a] = findParent(graph[a]);
        }
    }

    public static void union(int a,int b){ //하나의 루트노드를 다른 하나의 자식노드로 넣어 두 트리를 합친다.
        int parentA = findParent(a); //그룹A
        int parentB = findParent(b); //그룹B
        graph[parentA] = parentB; //같은 그룹!
    }
}

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost,o.cost);
    }
}
