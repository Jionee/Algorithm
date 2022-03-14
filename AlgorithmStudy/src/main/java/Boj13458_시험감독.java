import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Boj13458_시험감독 {
    static StringTokenizer st;
    static int N;
    static double B,C;
    static int[] A;
    static BigInteger answer = new BigInteger("0");

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Double.parseDouble(st.nextToken());
        C = Double.parseDouble(st.nextToken());

        for(int i=1;i<N+1;i++){ //주감독관 1명 배치
            A[i] -= B;
            answer = answer.add(BigInteger.valueOf(1));
        }
        //Greedy
        //B는 한 시험장마다 무조건 존재해야 하고, C는 몇 명이 있어도 상관 없음.
        for(int i=1;i<N+1;i++){
            if(A[i]>0){
                int tmp = (int) Math.ceil(A[i]/C);
                answer = answer.add(BigInteger.valueOf(tmp));
            }
        }

        System.out.println(answer);

    }
}
