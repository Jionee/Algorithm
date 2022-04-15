import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj15684_사다리조작 {
    static StringTokenizer st;
    static int N,M,H;
    static ArrayList<Integer>[] Arr;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken()); //가로
        Arr = new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            Arr[i] = new ArrayList<>();
        }

        for(int i=1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Arr[b].add(a);
        }

        for(int i=0;i<Arr.length;i++){ //1~2, 2~3, 3~4범위에 몇 번째 가로에 사다리가 존재하는지
            if(Arr[i].size()%2!=0){ //홀수면 맞춰야 함
                //홀수인 애에 더이상 놓을 수 있는애 없으면 -1, 시스템 종료
                //놓을 수 있는애 있으면 그거 놓고 dfs로 그다음애 진행
                dfs(Arr[i],i,0);
                break;
            }
        }

        if(isRight()){ //맨처음 상태 체크
            System.out.println(0);
            return;
        }
        if(answer==Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }

        //불가능한 경우
    }
    static void dfs(ArrayList<Integer> Range, int col, int count){
        //System.out.println(Range+"," +col+" -> COUNT : "+count);

        if(count <= 3 && col == N){ //3개 안쪽이면서 마지막 애이면 끝
            //System.out.println("들어옴");
            //전체 체크하는거 한 번 더 (1~N까지 실제로 옮겨가면서 되는지 확인)
            if(isRight()){
               // System.out.println("END => COUNT : "+count);
                answer = Math.min(answer,count);
                return;
            }
        }
        if(count > 3 || col >= N){
            //System.out.println("3초과 -1");
            return;
        }

        if(Arr[col].size()%2==0){ //짝수면 그냥 보내기
            //System.out.println("짝 ");
            dfs(Arr[col+1],col+1,count);
            return;
        }

        HashSet<Integer> AllSet = new HashSet<>();
        for(int i=1;i<=H;i++){
            AllSet.add(i);
        }
        HashSet<Integer> RangeSet = new HashSet<>(Range);
        AllSet.removeAll(RangeSet);

        boolean flag = false;
        for(int i:AllSet){ //col~col+1의 i번째 가로에 사다리를 놓을 수 있을까?
            //System.out.println("###"+i +" COL : "+col);
            if(isOk(col,i)){ //접하는 사다리 제거하기
                //System.out.println(col+"~"+(col+1)+"의 "+i+"가로에 두겟음");
                flag = true;
                Arr[col].add(i);
                dfs(Arr[col+1],col+1,count+1);
                Arr[col].remove(Arr[col].size()-1);
            }
        }
        if(!flag){
            //System.out.println("FLAG -1");
            System.out.println(-1);
            System.exit(0);
        }
    }

    static boolean isRight(){
        for(int i=1;i<=N;i++){
            int now = i;

            for(int row = 1;row <= H;row++){
                int front = now-1;
                int back = now;
                if(0<front && front<=N-1){
                    for(int item:Arr[front]){
                        if(item==row){
                            //있으면 거기로 이동
                            now = front;
                            break;
                        }
                    }
                }
                if(0<back && back<=N-1){
                    for(int item:Arr[back]){
                        if(item==row){
                            //있으면 거기로 이동
                            now = back+1;
                            break;
                        }
                    }
                }
            }
            if(now!=i){
                return false;
            }
        }
        return true;
    }

    static boolean isOk(int col, int i){ //col~col+1의 i번째 가로에 사다리를 놓을 수 있을까?
        //col 앞,뒤를 봐서 i가 존재하는지 확인 -> 존재하면 false 존재하지않으면 true
        int front = col-1;
        int back = col;
        if(0<front && front<=N-1){
            for(int item:Arr[front]){
                if(item==i){
                    return false;
                }
            }
        }
        if(0<back && back<=N-1){
            for(int item:Arr[back]){
                if(item==i){
                    return false;
                }
            }
        }
        return true;
    }
}
