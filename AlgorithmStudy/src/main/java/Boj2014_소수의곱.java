import java.io.*;
import java.util.*;

public class Boj2014_소수의곱 {
    static int K,N;
    static long[] num;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); //1~100개 소수
        N = Integer.parseInt(st.nextToken()); //1~10000번째 수 구하기
        num = new long[K];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            num[i] = Long.parseLong(st.nextToken());
            pq.add(num[i]);
        }

        int count = 1;
        long now = 0;
        while(count < N+1){ //중단조건 : count == N+1일 때 종료
            count++;
            now = pq.poll();
            for(long it:num){
                //if(!pq.contains(now * it)) //contains는 O(N)의 시간 복잡도를 갖는다.
                pq.add(now * it);

                if(now % it == 0){ //중복된 수 제거하기
                    // (now>=it 인 상황에서 now가 it의 배수이면 거기까지만 연산을 진행한다.)
                    //System.out.println("now = "+now+" it = "+it + " multi = "+ now*it);
                    break;
                }

            }
        }

        System.out.println(now);

    }
}
