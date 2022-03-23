import java.io.*;
import java.util.StringTokenizer;
//3:33~

public class Boj17136_색종이붙이기 {
    static int[][] Map;
    static StringTokenizer st;
    static int count = 0, C = 0;
    static int answer = Integer.MAX_VALUE;
    static int[] Paper = {0,5,5,5,5,5};
    static int[] countArr = {0,1,4,9,16,25};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map = new int[10+1][10+1];
        for(int i=1;i<11;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<11;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1인 곳에 색종이를 붙여야 함
        //for문 처음부터 끝까지 돌면서 1인 곳에 색종이 5종류 붙여보기
        //각 크기의 색종이는 5개씩 가지고 있음
        dfs(1,1,0);
        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }
    }

    static void dfs(int row, int col, int paper){
        //1. 체크인
        //2. 목적지인가? -> 끝에 도달했는가? -> answer = Math.min(answer,paper)
        if(row>=10 && col>10){
            answer = Math.min(answer,paper);
            return;
        }
        if(col>10){ //아랫줄로 이동
            dfs(row+1,1,paper);
            return;
        }
        //3. 인접 정점 탐색 -> 색종이 5개 고고
        if(Map[row][col]==1){
            for(int i=1;i<6;i++){
                //색종이 5개 긔긔
                if(Paper[i]>0){
                    //새로운 점들 벽체크, 1인지 체크
                    if(check(row,col,i)){
                        attach(row,col,i,0);
                        Paper[i]--;
                        dfs(row,col+1,paper+1);
                        Paper[i]++;
                        attach(row,col,i,1);
                    }
                }
            }
        }
        else{
            dfs(row,col+1,paper);
        }

        //4. 갈수있는가? -> 벽, 1임?, 갯수 1이상?
        //5. 간다 -> count 증가, Map고치기 ,색종이갯수감소-> dfs -> count그대로, Map그대로, 색종이갯수그대로
    }
    static boolean check(int row, int col, int paper){
        //맨왼쪽끝을 시작으로
        for(int i=0;i<paper;i++){
            for(int j=0;j<paper;j++){
                int newRow = row + i;
                int newCol = col + j;
                if(!(0<newRow && newRow<11 && 0<newCol && newCol<11)){ //벽이면 종료
                    return false;
                }
                if(Map[newRow][newCol]==0) {//0이면 종료
                    return false;
                }
            }
        }
        return true;
    }
    static void attach(int row, int col, int paper, int num){
        for(int i=0;i<paper;i++){
            for(int j=0;j<paper;j++){
                int newRow = row + i;
                int newCol = col + j;
                Map[newRow][newCol] = num;
            }
        }
    }
}
