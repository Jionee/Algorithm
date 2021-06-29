class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        String wshort = null;
        String wlong = null;
        for(int i=0;i<phone_book.length-1;i++){
            for(int k=i+1;k<phone_book.length;k++){
                 wshort = null;
                 wlong = null;
                if(phone_book[i].length()<phone_book[k].length()){
                    wshort =  phone_book[i];
                    wlong = phone_book[k];
                }
                else{
                    wshort =  phone_book[k];
                    wlong = phone_book[i];
                }
                int shortL = 0;
                //비교
                for(int j=0;j<wshort.length();j++){
                    if(wshort.charAt(j)==wlong.charAt(j)){
                        shortL++;
                    } 
                    if(shortL==wshort.length()){
                        answer = false;
                        break;
                    }
                }
                if(answer==false)
                    break;
            }
            if(answer==false)
                break;
        }
        return answer;
    }
}
