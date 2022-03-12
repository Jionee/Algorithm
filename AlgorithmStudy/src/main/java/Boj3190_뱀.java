import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3190_뱀 {
    static StringTokenizer st;
    static int N,K,L;
    static boolean Apple[][];
    static Queue<Bam> Game = new LinkedList<>();
    static int[] dRow = {-1,1,0,0}; //상하좌우
    static int[] dCol = {0,0,-1,1};
    static Deque<Point> deque = new LinkedList<>();
    
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        Apple = new boolean[N+1][N+1];
        for(int i=1;i<K+1;i++){
            st = new StringTokenizer(br.readLine());
            Apple[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        L = Integer.parseInt(br.readLine());
        for(int i=1;i<L+1;i++){
            st = new StringTokenizer(br.readLine());
            Game.add(new Bam(Integer.parseInt(st.nextToken()),st.nextToken().charAt(0)));
        }
        
        //벽 또는 자기자신의 몸과 부딪히면 게임이 끝남
        //time=0부터 시작해서 ++ -> time==n인 Game을 이동시킴
        //0. 진행방향으로 deque 맨 끝에 머리 추가하기
            //0-1. 진행방향으로 direction바꾸고, (Game의 초 확인하기) 벽이거나, 자기 자신 좌표랑 부딪히면 game끝
        //1. 사과를 만났을 때 -> 그대로
        //2. 사과를 안만났을 때 -> deque 맨 앞 삭제하기

        deque.add(new Point(1,1));
        Bam game = Game.poll();
        dfs(0,3, game);
    }
    static void dfs(int time, int dir, Bam game){ //상하좌우
        //0. 체크인
        int direction = dir;
        if(game !=null){
            if(game.time == time){
                direction = changeDir(game.LorD,dir);
                game = Game.poll();
            }
        }

        int newRow = deque.peekLast().row + dRow[direction];
        int newCol = deque.peekLast().col + dCol[direction];
        if(!(0<newRow && newRow<=N && 0<newCol && newCol<=N)){ //벽에 부딪혔을때
            System.out.println(time+1);
            return;
        }
        deque.addLast(new Point(newRow,newCol)); //맨끝에 머리 추가

        int count=0;
        for (Point point : deque){
            if(newRow==point.row && newCol==point.col){
                count++;
            }
        }
        if(count>=2){ //자기 몸에 부딪혔을때
            System.out.println(time+1);
            return;
        }

        if(!Apple[newRow][newCol]){
            deque.pollFirst(); //맨 앞 삭제
        }
        else{
            Apple[newRow][newCol] = false; //사과먹으면 사과 삭제
        }

        dfs(time+1,direction,game);
    }

    static int changeDir(char LorD, int dir){
        if(LorD=='L'){ //상0 하1 좌2 우3
            switch(dir){
                case 0:
                    return 2;
                case 1:
                    return 3;
                case 2:
                    return 1;
                case 3:
                    return 0;
            }
        }
        else{
            switch(dir){
                case 0:
                    return 3;
                case 1:
                    return 2;
                case 2:
                    return 0;
                case 3:
                    return 1;
            }
        }
        return -1;
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    static class Bam{
        int time;
        char LorD;

        public Bam(int time, char lorD) {
            this.time = time;
            LorD = lorD;
        }
    }
}
