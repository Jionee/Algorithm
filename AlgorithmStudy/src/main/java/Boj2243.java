public class Boj {
    public static void main(String[] args) {

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
