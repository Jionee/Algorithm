import java.io.*;
import java.util.StringTokenizer;

public class Boj14503_로봇청소기 {
    static int N,M;
    static Point start;
    static int[][] Map;
    static int[] dRow = {-1,0,1,0}; //북동남서
    static int[] dCol = {0,1,0,-1};
    static int clean, block;

    //왼쪽으로 돌았을 때 {}

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new int[N+1][M+1];

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken())+1,Integer.parseInt(st.nextToken())+1,Integer.parseInt(st.nextToken()));

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<M+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
                if(Map[i][j]==1){
                    block++;
                }
            }
        }

        //start에서 시작
        //bfs로 할거야 DFS로 할거야?  -> dfs로 하자(계속 타고 가는 거기때문)
        //왼쪽방향을 보고, 거기로 회전하는 것만 있음(0상->3좌, 1우->0상, 2하->1우, 3좌->2하)
        //한 점에서 4방향 청소가 완료됐는지 확인하는 로직 필요
        //청소했으면 clean++, Map에 -1기록하기
        dfs(start);
        System.out.println(clean);
    }
    public static void dfs(Point now){
//        System.out.println(now);
//        for (int[] ints : Map) {
//            for (int anInt : ints) {
//                System.out.print(anInt+" ");
//            }
//            System.out.println();
//        }
        //1. 체크인 -> 현재 위치 청소(이미 청소가 안되어있을 경우)
        if(Map[now.row][now.col] == 0){ //빈칸일때
            Map[now.row][now.col] = 2; //청소
            clean++;
        }
        //2. 목적지인가? -> clean==N*M-block
//        if(clean == N*M - block) {
//            return -1;
//        }
        //3. 인접한 정점 탐색 -> 현재 dir을 기준으로 회전해야 하는 방향 받아오기
                        // 그 방향으로 탐색 -> 아직 청소X -> 거기로 전진, 청소 -> return dfs(new Point(newR,newC,새dir))
                        //              -> 청소O, 벽 -> 그 dir로 회전, -> return dfs(new Point(nowR,nowC,새dir)
                        //              -> 이미 청소O, 벽, 후진 가능 -> return dfs(new Point(newR,newC(후진),기존dir)
                        //              -> 이미 청소O, 벽, 후진 불가(뒤가 벽) -> return;
            //4. 갈 수 있는가?
            //5. 간다
        boolean flag = false;
        int nextDir = now.dir; //init
        for(int i=0;i<4;i++){
            nextDir = getDir(nextDir);
            int newR = now.row + dRow[nextDir];
            int newC = now.col + dCol[nextDir];
            if(0<newR && newR<N+1 && 0<newC && newC<M+1){
                if(Map[newR][newC]==0){ //아직 청소 X(0)
                    dfs(new Point(newR, newC, nextDir));
                    flag = true;
                    return;
                }
            }
        }

        if(!flag){ //네 방향 모두 갈 수 없음
            int newR = now.row + dRow[getBackDir(now.dir)];
            int newC = now.col + dCol[getBackDir(now.dir)];
            if(0<newR && newR<N+1 && 0<newC && newC<M+1) {
                if(Map[newR][newC]!=1){ //뒤가 벽
                    dfs(new Point(newR,newC,now.dir));
                }
            }
        }
    }

    public static int getDir(int nowDir){
        switch(nowDir){
            case 0://상
                return 3;//좌
            case 1://우
                return 0;//상
            case 2://하
                return 1;//우
            case 3://좌
                return 2;//하
        }
        return -1;
    }

    public static int getBackDir(int nowDir){
        switch(nowDir){
            case 0://상
                return 2;//하
            case 1://우
                return 3;//좌
            case 2://하
                return 0;//상
            case 3://좌
                return 1;//우
        }
        return -1;
    }

    public static class Point{
        int row;
        int col;
        int dir;

        public Point(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", dir=" + dir +
                    '}';
        }
    }
}
