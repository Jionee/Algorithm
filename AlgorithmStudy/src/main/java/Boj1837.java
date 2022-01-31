import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1837 {
    static int MAX = 1000000;
    static String P;
    static int K;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        //1. K보다 작은 수 중 소수를 찾는다.
        //2. 하나라도 P % 해당 소수 == 0 -> BAD
        //3. 다 통과하면 -> GOOD

        //1. k보다 작은 수 중 소수 찾기
        ArrayList<Integer> Primes = new ArrayList<>();
        boolean[] isNotPrime = new boolean[MAX+1];
        for(int i = 2; i < MAX + 1; i++){
            if(isNotPrime[i] == false){
                Primes.add(i);
                //System.out.println(Arrays.toString(isNotPrime));
                for(int j = i *2; j< MAX + 1;j+=i){
                    isNotPrime[j] = true;
                }
            }
        }

        for (Integer prime : Primes) {
            if(prime >= K){
                break;
            }
            //System.out.println(prime);
            //P % 소수 == 0 -> BAD, 소수 -> break;
            //앞에서부터 한자리씩 가져와서 %연산 후 마지막 값이 0인지 확인
            String A = "";
            int mod = 0;
            for(int i=0;i<P.length();i++){
                A += Character.toString(P.charAt(i));
                mod = Integer.parseInt(A)% prime;
                if(i>0){
                    A = Character.toString(A.charAt(1));
                }
                System.out.println(A+"/"+prime+"="+mod);
            }
            if(mod == 0){
                System.out.println("BAD "+prime);
                return;
            }
        }

//        for(int prime:Primes){
//            if (prime >= K){
//                break;
//            }
//            if(checkIsBad(prime)){
//                System.out.println("BAD" + prime);
//                return;
//            }
//        }
        //이외의 경우는 GOOD
        System.out.println("GOOD");
    }
    static boolean checkIsBad(int x){
        int r = 0;
        for (int i=0;i<P.length();i++){
            r = (r*10 +(P.charAt(i) - '0')) % x;
        }
        if(r==0){
            return true;
        }
        else{
            return false;
        }
    }
}
