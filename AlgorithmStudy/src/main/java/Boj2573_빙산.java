import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2573_빙산 {
    static int N,M;
    static int[][] Map;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static int[][] melting;
    static boolean visited[][];

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map = new int[N+1][M+1];
        melting = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<M+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //단절점에서 자식 tree의 갯수를 찾았던 것처럼
        //모든 노드를 root로 dfs를 돌건데, visit하지 않은 애들만 돌면 됨
        //root를 시작으로 dfs를 들어간 횟수 -> 자식 트리의 갯수
        //갱신이 한 번에 같이 일어나야 하므로 melting배열을 만들어 주변에 0이 몇개있는지 보고
        //모든 노드를 ROOT로 돌았을 때 한 번에 갱신한다. -> visit 배열 초기화, melting 초기화

        //자식 트리의 갯수가 2개 이상이 될 때까지 위의 과정을 반복한다.
        //더이상 갈 노드가 없으면 0 출력

        int treeCount;
        int day = 0;
        while(true){
            //중단조건 -> 자식 트리의 갯수가 2개 이상인가?, 더이상 갈 노드가 없는가?
            treeCount=0;
            for(int i=1;i<N+1;i++){
                for(int j=1;j<M+1;j++){
                    if(Map[i][j]!=0 && !visited[i][j]){
                        //System.out.println("root - "+i+" "+j);
                        dfs(i,j);
                        treeCount++;
                    }
                }
            }
            //중단조건
            if(treeCount>=2){
                System.out.println(day);
                break;
            }
            else if(treeCount==0){
                System.out.println(0);
                break;
            }
            day++;

            //갱신 시작
            for(int i=1;i<N+1;i++){
                for(int j=1;j<M+1;j++){
                    Map[i][j]-=melting[i][j];
                    if(Map[i][j]<0){ //음수면 0으로
                        Map[i][j]=0;
                    }
                    visited[i][j] = false;
                    melting[i][j] = 0;
                }
            }
        }
    }

    static void dfs(int row, int col){
        //System.out.println(row+" "+col);
        //1. 체크인
        visited[row][col] = true;
        //2. 목적지인가?
        //3. 인접 정점 순회
        for(int k=0;k<4;k++){
            int nextRow = row + dRow[k];
            int nextCol = col + dCol[k];
            //4. 갈 수 있는가?
            if(0<nextRow && nextRow<=N && 0<nextCol && nextCol <=M){
                if(Map[nextRow][nextCol]==0){
                    melting[row][col]++;
                }
                else if(!visited[nextRow][nextCol]){
                    //5. 간다.
                    dfs(nextRow,nextCol);
                }
            }
        }
        //6. 체크아웃
    }
}
