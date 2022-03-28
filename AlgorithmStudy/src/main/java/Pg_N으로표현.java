import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

//2:51~
public class Pg_N으로표현 {
    static StringTokenizer st;
    static int N = 5;
    static int number = 12;
    static int answer;

    public static void main(String[] args) throws Exception{
        answer = Integer.MAX_VALUE;

        dfs(0,0);

        System.out.println(answer);
        //return answer==Integer.MAX_VALUE?-1:answer;
    }
    public static void dfs(int count, int sum){
        if(count>8) return;
        if(number==sum){
            answer = Math.min(answer,count);
        }
        System.out.println("count = "+count+", sum = "+sum);
        int X = N;
        for(int i=1;i<=8-count;i++){
            dfs(i+count,sum+X);
            dfs(i+count,sum-X);
            dfs(i+count,sum*X);
            dfs(i+count,sum/X);
            X = (10*X)+N;
        }
    }
}
