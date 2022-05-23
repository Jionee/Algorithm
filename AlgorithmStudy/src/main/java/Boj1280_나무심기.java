import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.OptionalInt;

public class Boj1280_나무심기 {
    static int N;
    static int[] indexTree;
    static long[] sumTree;
    static long[] tree;
    static long maxVal = 200000;
    static BigInteger answer = BigInteger.ONE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        tree = new long[N+1];

        for(int i=1;i<N+1;i++) {
            tree[i] = Integer.parseInt(br.readLine());
            //System.out.println("@@"+tree[i]);
        }
        maxVal = Arrays.stream(tree).max().getAsLong();

        int T=1;
        int logN=0;
        while(T<maxVal){
            T*=2;
            logN++;
        }
        indexTree = new int[T*2];
        sumTree = new long[T*2];

        //i에서부터 j까지의 거리를 반복해서 구한다-> 누적합 생각하기
        //+ 특정 인덱스 앞에 총 몇개가 있는지 갯수를 알아야 하므로 indexedTree 생각하기
        //누적합
        //0 X i -> (0~i)*X갯수만큼 - (0~X)들의 합 -> X는 i보다 작은 애들
        //0 i X -> (0~X)들의 합 - (0~i)*X갯수만큼 -> X는 i보다 큰 애들

        for(int i=1;i<N+1;i++){
            long treeX = tree[i];

            if(i==1){
                update(1,maxVal,1,treeX,1);
                continue;
            }

            //그값 곱해나가가기
//            int leftCount = queryCount(1,maxVal,1,1,treeX-1);
//            int rightCount = queryCount(1,maxVal,1,treeX+1,maxVal);
//            long leftSum = querySum(1,maxVal,1,1,treeX-1);
//            long rightSum = querySum(1,maxVal,1,treeX+1,maxVal);

            long result1 = treeX * queryCount(1,maxVal,1,1,treeX-1) - querySum(1,maxVal,1,1,treeX-1);
            long result2 = querySum(1,maxVal,1,treeX,maxVal) - treeX * queryCount(1,maxVal,1,treeX,maxVal);
            long result3 = result1 + result2;
            //System.out.println("leftCount : "+leftCount+" rightCount : "+rightCount+" leftSum : "+leftSum + " rightSum : "+ rightSum+" result1 : "+result1 + " result2 : "+result2 + " result3 : "+result3);
            answer = BigInteger.valueOf(result3).multiply(answer).mod(BigInteger.valueOf(1000000007));
            //System.out.println(answer);
            if(i!=1){
                update(1,maxVal,1,treeX,1);
            }
        }
        System.out.println(answer);
        //System.out.println(Arrays.toString(indexTree));
    }

    static int queryCount(long start, long end, int index, long targetStart, long targetEnd){
        if(targetEnd < start || end < targetStart){
            return 0;
        }
        if(targetStart<=start && end<=targetEnd){
            return indexTree[index];
        }
        long mid = (start+end)/2;
        return queryCount(start,mid,index*2,targetStart,targetEnd) + queryCount(mid+1,end,index*2+1,targetStart,targetEnd);
    }

    static long querySum(long start, long end, int index, long targetStart, long targetEnd){
        if(targetEnd < start || end < targetStart){
            return 0;
        }
        if(targetStart<=start && end<=targetEnd){
            return sumTree[index];
        }
        long mid = (start+end)/2;
        return querySum(start,mid,index*2,targetStart,targetEnd) + querySum(mid+1,end,index*2+1,targetStart,targetEnd);
    }

    static void update(long start,long end, int index, long target, int diff){
        if(target<start || end<target){
            return;
        }
        indexTree[index] += diff;
        sumTree[index] += target;
        if(start == end){
            return;
        }
        long mid = (start+end)/2;
        update(start,mid,index*2,target,diff);
        update(mid+1,end,index*2+1,target,diff);
    }
}
