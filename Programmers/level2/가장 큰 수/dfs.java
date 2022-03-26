//dfs로 풀이하면 메모리초과 남

import java.util.*;

class Solution {
    static boolean[] visit;
    static PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
    static String answer = "";
    
    public String solution(int[] numbers) {
        visit = new boolean[numbers.length];
        //dfs로 다 붙여서 pq에 넣기?
        for(int i=0;i<numbers.length;i++){
            visit[i]=true;
            dfs(String.valueOf(numbers[i]),1,numbers);
            visit[i]=false;
        }
        //answer = queue.poll();
        return answer;
    }
    
    static void dfs(String now,int count,int[] numbers){
        if(count>=numbers.length){
            //System.out.println(now);
            //queue.add(now);
            if(answer.compareTo(now)<0){
                answer = now;
            }
        }
        for(int i=0;i<numbers.length;i++){
            if(!visit[i]){
                visit[i] = true;
                dfs(now+String.valueOf(numbers[i]),count+1,numbers);
                visit[i] = false;
            }
        }
    }
}
