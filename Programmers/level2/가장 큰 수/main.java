import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] arr = new String[numbers.length];
        
        for(int i=0;i<numbers.length;i++){
            arr[i] = Integer.toString(numbers[i]);
        }
        //sorting
        Arrays.sort(arr,new MyComp());
        
        if(arr[0].equals("0"))
            return "0";
        
        for(String i:arr){
            //System.out.println(i);
            answer += i;
        }
        
        return answer;
}

class MyComp implements Comparator<String>{
    public int compare(String s1,String s2){
        return (s2+s1).compareTo(s1+s2);
    }
}
}
