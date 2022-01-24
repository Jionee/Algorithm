import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1717 {
    static int N,M;
    static int[] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1];
        init();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(type == 0){ //union
                union(a,b);
            }
            else{ //find
                System.out.println(find(a,b));
            }
        }
    }
    //2번째ㅐ미니멈 이런 수준은 안나옴, dp는 버려도 됨
    public static void init(){
        for(int i=0;i<N+1;i++){
            graph[i] = i;
        }
    }
    public static int findParent(int a){ //a의 parent를 찾기
        if(graph[a] == a){
            return a;
        }
        return graph[a] = findParent(graph[a]);
    }

    public static String find(int a,int b){ //a와 b가 같은 집합에 포함되어 있는가
        if(findParent(a) == findParent(b)){
            return "YES";
        }
        return "NO";
    }

    public static void union(int a,int b){ //a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다.
        int parentA = findParent(a);
        int parentB = findParent(b);
        graph[parentA] = parentB; //연결
    }
}
