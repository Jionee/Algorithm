//use Stack
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        for(int i=0; i<moves.length; i++){
            int x=-1;
            int y=moves[i]-1;
            for(int j=0; j<board[0].length; j++){
                if(board[j][y]>0){
                    x=j;
                    break;
                }
            }
            if(x!=-1){
                if(stack.peek()!=board[x][y]){
                    stack.push(board[x][y]);
                    
                }else{
                    answer=answer+2;
                    stack.pop();
                }
                board[x][y]=0;
            }
        }
        
        return answer;
    }
    
}
