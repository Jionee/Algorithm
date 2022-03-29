import java.io.*;
import java.util.*;

public class Pg_위장 {
    static String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
    static HashMap<String,Integer> Map = new HashMap<>();

    public static void main(String[] args) throws Exception{
        int answer = 1;
        for(String[] cloth: clothes){
            if(Map.containsKey(cloth[1])){
                Map.put(cloth[1],Map.get(cloth[1])+1);
            }
            else{
                Map.put(cloth[1],1);
            }
        }
        System.out.println(Map);

        for(java.util.Map.Entry<String,Integer> entrySet :Map.entrySet()){
            answer *= entrySet.getValue() + 1;
        }

        System.out.println(answer-1);
        //return answer;
    }
}
