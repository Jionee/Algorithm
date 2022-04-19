import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj15685_드래곤커브 {
    static StringTokenizer st;
    static int N;
    static int[][] Map = new int[101][101];
    static int[] dRow = {0,-1,0,1};
    static int[] dCol = {1,0,-1,0};
    static ArrayList<Point> Arr;
    static int answer = 0;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); //시작방향 0123 우상좌하
            int g = Integer.parseInt(st.nextToken()); //세대

            Arr = new ArrayList<>(); //각 드래곤 커브마다 초기화
            Arr.add(new Point(row,col)); //0세대 시작점
            if(0<=row+dRow[d] && row+dRow[d]<=100 && 0<=col+dCol[d] && col+dCol[d]<=100){
                Arr.add(new Point(row+dRow[d],col+dCol[d])); //0세대 끝점
            }

            dragonCurve(g);

            for(Point point:Arr){
                Map[point.row][point.col] = 1;
            }
        }

        //정사각형 판단
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (Map[i][j]==1 && Map[i][j + 1]==1 && Map[i + 1][j]==1 && Map[i + 1][j + 1]==1) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static void dragonCurve(int gen){
        if(gen<=0){
            return;
        }
        int newRow = Arr.get(Arr.size()-1).row;
        int newCol = Arr.get(Arr.size()-1).col;
        for(int i=Arr.size()-1;i>0;i--){
            int newDir = rotateDir(getDir(Arr.get(i),Arr.get(i-1))); //끝점 -> 시작점 방향 => 시계방향 90도 회전
            newRow += dRow[newDir];
            newCol += dCol[newDir];
            if(0<=newRow && newRow<=100 && 0<=newCol && newCol<=100){
                Arr.add(new Point(newRow, newCol));
            }
        }
        dragonCurve(gen-1);
    }

    public static int rotateDir(int dir){ //시계방향 90도 회전
        switch(dir){
            case 0:
                return 3;
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
        }
        return -1;
    }

    public static int getDir(Point end, Point start){ //끝점->시작점 방향 리턴
        int rowDiff = end.row - start.row;
        int colDiff = end.col - start.col;

        if(rowDiff == 0){
            if(colDiff == -1){
                return 0;
            }
            if(colDiff == 1){
                return 2;
            }
        }
        if(colDiff == 0){
            if(rowDiff == -1){
                return 3;
            }
            if(rowDiff == 1){
                return 1;
            }
        }
        return -1;
    }

    public static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
