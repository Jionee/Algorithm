import java.io.*;
import java.util.StringTokenizer;
//12:46~

public class Boj2467_용액 {
    static int N;
    static StringTokenizer st;
    static long[] Arr;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Arr = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            Arr[i] = Long.parseLong(st.nextToken());
        }
        //정렬된 순서로 주어졌을 때 0에 가장 가까운 용액
        //start, end 0보다 크면 작게 만들어야 되니까 end++ , 0보다 작으면 크게 만들어야 하니까 start--
        //start<end, 0에 가장 가까운 용액 -> 절댓값해서 값이 가장 작을 때의 값 기록해두기
        int start = 1, end = N;
        int one = 0, two = 0;
        long tmp = Long.MAX_VALUE;

        while(start<end){
            //System.out.println(start+"/"+end);
            long sum = Arr[start] + Arr[end];
            if(sum==0){
                System.out.println(Arr[start] +" "+Arr[end]);
                return;
            }
            if(tmp>Math.abs(sum)){
                tmp = Math.abs(sum);
                one = start;
                two = end;
            }
            if(sum>0){
                end--;
            }
            else if(sum<0){
                start++;
            }
        }
        System.out.println(Arr[one] +" "+Arr[two]);

    }
}
