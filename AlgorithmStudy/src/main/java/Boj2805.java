import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj2805 {
    static int N;
    static Long M;
    static int[] tree;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n=0;n<N;n++){
            tree[n] = Integer.parseInt(st.nextToken());
        }
        //System.out.println(Arrays.toString(tree));

        int answer = 1000000001;
        int start = 0 , end = Arrays.stream(tree).max().getAsInt();
        while(start <= end){ //start == end여야 높이 이분탐색을 끝까지 한거임
            //System.out.println(start+"**"+end);
            //잘린 나무들 합 => M -> min(높이), 높이--
            //잘린 나무들 합 < M -> 높이++
            int mid = (start+end) / 2;
            //잘린 나무들의 합 구하기
            long cut = getCut(mid);
            //System.out.println(mid+"/"+cut);
            if(cut >= M){
                answer = mid;
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static long getCut(int mid) {
        long cut = 0;
        for(int t: tree){
            if(t> mid){
                cut += t- mid;
            }
        }
        return cut;
    }
}
