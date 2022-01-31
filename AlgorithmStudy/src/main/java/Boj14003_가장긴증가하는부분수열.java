import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj14003_가장긴증가하는부분수열 {
    static int N;
    static int[] arr;
    static int[] maxIdx;
    static int[] prevIdx;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        maxIdx = new int[N+1];
        prevIdx = new int[N+1];
        maxIdx[1] = 1;

        //가장 긴 증가하는 부분 수열을 구하자
        //dp[i] => i번째까지 왔을 때 최장증가수열의 길이
        //제일 큰 값의 인댁스, 그 앞의 값의 인덱스
        for(int i=2;i<N+1;i++){
            int now = arr[i];
            if(now > arr[maxIdx[i-1]]){ //더 크면 갱신
                maxIdx[i] = i;
                prevIdx[i] = maxIdx[i-1];
            }
            else{
                maxIdx[i] = maxIdx[i-1];
                prevIdx[i] = prevIdx[i-1];
            }
        }

//        System.out.println(Arrays.toString(maxIdx));
//        System.out.println(Arrays.toString(prevIdx));

        //거꾸로 가면서 진행
        int start = maxIdx[N];
        int length = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        while(true){
            //length++;
            //parent찾아가기
            length++;
            //System.out.println(arr[start]);
            answer.add(arr[start]);
            start = prevIdx[start];
            //parent==0이면 stop
            if(start == 0){
                break;
            }
        }
        System.out.println(length);
        Collections.reverse(answer);
        for(int a:answer){
            System.out.print(a +" ");
        }
    }
}
