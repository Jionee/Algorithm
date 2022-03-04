import java.io.*;
import java.util.StringTokenizer;

public class Boj1405_미친로봇 {
    static int N;
    static double[] arr; //동,서,남,북
    static int[] dRow = {0,0,1,-1};
    static int[] dCol = {1,-1,0,0};
    static boolean[][] visited;
    static double answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new double[4];
        visited = new boolean[N*2+1][N*2+1];

        for(int i=0;i<4;i++){
            arr[i] = Double.parseDouble(st.nextToken()) *0.01;
        }

        //방문했던 곳을 다시 visit 하지 않을 확률 [N][N]을 시작점으로 하자구
        //N번, 4방을 다 가봄, 끝까지 갔을 때 visit안겹치면 OK
        //dfs로 끝까지 빠이야 해야함
        visited[N][N] = true;
        dfs(N,N,0,1.0);
        System.out.println(answer);
    }

    public static void dfs(int row,int col,int count,double calc){
        //System.out.println(row+","+col+" -> "+calc);

        //2. 목적지인가? -> count >= N -> 확률 계산 + answer
        if(count>=N){
            answer += calc;
            //System.out.println("answer! CALC-> "+calc + "answer : "+answer);
            return;
        }
        //3. 인접정점 탐색 -> 동서남북
        for(int i=0;i<4;i++){
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            //4. 갈 수 있는가? -> visited = false인가, 벽 체크
            if(0<=newRow && newRow<=N*2 && 0<=newCol && newCol<=N*2){
                if(!visited[newRow][newCol] && arr[i]!=0){
                    //1. 체크인
                    //5. 간다 -> dfs(newR,newC,count);
                    visited[newRow][newCol] = true;
                    dfs(newRow,newCol,count+1,calc*arr[i]);
                    //6. 체크아웃 -> visited,count 제대로
                    visited[newRow][newCol] = false;
                }
            }
        }
    }
}
