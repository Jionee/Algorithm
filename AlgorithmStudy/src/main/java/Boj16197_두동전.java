import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16197_두동전 {
    static StringTokenizer st;
    static int N,M;
    static char[][] Map; //0:빈칸, 1:동전, -1:벽
    static int[] dRow = {-1,1,0,0}; //상하좌우
    static int[] dCol = {0,0,-1,1};
    static Point start;
    static boolean[][][][] visit;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new char[N+1+1][M+1+1];
        visit = new boolean[N+1+1][M+1+1][N+1+1][M+1+1];

        for(int i=1;i<N+1;i++){
            String str = br.readLine();
            for(int j=1;j<M+1;j++){
                Map[i][j] = str.charAt(j-1);
                if(Map[i][j] == 'o'){ //처음 동전 위치 기록
                    if(start==null){
                        start = new Point(i,j);
                    }
                    else{
                        start.row2 = i;
                        start.col2 = j;
                    }
                }
            }
        }

        //상하좌우(4가지 경우의수)
        //두 동전 중 하나만 떨어뜨렸을 때 버튼의 최소 횟수 (최소 횟수면 bfs고고) //bfs는 너비우선탐색이기 때문에 visit을 다시 되돌려놓을 필요가 없다.
        //두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 하면 -1 출력
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visit[start.row1][start.col1][start.row2][start.col2] = true;

        while(!queue.isEmpty()){
            Point now = queue.poll();

            //1.목적지인가? -> 버튼 10이상(count>=10), 하나만 떨어짐(함수로빼서 체크,둘 중 하나만 true면 끝)
            if(now.count>=10){
                System.out.println(-1);
                return;
            }

            //2.인접정점탐색 -> 4방 탐색
            for(int i=0;i<4;i++){
                //3.갈수있는가? -> 벽이면 이동x, 나머지는 다 이동(위에서 걸러줄거임)
                int newRow1 = now.row1 + dRow[i];
                int newCol1 = now.col1 + dCol[i];
                int newRow2 = now.row2 + dRow[i];
                int newCol2 = now.col2 + dCol[i];

                if(isWall(newRow1,newCol1)){ //벽이면 이동안함(그대로)
                    newRow1 = now.row1;
                    newCol1 = now.col1;
                }
                if(isWall(newRow2,newCol2)){
                    newRow2 = now.row2;
                    newCol2 = now.col2;
                }

                boolean end1 = isEnd(newRow1,newCol1); //떨어졌는지 - (1)(2)(3)
                boolean end2 = isEnd(newRow2,newCol2);
                if(end1 ^ end2){ //(1)하나만 떨어지는 경우
                    System.out.println(now.count+1);
                    return;
                }
                else if(end1 && end2){ //(2)두개 다 떨어지는 경우
                    continue;
                }

                //(3)두개 다 안떨어지는 경우
                if(!(0<newRow1 && newRow1<=N && 0<newCol1 && newCol1<=M && 0<newRow2 && newRow2<=N && 0<newCol2 && newCol2<=M)){ //벽
                    continue;
                }
                if(visit[newRow1][newCol1][newRow2][newCol2]){ //방문체크
                    continue;
                }

                //4.간다
                visit[newRow1][newCol1][newRow2][newCol2] = true; //0. 체크인
                queue.add(new Point(newRow1,newCol1,newRow2,newCol2,now.count+1));
            }
        }
        //못떨어뜨리면 -1
        System.out.println(-1);
    }

    public static boolean isWall(int nx, int ny) {
        if(nx > 0 && ny > 0 && nx <= N && ny <= M && Map[nx][ny] == '#') { //벽
            return true;
        }
        return false;
    }

    static boolean isEnd(int row,int col){
        if(0<row && row<=N && 0<col && col<=M){
            return false;
        }
        return true; //게임끝
    }

    static class Point{
        int row1;
        int col1;
        int row2;
        int col2;
        int count;

        public Point(int row1, int col1) {
            this.row1 = row1;
            this.col1 = col1;
        }

        public Point(int row1, int col1, int row2, int col2, int count) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.count = count;
        }
    }
}
