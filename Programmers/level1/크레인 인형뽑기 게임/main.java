import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        ArrayList<Integer> stack = new ArrayList<>();

        for(int i=0;i<moves.length;i++){
            for(int k=0;k<board[0].length;k++){
                //System.out.println("k:"+k+" /moves[i]:"+moves[i]);
                if(board[k][moves[i]-1]!=0){
                    stack.add(board[k][moves[i]-1]);//stack add
                    board[k][moves[i]-1]=0;//pop
                    break;
                }
            }
        }

        //print stack
        for(int i=0;i<stack.size();i++){
            System.out.println(stack.get(i));
        }

        //사라진 인형 개수
        boolean isEnd = true;
        while(isEnd){
            isEnd=false;
            for(int i=0;i<stack.size()-1;i++){
                if(stack.get(i)-stack.get(i+1)==0){
                    answer++;
                    stack.remove(i+1);
                    stack.remove(i);
                    isEnd=true;
                }
            }
        }
        return answer*2;
    }
}
