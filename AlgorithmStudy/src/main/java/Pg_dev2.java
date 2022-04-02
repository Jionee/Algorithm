import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pg_dev2 {

    public static void main(String[] args) throws Exception{
        //input
        String[] grid = {"aa?"};
        Solution.solution(grid);
    }

    static class Solution {
        static char[][] Map;
        static ArrayList<Point> question = new ArrayList<>();
        static char[] Alpha = {'a','b','c'};
        static int answer;
        static boolean visit[][];
        static int N,M;
        static int[] dRow = {-1,1,0,0};
        static int[] dCol = {0,0,-1,1};

        public static int solution(String[] grid) {
            answer = 0;
            Map = new char [grid.length][grid[0].length()];
            visit = new boolean [grid.length][grid[0].length()];
            N = grid.length;
            M = grid[0].length();

            for(int i=0;i< grid.length;i++){
                for(int j=0;j<grid[0].length();j++){
                    Map[i][j] = grid[i].charAt(j);
                    if(Map[i][j] == '?'){
                        question.add(new Point(i,j));
                    }
                }
            }

            //물음표 위치를 돌면서 가능한 모든 경우의 수를 찾을 것임
            dfs(0);
            System.out.println(answer);
            return answer;
        }

        static void dfs(int count){
            if(count>= question.size()){
                Set<Character> usedChar = new HashSet<>();

                System.out.println("========");
                for(int i=0;i< Map.length;i++){
                    System.out.println(Arrays.toString(Map[i]));
                }
                int treeCount = 0;
                visit = new boolean [N][M];

                for(int i=0;i<N;i++){
                    for(int j=0;j<M;j++){
                        if(!visit[i][j]){
                            if(treeCount>=4){
                                return;
                            }
                            treeCount++;
                            usedChar.add(Map[i][j]);
                            System.out.println(treeCount+"->"+Map[i][j]+" "+i+","+j);
                            check(Map[i][j],i,j);
                        }
                    }
                }
                if(treeCount == usedChar.size()){
                    answer++;
                }
                return;
            }
            int row = question.get(count).row;
            int col = question.get(count).col;
            for(int i=0;i<3;i++){
                Map[row][col] = Alpha[i];
                dfs(count+1);
                Map[row][col] = ' ';
            }
        }
        static void check(int value,int row, int col){
            //단절점 체크
            for(int i=0;i<4;i++){
                int newRow = row+dRow[i];
                int newCol = col+dCol[i];
                if(0<=newRow && newRow<N && 0<=newCol && newCol <M){
                    if(!visit[newRow][newCol]){
                        if(value == Map[newRow][newCol]){
                            visit[newRow][newCol] = true;
                            check(Map[newRow][newCol], newRow,newCol);
                        }
                    }
                }
            }
        }

        public static class Point{
            int row;
            int col;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
    }
}
