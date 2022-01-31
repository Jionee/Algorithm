import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1806 {
    static int N,S;
    static int num[];
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        num = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int n=0;n<N;n++){
            num[n] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(num));

        //연속된 수들의 부분합구하기 -> 반복적인 작업을 최대한 제거한다.
        //투포인터 사용해서 조건 검사 후 이동하기
        int low = 0, high = 0, sum = num[0], min = N+1;
        while(true){
            //sum>=S -> 더 커 그러면 그 중 가장 작은거 구해야 하는데, min으로 갱신하고 low++
            //sum<S -> high++
            if (sum >= S){
                min = Math.min(min,high-low+1);
                sum -= num[low++];
            }
            else{
                sum += num[++high];
            }
            if (high == N)
                break;
        }

        if (min == N+1)
            System.out.println("0");
        else
            System.out.println(min);
    }
}
