import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String,Integer> hm = new HashMap();
        for(int i=0;i<clothes.length;i++){
            hm.put(clothes[i][1],hm.getOrDefault(clothes[i][1],1)+1);
            //getOrDefault : clothes[i][1]이 존재하면 그걸 사용, 아니면 1을 넣기
        }
        
        for(int k:hm.values()){
            answer *= k;
        }
        
        return answer-1;
    }
}
