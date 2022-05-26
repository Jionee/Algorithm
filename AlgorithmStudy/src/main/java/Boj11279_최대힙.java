import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj11279_최대힙 {
    static StringTokenizer st;
    static int N;
    static Maxheap maxheap = new Maxheap();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for(int n=0;n<N;n++){
            int num = Integer.parseInt(br.readLine());
            if(num!=0){ //삽입
                maxheap.insert(num);
                //System.out.println(maxheap.heap);
            }
            else if(num==0){ //삭제
                int delete = maxheap.delete();
                System.out.println(delete);
            }
        }

    }
    static class Maxheap{
        ArrayList<Integer> heap = new ArrayList<>();

        public Maxheap() {
            heap.add(0); //0번째 인덱스는 사용하지 않음
        }

        //맨 끝에 삽입 후 루트까지 비교하기
        public void insert(int num){
            heap.add(num);
            int idx = heap.size()-1;
            if(idx==1){ //루트면 그만
                return;
            }
            //비교 진행
            while(idx!=1 && heap.get(idx/2)<heap.get(idx)){
                int tmp = heap.get(idx/2);
                heap.set(idx/2,heap.get(idx));
                heap.set(idx,tmp);
                idx = idx/2;
            }
        }

        //루트에 삽입 후 아래로 가면서 비교하기
        public int delete(){
            if(heap.size()==1){
                return 0;
            }
            int deleteItem = heap.get(1);
            heap.set(1,heap.get(heap.size()-1));
            heap.remove(heap.size()-1);
            int idx = 1;

            while(idx*2<heap.size()){
                int minChild = heap.get(idx*2);
                int minIdxChild = idx*2;

                if(idx*2+1<heap.size() && minChild<heap.get(idx*2+1)){
                    minChild = heap.get(idx*2+1);
                    minIdxChild = idx*2+1;
                }

                if(heap.get(idx)>minChild){
                    break;
                }

                int tmp = heap.get(idx);
                heap.set(idx,minChild);
                heap.set(minIdxChild,tmp);
                idx = minIdxChild;
            }

            return deleteItem;
        }
    }
}
