import java.util.ArrayList;

public class Pg_gr4 {

    public static void main(String[] args) throws Exception{
        //input
        int n = 3;
        int rooks = 2;

        int answer = Solution.solution(n,rooks);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static int[][] Map;
        static boolean[][] visit;
        static boolean[][] Rook;
        static ArrayList<Point> Arr = new ArrayList<>();
        static int R,N;
        static int answer = 0;
        static int[] dRow1 = {-1,-1,1,1,0,0}; //정
        static int[] dCol1 = {-1,1,-1,1,-1,1};
        static int[] dRow2 = {-1,-1,1,1,0,0}; //역
        static int[] dCol2 = {-1,1,-1,1,-1,1};

        public static int solution(int n, int rooks) {
            N = n;
            R = rooks;

            //Map구성하기
            Map = new int[n+1][n*2+1]; //1~n층, 2~n*2까지
            visit = new boolean[n+1][n*2+1]; //1~n층, 2~n*2까지
            Rook = new boolean[n+1][n*2+1]; //1~n층, 2~n*2까지
            for(int i=n;i>=1;i--){
                int flag = 0;
                for(int j=(2+(n-i));j<=(n*2-(n-i));j++){
                    if(flag%2==0){
                        Map[i][j] = 1; //정삼각형
                    }
                    else{
                        Map[i][j] = 2; //역삼각형
                    }
                    flag++;
                }
            }
            for(int i=1;i<n+1;i++){
                for(int j=2;j<n*2+1;j++){
                    System.out.print(Map[i][j]+" ");
                }
                System.out.println();
            }

            //놓을거야 앞으로
            dfs(0);

            return answer;
        }
        static void dfs(int count){
            if(count>=R){
                //공격하는지 확인하기
                if(check()){
                    System.out.println(Arr);

                    answer++;
                }
                return;
            }
            for(int i=1;i<N+1;i++){
                for(int j=2;j<N*2+1;j++){
                    if(!visit[i][j]){
                        if(Map[i][j]==1 || Map[i][j]==2){
                            visit[i][j] = true;
                            Arr.add(new Point(i,j));
                            Rook[i][j] = true;
                            dfs(count+1);
                            visit[i][j] = false;
                            Rook[i][j] = false;
                            Arr.remove(Arr.size()-1);
                        }
                    }
                }
            }
        }
        static boolean check(){
           // System.out.println("### "+Arr);
            for(Point point:Arr){
                //각 점마다 6방향 확인해서 다른거 만나면 false 리턴하기
                //System.out.println("공격!->"+point);

                int row = point.row;
                int col = point.col;
                if(Map[row][col]==1){ //정삼각형
                    for(int i=0;i<6;i++){
                        if(i==2){
                            for(int k=1;k<N;k++){
                                int newRow = row + k*dRow1[i];
                                int newCol = col + k*dCol1[i];
                                int newRow2 = newRow;
                                int newCol2 = newCol+1;
                                if(1<=newRow && newRow<=N && 2<=newCol && newCol<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow][newCol]){
                                        return false;
                                    }
                                }
                                if(1<=newRow2 && newRow2<=N && 2<=newCol2 && newCol2<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow2][newCol2]){
                                        return false;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                        else if(i==3){
                            for(int k=1;k<N;k++){
                                int newRow = row + k*dRow1[i];
                                int newCol = col + k*dCol1[i];
                                int newRow2 = newRow;
                                int newCol2 = newCol-1;
                                if(1<=newRow && newRow<=N && 2<=newCol && newCol<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow][newCol]){
                                        return false;
                                    }
                                }
                                if(1<=newRow2 && newRow2<=N && 2<=newCol2 && newCol2<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow2][newCol2]){
                                        return false;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                        else{
                            for(int k=1;k<N;k++){

                                int newRow = row + k*dRow1[i];
                                int newCol = col + k*dCol1[i];
                                if(1<=newRow && newRow<=N && 2<=newCol && newCol<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow][newCol]){
                                        return false;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                    }

                }
                else if(Map[row][col]==2){ //역삼각형
                    for(int i=0;i<6;i++){
                        if(i==0){
                            for(int k=1;k<N;k++){
                                int newRow = row + k*dRow2[i];
                                int newCol = col + k*dCol2[i];
                                int newRow2 = newRow;
                                int newCol2 = newCol+1;
                                if(1<=newRow && newRow<=N && 2<=newCol && newCol<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow][newCol]){
                                        return false;
                                    }
                                }
                                if(1<=newRow2 && newRow2<=N && 2<=newCol2 && newCol2<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow2][newCol2]){
                                        return false;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                        else if(i==1){
                            for(int k=1;k<N;k++){
                                int newRow = row + k*dRow2[i];
                                int newCol = col + k*dCol2[i];
                                int newRow2 = newRow;
                                int newCol2 = newCol-1;
                                if(1<=newRow && newRow<=N && 2<=newCol && newCol<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow][newCol]){
                                        return false;
                                    }
                                }
                                if(1<=newRow2 && newRow2<=N && 2<=newCol2 && newCol2<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow2][newCol2]){
                                        return false;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                        else{
                            for(int k=1;k<N;k++){

                                int newRow = row + k*dRow2[i];
                                int newCol = col + k*dCol2[i];
                                if(1<=newRow && newRow<=N && 2<=newCol && newCol<=N*2){
                                    //System.out.println("정삼각형 - "+newRow+","+newCol);
                                    if(Rook[newRow][newCol]){
                                        return false;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        static class Point{
            int row;
            int col;

            @Override
            public String toString() {
                return "Point{" +
                        "row=" + row +
                        ", col=" + col +
                        '}';
            }

            public Point(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
        /**/
    }
}
