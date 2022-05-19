import java.io.*;
import java.util.StringTokenizer;

public class Boj11729_하노이탑이동순서 {
    static StringTokenizer st;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        //N개를 이동시키는 횟수, 수행 과정 출력 (A->B)로 옮긴다
        //N개를 이동시키는 최소 횟수
        sb.append((int)Math.pow(2,N)-1).append('\n');

        Hanoi(N,1,2,3);

        System.out.println(sb.toString());
    }
    //from -> to 로 보내기 위해  n-1개를 from -> mid로 옮긴다.
    public static void Hanoi(int n, int from, int mid, int to){
        //이동할 원반의 갯수가 1이라면 from에서 to로 하나 이동
        if(n==1){
            sb.append(from+" "+to+"\n");
            return;
        }

        //1. from의 n-1개를 mid로 이동
        Hanoi(n-1, from, to, mid);

        //2. 1개를 from에서 to로 이동
        sb.append(from+" "+to+"\n");

        //3. n-1개를 mid에서 to로 이동
        Hanoi(n-1,mid,from,to);
    }
}
