public class Pg_파괴되지않은건물 {

    public static void main(String[] args) throws Exception{
        //input
        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        int answer = Solution.solution(board, skill);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static int N,M;
        static int[][] Diff;

        public static int solution(int[][] board, int[][] skill) {
            int answer = 0;
            N = board.length;
            M = board[0].length;
            Diff = new int[N+1][M+1];

            for(int s=0;s<skill.length;s++){
                int type = skill[s][0];
                int r1 = skill[s][1];
                int c1 = skill[s][2];
                int r2 = skill[s][3];
                int c2 = skill[s][4];
                int degree = skill[s][5];

                int diff = 1;
                if(type==1){
                    diff = (-1) * degree;
                }
                else{
                    diff = degree;
                }

                Diff[r1][c1] += diff;
                Diff[r1][c2+1] += diff * (-1);
                Diff[r2+1][c1] += diff * (-1);
                Diff[r2+1][c2+1] += diff;

//                for(int i=r1;i<=r2;i++){
//                    for(int j=c1;j<=c2;j++){
//                        board[i][j] += diff;
//                    }
//                }


                //printMap(board);
            }
            operate();

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(Diff[i][j] + board[i][j]>=1){
                        answer++;
                    }
                }
            }

            return answer;
        }
        static void operate(){
            for (int y = 1; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    Diff[y][x] += Diff[y - 1][x];
                }
            }

            for (int x = 1; x < M; x++) {
                for (int y = 0; y < N; y++) {
                    Diff[y][x] += Diff[y][x - 1];
                }
            }
        }

        /**/
        static void printMap(int[][] Map){
            System.out.println("==========");
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.printf("%3d",Map[i][j]);
                }
                System.out.println();

            }
        }
    }


}
