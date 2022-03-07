import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13460_구슬탈출2 {
    static StringTokenizer st;
    static int N,M;
    static char[][] Map;
    static Point start = new Point();
    static int goalRow,goalCol;
    static boolean[][][][] visit;
    static int[] dRow = {-1,1,0,0}; //상하좌우
    static int[] dCol = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new char[N+1][M+1];
        visit = new boolean[N+1][M+1][N+1][M+1];

        for(int i=1;i<N+1;i++){
            String str = br.readLine();
            for(int j=1;j<M+1;j++){
                Map[i][j] = str.charAt(j-1);
                if(Map[i][j] == 'R'){
                    start.redRow = i;
                    start.redCol = j;
                }
                else if(Map[i][j] == 'B'){
                    start.blueRow = i;
                    start.blueCol = j;
                }
                else if(Map[i][j] == 'O'){
                    goalRow = i;
                    goalCol = j;
                }
            }
        }

        //bfs 돌릴거임
        //빨간구슬만 탈출해야함 (둘 다 > -1, 빨간색만 > count출력, 파란색만 > -1, 둘다X > bfs계속)
        //조건
        //1) 동시에 같은 칸에 있을 수 없음
        //2) 벽체크, 장애물 이동 불가
        //3) 11번부터는 -1출력
        //4) 장애물을 만나기 전까지, 구멍을 만나기 전까지 특정 방향으로 계속해서 갈 수 있음

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visit[start.redRow][start.redCol][start.blueRow][start.blueCol] = true;
        while(!queue.isEmpty()){
            Point now = queue.poll();

            if(now.count>=11){
                System.out.println(-1);
                return;
            }

            //1.목적지인가? -> 빨간구슬만 통과
            boolean red = isSuccess(now.redRow,now.redCol);
            boolean blue = isSuccess(now.blueRow,now.blueCol);
            if((!red && blue) || (red && blue)){
                continue;
            }
            else if(red && !blue){
                System.out.println(now.count);
                return;
            }

            //2.인접정점탐색
            for(int i=0;i<4;i++){
                //dRow,dCol을 더하는 행위를 while,
                // 구멍만나면 그만 > 구멍이 걔의 좌표,
                // #장애물을 만나면 그만 > 그 직전이 걔의 좌표
                Po Red = newPosition(now.redRow,now.redCol,i);
                Po Blue = newPosition(now.blueRow,now.blueCol,i);
                int nRedRow = Red.row;
                int nRedCol = Red.col;
                int nBlueRow = Blue.row;
                int nBlueCol = Blue.col;

                if(!(0<nRedRow && nRedRow<=N && 0<nRedCol && nRedCol<=M && 0<nBlueRow && nBlueRow<=N && 0<nBlueCol && nBlueCol<=M)){
                    continue;
                }
                if(visit[nRedRow][nRedCol][nBlueRow][nBlueCol]==true){ //방문체크
                    continue;
                }
                if(nRedRow==nBlueRow && nRedCol==nBlueCol){ //두 구슬이 같은 곳에 있을 때
                    //이전 좌표랑 비교해서 좌표 재조정하기
                    if(nRedRow==goalRow && nRedCol==goalCol && nBlueRow==goalRow && nBlueCol==goalCol){ //둘 동시에 구멍에 빠지면 실패
                        continue;
                    }
                    if(i==0){ //상
                        if(now.redRow < now.blueRow){
                            nBlueRow = nRedRow + 1;
                        }
                        else{
                            nRedRow = nBlueRow + 1;
                        }
                    }
                    else if(i==1){ //하
                        if(now.redRow < now.blueRow){
                            nRedRow = nBlueRow - 1;
                        }
                        else{
                            nBlueRow = nRedRow - 1;
                        }
                    }
                    else if(i==2){ //좌
                        if(now.redCol < now.blueCol){
                            nBlueCol = nRedCol + 1;
                        }
                        else{
                            nRedCol = nBlueCol + 1;
                        }
                    }
                    else{ //우
                        if(now.redCol < now.blueCol){
                            nRedCol = nBlueCol - 1;
                        }
                        else{
                            nBlueCol = nRedCol - 1;
                        }
                    }
                }

                //간다.
                queue.add(new Point(nRedRow,nRedCol,nBlueRow,nBlueCol,now.count+1));
                visit[nRedRow][nRedCol][nBlueRow][nBlueCol] = true;
            }
        }
        System.out.println(-1);
    }
    static Po newPosition(int row,int col,int dir){
        while(true){
            int newRow = row + dRow[dir];
            int newCol = col + dCol[dir];

            if(isWall(newRow,newCol)){ //장애물이면
                return new Po(row,col);
            }
            else if(newRow==goalRow && newCol==goalCol){ //구멍이면
                return new Po(newRow,newCol);
            }
            row = newRow;
            col = newCol;
        }
    }

    static boolean isSuccess(int row,int col){
        if(row==goalRow && col==goalCol){
            return true;
        }
        return false;
    }

    static boolean isWall(int row,int col){
        if(0<row && row<=N && 0<col && col<=M && Map[row][col]=='#'){
            return true;
        }
        return false;
    }

    static class Po{
        int row;
        int col;

        public Po(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Point{
        int redRow;
        int redCol;
        int blueRow;
        int blueCol;
        int count;

        public Point() {
        }

        public Point(int redRow, int redCol, int blueRow, int blueCol, int count) {
            this.redRow = redRow;
            this.redCol = redCol;
            this.blueRow = blueRow;
            this.blueCol = blueCol;
            this.count = count;
        }
    }
}
