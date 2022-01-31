import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Boj2579_계단오르기 {
    static int N;
    static int[] stairs;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N+1];

        for(int i=1;i<N+1;i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        //계단 오를건데 1,1,1 로는 못오름 1,2/2,1 가능
        //마지막 계단 꼭 올라야함(딱 맞춰야함)
        
    }
}
