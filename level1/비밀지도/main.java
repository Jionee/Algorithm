import java.util.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        answer = new String[arr1.length];

        int[] map = new int[arr1.length];

        //arr1, arr2 각각 |연산해서 새 배열에 넣은 후 2진수로 변환
        for(int i=0;i<arr1.length;i++){
            map[i] = arr1[i] | arr2[i]; //or 연산
            System.out.println(map[i]);
        }

        for(int i=0;i<map.length;i++){
            boolean isEnd = true;
            String tmp = "";
            System.out.println(tenToTwo(n,map[i]));
            answer[i] = tenToTwo(n,map[i]);
        }

        //#으로 변환
        for(int i=0;i<arr1.length;i++){
            answer[i] = answer[i].replaceAll("1","#");
            answer[i] = answer[i].replaceAll("0"," ");
        }

        return answer;
    }
    
    public String tenToTwo(int n, int num){
        int[] ret = new int[n];
        for(int i=n-1; i>=0; i--){
            ret[i]=num%2;
            num=num/2;
        }
        String str = "";
        for(int i=0;i<ret.length;i++){
            str += ret[i];
        }
        return str;
    }
}
