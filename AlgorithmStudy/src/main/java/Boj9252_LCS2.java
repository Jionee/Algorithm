import java.io.*;
import java.util.Arrays;

public class Boj9252_LCS2 {
    static String A,B;
    static int[][] dp;
    static int[][] arrow;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine();
        B = br.readLine();
        int lengthA = A.length();
        int lengthB = B.length();

        dp = new int[lengthA+1][lengthB+1];
        arrow = new int[lengthA+1][lengthB+1]; //1:위에서 옴, 2:왼쪽에서 옴, 3:대각선

        for(int i=1;i<lengthA+1;i++){
            for(int j=1;j<lengthB+1;j++){
//                dp[i][j-1] //1:왼쪽에서 옴
//                dp[i-1][j] //2:위에서 옴
                if(dp[i-1][j] < dp[i][j-1]){ //왼쪽에서 온게 더 컸으면 -> distance, dp[i][j] 갱신
                    dp[i][j] = dp[i][j-1];
                    arrow[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i-1][j];
                    arrow[i][j] = 2;
                }
                if(A.charAt(i-1) == B.charAt(j-1)){
                    if(dp[i][j] < dp[i-1][j-1] + 1){
                        dp[i][j] = dp[i-1][j-1] + 1;
                        arrow[i][j] = 3;
                    }
                }
            }
        }

        //역추적하기
        StringBuffer LCS = new StringBuffer();
        for(int i=lengthA , j=lengthB; i>0 && j>0 ; ){
            //System.out.println(i+" "+j);
            if(arrow[i][j] == 1){//위로
                j--;
            }
            else if(arrow[i][j] == 2){//왼쪽으로
                i--;
            }
            else{
                LCS.append(A.charAt(i-1));
                //System.out.println(A.charAt(i-1));
                i--;
                j--;
            }
        }

        bw.write(dp[lengthA][lengthB]+"\n");
        bw.write(LCS.reverse()+"\n");
        bw.flush();
        bw.close();
    }
}
