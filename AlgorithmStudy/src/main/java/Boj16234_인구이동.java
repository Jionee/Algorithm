import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj16234_인구이동 {
    static StringTokenizer st;
    static int N,L,R;
    static int[][] Map;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};

    static boolean flag = true;

    static boolean[][] visit;
    static ArrayList<Integer> sumArr = new ArrayList<>();
    static ArrayList<ArrayList<Point>> AllArr = new ArrayList<>();

    static int sum = 0;
    static ArrayList<Point> Arr = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //시작
        //while문 시작
        int answer = 0; //총 몇일지났는지
        while (true) {
            //flag 확인
            if (!flag) { //한 번도 인구이동이 일어나지 않았으면 break
                break;
            }
            //새로운 날이 시작되었으므로 변수 초기화
            AllArr = new ArrayList<>();
            sumArr = new ArrayList<>();
            flag = false;
            visit = new boolean[N][N];
            answer++;

            //Map을 살피면서 어디가 연합을 이루고 있는지 보기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) { //방문하지 않은 점에 대해
                        visit[i][j] = true; //방문 설정
                        Arr = new ArrayList<>(); //한 연합에 대해 초기화 (어느 지역이 포함되어있는지)
                        Arr.add(new Point(i, j)); //첫 방문 지역 추가
                        sum = Map[i][j]; //한 연합에 대해 초기화(sum이므로 처음 인구값으로)

                        dfs(i, j, 0); //연합 확인 고고

                        AllArr.add(Arr); //연합 확인됐으면 걔네들을 AllArr에 넣음
                        sumArr.add(sum);
                    }
                }
            }

            //하루 끝났으니까 AllArr 보면서 -> Map 갱신
            for (int i = 0; i < AllArr.size(); i++) { //연합 덩어리마다
                for (int j = 0; j < AllArr.get(i).size(); j++) { //한 연합에 속해있는 지역들 Map 갱신
                    Point point = AllArr.get(i).get(j);
                    Map[point.row][point.col] = sumArr.get(i) / AllArr.get(i).size();
                }
            }
        }
        System.out.println(answer-1); //마지막 안움직인 날 체크한거는 빼고 날짜 세기(-1)
    }

    static void dfs(int row, int col, int count){
        for(int i=0;i<4;i++){
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if(0<=newRow && newRow <N && 0<=newCol && newCol <N){ //범위 체크
                if(!visit[newRow][newCol]){ //방문하지 않았으면
                    int diff = Math.abs(Map[row][col] - Map[newRow][newCol]); //인구차이
                    if(L<=diff && diff <=R){ //인구차이 범위 체크, L이상 R이하면 연합으로 인정
                        visit[newRow][newCol] = true; //연합이니까 방문처리
                        Arr.add(new Point(newRow,newCol)); //연합이니까 Arr에 추가
                        sum+=Map[newRow][newCol]; //sum 추가
                        dfs(newRow,newCol,count+1); //다음 연합 찾으러 고고씽
                        flag = true; //국경 하나라도 열림
                    }
                }
            }
        }
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

}
