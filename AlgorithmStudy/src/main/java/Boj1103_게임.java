import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1103_게임 {
    static int N,M;
    static char[][] map;
    static int answer = 0;
    static int[] dRow = {-1,1,0,0}; //위,아래,오른쪽,왼쪽
    static int[] dCol = {0,0,1,-1};
    static boolean[][] visit;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //세로 //가로
        M = Integer.parseInt(st.nextToken());
        map = new char[N+1][M+1];
        visit = new boolean[N+1][M+1];
        dp = new int [N+1][M+1];

        for(int i=1;i<N+1;i++){
            String string = br.readLine();
            for(int j=1;j<M+1;j++){
                map[i][j] = string.charAt(j-1);
            }
        }

//        for(char[] c : map){
//            System.out.println(Arrays.toString(c));
//        }

        //동전이 구멍(H)에 빠지거나, 보드의 바깥으로 나가면 게임이 종료됨
        //최대 동전을 움직일 수 있는 횟수
        //위,아래,오른쪽,왼쪽 이동 가능
        //(1,1)에서 시작 dfs로 풀어보자
        visit[1][1] = true;
        dfs(new Point(1,1,Integer.parseInt(String.valueOf(map[1][1]))),1);
        if(answer == Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println(answer);
        }

    }
    static int dfs(Point now,int count){
        //1. 체크인
        dp[now.row][now.col] = count;
        answer = Math.max(answer,count); //갱신
        //2. 목적지인가?
        //3. 인접점 탐색 -> 위,아래,오른쪽,왼쪽
        for(int i=0;i<4;i++){

            int nextRow = now.row + now.value * dRow[i];
            int nextCol = now.col + now.value * dCol[i];

            //4. 갈 수 있는가? -> H안됨, 벽 안됨(answer갱신하기)

            if(nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M){
                answer = Math.max(answer,count);
                continue;
            }
            if(map[nextRow][nextCol] == 'H'){
                //answer 갱신
                answer = Math.max(answer,count);
                continue;
            }
            if(visit[nextRow][nextCol] == true){ //이미 방문한 애면 무한히 움직이는 애
                answer = Integer.MAX_VALUE;
                return answer;
            }
            if(dp[nextRow][nextCol] > count){
                continue; //다음 점의 dp에 이미 뭐가 적혀져있음(이미 다른길에서 지나왔어, 근데 그게 지금 count보다 크다? 그럼 어차피 그게 더 크니까 더 할 필요 없음)
            }

            //5. 간다
            visit[nextRow][nextCol] = true;
            dfs(new Point(nextRow,nextCol,Integer.parseInt(String.valueOf(map[nextRow][nextCol]))),count+1);
            visit[nextRow][nextCol] = false;
        }
        //6. 체크아웃
        return count;
    }

    static class Point{
        int row;
        int col;
        int value;


        public Point(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }


        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", value=" + value +
                    '}';
        }
    }
}
