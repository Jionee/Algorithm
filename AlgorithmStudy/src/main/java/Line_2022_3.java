import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Line_2022_3 {
    static StringTokenizer st;
    static int[] arr = {3, 7, 1, 2, 4};
    static int[] brr = {4, 4,2,3,4};
    static int answer;
    static int N;
    public static void main(String[] args) throws Exception{

        answer = 0;
        N = arr.length;
        //양끝부터 맞추면서  start<end 일때까지 수행
        dfs(0,N-1);
        System.out.println(answer);
    }
    static void dfs(int start,int end){
        if(start>=end){
            return;
        }
        //System.out.println("@@@"+start+" "+end + Arrays.toString(arr));

        if(arr[start]!=brr[start]){
            int diff = arr[start] - brr[start];
            arr[start] = arr[start] + (-diff);
            arr[start+1] = arr[start+1] + diff;
            //System.out.println(start+" "+end + Arrays.toString(arr));
            answer++;
        }
        if(arr[end]!=brr[end]){
            int diff = arr[end] - brr[end];
            arr[end] = arr[end] + (-diff);
            arr[end-1] = arr[end-1] + diff;
            //System.out.println(start+" "+end + Arrays.toString(arr));

            answer++;
        }
        start++;
        end--;
        dfs(start,end);
    }
}
