import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};

        // System.out.println(strings[0]);
        // System.out.println(strings[0].charAt(0));

        ArrayList<stringMap> list = new ArrayList<>();
        for(int i=0;i<strings.length;i++){
            list.add(new stringMap(strings[i],Character.toString(strings[i].charAt(n))));
        }

        Collections.sort(list);

        answer = new String[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i).getStr();
        }

        return answer;
    } 
}

 class stringMap implements Comparable<stringMap>{
        private String str;
        private String atStr;
        public stringMap(String str,String atStr){
            this.str = str;
            this.atStr = atStr;
        }
        public int compareTo(stringMap m){ //compare
            int result = this.atStr.compareTo(m.atStr);
                if(this.atStr.equals(m.atStr)){ //인덱스 같으면 사전식배열
                    result = this.str.compareTo(m.str);
                }
            return result;
        }
        public String getStr(){
            return str;
        }
        public String getAtStr(){
            return atStr;
        }
    }
