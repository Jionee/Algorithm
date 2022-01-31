import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11404 {
    static int N,M;
    static int[][] adjacentMatrix; //인접행렬
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjacentMatrix = new int [N+1][N+1];

        //행렬 구성하기
        for(int m=0;m<M;m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) ;
            int end = Integer.parseInt(st.nextToken()) ;
            int weight = Integer.parseInt(st.nextToken()) ;
            //더 좋은 값을 가져야 함
            if(adjacentMatrix[start][end] == 0 || adjacentMatrix[start][end] > weight){
                adjacentMatrix[start][end] = weight;
            }
        }

        //플로이드-워셜 알고리즘
        // 경유하는 모든 선들 보면서 최소 값 찾기
        for(int middle=1;middle<N+1;middle++){ //경유지 선택
            for(int start=1;start<N+1;start++){
                for(int end=1;end<N+1;end++){ //쌍 만들기
                    //init(j)->middle(i)->end(k)  init(j)->end(k)비교, 앞에거가 더 크다면 갱신
                    if(start!=end && adjacentMatrix[start][middle] != 0 && adjacentMatrix[middle][end]!=0){
                        int initToMiddleToEnd = adjacentMatrix[start][middle] + adjacentMatrix[middle][end];
                        int initToEnd = adjacentMatrix[start][end];
                        if(adjacentMatrix[start][end] == 0 || initToMiddleToEnd < initToEnd){
                            adjacentMatrix[start][end] = initToMiddleToEnd;
                        }
                    }
                }
            }
        }

        //출력
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                System.out.print(adjacentMatrix[i][j] +" ");
            }
            System.out.println();
        }
    }
}
