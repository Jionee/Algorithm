import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<sets> arr = new LinkedList<>();
        boolean loc = false;
        for(int i=0;i<priorities.length;i++){
                if(i==location)
                    loc=true;
                else
                    loc=false;
                arr.offer(new sets(priorities[i],loc));
        }

        while(!arr.isEmpty()){
            int tmp = arr.peek().getPri();
            boolean flag = false;

            for(sets i:arr){
                if(i.getPri() > tmp){
                    flag = true;
                    break;
                }
            }

            if(flag){ //큰게 있으면
                arr.offer(arr.poll());
            }
            else{
                sets tmpsets = arr.poll();
                if(tmpsets.getLoc() == true){
                    answer = priorities.length - arr.size();
                }
            }
        }


       //정렬
        // Collections.sort(arr);
        // for(sets i:arr){
        //     System.out.println(i.getPri() + " - "+i.getLoc());
        // }


        // for(int i=0;i<arr.size();i++){
        //     if(arr.get(i).getLoc()==true){
        //         answer = i+1;
        //     }
        // }
        return answer;
    }
}

class sets implements Comparable<sets>{
    public int priorities;
    public boolean location;

    public sets(int pri,boolean location){
        this.priorities = pri;
        this.location = location; //true면 target
    }

    public int compareTo(sets s){
        if(this.priorities == s.priorities)
            return 0;
        else if(this.priorities > s.priorities)
            return -1;
        else return 1;
    }

    public int getPri(){
        return this.priorities;
    }
    public boolean getLoc(){
        return this.location;
    }
}
