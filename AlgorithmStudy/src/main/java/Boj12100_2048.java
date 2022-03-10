import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj12100_2048 {
    static StringTokenizer st;
    static int N;
    static int[][] Map;
    static int answer = 0;
    static int[] dRow = {-1,1,0,0}; //상하좌우
    static int[] dCol = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        Map = new int[N+1][N+1];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++){
                Map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        //상하좌우 4방으로 이동할건데, 존재하는 모든 블록들이 다 이동할거임
        //5번까지 하면서 최대값을 찾기 -> bfs도 괜찮은데 어차피 다 할거면 dfs도 괜춘(둘 다 차이없을듯) ->아니다 Map을 변화시키면서 해야하니까 dfs로 하자
        //체크인(갱신),체크아웃(되돌려놓기)

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int count){

        //1.체크인
        //2.목적지인가? -> count>=5면 중지,최대값 확인해서 answer갱신하기
        if(count>=5){
            //System.out.println(answer + ","+findMax(Map));
            answer = Math.max(answer,findMax(Map));
            return;
        }
        //3.인접정점탐색 -> 상하좌우, 애들 newPoint해서 이동시키기
        int[][] copy  = new int[N+1][N+1];
        for(int k=1;k<N+1;k++){ //백트래킹이므로 전역변수를 이전 상태로 원복시켜야 함
            copy[k] = Map[k].clone();
        }
        for(int i=0;i<4;i++){
            //4. 간다.
            move(i);
            dfs(count+1);

            for(int k=1;k<N+1;k++){ //백트래킹 원복
                Map[k] = copy[k].clone();
            }
        }
    }

    public static int findMax(int[][] Map){
        int max = 0;
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                max = Math.max(max,Map[i][j]);
            }
        }
        return max;
    }

    public static void move(int dir){ //이동하는 방향으로 먼저 겹칠거니까 방향마다 다르게 진행해야 함
        boolean[][] duplicate = new boolean[N+1][N+1]; //이미 합친거는 더이상 안합치게끔
        // ex) 16 16 32 우로 이동 시 0 0 64 가 아니라 0 32 32가 되게끔

        switch(dir){
            case 0://상
                for(int i=1;i<N+1;i++){ //맨 윗줄부터 시작
                    for(int j=1;j<N+1;j++){
                        commonMove(dir, i, j, duplicate);
                    }
                }
                break;
            case 1://하
                for(int i=N;i>0;i--){ //맨 아랫줄부터 시작
                    for(int j=1;j<N+1;j++){
                        commonMove(dir, i, j, duplicate);
                    }
                }
                break;
            case 2://좌
                for(int j=1;j<N+1;j++){ //맨 왼쪽부터 시작
                    for(int i=1;i<N+1;i++){
                        commonMove(dir, i, j, duplicate);
                    }
                }
                break;
            case 3://우
                for(int j=N;j>0;j--){ //맨 오른쪽부터 시작
                    for(int i=1;i<N+1;i++){
                        commonMove(dir, i, j, duplicate);
                    }
                }
                break;
        }
    }

    private static void commonMove(int dir, int i, int j, boolean[][] duplicate) {
        if(Map[i][j] == 0)
            return;
        int newRow = i + dRow[dir];
        int newCol = j + dCol[dir];
        //경우 1. 다음거가 벽인 경우 > 아무것도 안함
        //경우 2. 다음이 벽이 아닌 경우
            //2-1. 다음이 같은 경우
            //2-2. 다음이 0인 경우
            //2-3. 다음이 다른 경우 > 아무것도 안함

        //경우 2. 다음거가 벽이 아니면
        if (0 < newRow && newRow <= N && 0 < newCol && newCol <= N) {
            if (Map[newRow][newCol] == Map[i][j] && !duplicate[newRow][newCol]) { //2-1. 다음거가 같으면 합치기 (이미 합친적이 있으면 안함)
                Map[newRow][newCol] = Map[i][j] * 2;
                Map[i][j] = 0;
                duplicate[newRow][newCol]=true;
            } else if (Map[newRow][newCol] == 0) { //2-2. 다음거가 빈칸이면 그냥 이동
                Map[newRow][newCol] = Map[i][j];
                Map[i][j] = 0;
                commonMove(dir,newRow,newCol,duplicate);
            }
        }
    }
}
