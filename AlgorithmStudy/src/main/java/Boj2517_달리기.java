import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj2517_달리기 {
    static int N;
    static int[] indexTree;
    static Player[] player;
    static int treeSize, offset; //leaf노드 바로 전 index
    static int[] answer;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        treeSize = getTreeSize();
        indexTree = new int[treeSize];
        player = new Player[N+1];
        answer = new int[N+1];
        offset = (treeSize >> 1) - 1; //treeSize/2 - 1

        player[0] = new Player(0,0);
        for(int i=1;i<N+1;i++){
            player[i] = new Player(i,Integer.parseInt(br.readLine()));
        }

        Arrays.sort(player); //실력 안좋은 순으로 정렬

        for(int i=1;i<N+1;i++){
            //query(1,index) 진행하면 앞지를 수 있는 수 나옴
            int subValue = query(1,N,1,1,player[i].idx);
            answer[player[i].idx] = player[i].idx - subValue;

            //update 진행
            updateTree(1,N,1, player[i].idx,1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<N+1;i++){
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }

    static int query(int start, int end, int index, int targetStart, int targetEnd){
        if(targetEnd < start || targetStart > end){
            return 0;
        }
        if(targetStart <= start && end <= targetEnd){
            return indexTree[index];
        }
        int mid = (start+end)/2;
        return query(start,mid,index*2,targetStart,targetEnd) + query(mid+1,end,index*2+1,targetStart,targetEnd);
    }

    static void updateTree(int start,int end, int index, int target, int diff){
        //System.out.println(start+" "+end+" "+index+" "+target+" "+diff);

        //범위가 아닐 때
        if(start > target || end < target){
            return;
        }
        indexTree[index] += diff;
        //leaf노드면 리턴
        if(start == end){
            return;
        }
        int mid = (start+end)/2;
        updateTree(start,mid,index*2,target,diff);
        updateTree(mid+1,end,index*2+1,target,diff);
        return;
    }

    static int getTreeSize(){
        int k=1;
        while(k < N){
            k *= 2;
        }
        return k * 2;
    }

    static class Player implements Comparable<Player>{
        int idx;
        int value;

        public Player(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Player o) {
            return Integer.compare(this.value,o.value);
        }

        @Override
        public String toString() {
            return "Player{" +
                    "idx=" + idx +
                    ", value=" + value +
                    '}';
        }
    }

}
