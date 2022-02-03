import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class Boj3955_캔디분배 {
    static int T;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Integer.parseInt(st.nextToken());
            long B = Integer.parseInt(st.nextToken());
            //X : 인당 나눠줄 사탕의 수
            //Y : 사탕 봉지의 수(??)
            //A * x + 1 = B * Y
            //AX +By = C의 형태로 변환
            //-Ax + By = 1
            long C = 1;
            //A(-x) + By = 1의 형태로 변환 //A=문제K,B=문제C

            //1. 해 검증
            //D = gcd(A,B) = egcd(A,B).r
            //Ax + By = C 일때 C % D==0이어야 해를 가질 수 있음 : 배주 항등식
            //C % D !=0 -> 해가 없음
            EGResult result = extendedGcd(A,B);
            long D = result.r;
            if(1 % result.r != 0){ //해를 가질 수 있음
                System.out.println("IMPOSSIBLE");
                continue;
            }
            else{
                //2. 초기 해 구하기
                //x0 = s * C / D
                //y0 = t * C / D
                long x0 = result.s * C / D;
                long y0 = result.t * C / D;

                //3. 일반해 구하기
                //x = x0 + B/D * k
                //y = y0 - A/D * k
                //(4번을 유도해내지 못했을 때) while <-k-> 이동시키면서 찾아도 됨

                //4. k의 범위
                //x < 0
                //x0 + B * k < 0
                //k < -x0 / B

                //0 < y <= 1e9
                //0 < y0 - A * k <= 1e9
                //-y0 < -A * k <= 1e9 -y0
                //(y0 - 1e9) / A <= k < y0 / A

                //(y0 - 1e9) / A <= k < y0 / A
                //                  k < -x0 / B

                //< -> ceil
                //<= -> floor
                //등호가 없기 때문에 천장함수를 이용해서 ceil 후에 -1을 한다.
                long kFromY = (long) Math.ceil((double)y0/(double)A) - 1; //double로 나누어야 소숫점이 안날아감
                long kFromX = (long) Math.ceil((double)-x0/(double)B) - 1; //double로 나누어야 소숫점이 안날아감
                //둘을 만족하는 K = 둘을 만족하는 값 중 최소값
                long k = Math.min(kFromY,kFromX);

                long kLimitFromY = (long) Math.ceil((double)(y0 - 1e9) / (double) A);
                //5. 만족하는 K가 있는지 찾고 k를 통해 y를 구한다.
                if(kLimitFromY <= k){
                    //조건을 만족하는 Y값 찾기
                    //아무거나 하나만 출력하면 되므로 k로 했음 (kLimitFromY로 해도 됨)
                    System.out.println(y0 - A * k);
                }
                else{
                    System.out.println("IMPOSSIBLE");
                }
            }
        }
    }

    static EGResult extendedGcd(long a,long b){
        //확장 유클리드 호제법
        //init
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long tmp; //swap
        while(r1!=0){
            long q = r0 / r1; // 몫
            tmp = r0 - q * r1;
            r0 = r1;
            r1 = tmp;

            tmp = s0 - q * s1;
            s0 = s1;
            s1 = tmp;

            tmp = t0 - q * t1;
            t0 = t1;
            t1 = tmp;
        }
        return new EGResult(s0,t0,r0);
    }

    static class EGResult{
        long s;
        long t;
        long r;

        public EGResult(long s, long t, long r) {
            this.s = s;
            this.t = t;
            this.r = r;
        }

        @Override
        public String toString() {
            return "EGResult{" +
                    "s=" + s +
                    ", t=" + t +
                    ", r=" + r +
                    '}';
        }
    }
}


