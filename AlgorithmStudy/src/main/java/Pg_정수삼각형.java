import java.util.*;

public class Pg_정수삼각형 {
    static int[][] triangle = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
    static int answer;
    static int N;
    static int[] dCol = {0,1};
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        answer = 0;
        N = triangle.length;
        dp = new int[triangle.length+1][triangle.length+1];
        for(int[] arr:dp){
            Arrays.fill(arr,-1);
        }

        //끝까지 가야하니까 dfs로 푸는 것이군 -> 근데 시간초과 남, 중간에 저장할 수 있는 dp로 풀자

        //answer = TopDown(0,0, triangle);
        answer = BottomUp(triangle);
        System.out.println(answer);
    }
    static int BottomUp(int[][] triangle){
        //root가 아니라 아래에서부터 채워준다.
        for(int i=0;i<N;i++){ //제일 아래 값 채워주기
            dp[N-1][i] = triangle[N-1][i];
        }
        for(int i=N-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                dp[i][j] = Math.max(dp[i+1][j],dp[i+1][j+1]) + triangle[i][j];
            }
        }
        return dp[0][0];
    }

    static int TopDown(int row,int col, int[][] triangle){
        if(row >= N-1){
            dp[row][col] = triangle[row][col]; //마지막 애는 아무것도 안하고 그냥 그 값 리턴해줘야 함
            return dp[row][col];
        }

        if(dp[row][col]!=-1){ //dp저장된값이 있다는 것은 리턴이 이미 완료되었다는 뜻이므로
            return dp[row][col];
        }

        dp[row][col] = 0; //방문했다는 표시
        int ret = 0; //top-down 방식의 dp에서 그 포인트 아래로의 쭈루쭉쭉 연산이 모두 끝났을 때 return값

        for(int i=0;i<2;i++){
            int newRow = row+1;
            int newCol = col + dCol[i];
            if(0<=newCol && newCol<triangle[newRow].length){
                ret = Math.max(ret, TopDown(newRow,newCol,triangle) + triangle[row][col]); //아래로 뻗쳐나가는 가지 중에서 가장 최댓값을 취해야 하므로 max연산을 해줌, 가지값+내꺼랑 비교한당
            }
        }

        return dp[row][col] = ret; //연산이 끝남, 리턴해줌
    }
}