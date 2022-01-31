import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14476 {
    static int N;
    static int[] arr;
    static int[] LR,RL;
    static int maxGcd,subNum;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0;n<N;n++){
            arr[n] = Integer.parseInt(st.nextToken());
        }
        //System.out.println(Arrays.toString(arr));

        LR = new int[N];
        RL = new int[N];
        LR[0] = arr[0];
        RL[arr.length-1] = arr[arr.length-1];
        //LR , RL n번씩 돌면서 최대공약수 찾기
        for(int i=0;i<N-1;i++){
            LR[i+1] = getGcd(LR[i],arr[i+1]);
        }
        for(int i=N-1;i>0;i--){
            RL[i-1] = getGcd(RL[i],arr[i-1]);
        }
        //System.out.println(Arrays.toString(LR));
        //System.out.println(Arrays.toString(RL));

        for(int i=0;i<N;i++){
            int gcd;
            //1. index 앞뒤로 LR,RL의 최대공약수 구하기
            if(i==0){
                gcd = RL[1];
            }
            else if(i==N-1){
                gcd = LR[N-1];
            }
            else{
                gcd = getGcd(LR[i-1],RL[i+1]);
            }
            //2. 구한거랑 arr[i]가 약수인지 확인 -> 최대공약수가 맞으면 max갱신
            if(!(gcd%arr[i] == 0 || arr[i]%gcd==0)){
                if(maxGcd < gcd){
                    maxGcd = gcd;
                    subNum = arr[i];
                }
            }
        }

        if(subNum == 0){
            System.out.println("-1");
        }
        else{
            System.out.println(maxGcd + " "+subNum);
        }
    }

    static int getGcd(int a, int b){
        int r = a % b;
        while(r!=0){
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
    static int getGcd2(int a,int b){
        while(b!=0){
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}
