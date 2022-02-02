import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj3020_개똥벌레 {
    static int N,H;
    static int[] bottom,top;
    static final int MAX_BARRIER = 200001;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken()); //1~H
        //N은 항상 짝수이다 -> 뭐다? 반띵해서 나눠서 써보지 않을래??
        bottom = new int[N/2];
        top = new int[N/2];

        //0. Bottom과 Top을 나눠서 생각해보자.
        //1. 각 높이 h 에서 닿는 장애물 갯수는 (제일 처음 닿는 애 ~ 마지막 애) 개수이다.
            //1-1. 처음 닿는 애 index 구하기 -> 전체 갯수 - 처음 닿는 애 index = 닿는 장애물 갯수
            //1-2. 처음 닿는 애는 총 2/N개 중에서 h <= 걔 높이 인 애. -> logN으로 바꾸기 위해 이분탐색 진행 (이분탐색은 정렬된 상태에서 진행)
        //2. 1~H까지 모든 높이에 대해 진행, 최소 높이를 만나면 count 초기화, 증가

        //0. Bottom -> Top -> Bottom -> Top 순으로 진행
        for(int i=0;i<N;i++){
            int size = Integer.parseInt(br.readLine());
            if(i%2==0){ //Bottom
                bottom[i/2] = size;
            }
            else{ //Top
                top[i/2] = size;
            }
        }
        //이분탐색할 것이므로 정렬
        Arrays.sort(bottom);
        Arrays.sort(top);

        int answerBomb = MAX_BARRIER;
        int count = 0;
        //1~2
        for(int i=1;i<H+1;i++){ //모든 높이에 대해서 부딪히는 장애물 갯수 구하기
            //bottom 부딪히는 index, top 맨 처음 부딪히는 Index 구하기
            int bottomFirstIdx = binarySearch(0,bottom.length-1,i,bottom);
            int topFirstIdx = binarySearch(0,top.length-1,H - i + 1,top); //top은 거꾸로 매달려 있으므로

            int bottomBomb = bottom.length - bottomFirstIdx;
            int topBomb = top.length - topFirstIdx;

            int totalBomb = bottomBomb + topBomb;
            if(totalBomb < answerBomb){
                answerBomb = totalBomb;
                count = 1;
            }
            else if(totalBomb == answerBomb){
                count++;
            }
        }
        System.out.println(answerBomb +" "+count);
    }

    static int binarySearch(int start,int end, int h, int[] arr){
        int index = MAX_BARRIER;

        while(start<=end){ //두 포인터가 같아도 되므로 등호를 넣는다.
            int mid = (start+end) / 2;

            if(arr[mid] >= h){ //처음으로 조건을 만족하는 애를 찾아야 함.
                index = Math.min(index,mid);
                end = mid - 1;
            }
            else if(arr[mid] < h){ //장애물높이 < h 이면, 장애물 높이를 더 높여야 하므로 오른쪽으로 이동
                start = mid + 1;
            }
        }
        return start;
    }
}
