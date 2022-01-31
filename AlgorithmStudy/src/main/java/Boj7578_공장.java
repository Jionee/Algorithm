import com.sun.source.tree.Tree;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj7578_공장 {
    static int N;
    static int[] A;
    static HashMap<Integer,Integer> B;
    static long[] indexTree;
    static int TreeSize, Offset; //Offset = leaf노드 시작하기 바로 전
    static long Answer; //Tree 및 Answer의 경우 Integer의 범위를 넘을 수 있음을 고려하자.

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        B = new HashMap<>();
        Answer = 0;

        //IndexTree 초기화
        TreeSize = getTreeSize();
        Offset = (TreeSize >> 1) - 1; //리프노드 시작 바로 직전 점
        indexTree = new long[TreeSize];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            A[i] = Integer.parseInt(st1.nextToken());
            B.put(Integer.parseInt(st2.nextToken()),i);
        }

        //교차점을 찾으려면 for(int i: 1~N) for(int j:i+1~N) 만큼을 돌아야 한다. -> O(logN)
        //그러므로 두번째 for문을 O(NlogN)인 인덱스 트리로 변경해보자
        //B를 HashMap을 사용해서 구현하자
        int index;
        for(int i=1;i<N+1;i++){
            index = B.get(A[i]); //B에 존재하는 애의 Index
            Answer += getSum(1,N,1,index+1,N);
            //System.out.println("* "+Answer);
            //System.out.println(Arrays.toString(indexTree));
            updateTree(1,N,1,index,1);
        }
        System.out.println(Answer);
    }

    private static long getSum(int start, int end, int index, int targetStart, int targetEnd) {
        if(end < targetStart || targetEnd < start){
            return 0;
        }
        if(targetStart<= start && end <= targetEnd){
            return indexTree[index];
        }
        int mid = (start+end)/2;
        return getSum(start,mid,index*2,targetStart,targetEnd) + getSum(mid+1,end,index*2+1,targetStart,targetEnd);
    }

    private static void updateTree(int start, int end, int index, int updateIndex, int diff){
        if(updateIndex<start || end<updateIndex){
            return;
        }
        indexTree[index] += diff; //범위에 해당하므로 업데이트
        if(start==end){ //leaf도달 시 멈추기
            return;
        }
        int mid = (start+end)/2;
        updateTree(start,mid,index*2,updateIndex,diff);
        updateTree(mid+1,end,index*2+1,updateIndex,diff);
        return;
    }

    public static int getTreeSize(){
        TreeSize = 1;
        while(TreeSize < N){
            TreeSize *= 2;
        }
        return TreeSize * 2; //앞에만큼 뒤에도 있어야 저장함
    }
}
