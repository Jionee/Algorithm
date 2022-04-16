import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj15684_사다리조작 {
    static StringTokenizer st;
    static int N,M,H;
    static ArrayList<Integer>[] Arr;
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken()); //가로
        visit = new boolean[H+1][N]; //방문 배열

        Arr = new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            Arr[i] = new ArrayList<>();
        }

        for(int i=1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            Arr[col].add(row); //col~col+1에 row번째 사다리가 존재한다.
            visit[row][col] = true; //방문처리
        }

        if(isRight()){ //맨처음 상태 체크
            System.out.println(0);
            return;
        }

        for(int i=1;i<=3;i++){ //사다리 i개 설치하는 경우
            newDfs(1,1,0,i);
        }

        if(answer==Integer.MAX_VALUE){ //갱신이 이루어지지 않은 경우는 불가능한 경우
            System.out.println(-1);
        }
    }

    static void newDfs(int C,int R, int count,int num){
        if(count >= num){
            if(isRight()){ //i번째가 i번째에 도달하는지 확인
                answer = Math.min(answer,count);
                System.out.println(answer);
                System.exit(0);
            }
            return;
        }
        if(count>3){
            return;
        }

        for(int col=C;col<N;col++){ //세로
            for(int row=R;row<H+1;row++){ //가로
                if(!visit[row][col]){ //방문체크
                    if(isOk(col,row)){ //놓을 수 있는지 체크(양옆에 사다리 있니?)
                        visit[row][col] = true;
                        Arr[col].add(row);
                        newDfs(col,row,count+1,num);
                        Arr[col].remove(Arr[col].size()-1); //백트래킹
                        visit[row][col] = false;
                    }
                }
            }
            R = 1; //다음은 row가 다시 1부터 시작해야 하므로
        }
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

    static boolean isRight(){ //i가 i로 도착하는지 확인
        for(int i=1;i<=N;i++){
            int now = i;

            for(int row = 1;row <= H;row++){ //가로 한 칸씩 내려가면서 i가 어디로 가있는지 확인하자
                int front = now-1; //왼쪽 사다리 볼거임
                int back = now; //오른쪽 사다리 볼거임
                if(0<front && front<=N-1){ //범위 체크
                    for(int item:Arr[front]){ //front~front+1사이에 사다리가 있을 때 row번째 애인지 체크
                        if(item==row){
                            now = front; //있으면 거기로 이동
                            break;
                        }
                    }
                }
                if(0<back && back<=N-1){
                    for(int item:Arr[back]){
                        if(item==row){
                            now = back+1; //있으면 거기로 이동
                            break;
                        }
                    }
                }
            }
            if(now!=i){ //i가 i로 오지 않았으면 false 리턴
                return false;
            }
        }
        return true; //다 i로 무사히 온 것이므로 true 리턴
    }

    static boolean isOk(int col, int row){ //col~col+1의 i번째 가로에 사다리를 놓을 수 있을까?
        //col 앞,뒤를 봐서 i가 존재하는지 확인 -> 존재하면 false 존재하지않으면 true
        int front = col-1; //col의 앞 사다리
        int back = col+1; //col의 뒷 사다리
        if(0<front && front<=N-1){ //범위체크
            for(int item:Arr[front]){
                if(item==row){
                    return false;
                }
            }
        }
        if(0<back && back<=N-1){
            for(int item:Arr[back]){
                if(item==row){
                    return false;
                }
            }
        }
        return true;
    }
}
