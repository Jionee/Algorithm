import java.io.*;
import java.util.*;
//1:39~

public class Boj15961_회전초밥 {
    static int N,D,K,C;
    static StringTokenizer st;
    static int[] Arr;
    static Deque<Integer> eat = new LinkedList<>();
    static int answer = 0;
    static int maxAnswer = 0;
    static int[] ate;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Arr = new int[N+1];
        ate = new int[D+1];
        for(int i=1;i<N+1;i++){
            Arr[i] = Integer.parseInt(br.readLine());
        }
        Arr[0] = Arr[N];

        //먹을 수 있는 초밥 가짓수의 최댓값
        //N개의 초밥 접시에는 D가지의 초밥이 놓여져 있고, 손님은 K개를 연속으로 먹는데 C는 공짜로 준다.
        //1. K개에 C가 포함되어 있는 경우 -> k가지
        //2. K개에 C가 포함되어 있지 않은 경우 -> k+1가지
        //연속된 배열의 값들을 계속해서 비교해 나가는 것이기 때문에 슬라이딩 윈도우를 사용한다.
        //한바퀴돌고 꼬리끼리 연결되는 부분은 어떻게 되는거지?

        for(int i=1;i<K+1;i++){
            if(ate[Arr[i]]==0){
                answer++;
            }
            ate[Arr[i]]++;
        }
        maxAnswer = answer;
        boolean flag = false;
        //하나씩 먹고, 하나씩 뱉으면서 가짓수 세기
        for(int i=K+1;i<N+K;i++){
//            System.out.println((i-K)+"   "+(i%N));
//            System.out.println(Arr[(i%N)] + Arrays.toString(Arr));
            //뱉기
            ate[Arr[i-K]]--;
            if(ate[Arr[i-K]]==0){
                answer--;
            }
            //먹기
            if(ate[Arr[i%N]]==0){
                answer++;
            }
            ate[Arr[i%N]]++;

            if(maxAnswer <= answer){
                if(ate[C] == 0){
                    maxAnswer = answer + 1;
                }
                else{
                    maxAnswer = answer;
                }
            }
        }
        System.out.println(maxAnswer);

    }
}
