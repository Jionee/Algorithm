import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//22:34~00:12
public class Boj14502_연구소 {
    static StringTokenizer st;
    static int N,M;
    static int[][] Map;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static int answer = 0;
    static ArrayList<Point> Virus = new ArrayList<>();
    static boolean[][] visit;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new int[N+1+1][M+1+1];
        visit = new boolean[N+1+1][M+1+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<M+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
                if(Map[i][j] == 2){
                    Virus.add(new Point(i,j));
                }
            }
        }

        //0:빈칸 1:벽 2:바이러스
        //1벽 3개를 세워서 만들 수 있는 가장 큰 안전영역 넓이
        //빈칸에 벽을 세울 수 있는데 이걸 다 해봐야 한다. DFS
        //벽을 세워보고 -> 바이러스 다 퍼트려서 0 개수 세기 -> 반복
        dfs(0);
        System.out.println(answer);
    }
    public static void dfs(int num){
        //0. 체크인
        //1. 목적지인가? -> num >=3이상인가 -> 벽 다 세웠다는 거니까 -> 바이러스 고고
        if(num>=3){
            bfs();
            return;
        }
        //2. 인접정점탐색 -> for for
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                //3. 갈 수 있는가? -> 0인가(빈칸인가)
                if(Map[i][j]==0){
                    //4. 간다 -> dfs(num+1)
                    //5. 체크아웃
                    Map[i][j] = 1; //벽으로 변경
                    dfs(num+1);
                    Map[i][j] = 0; //원상복구
                }
            }
        }
    }

    public static void bfs(){
        int[][] Copy = new int[N+1+1][M+1+1];
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                Copy[i][j] = Map[i][j];
            }
        }
        //printMap(Copy);
        //바이러스 퍼뜨리기
        Queue<Point> queue = new LinkedList<>();
        queue.addAll(Virus);

        while(!queue.isEmpty()){
            Point now = queue.poll();

            for(int i=0;i<4;i++){
                int nRow = now.row + dRow[i];
                int nCol = now.col + dCol[i];
                if(0<nRow && nRow<=N && 0<nCol && nCol<=M){
                    if(Copy[nRow][nCol]==0){
                        Copy[nRow][nCol]=2;
                        queue.add(new Point(nRow,nCol));
                    }
                }
            }
        }
        //printMap(Copy);
        int count=0;
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                if(Copy[i][j]==0){
                    count++;
                }
            }
        }
        //System.out.println("COUNT:"+count);
        answer = Math.max(answer,count);
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static void printMap(int[][] m){
        System.out.println("=========");
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
}
