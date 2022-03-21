import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6236_용돈관리 {
    static StringTokenizer st;
    static int N,M;
    static int[] Daily;
    static int Max;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Daily = new int[N+1];
        for(int i=1;i<N+1;i++){
            Daily[i] = Integer.parseInt(br.readLine());
            Max = Math.max(Max,Daily[i]);
        }

        //통장에서 K원을 인출
        //Daily<=K면 그대로 사용, else면 남은금액 K-Daily는 통장에 집어넣고, 다시 K원 인출
        //K의 최소 금액은?
        int start = 1;
        int end = Max * M;

        while(start<=end){
            int mid = (start+end)/2;
            int payresult = possiblePay(mid);
            if(payresult == -1){
                start = mid+1;
            }
            else if(payresult == 0){
                start = mid+1;
            }
            else if(payresult == 1){
                end = mid - 1;
            }
            else if(payresult == 2){
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

    static int possiblePay(int K){
        int nowMoney = 0;
        int nowCount = 0;

        for(int i=1;i<N+1;i++){
            if(K < Daily[i]){
                return -1;
            }
            if(nowMoney < Daily[i]){
                nowCount++;
                nowMoney = K;
                if(nowCount>M){
                    return 0;
                }
            }
            nowMoney -= Daily[i];
        }
        return nowCount == M ? 1 : 2;
    }
}
