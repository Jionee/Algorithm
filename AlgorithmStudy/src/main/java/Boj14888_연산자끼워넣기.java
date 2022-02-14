import java.io.*;
import java.util.StringTokenizer;

public class Boj14888_연산자끼워넣기 {
    static int N;
    static int[] num,sign;
    static StringTokenizer st1,st2;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        num = new int[N+1]; //1~N
        sign = new int[5]; //1~4
        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            num[i] = Integer.parseInt(st1.nextToken());
        }
        for(int i=1;i<5;i++){
            sign[i] = Integer.parseInt(st2.nextToken());
        } // + - * / 순

        //앞에서부터 연산을 진행한다.
        //숫자는 고정이고 연산자의 위치만 바뀐다.
        //연산자들의 모든 경우의 수를 계산해야 함
        //calculate(a,b,0); //을 N-1번 반복(연산자 수만큼) //O((N-1)!)
        dfs(num[1],1);
        System.out.println(max);
        System.out.println(min);
        //System.out.println(Integer.MIN_VALUE);
    }
    static void dfs(int now,int index){
        //1.체크인
        //2.목적지인가? -> index >= N 이면 다 온것
        if(index >= N){
            min = Math.min(min,now);
            max = Math.max(max,now);
            return;
        }
        //3.가지치기 -> 남은 부호 애들 진행
        for(int i=1;i<5;i++){
            //4.갈 수 있는가? -> sign 갯수가 1개이상?
            if(sign[i]>0){
                //-> sign[해당] --;
                sign[i]--;
                //5.간다. ->calculate, dfs(결과,index++);
                int result = calculate(now,num[index+1],i);
                dfs(result,index+1);
                //-> sign[해당] ++;
                sign[i]++;
            }
        }
        //6.체크아웃
    }

    static int calculate(int a, int b, int s){
        if(s == 1){ //덧셈
            return a+b;
        }
        if(s == 2){ //뺄셈
            return a - b;
        }
        if(s == 3){ //곱셈
            return a * b;
        }
        if(s == 4){ //나눗셈
            if(a*b<0){ //음수일 경우
                return -(Math.abs(a) / Math.abs(b));
            }
            else{
                return a / b;
            }
        }
        return 0;
    }
}
