import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj14003_가장긴증가하는부분수열 {
    static int N;
    static int[] arr;
    static int[] dp; //LIS를 찾기 위한 배열 (실제 LIS와는 다르다. -> indexOrder가 필요)
    static int[] indexOrder; //dp배열에 현재 arr[i]가 몇번째에 저장되어 있는지
    // indexOrder[j] = k -> arr[j]가 dp[k]에 저장되어 있음

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){ //1~N
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N+1];
        indexOrder = new int[N+1];
        dp[1] = arr[1];
        indexOrder[1] = 1;

        int length = 1;
        //가장 긴 증가하는 부분 수열을 구하자
        for(int i=2;i<N+1;i++){ //수열의 수들을 한 번씩 지나가면서 몇 번째에 속하는지를 기록
            //index를 binarySearch를 통해 찾아서 indexOrder에 넣기
            int searchIndex = binarySearch(1,length,arr[i]);
            indexOrder[i] = searchIndex; //dp에서 i번째 수가 몇번째에 존재하는지를 저장
            //length < searchIndex -> 추가로 넣는 것이므로 length 증가,  dp[length]=arr[i];
            if(length < searchIndex){
                length++;
                dp[length] = arr[i];
            }
            //length >= searchIndex -> 바꿔치기, dp[searchIndex] = arr[i];
            else{
                dp[searchIndex] = arr[i];
            }
        }

        int Answer = length;

        int[] Tracking = new int[length+1];
        for(int i=N;i>0;i--){
            if(indexOrder[i]==length){
                Tracking[length] = arr[i];
                length--;
            }
        }

        bw.write(Answer+"\n");
        for (int i=1;i<Answer+1;i++) {
            bw.write(Tracking[i]+" ");
        }

        bw.flush();
        bw.close();
    }

    private static int binarySearch(int s, int e, int target) {
        int start = s;
        int end = e;

        while(start<=end){ //dp배열에서 target이 어디에 위치하는지 리턴하기
            int mid = (start+end)/2;
            if(dp[mid] > target){
                end = mid-1;
            }
            else if(dp[mid] < target){
                start = mid + 1;
            }
            else{
                return mid; //딱 target 찾음
            }
        }
        return start; //target을 못찾았으면 그게 들어가는 범위 리턴 ex) 1,2,6,7 target이 3일때 (1,4) (3,4) -> 3
    }
}
