import java.io.*;
import java.util.StringTokenizer;

//8:15~
public class Boj14500_테트로미노 {
    static StringTokenizer st;
    static int N,M;
    static int[][] Map;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new int[N+1+1][M+1+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<M+1;j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;

        //하나를 놓아서 합이 최대가 되도록 해야 함.
        //1,1부터 시작해서 5개를 모두 검사
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                for(int k=0;k<5;k++){
                    answer = Math.max(answer,isInMap(i,j,k));
                    //System.out.println(i+","+j+" K ="+k+" answer="+answer);
                }
            }
        }
        System.out.println(answer);

    }
    static int[] rowZero = {0,0,0,0, 0,1,2,3, 0,0,1,1};
    static int[] colZero = {0,1,2,3, 0,0,0,0, 0,1,0,1};
    static int[] rowOne = {0,0,0, -1,-1,-1,1,1,1};
    static int[] colOne = {0,1,2, 0,1,2,0,1,2};
    static int[] rowTwo = {0,1,2, 0,1,2,0,1,2};
    static int[] colTwo = {0,0,0, -1,-1,-1,1,1,1};
    static int[] rowThree = {0,0, -1,1,-1,1};
    static int[] colThree = {0,1, 0,1,1,0};
    static int[] rowFour = {0,1, 0,1,1,0};
    static int[] colFour = {0,0, -1,1,-1,1};

    static int isInMap(int row,int col, int k){
        switch (k){
            case 0:
                int max = 0;
                int tmp = 0;
                boolean flag = true;
                for(int i=0;i<4;i++){
                    //map에 들어잇는지 먼저 확인, 들어있으면 Map값 더해서 리턴하기, 얘네 셋 중에서 젤 큰거 리턴
                    int r1 = row + rowZero[i];
                    int c1 = col + colZero[i];
                    if(isPossible(r1,c1)){
                        tmp += Map[r1][c1];
                    }
                    else{
                        tmp = 0;
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    max = Math.max(max,tmp);

                tmp = 0;
                flag = true;
                for(int i=4;i<8;i++){
                    //map에 들어잇는지 먼저 확인, 들어있으면 Map값 더해서 리턴하기, 얘네 셋 중에서 젤 큰거 리턴
                    int r1 = row + rowZero[i];
                    int c1 = col + colZero[i];
                    if(isPossible(r1,c1)){
                        tmp += Map[r1][c1];
                    }
                    else{
                        tmp = 0;
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    max = Math.max(max,tmp);

                tmp = 0;
                flag = true;
                for(int i=8;i<12;i++){
                    //map에 들어잇는지 먼저 확인, 들어있으면 Map값 더해서 리턴하기, 얘네 셋 중에서 젤 큰거 리턴
                    int r1 = row + rowZero[i];
                    int c1 = col + colZero[i];
                    if(isPossible(r1,c1)){
                        tmp += Map[r1][c1];
                    }
                    else{
                        tmp = 0;
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    max = Math.max(max,tmp);
                return max;
            case 1:
                int max1 = 0;
                int body = 0;
                for(int i=0;i<3;i++){
                    int r1 = row + rowOne[i];
                    int c1 = col + colOne[i];
                    if (isPossible(r1,c1)){
                        body += Map[r1][c1];
                    }
                    else{
                        return 0;
                    }
                }
                for(int i=3;i<9;i++){
                    int r2 = row + rowOne[i];
                    int c2 = col + colOne[i];
                    if(isPossible(r2,c2)){
                        max1 = Math.max(max1,body+Map[r2][c2]);
                    }
                }
                return max1;
            case 2:
                int max2 = 0;
                int body2 = 0;
                for(int i=0;i<3;i++){
                    int r1 = row + rowTwo[i];
                    int c1 = col + colTwo[i];
                    if (isPossible(r1,c1)){
                        body2 += Map[r1][c1];
                    }
                    else{
                        return 0;
                    }
                }
                for(int i=3;i<9;i++){
                    int r2 = row + rowTwo[i];
                    int c2 = col + colTwo[i];
                    if(isPossible(r2,c2)) {
                        max2 = Math.max(max2, body2 + Map[r2][c2]);
                    }
                }
                return max2;
            case 3:
                int max3 = 0;
                int body3 = 0;
                for(int i=0;i<2;i++){
                    int r1 = row + rowThree[i];
                    int c1 = col + colThree[i];
                    if(isPossible(r1,c1)){
                        body3 += Map[r1][c1];
                    }
                    else{
                        return 0;
                    }
                }
                int tmp3 = body3;
                for(int i=2;i<4;i++){
                    int r2 = row + rowThree[i];
                    int c2 = col + colThree[i];
                    if(isPossible(r2,c2)) {
                        tmp3 += Map[r2][c2];
                    }
                }
                max3 = Math.max(max3,tmp3);
                tmp3 = body3;
                for(int i=4;i<6;i++){
                    int r2 = row + rowThree[i];
                    int c2 = col + colThree[i];
                    if(isPossible(r2,c2)) {
                        tmp3 += Map[r2][c2];
                    }
                }
                max3 = Math.max(max3, tmp3);
                //System.out.println("** "+row+","+col+" MAX3 : "+max3);
                return max3;
            case 4:
                int max4 = 0;
                int body4 = 0;
                for(int i=0;i<2;i++){
                    int r1 = row + rowFour[i];
                    int c1 = col + colFour[i];
                    if(isPossible(r1,c1)){
                        body4 += Map[r1][c1];
                    }
                    else{
                        return 0;
                    }
                }
                int tmp4 = body4;
                for(int i=2;i<4;i++){
                    int r2 = row + rowFour[i];
                    int c2 = col + colFour[i];
                    if(isPossible(r2,c2)) {
                        tmp4 += Map[r2][c2];
                    }
                }
                max4 = Math.max(max4,tmp4);
                tmp4 = body4;
                for(int i=4;i<6;i++){
                    int r2 = row + rowFour[i];
                    int c2 = col + colFour[i];
                    if(isPossible(r2,c2)) {
                        tmp4 += Map[r2][c2];
                    }
                }
                max4 = Math.max(max4, tmp4);
                return max4;
        }
        return -1;
    }

    static boolean isPossible(int row,int col){
        return 0 < row && row <= N && 0 < col && col <= M;
    }
}
