import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7569_토마토 {
    static int M,N,H;
    static StringTokenizer st;
    static int[][][] Map;
    static Queue<Point> queue = new LinkedList<>();
    static int[] drow = {0,0,-1,1,0,0}; //왼,오,위,아래,상,하
    static int[] dcol = {-1,1,0,0,0,0};
    static int[] dheight = {0,0,0,0,1,-1};
    static int doneTomato = 0;
    static int ssuck = 0;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //왜 bfs? -> 토마토가 익으면서 맵의 상태가 계속해서 변해감 But 이거는 dfs도 대응할 수 있는거 아닌가? -> 그 이유에 대해서 자세히 알아보자
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        Map = new int[N+1][M+1][H+1]; //세로row,가로col,높이

        for(int k=1;k<H+1;k++){
            for(int i=1;i<N+1;i++){//세로 row
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<M+1;j++){ //가로 col
                    int tomato = Integer.parseInt(st.nextToken());
                    if(tomato == 1){ //익은 토마토 넣기
                        queue.add(new Point(i,j,k,0));
                    }
                    else if(tomato == -1){
                        ssuck++;
                    }
                    Map[i][j][k] = tomato;
                }
            }
        }

        if(M*N*H == ssuck){
            System.out.println(-1);
            return;
        }

        if(queue.size() == M*N*H - ssuck){
            System.out.println(0);
            return;
        }

        int answer = -1;
        boolean isEnd = false;
        while(!queue.isEmpty()){

            Point now = queue.poll();
            doneTomato ++;
            answer = Math.max(answer,now.day);

            if(doneTomato + ssuck >= N*M*H){ //목적지인가?
                isEnd = true;
                break;
            }

            for(int i=0;i<6;i++){
                //갈수있는가? -> 벽, 0인지
                int row = now.row + drow[i];
                int col = now.col + dcol[i];
                int height = now.height + dheight[i];
                if(1<=row && row<=N && 1<=col && col<=M && 1<= height && height <= H){
                    if(Map[row][col][height] == 0){
                        Map[row][col][height] = 1;
                        queue.add(new Point(row,col,height,now.day+1));
                    }
                }
            }
        }

        if(!isEnd){
            System.out.println(-1);
        }
        else {
            System.out.println(answer);
        }
    }

    static class Point{
        int row;
        int col;
        int height;
        int day;

        public Point(int row, int col, int height, int day) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.day = day;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", height=" + height +
                    ", day=" + day +
                    '}';
        }
    }
}
