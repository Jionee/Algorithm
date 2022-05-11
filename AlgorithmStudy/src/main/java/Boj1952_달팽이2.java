import java.io.*;
import java.util.StringTokenizer;

public class Boj1952_달팽이2 {
    static StringTokenizer st;
    static int M,N;
    static int[][] Map;
    static int[] dRow = {-1,0,1,0};//상우하좌
    static int[] dCol = {0,1,0,-1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        Map = new int[M+1][N+1];
        Map[1][1] = 1;

        int row = 1;
        int col = 1;
        int dir = 1;

        while(true){
            //진행방향 꺾고도 더이상 진행할 수 없으면 break
            int newRow = row + dRow[dir%4];
            int newCol = col + dCol[dir%4];
            //System.out.println(newRow+" "+newCol);

            if(!check(newRow,newCol) || (check(newRow,newCol)&&Map[newRow][newCol]!=0)){
                dir++;
                newRow = row + dRow[dir%4];
                newCol = col + dCol[dir%4];
                //System.out.println("** "+newRow+" "+newCol);
                if(!check(newRow,newCol) || (check(newRow,newCol)&&Map[newRow][newCol]!=0)) {
                    break;
                }
            }

            Map[newRow][newCol] = Map[row][col] + 1;
            row = newRow;
            col = newCol;

        }

//        for(int i=1;i<M+1;i++){
//            for(int j=1;j<N+1;j++){
//                System.out.printf("%3d",Map[i][j]);
//            }
//            System.out.println();
//        }
        System.out.println(dir-1-1);
        System.out.println(row+ " "+col);
    }

    static boolean check(int newRow,int newCol){
        if(1<=newRow && newRow<=M && 1<=newCol && newCol<=N){
            return true;
        }
        return false;
    }
}
