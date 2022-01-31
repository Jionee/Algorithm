import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2243 {
    static int N;
    static int[] tree;
    static int S;
    static int max = 1000000;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        S = 1;
        while(S<max){
            S *= 2;
        }
        tree = new int[S*2];

        for(int n=0;n<N;n++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type==1){ //사탕을 꺼내는 경우
                int rank = Integer.parseInt(st.nextToken());
                //IndexedTree Query - rank번째 사탕을 꺼내와야 함
                int query = query(1,S,1,rank);
                System.out.println(query);
                update(1,S,1,query,-1);

            }
            else if(type==2){ //사탕을 넣는 경우
                int taste = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                //IndexedTree update - taste에 diff를 num으로 업데이트
                update(1,S,1,taste,num);
            }
        }
    }

    public static int query(int start, int end, int node, int num){
        if(start==end){
            return start;
        }
        int mid = (start+end)/2;
        //1. 왼쪽 >= num -> query(start,mid,node*2,num);
        int leftValue = tree[node*2];
        if(leftValue>=num){
            return query(start,mid,node*2,num);
        }
        //2. 왼쪽 < num -> 왼쪽에서 나온 숫자만큼 빼서 다음 쿼리 오른쪽으로 실행
        else{
            return query(mid+1,end,node*2+1,num-leftValue);
        }
    }

    public static void update(int start,int end,int node, int target, int diff){
        if(start <= target && target <= end){
            tree[node] += diff;
            //update
            if(start!=end){
                int mid = (start+end)/2;
                update(start,mid,node*2,target,diff);
                update(mid+1,end,node*2+1,target,diff);
            }
        }
    }
}

class IndexedTree{
    int[] tree;
    int[] data;

    public IndexedTree(int[] data){
        this.data = new int[data.length];
        for (int i=0;i<data.length ;i++) {
            this.data[i] = data[i];
        }
        tree = new int[size(data.length)];
    }

    public int size(int dataSize){
        int height = (int)(Math.log(dataSize)) + 1;
        int treeSize = (int)Math.pow(2,height);
        return treeSize;
    }

    public int init(int start,int end,int node){
        if(start==end){
            tree[node] = data[start];
            return tree[node];
        }
        int mid = (start + end) / 2;
        tree[node] = init(start,mid,node*2) + init(mid+1,end,node*2+1);
        return tree[node];
    }

    public long query(int start,int end, int node,int queryStart,int queryEnd){
        //쿼리범위에 아예 포함되지 않을 때
        if(queryEnd < start || end < queryStart){
            return 0;
        }
        //쏙들어갈때
        if(queryStart <= start && end <= queryEnd){
            return tree[node];
        }
        //애매한경우
        int mid = (start+end)/2;
        return query(start,mid,node*2,queryStart,queryEnd) + query(mid+1,end,node*2,queryStart,queryEnd);
    }

    public void update(int start,int end, int node, int target,int diff){
        //쿼리범위에 아예 포함되지 않을 때
        if(target < start || end < target){
            return ;
        }
        //연관있음
        tree[node] += diff;
        if(start != end){
            int mid = (start+end)/2;
            update(start,mid,node*2,target,diff);
            update(mid+1,end,node*2,target,diff);
        }
    }
}
