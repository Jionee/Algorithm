import java.io.*;
import java.util.StringTokenizer;

public class Pg_H인덱스 {
    static StringTokenizer st;
    static int N;
    static int[] citations;

    public static int main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int answer = 0;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            citations[i] = Integer.parseInt(st.nextToken());
        }

        //논문 h회이상 인용이 h번 이상 -> h의 최댓값 -> 이분탐색 쌉가능?
        int start = 0;
        int end = 1000;
        while(start<=end){
            int mid = (start+end)/2;
            int count = 0;
            for(int i=0;i<citations.length;i++){
                if(citations[i]>=mid){
                    count++;
                }
            }
            if(count>=mid){
                start = mid + 1;
                answer = Math.max(answer,mid);
            }
            else{
                end = mid - 1;
            }
        }

        return answer;
    }
}
