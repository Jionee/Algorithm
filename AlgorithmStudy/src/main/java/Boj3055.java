import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj3055 {
    static int R,C;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue = new LinkedList<>();
    static boolean foundAnswer;

    // 좌,우,위,아래

    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        //Input 입력받기
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();

        Point startPoint = null;
        for (int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine()); //한 줄 읽어오기
            char[] ch = st.nextToken().toCharArray(); // java string -> charArr
            for (int j = 0; j < ch.length; j++) {
                map[i][j] = ch[j];
                if (map[i][j] == 'S'){
                    startPoint = new Point(i,j,'S');
                }
                else if(map[i][j] == '*'){
                    queue.add(new Point(i,j,'*'));
                }
            }
        }
        queue.add(startPoint);

        /**
     * 0. 큐에 시작점 넣기
     *          while(!queue.isEmpty()){
     *             1. 큐에서 가져옴 -> S, ., D, *
     *             2. 목적지인가? -> D
     *             3. 연결된 곳을 순회 -> 좌,우,위,아래
     *                 4. 갈 수 있는가?(고슴도치) -> 맵을 벗어나지 않음, . or D, 방문하지 않음
     *                     5. 체크인 -> dp에 현재 +1 기록
     *                     6. 큐에 넣음
     *                 4. 갈 수 있는가?(물) -> 맵을 벗어나지 않음, .
     *                     5. 체크인 -> 지도에 * 표시
     *                     6. 큐에 넣음
     *         }
         **/

        while(!queue.isEmpty()){
            //1. 큐에서 가져옴 -> S, ., D, *
            Point p = queue.poll();

            //2. 목적지인가? -> D
            if(p.type == 'D'){
                System.out.println(dp[p.y][p.x]);
                foundAnswer = true;
                break;
            }
            //3. 연결된 곳을 순회 -> 좌,우,위,아래
            for(int i=0;i<4;i++){
                int ty = p.y + my[i];
                int tx = p.x + mx[i];
                //4. 갈수있는가? (고슴도치) -> 맵을 벗어나지 않음, . or D, 방문하지 않음
                //4. 갈수있는가? (물) -> 맵을 벗어나지 않음, .
                if (0 <= ty && ty <R && 0<=tx && tx <C){
                    if(p.type == '.' || p.type == 'S'){ //고슴도치
                        if(map[ty][tx] == '.' || map[ty][tx] == 'D'){
                            if(dp[ty][tx] == 0){
                                //5. 가자 -> dp에 현재 +1 기록
                                //6. 큐에 넣음
                                dp[ty][tx] = dp[p.y][p.x] + 1;
                                queue.add(new Point(ty,tx,map[ty][tx]));
                            }
                        }
                    }
                    else if(p.type == '*'){ //물
                        if(map[ty][tx] == '.'){
                            //5. 가자 -> 지도에 * 표기
                            //6. 큐에 넣음
                            map[ty][tx] = '*';
                            queue.add(new Point(ty,tx,'*'));
                        }
                    }
                }
            }
        }
        if (foundAnswer == false){
            System.out.println("KAKTUS");
        }
    }

 static class Point{
        int y;
        int x;
        char type;
        public Point(int y,int x,char type){
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }
}