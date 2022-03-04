import java.io.*;
import java.util.StringTokenizer;

public class Boj2468_안전영역{
    static int N;
    static int[][] Map;
    static StringTokenizer st;
    static int maxHeight;
    static int answer;
    static boolean[][] visited;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,1,-1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        Map = new int[N+1][N+1];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight,Map[i][j]);
            }
        }

        //모든 물의 높이에 대하여 단절점 찾기
        for(int h=0;h<=maxHeight;h++){
            visited = new boolean[N+1][N+1];
            int treeCount = 0;
            for(int i=1;i<N+1;i++){
                for(int j=1;j<N+1;j++){
                    if(!visited[i][j] && Map[i][j]-h > 0){
                        visited[i][j] = true;
                        dfs(i,j,h);
                        treeCount++;
                    }
                }
            }
            //System.out.println(treeCount+","+answer);
            answer = Math.max(answer,treeCount);
        }
        System.out.println(answer);
    }

    public static void dfs(int row,int col,int height){
        //목적지인가
        for(int i=0;i<4;i++){
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if(0<newRow && newRow<N+1 && 0<newCol && newCol<N+1){
                if(!visited[newRow][newCol] && Map[newRow][newCol] - height > 0){
                    visited[newRow][newCol] = true;
                    dfs(newRow,newCol,height);
                }
            }
        }
    }
}
