import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1837_암호제작 {
    static BigInteger P;
    static int K;
    static boolean isPrime[];
    static ArrayList<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        P = new BigInteger(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        isPrime = new boolean[K+1]; //2~K까지
        for(int i=2;i<K;i++){
            if(isPrime[i]){ //소수면 넘어감
                continue;
            }
            if((P.remainder(BigInteger.valueOf(i))).compareTo(BigInteger.valueOf(0))==0){
                System.out.println("BAD "+ i);
                return;
            }
            //배수 삭제
            for(int j = i+i;j<K;j+=i){
                isPrime[j] = true;
            }
        }

        System.out.println("GOOD");

        //1. K보다 작은 수 중 소수를 찾는다.
        //2. 하나라도 P % 해당 소수 == 0 -> BAD
        //3. 다 통과하면 -> GOOD

        //1. k보다 작은 수 중 소수 찾기 (에라토스테네스의 체 이용)
        //2~K중 false인 것 중에 최소인것

    }
}
