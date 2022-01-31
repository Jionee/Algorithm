import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1256 {
    static int N,M,K;
    static int KMax = 1000000000;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //파스칼삼각형 구성하기
        int[][] pasKal = new int[N+M+1][N+M+1];
        pasKal[0][0]=1;
        for(int i=1;i<N+M+1;i++){
            for(int k=0;k<N+M+1;k++){
                if(k == 0){
                    pasKal[i][k] = 1;
                }
                else if(k==i){
                    pasKal[i][k] = 1;
                    continue;
                }
                else{
                    if(pasKal[i-1][k-1]+pasKal[i-1][k] > 1e9){ //Query(K)중 K 이상인 값은 어차피 왼쪽으로 이동하므로 K로 초기화하기
                        pasKal[i][k] = (int) 1e9;
                    }else{
                        pasKal[i][k] = pasKal[i-1][k-1]+pasKal[i-1][k];
                    }
                }
            }
        }
//        for(int[] pa:pasKal){
//            System.out.println(Arrays.toString(pa));
//        }
        //a가 N개, b가 M개일 때 구성될 수 있는 문자열 중
        //K번째 문자열이 무엇인지 구하기
        //Query(K)
        //for문으로(i) N+M번 돌 건데, 한 번 돌 때마다 z를 기준으로 생각하기
        //맨 앞을 a로 생각했을 때
        // N+M-i C a남은 개수-1 >= K -> 왼쪽으로
        //Query(K) 그대로
        //맨 앞을 z로 생각했을 때
        // N+M-i C a남은 개수-1 < K -> 오른쪽으로
        //Query(K-(N+M-i C a남은개수-1))
        int aNum = N;
        int bNum = M;
        String answer = "";
        for(int i=0;i<N+M;i++){
            int result = 0;
            if(aNum>0){ //a가 남아있는 경우
                result = pasKal[N+M-i-1][aNum-1];
            }
            else{ //a를 다 쓴 경우 z만 남아있음
                result = pasKal[N+M-i-1][bNum-1];
            }
            if(result < K && i==0){
                System.out.println("-1");
                break;
            }

                //System.out.println("==="+(N+M-i-1)+" C "+(aNum-1) + "=result : "+result +"/K = "+K);
            // N+M-i C a남은 개수-1 >= K -> 왼쪽으로
            if(result >= K && aNum>0){
                answer += "a";
                aNum --;
                //System.out.println("aNum = "+aNum + " answer = "+answer);
            }
            // N+M-i C a남은 개수-1 < K -> 오른쪽으로
            else{
                answer += "z";
                //System.out.println("K = "+K+ " answer = "+answer);

                K = K- result;
            }
        }
        System.out.println(answer);
    }
}
