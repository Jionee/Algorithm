import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Line_2022_4 {
    static StringTokenizer st;
    static String[] Tag = {"team_name", "application_name", "error_level", "message"};
    //static Set<String> TagString ;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] logs = {"team_name : db application_name : dbtest error_level : info message : test", "team_name : test application_name : I DONT CARE error_level : error message : x", "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever", "team_name : oberervability application_name : LogViewer error_level : error"};
        //===
        //TagString = new HashSet<String>(Arrays.asList(Tag));
        int answer = 0;

        //각 logs[i]의 length가 100이하여야 함 -> 100초과면 answer++; + continue;
        //각 logs[i]마다 " "로 다 분리, String[] 배열에 넣어두기
        //0 team_name , 1 : , 2 T , 3 appliacation_name , 4 : , 5: A , 6 error_level , 7 : , 8 E, 9 message , 10 : , 11 M
        //0에서는 team_name이랑 동일한지 확인, 동일하지 않으면 answer++, continue; (num%3==0)
        //1은 패스
        //2에서는 알파벳 소문자, 대문자인지 확인 (num%3==2)
        int flag = 0;

        for(String log:logs){
            //System.out.println(flag+" "+log);
            if(log.length()>100){
                answer++;
                //System.out.println(flag+"길이100이상");
                continue;
            }
            String[] splitLog = log.split(" ");
            if(splitLog.length!=12){
                answer++;
                //System.out.println(flag+"스플릿 12개아님, 형식 아예 틀림");
                continue;
            }
            for(int i=0;i<12;i++){
                if(i%3==0){
                    if(!Tag[i/3].equals(splitLog[i])){
                        answer++;
                        //System.out.println(flag+"태그명 불일치");
                        break;
                    }
                }
                else if(i%3==2){
                    for(char c:splitLog[i].toCharArray()){
                        if(!(('A'<=c && c<='Z') || ('a'<=c && c<='z'))){
                            answer++;
                            //System.out.println(flag+"알파벳아님");
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
