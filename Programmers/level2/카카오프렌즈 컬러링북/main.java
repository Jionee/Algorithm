import java.util.*;



class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int [][] isViewd = new int[picture.length][picture[0].length];

        int width = 0;

        for(int i=0;i<m;i++){
            for(int k=0;k<n;k++){
                width = find(i,k,m,n,picture,isViewd);
                if(width>0){ //영역이 존재하면
                    numberOfArea++;
                    maxSizeOfOneArea = maxSizeOfOneArea < width ? width : maxSizeOfOneArea;
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    } 


    //이전 값이랑 같은지, 0이 아닌지, 이동할 수 있는지, 이미 본 지역인지
    public int find(int x,int y,int m,int n,int[][] picture, int[][] isViewd){
        if(isViewd[x][y]++>0){//false면 이미 방문
            return 0;
        }
        if(picture[x][y]==0) return 0; //0이면 X

        int count =1;
        for(int i=0;i<4;i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX<=-1 || newX>=m || newY<=-1 || newY>=n){ //이러면 안됨
                continue;
            } 
            else{
                if(picture[newX][newY]==picture[x][y]) 
                    count += find(newX,newY,m,n,picture,isViewd);
            }
        }
        return count;
    }

}
