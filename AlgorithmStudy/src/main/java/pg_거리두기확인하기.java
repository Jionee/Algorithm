import java.util.*;

class Solution {
    static int[] dFirstRow = {-1,1,0,0};
    static int[] dFirstCol = {0,0,-1,1};
    static int[] dSecondRow = {-1,-1,1,1};
    static int[] dSecondCol = {-1,1,-1,1};
    static int[] dThirdRow = {-2,2,0,0};
    static int[] dThirdCol = {0,0,-2,2};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int p=0;p<5;p++){
            char[][] Map = new char[5][5];
            String[] placeStr = places[p];

            for(int r=0;r<5;r++){
                for(int q=0;q<5;q++){
                    Map[r][q] = placeStr[r].charAt(q);
                }
            }
            // System.out.println("=======");
            // for(char[] M:Map){
            //     System.out.println(Arrays.toString(M));
            // }

            boolean flag = false;
            //총 12방 살필것임
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(Map[i][j] == 'P'){
                        //first
                    for(int f=0;f<4;f++){
                        int newRow = i + dFirstRow[f];
                        int newCol = j + dFirstCol[f];
                        if(isRange(newRow,newCol)){
                            if(Map[newRow][newCol]=='P'){
                                //System.out.println("FIRST : "+i+" "+j+" / NEW "+newRow+" "+newCol);
                                flag = true;
                                break;
                            }
                        }
                    }
                    if(flag){
                       break; 
                    }
                    //second
                    for(int f=0;f<4;f++){
                        int newRow = i + dSecondRow[f];
                        int newCol = j + dSecondCol[f];
                        if(isRange(newRow,newCol)){
                            if(Map[newRow][newCol]=='P'){
                                if(!isSquare(i,j,newRow,newCol,Map)){
                                    //System.out.println("SECOND : "+i+" "+j+" / NEW "+newRow+" "+newCol);

                                    flag = true;
                                    break;
                                }   
                            }
                        }
                    }
                    if(flag){
                       break; 
                    }
                    //third
                    for(int f=0;f<4;f++){
                        int newRow = i + dThirdRow[f];
                        int newCol = j + dThirdCol[f];
                        if(isRange(newRow,newCol)){
                            if(Map[newRow][newCol]=='P'){
                                if(!isLine(i,j,newRow,newCol,Map)){
                                    //System.out.println("THIRD : "+i+" "+j+" / NEW "+newRow+" "+newCol);

                                    flag = true;
                                    break;
                                }   
                            }
                        }
                    }
                    if(flag){
                       break; 
                    }
                }

                }
            }
            if(flag){
                answer[p] = 0;
            }
            else{
                answer[p] = 1;
            }
        }

        return answer;
    }

    static boolean isLine(int row, int col, int newRow, int newCol, char[][] Map){
        if(row==newRow){
            if(col<newCol){
                if(Map[row][col+1]=='X'){
                    return true;
                }
            }
            else{
                if(Map[row][newCol+1]=='X'){
                    return true;
                }
            }
        }   
        else if(col==newCol){
            if(row<newRow){
                if(Map[row+1][col]=='X'){
                    return true;
                }
            }
            else{
                if(Map[newRow+1][col]=='X'){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isSquare(int row, int col, int newRow, int newCol, char[][] Map){
        if(row>newRow){
            int tmp = row;
            row = newRow;
            newRow = tmp;
        }
        if(col>newCol){
            int tmp = col;
            col = newCol;
            newCol = tmp;
        }

        int num = 0;
        for(int r = row;r<=newRow;r++){
            for(int c = col;c<=newCol;c++){
                if(Map[r][c]=='X'){
                    num++;
                }
            }
        }
        if(num<2){
            return false;
        }
        else{
            return true;
        }
    }

    static boolean isRange(int newRow,int newCol){
        if(0<=newRow && newRow<5 && 0<=newCol && newCol<5){
            return true;
        }
        return false;
    }
}
