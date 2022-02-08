import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2342_DanceDanceRevolution {
    static int[] question;
    static int[][][] dp; //dp[i][j][k] = i번째 질의를 해결했을 때, 왼발은 j 오른발은 k에 있을 때 최소 비용
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        question = new int[100000+1];
        int index=1;
        while(st.hasMoreTokens()){
            int q = Integer.parseInt(st.nextToken());
            if(q==0) break;
            question[index++] = q;
        }
        index--;
        //System.out.println(Arrays.toString(question)+" "+ index);

        //dp는 초기화가 중요하다.
        dp = new int[index+1][5][5]; //총 질문 4개 -> 1~4만 사용
        for(int i=1;i<index+1;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    dp[i][j][k] = INF; //최소비용을 구할 것이므로 최대로 초기화
                }
            }
        }
        dp[1][question[1]][0] = 2; //처음 왼발로 움직였을 때
        dp[1][0][question[1]] = 2; //처음 오른발로 움직였을 때

        for(int i=2;i<index+1;i++){ //질의 //2~4 질의
            for(int j=0;j<5;j++){ //왼발
                for(int k=0;k<5;k++){ //오른발
                    if(dp[i-1][j][k]!=INF){
                        //question[i]로 이동해야 함
                        //left
                        if(k != question[i]){ //오른발이 이미 거기있으면 안됨
                            dp[i][question[i]][k] = Math.min(dp[i][question[i]][k], dp[i-1][j][k] + getScore(j,question[i]));
                        }
                        //right
                        if(j != question[i]){
                            dp[i][j][question[i]] = Math.min(dp[i][j][question[i]] ,dp[i-1][j][k] + getScore(k,question[i]));
                        }
                    }
                }
            }
        }

        int answer = INF;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                answer = Math.min(answer,dp[index][i][j]);
            }
        }
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }

    private static int getScore(int a, int b){
        if(a==0) return 2;
        if(a==b) return 1;
        if(Math.abs(a-b)==2) return 4;
        return 3;
    }
}