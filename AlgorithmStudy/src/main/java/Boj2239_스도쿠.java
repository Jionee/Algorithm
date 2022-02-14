import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2239_스도쿠 {
    static int[][] Map = new int[10][10];
    static ArrayList<Point> zeroList = new ArrayList<>();
    static StringTokenizer st;
    static boolean isEnd;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=1;i<10;i++){
            String str = br.readLine();
            for(int j=1;j<10;j++){
                int num = Integer.parseInt(String.valueOf(str.charAt(j-1)));
                if(num == 0){
                    zeroList.add(new Point(i,j)); //0인 점 세팅
                }
                Map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j-1)));
            }
        }


        //0이 빈 칸
        //모든 빈 칸에 대해서 수행할 것임
        dfs(0);
    }

    static void dfs(int zeroNum){
        //2. 목적지인가? -> 다 채워졌는가, zero가 다 쓰였나?
        // -> zeroNum = 0부터 시작해서 zeroNum>=zeroList.size 이면 return
        if(zeroNum >= zeroList.size()){
            for(int k=1;k<10;k++){
                for(int j=1;j<10;j++){
                    System.out.print(Map[k][j]);
                }
                System.out.println();
            }
            isEnd = true; //한 번이라도 도달하면 끝
            return;
        }
        int row = zeroList.get(zeroNum).row;
        int col = zeroList.get(zeroNum).col;

        //3. 인접한 점 탐색 -> 1~9 for문
        for(int i=1;i<10;i++) {
            //4. 갈 수 있는가? -> 가로,세로,사각형 보고 갈 수 있는지 확인
            if(isPossible(row,col,i)){
                //5-1. Map에 숫자 채우기, zeroNum++;
                Map[row][col] = i;
                zeroNum++;
                //5. 간다. -> dfs(zero.get(zeroNum).row,col)
                dfs(zeroNum);
                //5-2. Map에 숫자 지우기, zeroNum--;
                if(isEnd) return;
                Map[row][col] = 0;
                zeroNum--;
            }
        }
    }

    //가로 한 줄 중복된 수 X
    //세로 한 줄 중복된 수 X
    //사각형 중복된 수 X
    static boolean isPossible(int row, int col, int num){
        //가로
        for(int i=1;i<10;i++){
            if(Map[row][i] == num){ //같으면 false
                return false;
            }
        }
        //세로
        for(int i=1;i<10;i++){
            if(Map[i][col] == num){ //같으면 false
                return false;
            }
        }
        //사각형
        int rowStart = ((row-1) / 3) * 3 + 1;
        int colStart = ((col-1) / 3) * 3 + 1;
        for(int rs = rowStart;rs<rowStart+3;rs++){
            for(int cs = colStart;cs<colStart+3;cs++){
                if(Map[rs][cs] == num){
                    return false;
                }
            }
        }
        return true;
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
