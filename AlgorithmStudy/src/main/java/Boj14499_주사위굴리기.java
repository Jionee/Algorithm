import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;
//22.03.14 16:52~

public class Boj14499_주사위굴리기 {
    static StringTokenizer st;
    static int N,M,K;
    static int row,col;
    static int[][] Map;
    static int[] Dice = new int[7];
    static int[] dRow = {0,0,0,-1,1}; //동서북남
    static int[] dCol = {0,1,-1,0,0};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken()) + 1;
        col = Integer.parseInt(st.nextToken()) + 1;
        K = Integer.parseInt(st.nextToken());

        Map = new int[N+1][M+1];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<M+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //주사위 위치 매 명령마다 바꾸기
        //칸 0 -> 주사위 맨 아래 값(5번) -> 맵에 복사
        //칸 0 아님 -> 5번에 칸 값 복사, 칸은 0으로
        st = new StringTokenizer(br.readLine());
        for(int k=1;k<K+1;k++){
            int dir = Integer.parseInt(st.nextToken());
            row = row + dRow[dir];
            col = col + dCol[dir];
            if(0<row && row <=N && 0<col && col <=M){
                move(dir);
                if(Map[row][col]!=0){
                    Dice[5] = Map[row][col];
                    Map[row][col] = 0;
                }
                else{
                    Map[row][col] = Dice[5];
                }
                System.out.println(Dice[1]);
            }
            else{
                row = row - dRow[dir]; //원상복구
                col = col - dCol[dir];
            }
        }
    }

    static void move(int dir){
        int[] copy = Dice.clone();
        switch(dir){
            case 1: //동
                Dice[1] = copy[4];
                Dice[3] = copy[1];
                Dice[4] = copy[5];
                Dice[5] = copy[3];
                break;
            case 2: //서
                Dice[1] = copy[3];
                Dice[3] = copy[5];
                Dice[4] = copy[1];
                Dice[5] = copy[4];
                break;
            case 3: //북
                Dice[1] = copy[6];
                Dice[2] = copy[1];
                Dice[5] = copy[2];
                Dice[6] = copy[5];
                break;
            case 4: //남
                Dice[1] = copy[2];
                Dice[2] = copy[5];
                Dice[5] = copy[6];
                Dice[6] = copy[1];
                break;
        }
    }
}
