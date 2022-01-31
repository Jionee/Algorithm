import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj3830_교수님은기다리지않는다 {
    static long[] dist;
    static int[] parent;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) { //종료
                break;
            }
            else{
                //Union-Find 사용
                //Union -> 다른 그룹일 경우 관계를 추가하고, dist를 갱신한다.
                //Find -> Parent를 찾는다. 찾는 과정에서 dist를 갱신하는 과정을 수행한다.

                //초기화
                parent = new int[N+1];
                dist = new long[N+1];
                for(int i=1;i<N+1;i++){
                    parent[i] = i; //self-loop 설정
                }
                for(int i=0;i<M;i++){
                    st = new StringTokenizer(br.readLine());
                    String qu = st.nextToken();
                    if(qu.equals("!")){ //a<b w만큼
                        int a = Integer.parseInt(st.nextToken());
                        int b = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());
                        //새로운 관계를 추가한다.
                        union(a,b,w);
                    }
                    else if(qu.equals("?")){//b가 a보다 얼마나 무거운지 출력
                        int a = Integer.parseInt(st.nextToken());
                        int b = Integer.parseInt(st.nextToken());
                        int parentA = find(a);
                        int parentB = find(b);
                        if(parentA!=parentB){
                            System.out.println("UNKNOWN");
                        }
                        else{
                            System.out.println(dist[b] - dist[a]);
                        }
                    }
                }
            }
        }
    }
    static void union(int a, int b, int w){
        int parentA = find(a); //dist[a] 갱신
        int parentB = find(b); //dist[b] 갱신
        if(parentA==parentB) //이미 같은 그룹에 속하면 아무것도 하지 않음
            return;
        dist[parentB] = dist[a] - dist[b] + w;
        parent[parentB] = parentA; //parentB를 parentA에 연결
    }

    //find연산을 하며 부모를 찾고, + 해당 노드의 root(부모)까지의 dist도 함께 갱신한다.
    static int find(int a){
        if (parent[a] == a) { //self-loop
            return a;
        }
        //아니면 부모 찾기
        int parentId = find(parent[a]);
        //dist 갱신
        //a->기존a루트 + 기존a루트->갱신된루트
        //재귀적으로 루트부모갱신이 다 이루어진 후에 더해야 하기때문에 재귀호출을 끝내고 부른다.
        dist[a] = dist[a] + dist[parent[a]];
        return parent[a] = parentId;
    }
}
