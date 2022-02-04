import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Boj2904_수학은너무쉬워 {
    static int MAX;
    static int N;
    static PriorityQueue<Integer> nums = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o2,o1);
        }
    });
    static boolean[] isPrime;
    static ArrayList<Integer> allPrimes = new ArrayList<>();
    static int[] eachPrimes; //eachPrimes[num] = count
    //static int[][] numPrime; //numPrime[N][1~100000] = count
    static ArrayList<HashMap<Integer,Integer>> numPrimeArrayList = new ArrayList<>(); //numPrimeArrayList.get(N).get(1~10000) = count
    static ArrayList<info> MaxGcd = new ArrayList<>();

    static int maxGcd=1;
    static int answerCount = 0;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            nums.add(Integer.parseInt(st.nextToken()));
        }
        MAX = nums.peek();
        eachPrimes = new int[MAX+1];
        //numPrime = new int[N+1][MAX+1];
//        for(int i=0;i<N+1;i++){
//            numPrimeArrayList.add(new ArrayList<>()); //1~N까지 arrayList초기화
//        }

        //0. 1000000보다 작은 모든 소수들 구하기
        isPrime = new boolean[MAX+1];
        findAllPrimes();

        //1. 각 수들을 소인수분해한다.
        // -> prime(0~1000000) 각 소수들이 몇 회 등장했는지 기록
        // -> numPrime(1~N) 각 숫자에 arrayList형태로 어떤 소수가 몇 회 등장했는지 기록
        for(int i=1;i<N+1;i++){
            int a = nums.poll(); //a % prime == 0 -> 기록 , a = a / prime, prime그대로 다시 사용해야 함
            HashMap<Integer,Integer> hm = new HashMap<>();
            for(int prime:allPrimes){
                //각 소수들로 나눠지면 기록
                while(a % prime == 0){
                    if(a == 1){
                        break;
                    }
                    a /= prime;
                    eachPrimes[prime]++; //각 소수 count 기록
                    //numPrime[i][prime]++; //각 num에 prime count 기록
                    if(hm.containsKey(prime)){
                        hm.put(prime,hm.get(prime)+1);
                    }
                    else{
                        hm.put(prime,1);
                    }
                }
            }
            numPrimeArrayList.add(hm);
        }

        //2. MaxGcd 구하기
        // -> prime.count / num.length 만큼 -> MaxGcd에 저장
        for(int i=1;i<MAX+1;i++){
            if(eachPrimes[i]!=0){
                MaxGcd.add(new info(i,eachPrimes[i]/N)); //이걸 MaxGcd에 저장
                maxGcd = (int) (maxGcd * Math.pow(i,eachPrimes[i]/N));
            }
        }

        //3. 각 numPrime 돌면서 , prime iterate
        // -> 각 numPrime.count > MaxGcd.prime.count
        // -> answer += numPrime.count - prime.count
        for(int i=0; i<MaxGcd.size();i++){
            //각 MaxGcd에 대하여 -> numPrime[i] 비교
            for(int j=1;j<N+1;j++){
               // int[] it = numPrime[j];
                HashMap<Integer,Integer> arrayIt = numPrimeArrayList.get(j-1);
                if(arrayIt.containsKey(MaxGcd.get(i).data)){
                    if(arrayIt.get(MaxGcd.get(i).data) < MaxGcd.get(i).count){
                        answerCount += MaxGcd.get(i).count - arrayIt.get(MaxGcd.get(i).data);
                    }
                }
                else{
                    answerCount += MaxGcd.get(i).count;
                }

//                if(it[MaxGcd.get(i).data] < MaxGcd.get(i).count){
//                    answerCount += MaxGcd.get(i).count - it[MaxGcd.get(i).data];
//                }
            }
        }
        System.out.println(maxGcd+ " " +answerCount);
    }

    private static void findAllPrimes() {
        for(int i=2;i<=MAX;i++){
            if(isPrime[i]){
                continue;
            }
            allPrimes.add(i);
            for(int j=i+i;j<=MAX;j+=i){
                isPrime[j] = true;
            }
        }
    }

    static class info{
        int data;
        int count;

        public info(int data, int count) {
            this.data = data;
            this.count = count;
        }
    }
}
