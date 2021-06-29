import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String,String> hm = new HashMap<>();
        int size=0;

        for(int i=0;i<record.length;i++){
            String[] splits = record[i].split(" ");
            switch(splits[0]){
                case "Enter":
                    hm.put(splits[1],splits[2]);
                    size++;
                    break;
                case "Leave":
                    size++;
                    break;
                case "Change":
                    //id 찾아서 바꾸기
                    hm.put(splits[1],splits[2]);
                    break;
            }
        }

        //~이 들어왔습니다  / ~이 나갔습니다.
        answer = new String[size];
        int num=0;
        for(String s:record){
            String[] splits = s.split(" ");
            switch(splits[0]){
                case "Enter":
                    answer[num++]=hm.get(splits[1])+"님이 들어왔습니다.";
                    break;
                case "Leave":
                    answer[num++]=hm.get(splits[1])+"님이 나갔습니다.";
                    break;
                default:
                    break;
            }
        }

        return answer;
    }
}
