import java.util.*;

class Solution {
    HashMap<String,Integer> hashSet = new HashMap<>();

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i=0;i<skill.length();i++){
            hashSet.put(String.valueOf(skill.charAt(i)),i);
        }

        for(String string:skill_trees){
            answer += isPossible(string);
        }

        return answer;
    }

    //============================================

    public int isPossible(String str){
        int number = 0;
        StringBuilder builder = new StringBuilder(str);

        for(int i=0;i<str.length();i++){
            if(hashSet.get(String.valueOf(str.charAt(i))) != null){
                number = hashSet.get(String.valueOf(str.charAt(i)));

                builder.setCharAt(i,Character.forDigit(number,10));
                str = builder.toString();
                //System.out.println(number+" - "+str);
            }
            else{
                builder.deleteCharAt(i);
                str = builder.toString();
                //System.out.println("remove - "+str);
                i--;
            }
        }

        //System.out.println(str);
        if(str.length() <= 0){
            return 1;
        }


        int lastNum = Character.getNumericValue(str.charAt(str.length()-1));
        
        //오름차순 검사
        for(int i=0;i<str.length()-1;i++){
            for(int k=i+1;k<str.length();k++){
                if(str.charAt(i) > str.charAt(k)){
                    return 0;
                }
            }
        }

        //선행 스킬 검사
        int ho = 0;
            for(int k=0;k<str.length();k++){
                for(int i=ho;i<lastNum+1;i++){
                    if(i == Character.getNumericValue(str.charAt(k))){
                        //System.out.println("break "+Character.getNumericValue(str.charAt(k))+" / "+i);
                        ho = i+1;
                        break;
                    }
                    else{
                        //System.out.println("return "+Character.getNumericValue(str.charAt(k))+" / "+i);
                        return 0;
                    }
                }    
            }


        //가능하면 1 아니면 0 return
        return 1;
    }
}
