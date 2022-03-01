import java.io.*;
import java.util.*;

public class Boj9205_맥주마시면서걸어가기 {
    static StringTokenizer st;
    static int T,N;
    static Point Home;
    static Point[] Shop;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            N = Integer.parseInt(br.readLine());
            Shop = new Point[N+2];

            st = new StringTokenizer(br.readLine());
            Home = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            for(int j=1;j<N+2;j++){
                st = new StringTokenizer(br.readLine());
                Shop[j] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }

            //bfs
            boolean[] visited = new boolean[N+2];
            Queue<Point> queue = new LinkedList<>();
            queue.add(Home);
            boolean flag = false;

            while(!queue.isEmpty()) {
                Point now = queue.poll();
                //2.목적지인가?
                if (now.row == Shop[N + 1].row && now.col == Shop[N + 1].col) {
                    System.out.println("happy");
                    flag = true;
                    break;
                }
                //3.인접한 점 탐색 - 거리가 1000m 이내인지 확인, visit 안한 것
                for (int j = 1; j < N + 2; j++) {
                    if (!visited[j]) {
                        int distanceR = Math.abs(now.row - Shop[j].row);
                        int distanceC = Math.abs(now.col - Shop[j].col);
                        if (distanceR + distanceC <= 1000) {
                            queue.add(Shop[j]);
                            visited[j] = true;
                        }
                    }
                }
                //4. 갈수있는가?
                //5. 간다 ,체크인
            }
            if(!flag){
                System.out.println("sad");
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
