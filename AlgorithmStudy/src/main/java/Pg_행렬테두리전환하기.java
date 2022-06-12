import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class Pg_행렬테두리전환하기 {

    public static void main(String[] args) throws Exception{
        //input
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}} ;
        Solution.solution(rows, columns, queries);
    }

    static class Solution {
        static int[][] Map;
        static int Row,Col;

        public static int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];
            Row = rows;
            Col = columns;
            Map = new int[rows+1][columns+1];
            for(int i=1;i<rows+1;i++){
                for(int j=1;j<columns+1;j++){
                    Map[i][j] = (i-1)*columns + j;
                }
            }

            for(int i=0;i<queries.length;i++){
                int[] q = queries[i];
                int min = rotate(q[0],q[1],q[2],q[3]);
                answer[i] = min;
            }
            System.out.println(Arrays.toString(answer));
            return answer;
        }
        static int rotate(int row1,int col1, int row2, int col2){
            int[][] copyMap = new int[Row+1][Col+1];
            for(int i=1;i<Row+1;i++){
                for(int j=1;j<Col+1;j++){
                    copyMap[i][j] = Map[i][j];
                }
            }

            int minValue = Integer.MAX_VALUE;
            //오른쪽 이동
            int haveToGoDown = Map[row1][col2];
            for(int i=col1+1;i<=col2;i++){
                Map[row1][i] = copyMap[row1][i-1];
                minValue = Math.min(minValue,copyMap[row1][i-1]);
            }
            //Map[row1][col1] = -1;

            int haveToGoLeft = Map[row2][col2];
            for(int i=row1+2;i<=row2;i++){
                Map[i][col2] = copyMap[i-1][col2];
                minValue = Math.min(minValue,copyMap[i-1][col2]);

            }
            Map[row1+1][col2] = haveToGoDown;

            int haveToGoUp = Map[row2][col1];
            for(int i=col2-2;i>=col1;i--){
                Map[row2][i] = copyMap[row2][i+1];
                minValue = Math.min(minValue,copyMap[row2][i+1]);

            }
            Map[row2][col2-1] = haveToGoLeft;

            for(int i=row2-2;i>=row1;i--){
                Map[i][col1] = copyMap[i+1][col1];
                minValue = Math.min(minValue,copyMap[i+1][col1]);
            }
            Map[row2-1][col1] = haveToGoUp;

            minValue = Math.min(minValue,haveToGoDown);
            minValue = Math.min(minValue,haveToGoLeft);
            minValue = Math.min(minValue,haveToGoUp);
            return minValue;
//            System.out.println("======");
//
//            for(int[] m:Map){
//                System.out.println(Arrays.toString(m));
//            }

        }

    }
}
