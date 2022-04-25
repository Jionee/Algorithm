import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17144_미세먼지안녕 {
    static StringTokenizer st;
    static int R,C,T;
    static int[][] Map;
    static int[][] Plus;
    static int[][] Minus;
    static int answer;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static int A,B;
    
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        Map = new int[R+1][C+1];
       
        
        for(int i=1;i<R+1;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1;j<C+1;j++) {
        		Map[i][j] =  Integer.parseInt(st.nextToken());
        		if(Map[i][j]==-1) {
        			if(A==0) {
        				A = i;
        			}
        			else {
        				B = i;
        			}
        		} 
        	}
        }
        
        for(int t=0;t<T;t++) { //T초동안
        	 Plus = new int[R+1][C+1];
             Minus = new int[R+1][C+1];
        	//확산
             for(int i=1;i<R+1;i++) {
             	for(int j=1;j<C+1;j++) {
             		if(Map[i][j]!=-1 && Map[i][j]>0) { //공기청정기가 아니고, 미세먼지이면
             			int unit = Map[i][j] / 5;
             			for(int d=0;d<4;d++) { //4방으로 고고
                 			int newRow = i + dRow[d];
                 			int newCol = j + dCol[d];
                 			if(0<newRow && newRow<=R && 0<newCol && newCol<=C) { //범위 검사
                 				if(Map[newRow][newCol]!=-1) { //공기청정기가 아니면
                 					Plus[newRow][newCol] += unit;
                 					Minus[i][j] -= unit;
                 				}
                 			}
                 		}
             		}
             	}
             }
             //한번에 업데이트
             for(int i=1;i<R+1;i++) {
              	for(int j=1;j<C+1;j++) {
              		Map[i][j] += Plus[i][j];
              		Map[i][j] += Minus[i][j];
              	}
             }
            // printMap();

             //공기청정기
//            for(int i=A-2;i>=1;i--) {
//            	Map[i+1][1] = Map[i][1]; //아래로 밀기
//            }
//            for(int j=2;j<=C;j++) {
//            	Map[1][j-1] = Map[1][j]; //왼쪽으로 밀기
//            }
//            for(int i=2;i<=A;i++) {
//            	Map[i-1][C] = Map[i][C]; //위로 밀기
//            }
//            for(int i=C-1;i>=2;i--) {
//            	Map[A][i+1] = Map[A][i]; //오른쪽으로 밀기
//            }
//            Map[A][2] = 0;
            printMap();
            int tmp = Map[A][1];
            for(int i=A-1;i>=1;i--){
                Map[i+1][1] = Map[i][1]; //아래로밀기
            }
            for(int j=2;j<=C;j++){ //왼쪽으로밀기
                Map[1][j-1] = Map[1][j];
            }
            for(int i=2;i<=A;i++){
                Map[i-1][C] = Map[i][C];
            }
            for(int j=C-1;j>=1;j--){
                Map[A][j+1] = Map[A][j];
            }
            Map[A][2] = tmp;

            for(int j=2;j<=C;j++){
                Map[A][j-1] = Map[A][j];
            }
            for(int i=A-1;i>=1;i--){
                Map[i+1][C] = Map[i][C];
            }
            for(int j=C-1;j>=1;j--){
                Map[1][j+1] = Map[1][j];
            }
            for(int i=2;i<=A-1;i++){
                Map[i-1][1] = Map[i][1];
            }
            Map[A][2] = tmp;
            printMap();
            
            for(int i=B+2;i<=R;i++) { //위로밀기
            	Map[i-1][1] = Map[i][1];
            }
            for(int i=2;i<=C;i++) { //왼쪽으로밀기
            	Map[R][i-1] = Map[R][i];
            }
            for(int i=R-1;i>=B;i--) { //아래로밀기
            	//System.out.println(i+","+C);
            	Map[i+1][C] = Map[i][C];
            }
            for(int i=C-1;i>=2;i--) { //오른쪽으로밀기
            	Map[B][i+1] = Map[B][i];
            }
            Map[B][2] = 0;
           
            //printMap();

        }
        for(int i=1;i<R+1;i++) {
        	for(int j=1;j<C+1;j++) {
        		answer += Map[i][j];
        	}
        }
        System.out.println(answer+2);
    }
    static void printMap() {
    	System.out.println("===============");
    	for(int i=1;i<R+1;i++) {
         	for(int j=1;j<C+1;j++) {
         		System.out.print(Map[i][j]+" ");
         	}
         	System.out.println();
         }
    }
}
