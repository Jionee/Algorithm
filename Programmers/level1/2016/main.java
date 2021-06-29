class Solution {
    public String solution(int a, int b) {
        String answer = "";
        String[] days = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
        int[] num = {31,29,31,30,31,30,31,31,30,31,30,31};
        int[] cNum = new int[num.length];

        int month = a;
        int day = b;

        int sum = 0;
        for(int i=0;i<month-1;i++){
            sum += num[i];
        }
        sum+=day;

        switch(sum%7){
            case 0:
                answer = days[4];
                break;
            case 1:
                answer = days[5];
                break;
            case 2:
                answer = days[6];
                break;
            case 3:
                answer = days[0];
                break;
            case 4:
                answer = days[1];
                break;
            case 5:
                answer = days[2];
                break;
            case 6:
                answer = days[3];
                break;
        }

        return answer;
    }
}
