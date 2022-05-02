import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1275_커피숍2 {
    static StringTokenizer st;
    static int N,Q;
    static long[] dataArr;
    static long[] indexTree;
    static int treeSize;


    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dataArr = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            dataArr[i] = Long.parseLong(st.nextToken());
        }

        //0. 인덱스 트리 사이즈 구하기
        treeSize = getTreeSize2(N);
        int offset = (treeSize >> 1) - 1;
        indexTree = new long[treeSize];

        //1. 인덱스 트리 초기화
        init(1,N,1);

        for(int q=0;q<Q;q++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //x~y 합 구하기
            int y = Integer.parseInt(st.nextToken());
            if(x>y){
                int tmp = x;
                x = y;
                y = tmp;
            }
            long answer = query(1,N,x,y,1);
            System.out.println(answer);

            int a = Integer.parseInt(st.nextToken()); //a번째를 b로 바꾸기
            long b = Long.parseLong(st.nextToken());
            long diff = b - dataArr[a];
            update(1,N,1,a,diff);
            dataArr[a] = b;
        }
    }
    static void update(int start, int end, int index, int target, long diff){
        if(target<start || end<target){
            return;
        }
        indexTree[index] += diff;
        if(start==end){
            return;
        }

        int mid = (start+end)/2;
        update(start,mid,index*2,target,diff);
        update(mid+1,end,index*2+1,target,diff);
        return;
    }

    static long query(int start,int end, int targetStart, int targetEnd, int index){
        if(targetStart>end || start > targetEnd){
            return 0;
        }
        if(targetStart <= start && end <= targetEnd){
            return indexTree[index];
        }
        int mid = (start+end)/2;
        return query(start,mid,targetStart,targetEnd,index*2) + query(mid+1,end,targetStart,targetEnd,index*2+1);
    }

    static long init(int start, int end, int index){
        if(start==end){
            indexTree[index] = dataArr[start];
            return indexTree[index];
        }
        int mid = (start+end)/2;
        return indexTree[index] = init(start,mid,index*2) + init(mid+1,end,index*2+1);
    }

    static int getTreeSize(int N){
        int logN = 0;
        int T = 1;
        while(T<N){
            T *= 2;
            logN++;
        }
        return T * 2;
    }

    static int getTreeSize2(int n) {
        int h = (int)Math.ceil(Math.log(n)/Math.log(2));
        return (int) Math.pow(2, h+1);
    }
}
