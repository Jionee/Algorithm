import java.io.*;
import java.util.*;

public class Boj2842_집배원한상덕 {
    static StringTokenizer st;
    static int N;
    static char[][] Map;
    static int[][] weight;
    static ArrayList<Integer> height = new ArrayList<>();
    static Point start; //시작지점
    static int[] dRow = {-1,1,0,0,-1,-1,1,1}; //상,하,좌,우,왼상,오상,왼하,오하
    static int[] dCol = {0,0,-1,1,-1,1,-1,1};
    static int house;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Map = new char[N+1][N+1];
        weight = new int[N+1][N+1];

        for(int i=1;i<N+1;i++){
            String str = br.readLine();
            for(int j=1;j<N+1;j++){
                char point = str.charAt(j-1);
                if(point=='P'){
                    start = new Point(i,j); //시작지점
                }
                else if(point == 'K'){
                    house++;
                }
                Map[i][j] = point;
            }
        }

        height.add(-1); //1~size-1
        for(int i=1;i<N+1;i++){ //1~N
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++){
                weight[i][j] = Integer.parseInt(st.nextToken());
                if(!height.contains(weight[i][j])){
                    height.add(weight[i][j]);
                }
            }
        }
        Collections.sort(height); //중복 제거하고, 오름차순 정렬

        //투포인터 + bfs 문제
        //투포인터를 이용해서 각 포인터에 min,Max값을 지정한다.
        //해당 min,Max 범위 내의 길을 이용해서 모든 집을 방문할 수 있다면 min+1 , answer = Math.min(answer,min) 갱신
                                        //방문할 수 없다면 Max+1
        //min,Max가 length를 넘지 않을 때까지만 진행한다.
        //1,5 범위에서 모든 집을 방문할 수 있다면 1,6이상은 살펴보지 않아도 방문할 수 있다. 따라서 NlogN시간에 탐색이 가능하다.

        //투포인터를 사용하여 min,Max값 후보를 바이너리 서치하고 -> bfs조건으로 건다.
        int min = 1; //1~size-1
        int max = 1;
        int answer = 1000000;
        while(min<=height.size()-1 && max<=height.size()-1){
            //System.out.println(min+","+max);
            if(doBfs(height.get(min),height.get(max))){
                answer = Math.min(height.get(max) - height.get(min),answer);
                //System.out.println(height.get(max)+"-"+height.get(min)+"="+answer);
                min++;
            }
            else{
                max++;
            }
        }
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }

    private static boolean doBfs(int min, int max) { //min,max 내에 있는 것으로만 지나갈 수 있음. 이것만 지나면서 모든 K를 방문할 수 있는지 확인
        if(weight[start.row][start.col]<min || weight[start.row][start.col]>max){ //시작점부터 거르기
            return false;
        }
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.row][start.col] = true;
        int countHouse = 0;
        //System.out.println("@@@ "+min+" "+max);

        while(!queue.isEmpty()){
            //체크인
            Point now = queue.poll();
            if(Map[now.row][now.col] == 'K'){
                //System.out.println(now.row+":"+now.col+" is K!!");
                countHouse++;
            }
            if(countHouse >= house){ //목적지인가?
                //System.out.println("=====");
                return true;
            }
            //인접 점 스캔
            for(int i=0;i<8;i++){
                int nextRow = now.row + dRow[i];
                int nextCol = now.col + dCol[i];
                //System.out.println(nextRow+","+nextCol);
                //갈수있는가?
                if(1<=nextRow && nextRow<=N && 1<=nextCol && nextCol<=N){ //벽
                    if(!visited[nextRow][nextCol]){ //방문했었나?
                        if(min<=weight[nextRow][nextCol] && weight[nextRow][nextCol]<=max){ //min,max에 해당하는 길만 OK
                            visited[nextRow][nextCol] = true;
                            //System.out.println("^^"+nextRow+","+nextCol+"->"+weight[nextRow][nextCol]);
                            queue.add(new Point(nextRow,nextCol));
                        }
                    }
                }
            }
        }
        return false;
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
