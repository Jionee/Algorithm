import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj15683_감시 {
    static StringTokenizer st;
    static int N,M;
    static int[][] Map;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static ArrayList<Point> CCTV = new ArrayList<>();
    static int zero = 0;
    static boolean[][] visit;
    static int answer = 100;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new int[N+1][M+1];
        visit = new boolean[N+1][M+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            int j = 1;
            while(st.hasMoreTokens()){
                Map[i][j] = Integer.parseInt(st.nextToken());
                if(Map[i][j]==0){ //0갯수 세기
                    zero++;
                }
                else if(Map[i][j] != 6){ //CCTV위치 저장
                    CCTV.add(new Point(i,j,Map[i][j]));
                }
                j++;
            }
        }

        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int index,int count){ //index : 몇번째 cctv를 볼건지 , count : CCTV가 감시하는 영역의 수(누적)
        if(count>=zero){ //다 감시했으면 끝
            answer = 0;
            return;
        }
        if(index>=CCTV.size()){ //마지막 CCTV까지 다 감시함
            answer = Math.min(answer,zero-count);
            return;
        }

        Point cctv = CCTV.get(index); //index번 CCTV를 살펴보자

        boolean[][] copy = new boolean[N+1][M+1]; //visit 복사(dfs 하나 끝나고 이전 배열 돌려놓기 위함)
        for(int i=1;i<N+1;i++){ //깊은 복사
            for(int j=1;j<M+1;j++){
                copy[i][j] = visit[i][j];
            }
        }

        int watchCount = 0; //지금 보고있는 cctv가 감시할 수 있는 곳들 갯수
        switch(cctv.type){
            case 1: //상,하,좌,우
                parameterOne(0,index, count, cctv, copy, watchCount); //상
                parameterOne(1,index, count, cctv, copy, watchCount); //하
                parameterOne(2,index, count, cctv, copy, watchCount);
                parameterOne(3,index, count, cctv, copy, watchCount);
                break;
            case 2: //상하 01, 좌우23
                parameterTwo(0,1,index, count, cctv, copy, watchCount); //상하
                parameterTwo(2,3,index, count, cctv, copy, watchCount); //좌우
                break;
            case 3: //상우 03, 우하 31, 하좌 12, 좌상 20
                parameterTwo(0,3,index, count, cctv, copy, watchCount);
                parameterTwo(3,1,index, count, cctv, copy, watchCount);
                parameterTwo(1,2,index, count, cctv, copy, watchCount);
                parameterTwo(2,0,index, count, cctv, copy, watchCount);
                break;
            case 4: //좌상우 203, 상우하 031, 우하좌 312, 하좌상 120
                parameterThree(2,0,3,index, count, cctv, copy, watchCount); //좌상우
                parameterThree(0,3,1,index, count, cctv, copy, watchCount); //상우하
                parameterThree(3,1,2,index, count, cctv, copy, watchCount);
                parameterThree(1,2,0,index, count, cctv, copy, watchCount);
                break;
            case 5: //상하좌우 0123
                parameterFour(0,1,2,3,index, count, cctv, copy, watchCount); //상하좌우
                break;
        }
    }

    private static void parameterOne(int one, int index, int count, Point cctv, boolean[][] copy, int watchCount) {
        watchCount += getWatchCount(cctv, one); //갈 수 있는 곳까지 쭈욱
        dfs(index + 1, count + watchCount); //그상태에서 다음 CCTV 고
        for (int n = 1; n < N + 1; n++) { //원상복구
            for (int j = 1; j < M + 1; j++) {
                visit[n][j] = copy[n][j];
            }
        }
    }

    private static void parameterTwo(int one, int two, int index, int count, Point cctv, boolean[][] copy, int watchCount) {
        watchCount += getWatchCount(cctv, one); //갈 수 있는 곳까지 쭈욱
        watchCount += getWatchCount(cctv, two);
        dfs(index + 1, count + watchCount); //그상태에서 다음 CCTV 고
        for (int n = 1; n < N + 1; n++) { //원상복구
            for (int j = 1; j < M + 1; j++) {
                visit[n][j] = copy[n][j];
            }
        }
    }
    private static void parameterThree(int one, int two, int three, int index, int count, Point cctv, boolean[][] copy, int watchCount) {
        watchCount += getWatchCount(cctv, one); //갈 수 있는 곳까지 쭈욱
        watchCount += getWatchCount(cctv, two);
        watchCount += getWatchCount(cctv, three);
        dfs(index + 1, count + watchCount); //그상태에서 다음 CCTV 고
        for (int n = 1; n < N + 1; n++) { //원상복구
            for (int j = 1; j < M + 1; j++) {
                visit[n][j] = copy[n][j];
            }
        }
    }
    private static void parameterFour(int one, int two, int three, int four, int index, int count, Point cctv, boolean[][] copy, int watchCount) {
        watchCount += getWatchCount(cctv, one); //갈 수 있는 곳까지 쭈욱
        watchCount += getWatchCount(cctv, two);
        watchCount += getWatchCount(cctv, three);
        watchCount += getWatchCount(cctv, four);
        dfs(index + 1, count + watchCount); //그상태에서 다음 CCTV 고
        for (int n = 1; n < N + 1; n++) { //원상복구
            for (int j = 1; j < M + 1; j++) {
                visit[n][j] = copy[n][j];
            }
        }
    }

    private static int getWatchCount(Point cctv, int i) {
        int count = 0;
        for(int k=1;k<=8;k++){
            int newRow = cctv.row + k * dRow[i]; //갈 수 있는 곳까지 k 곱하면서 쭉
            int newCol = cctv.col + k * dCol[i];
            if((0<newRow && newRow<=N && 0<newCol && newCol<=M)){
                if(Map[newRow][newCol]==6){ //벽을 만나면 끝
                    break;
                }
                else{
                    if(Map[newRow][newCol]==0){ //빈칸인 경우만
                        if(!visit[newRow][newCol]){ //이미 다른 CCTV가 감시한 곳이면 셀 필요없음
                            visit[newRow][newCol] = true;
                            count++;
                        }
                    }
                }
            }
            else {
                break; //범위를 벗어나면 끝
            }
        }
        return count;
    }

    static class Point{
        int row;
        int col;
        int type;

        public Point(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}
