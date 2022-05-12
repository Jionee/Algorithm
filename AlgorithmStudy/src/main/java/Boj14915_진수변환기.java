import java.io.*;
import java.util.StringTokenizer;

public class Boj14915_진수변환기 {
    static StringTokenizer st;
    static int N,M;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String answer = convert(N,M);
        //System.out.println(answer);

        String N2 = "F8";
        int M2 = 16;
        int answer2 = reverseConvert(N2,M2);
        System.out.println(answer2);
    }
    static public int reverseConvert(String N,int M){
//        if(Integer.parseInt(N)==0){
//            return 0;
//        }
        int result = 0;
        int K = 1;
        for(int i=N.length()-1;i>=0;i--){
            int n = 0;
            if(N.charAt(i)>='A'){
                n = N.charAt(i) - 'A' + 10;
            }
            else{
                n = Integer.parseInt(String.valueOf(N.charAt(i)));
            }
            result += n * K;
            K *= M;
        }
        return result;
    }
    static public String convert(int N, int M){
        if(N==0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int num = N;
        while(N>0){ //몫이 0 미만일 때까지 계속 나머지를 더한다.
            int r = N%M;
            if(r >= 10){
                char tmp = changeAlpha(r);
                sb.append(tmp);
            }
            else{
                sb.append(r);
            }
            N = N/M;
        }
        return sb.reverse().toString();
    }
    static char changeAlpha(int r){
        int idx = r-10;
        return (char) ('A'+idx);
    }
}
