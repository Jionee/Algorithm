import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1927 {
    static int N;
    static MinHeap minHeap;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        minHeap = new MinHeap();

        for(int n=0;n<N;n++){
            int num = Integer.parseInt(br.readLine());
            if(num > 0){
                minHeap.insert(num);
            }
            else{
                System.out.println(minHeap.delete());
            }
        }
    }
}

class MinHeap{
    List<Integer> list;

    public MinHeap(){
        list = new ArrayList<>();
        list.add(0);
    }
    public void insert(int val){
        //1. leaf 마지막에 삽입
        list.add(val); //arraylist는 알아서 끝에 들어감

        int current = list.size()-1;
        int parent = current / 2;
        //2. 부모와 비교 후 조건에 맞지 않으면 swap
        //3. 조건이 만족되거나 root까지 반복
        while(true){
            if(parent == 0 || list.get(parent) <= list.get(current)){
                break;
            }
            //SWAP
            int tmp = list.get(parent);
            list.set(parent,list.get(current));
            list.set(current,tmp);

            current = parent;
            parent = current / 2;
        }
    }

    public int delete(){
        if(list.size() == 1){
            return 0;
        }
        int top = list.get(1);

        //1. root에 leaf 마지막 데이터 가져옴
        list.set(1,list.get(list.size()-1));
        list.remove(list.size()-1);

        //2. 자식과 비교 후 조건이 맞지 않으면 swap
        //3. 조건이 만족되거나 leaf까지 반복
        int currentPos = 1;
        while(true){
            int leftPos = currentPos * 2;
            int rightPos = currentPos * 2 + 1;
            //왼쪽자식 여부 확인
            if(leftPos >= list.size()){ //왼쪽 자식을 봤는데 size보다 같거나 크면 -> leaf노드임
                break;
            }
            int minValue = list.get(leftPos);
            int minPos = leftPos;

            //오른쪽 자식 여부 확인
            if(rightPos < list.size() && list.get(rightPos) < minValue){
                minValue = list.get(rightPos);
                minPos = rightPos;
            }
            //SWAP
            if(list.get(currentPos) > minValue){
                int tmp = list.get(currentPos);
                list.set(currentPos,list.get(minPos));
                list.set(minPos,tmp);
                currentPos = minPos;
            }
            else{ //조건 만족
                break;
            }
        }
        return top;
    }
}