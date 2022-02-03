import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2725_보이는점의개수 {
    static int C,N;
    static int[] tc;
    static PriorityQueue<Integer> testCase = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o2,o1);
        }
    });
    static int[] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());
        tc = new int[C];
        for(int i=0;i<C;i++){
            int a = Integer.parseInt(br.readLine());
            testCase.add(a);
            tc[i] = a;
        }
        int max = testCase.peek();
        dp = new int[max+1];

        dp[0] = 2;
        dp[1] = 3;
        for(int son=2;son<=max;son++){ //1,2,3,...max까지
            int count = 0;
            for(int j=1;j<=son-1;j++){ //1,2,3...son-1까지
                if(son < j){
                    continue;
                }
                if(getGcd(son,j) == 1){ //기약분수 == 둘의 최대 공약수가 1이다.
                    count++;
                }
            }
            dp[son] = dp[son-1] + count * 2;
        }
        //System.out.println(Arrays.toString(dp));

        for(int t:tc){
            System.out.println(dp[t]);
        }
    }
    static int getGcd(int a, int b){
        while(b!=0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
