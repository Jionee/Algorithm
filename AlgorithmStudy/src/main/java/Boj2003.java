import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2003 {
    static int N,M;
    static int[] input;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int n=0 ; n<N ; n++){
            input[n] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(input));

        int low = 0, high = 0, sum = input[0], count = 0;
        //M을 만족하는 것을 구해야 하는데 처음부터 다 하게 되면 시간복잡도 오지게 복잡해짐
        //투포인터를 사용해서 M을 만족하면 start를 증가, 아직 작으면 end를 증가 시켜서 구현해보자
        while (true){
            //System.out.println(low+","+high+","+sum);
            //sum == M -> 답 ,low++
            //sum > M -> low++
            //sum < M -> high++
            if(sum == M){
                count++;
                sum -= input[low++];
            }
            else if(sum > M){
                sum -= input[low++];
            }
            else{
                sum += input[++high];
            }
            if(high == N){
                break;
            }
        }
        System.out.println(count);
    }
}