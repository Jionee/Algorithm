import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16236_아기상어 {
    static StringTokenizer st;
    static int N;
    static int[][] Map;
    static Shark shark;
    static int answer;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static boolean[][] visit;
    static int fishNum = 0;
    static int eatFishNum = 0;

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
                if(1<=Map[i][j] && Map[i][j]<=6){
                    fishNum++; //총 물고기 개수
                }
                if(Map[i][j] == 9){
                    shark = new Shark(i,j,2); //상어 정보 저장
                    Map[i][j] = 0; //상어 정보는 저장했으므로 9는 이제 빈칸으로 취급해야 함
                }
            }
        }


        while(true){
            if(fishNum<=0){ //물고기 다 먹으면 끝
                break;
            }

            //상어 이동시킬때마다 초기화
            PriorityQueue<Fish> queue = new PriorityQueue<>(); //상어가 먹을 수 있는 물고기 후보들 -> 우선순위큐 사용
            visit = new boolean[N+1][N+1]; //상어가 방문한 좌표 관리
            Queue<Shark> move = new LinkedList<>(); // 상어가 이동하는 좌표(bfs돌리면서 여기다 담을거임)
            move.add(shark); //상어가 처음 있는 곳
            visit[shark.row][shark.col] = true; //상어가 처음 있는 곳 방문처리

            //bfs 시작
            while(!move.isEmpty()){
                Shark now = move.poll();
                for(int i=0;i<4;i++) {
                    int newRow = now.row + dRow[i];
                    int newCol = now.col + dCol[i];

                    if (0 < newRow && newRow <= N && 0 < newCol && newCol <= N) { //범위 확인
                        if (!visit[newRow][newCol]) { //방문할 수 있다면
                            visit[newRow][newCol] = true; //방문처리
                            if (Map[newRow][newCol] <= shark.size) { //상어가 갈 수 있냐? //0,같거나 작은 곳
                                if (Map[newRow][newCol] < shark.size && Map[newRow][newCol] != 0) { //먹을수 있는 물고기냐? //작은 곳
                                    queue.add(new Fish(newRow, newCol, now.count+1)); //물고기 후보에 추가
                                }
                                move.add(new Shark(newRow, newCol, now.size, now.count + 1)); //0,같거나 작은 곳에서 모두 가야하므로 move에 좌표 추가
                            }
                        }
                    }
                }
            }//bfs 끝

            //물고기 잡을 수 있는지 확인
            if(queue.isEmpty()){
                break; //더이상 먹을 수 있는 물고기가 없다면 엄마찬스
            }
            Fish eat = queue.poll();
            eatFishNum++;
            //아기상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
            int Size = shark.size;
            if(eatFishNum % shark.size==0){
                Size = shark.size+1;
                eatFishNum = 0;
            }

            shark = new Shark(eat.row,eat.col,Size); //상어는 물고기를 먹은 자리에 가서 서있어야 함

            Map[eat.row][eat.col] = 0; //물고기는 먹혔으므로 0 설정
            fishNum--;

            answer += eat.count; //상어가 이동한 거리는 먹힌 물고기한테 간 거리이기 때문
        }

        System.out.println(answer);
    }

    public static class Shark{
        int row;
        int col;
        int size;
        int count;

        public Shark(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
        }

        public Shark(int row, int col, int size, int count) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.count = count;
        }
    }

    public static class Fish implements Comparable<Fish>{
        int row;
        int col;
        int count;

        public Fish(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        //거리가 가까운 순, 거리가 가까운 물고기가 많다면 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면 가장 왼쪽에 있는 물고기
        @Override
        public int compareTo(Fish o) {
            int cmp = Integer.compare(count,o.count);
            if(cmp==0){
                cmp = Integer.compare(row,o.row);
                if(cmp==0){
                    cmp = Integer.compare(col,o.col);
                }
            }
            return cmp;
        }
    }
}
