import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17822_원판돌리기 {
    static StringTokenizer st;
    static int N,M,T;
    static int[][] Map;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        Map = new int[N+1][M+1];
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<M+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for(int n=1;n<N+1;n++){
                if(n%x==0){ //x의배수이면
                    rotate(n,d,k);
                }
            }
            remove();
        }

        int total = 0;
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++) {
                total += Map[i][j];
            }
        }
        System.out.println(total);

    }
    static void printMap(){
        System.out.println("======");
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++) {
                System.out.print(Map[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void remove(){
        boolean flag = false;
        int[][] Copy = new int[N+1][M+1];
        for(int[] c:Copy){
            Arrays.fill(c,-1);
        }

        int total = 0;
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                total += Map[i][j];
                if(i==1){
                    if(Map[i][j]==Map[2][j] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[2][j] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0){
                            Copy[i][j] = Map[i][j];
                        }
                    }
                }
                else if(i==N){
                    if(Map[i][j]==Map[N-1][j] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[N-1][j] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                }
                else{
                    if(Map[i][j]==Map[i-1][j] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i-1][j] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                    if(Map[i][j]==Map[i+1][j] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i+1][j] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                }
                //
                if(j==1){
                    if(Map[i][j]==Map[i][2] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i][2] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                    if(Map[i][j]==Map[i][M] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i][M] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                }
                else if(j==M){
                    if(Map[i][j]==Map[i][M-1] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i][M-1] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                    if(Map[i][j]==Map[i][1] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i][1] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                }
                else{
                    if(Map[i][j]==Map[i][j-1] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i][j-1] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                    if(Map[i][j]==Map[i][j+1] && Map[i][j]!=0){
                        flag = true;
                        Copy[i][j] = 0;
                        Copy[i][j+1] = 0;
                    }
                    else{
                        if(Copy[i][j]!=0) {
                            Copy[i][j] = Map[i][j];
                        }
                    }
                }
            }
        }

        if (flag) { //같은 수가 하나라도 있으면
            for(int i=1;i<N+1;i++){
                for(int j=1;j<M+1;j++){
                    Map[i][j] = Copy[i][j];
                }
            }
        }
        else{ //같은 수가 없으면 평균 구해서 갱신
            int count = 0;
            for(int i=1;i<N+1;i++){
                for(int j=1;j<M+1;j++){
                    if(Map[i][j]>0){
                        count++;
                    }
                }
            }
            for(int i=1;i<N+1;i++){
                for(int j=1;j<M+1;j++){
                    if(Map[i][j]!=0){
                        if(Map[i][j]>(double)total/count){ //double로 평균 구하기
                            Map[i][j]-=1;
                        }
                        else if(Map[i][j]<(double)total/count){
                            Map[i][j]+=1;
                        }
                    }

                }
            }
        }
    }

    static void rotate(int n,int d,int K){
        if(d==0){ //시계방향
            for(int k=0;k<K;k++){
                int tmp = Map[n][M];
                for(int j=M;j>=2;j--){
                    Map[n][j] = Map[n][j-1];
                }
                Map[n][1] = tmp;
            }
        }
        else if(d==1){ //반시계방향
            for(int k=0;k<K;k++){
                int tmp = Map[n][1];
                for(int j=1;j<M;j++){
                    Map[n][j] = Map[n][j+1];
                }
                Map[n][M] = tmp;
            }
        }
    }
}
