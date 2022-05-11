import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj1913_달팽이 {
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] Map;
    static int[] dRow = {0,-1,0,1};
    static int[] dCol = {-1,0,1,0};
    static int[] dRow2 = {0,1,0,-1}; //좌하우상
    static int[] dCol2 = {-1,0,1,0};
    static HashMap<Integer,Point> HashMap = new HashMap<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Map = new int[N+1][N+1];

//        int row = N/2+1;
//        int col = N/2+1;
//        Map[row][col] = 1;
//        HashMap.put(1,new Point(row,col));
//
//        int replay = 0;
//        for(int i=1;i<=(N-1)*2;i++){
//            if(i%2==1){
//                replay++;
//            }
//            for(int j=0;j<replay;j++){
//                int newRow = row+dRow[i%4];
//                int newCol = col+dCol[i%4];
//                Map[newRow][newCol] = Map[row][col] + 1;
//                HashMap.put(Map[newRow][newCol],new Point(newRow,newCol));
//
//                row = newRow;
//                col = newCol;
//            }
//        }
//        for(int j=0;j<replay;j++){
//            int newRow = row+dRow[1];
//            int newCol = col+dCol[1];
//            Map[newRow][newCol] = Map[row][col] + 1;
//            HashMap.put(Map[newRow][newCol],new Point(newRow,newCol));
//
//            row = newRow;
//            col = newCol;
//        }
        int row = 1;
        int col = 1;
        Map[row][col] = N*N;

        int dir = 1;
        while(true){
            //진행방향 꺾고도 더이상 진행할 수 없으면 break
            int newRow = row + dRow2[dir%4];
            int newCol = col + dCol2[dir%4];
            //System.out.println(newRow+" "+newCol);

            if(!check(newRow,newCol) || (check(newRow,newCol)&&Map[newRow][newCol]!=0)){
                dir++;
                newRow = row + dRow2[dir%4];
                newCol = col + dCol2[dir%4];
                //System.out.println("** "+newRow+" "+newCol);
                if(!check(newRow,newCol) || (check(newRow,newCol)&&Map[newRow][newCol]!=0)) {
                    break;
                }
            }

            Map[newRow][newCol] = Map[row][col] - 1;
            row = newRow;
            col = newCol;
        }

        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                System.out.printf("%3d",Map[i][j]);
                //sb.append(Map[i][j]+" ");
                //System.out.print(Map[i][j]+" ");
            }
            //sb.append("\n");
            System.out.println();
        }
        int K = Integer.parseInt(br.readLine());
        Point point = HashMap.get(K);
        //System.out.println(point.row+" "+point.col);
        //sb.append(point.row+" "+point.col);
        //System.out.println(sb.toString());

    }

    static boolean check(int newRow,int newCol){
        if(1<=newRow && newRow<=N && 1<=newCol && newCol<=N){
            return true;
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
