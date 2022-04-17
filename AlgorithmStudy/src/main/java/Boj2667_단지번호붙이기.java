import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2667_단지번호붙이기 {
    static StringTokenizer st;
    static int N;
    static int[][] Map;
    static ArrayList<Point> Arr = new ArrayList<>();
    static int answer = 0;
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static int count;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Map = new int[N+1][N+1];

        for(int i=1;i<N+1;i++){
            String str = br.readLine();
            for(int j=1;j<N+1;j++){
                Map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j-1)));
                if(Map[i][j] == 1){
                    Arr.add(new Point(i,j));
                }
            }
        }

        for(Point now:Arr){
            if(Map[now.row][now.col]==1){ //이동가능한 집이면
                count = 1;
                answer++;
                Map[now.row][now.col] = 3;
                dfs(now.row,now.col);
                queue.add(count);
            }
        }
        System.out.println(answer);
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

    public static void dfs(int row, int col){
        for(int i=0;i<4;i++){
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if(0<newRow && newRow<=N && 0<newCol && newCol<=N){
                if(Map[newRow][newCol]==1){ //이동가능한 집이면
                    Map[newRow][newCol] = 3; //방문처리
                    count++;
                    dfs(newRow,newCol);
                }
            }
        }
    }

    public static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
