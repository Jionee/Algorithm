import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//2:41~3:26
public class Boj1253_좋다 {
    static StringTokenizer st;
    static int N;
    static int[] Arr;
    static int Answer;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            Arr[i] = Integer.parseInt(st.nextToken());
        }

        //Arr을 한 번 다 돌면서 다른 수 두 개의 합으로 나타낼 수 있는지 보기
        //같은 수가 여러번 등장할 수 있음, 근데 다른 수로 취급
        //투포인터로 해보자
        Arrays.sort(Arr);
        for(int i=0;i<N;i++){
            Answer += check(Arr[i],i);
        }
        System.out.println(Answer);
    }

    static int check(int target,int index){
        int start = 0, end = N-1;
        while(start<end){
            if(start==index){
                start+=1;
                continue;
            }
            if(end==index){
                end-=1;
                continue;
            }
            int sum = Arr[start] + Arr[end];
            if(sum==target){
                return 1;
            }
            if(sum<target){
                start += 1;
            }
            else if(sum>target){
                end -= 1;
            }
        }
        return 0;
    }
}
