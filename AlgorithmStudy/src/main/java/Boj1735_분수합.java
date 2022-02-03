import java.io.*;
import java.util.StringTokenizer;

public class Boj1735_분수합 {
    static int aTop,aBottom, bTop, bBottom;
    static int rTop,rBottom;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        aTop = Integer.parseInt(st.nextToken()); //분자
        aBottom = Integer.parseInt(st.nextToken()); //분모

        st = new StringTokenizer(br.readLine());
        bTop = Integer.parseInt(st.nextToken()); //분자
        bBottom = Integer.parseInt(st.nextToken()); //분모

        //1. 두 분수의 합 구하기
        aTop *= bBottom;
        bTop *= aBottom;
        aBottom *= bBottom;
        bBottom = aBottom;

        rTop = aTop + bTop;
        rBottom = aBottom;

        //2. 결과 값 기약 분수 형태로 변형
        //최대 공약수로 위 아래 나누기
        int gcd = getGcd(rTop,rBottom);
        rTop = rTop / gcd;
        rBottom = rBottom / gcd;

        System.out.println(rTop +" "+rBottom);
    }

    static int getGcd(int a, int b){ //a>=b 인 상황
        if(a<b){
            return getGcd(b,a);
        }
        while(b!=0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
