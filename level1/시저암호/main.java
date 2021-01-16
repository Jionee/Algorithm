class Solution {
    public String solution(String s, int n) {
        String answer = "";
        char[] cArr = s.toCharArray();


        for(int i=0;i<cArr.length;i++){
            int num = 0;
            if(cArr[i]==(char)' ')
                continue;
            else{
                num = (int)cArr[i];
                cArr[i]= (char)(num + n);

                int aNum = (int)'a';
                int zNum = (int)'z';
                int ANum = (int)'A';
                int ZNum = (int)'Z';

                //num : origin , num+n : new
                if(aNum <= num && num <= zNum){ //a-z
                    if(!(aNum <= num+n && num+n <= zNum)){
                        num = (int)cArr[i] - (int)'z';
                        num = (int)'a' + num - 1;
                        cArr[i]= (char)(num);
                    }
                }
                else if(ANum <= num && num <= ZNum){ //A-Z
                    if(!(ANum<= num+n && num+n <= ZNum)){
                        num = (int)cArr[i] - (int)'Z';
                        num = (int)'A' + num - 1;
                        cArr[i]= (char)(num);
                    }
                }
            }
        }
        answer = new String(cArr);
        return answer;
    }
}
