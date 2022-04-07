import java.io.*;
import java.util.StringTokenizer;

public class Boj14890_경사로 {
    static StringTokenizer st;
    static int N,L;
    static int[][] Map;
    static int answer;
    static int[] dRow = {-1,1,0,0};//상하좌우
    static int[] dCol = {0,0,-1,1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=N;i++){
            dfs(1,i,0,1,1); //하
            dfs(i,1,0,3,1); //우
        }
        System.out.println(answer);
    }

    //높은 칸을 만났을 때는 지금까지 연속한 칸 수 저장한거가 L이상인지 확인
    //낮은 칸을 만났을 때는 L만큼 가면서 연속하는지 확인
    static boolean dfs(int row, int col, int count, int dir, int continued){
        //System.out.println(row+","+col+" count = "+count+" dir = "+ dir);
        if(count>=N-1){
            answer++;
            return true;
        }
        int nextRow = row + dRow[dir];
        int nextCol = col + dCol[dir];
        if(checkCoord(nextRow,nextCol)){
            int nowHeight = Map[row][col];
            int nextHeight = Map[nextRow][nextCol];
            int diff = Math.abs(nowHeight-nextHeight);

            if(diff==0){//차이가 같은데
                dfs(nextRow,nextCol,count+1,dir,continued+1); //다음칸으로 이동
            }
            else if(diff==1){ //차이가 1일 때

                if(nowHeight - nextHeight < 0){ //1. 높은칸을 만남 ex) 2 > 3
                    if(visited[row][col] || continued<L){ //높은칸을 만나서 지금있는 낮은칸에 경사 설치해야 하는데 이미 설치돼있으면 안됨(visited), L수보다 낮은칸 연속 작음
                        return false;
                    }
                    if(continued>=L){ //낮은칸 연속 작고 경사 설치가 처음이면
                        visited[row][col] = true; //현재 있는 낮은 칸에 경사 설치
                        dfs(nextRow,nextCol,count+1,dir,1);
                        visited[row][col] = false; //원위치
                    }
                }
                else{ //2. 낮은칸을 만남 ex) 3 > 2
                    boolean flag = true; //갈 수 있는지

                    for(int i=1;i<L+1;i++){ //L개만큼 가면서 높이가 동일한지 확인
                        int newRow = row + dRow[dir] * i;
                        int newCol = col + dCol[dir] * i;
                        if(checkCoord(newRow,newCol)){
                            if(!(Map[newRow][newCol] == nextHeight)){ //높이가 갖지 않으면 끝
                                flag = false;
                                break;
                            }
                       }
                    }
                    if(flag){ //GoGo
                        int coorRow = row + dRow[dir] * L; //L칸 후 가야할 곳
                        int coorCol = col + dCol[dir] * L;
                        if(checkCoord(coorRow,coorCol)) {
                            visited[coorRow][coorCol] = true; //어차피 한 방향으로만 가므로 마지막 낮은 칸에만 visited 설정
                            dfs(coorRow, coorCol, count + L, dir, 0);
                            visited[coorRow][coorCol] = false; //원위치
                        }
                    }
                }
            }
        }
        return true;
    }
    static boolean checkCoord(int row, int col){ //벽 확인
        if(1<=row && row<N+1 && 1<=col && col<N+1){
            return true;
        }
        return false;
    }
}
