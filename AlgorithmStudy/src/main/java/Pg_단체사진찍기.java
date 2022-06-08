import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Pg_단체사진찍기 {
    static StringTokenizer st;
    static String[] data = {"N~F=0", "R~T>2"};
    static int N = 2;
    static ArrayList<Point> Arr = new ArrayList<>();
    static boolean[] visit;
    static char[] Friend = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int answer = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        //N = Integer.parseInt(br.readLine());
//        data = new String[N];
//        for(int i=0;i<N;i++){
//            data[i] = br.readLine();
//        }
        //===
        answer = 0;
        sb = new StringBuilder();
        for(int i=0;i<N;i++){
            String str = data[i];
            Arr.add(new Point(str.charAt(0),str.charAt(2),str.charAt(3),str.charAt(4) - '0'));
            System.out.println(str.charAt(4) - '0' + 1);
        }
        visit = new boolean[8];
        //이 조건을 다 만족하면서 애들을 줄세워야함 {A, C, F, J, M, N, R, T}
        //dfs로 다 만든다음에 8명 됐을 때 n개 조건 검사 긔긔 해보자
        dfs(sb,0);
        System.out.println(answer);
    }

    static void dfs(StringBuilder now,int count){
        if(count>=8){
            //System.out.println(now);
            //조건검사
            check(now.toString());
            return;
        }
        for(int i=0;i<8;i++){
            if(!visit[i]){
                visit[i] = true;
                now.append(Friend[i]);
                dfs(now,count+1);
                now.deleteCharAt(sb.length()-1);
                visit[i] = false;
            }
        }
    }

    static void check(String now){

        for(int i=0;i<Arr.size();i++){
            //조건 검사 후 안맞으면 return;
            //맞으면 계속 고고
            Point cond = Arr.get(i);
            int position1 = now.indexOf(cond.from);
            int position2 = now.indexOf(cond.to);

            if(Character.compare(cond.condition,'=')==0){
                if(!(Math.abs(position1 - position2) == cond.num+1)) {
                    return;
                }
            }
            else if(Character.compare(cond.condition,'>')==0){
                if(!(Math.abs(position1 - position2) > cond.num+1)) {
                    return;
                }
            }
            else if(Character.compare(cond.condition,'<')==0){
                if(!(Math.abs(position1 - position2) < cond.num+1)) {
                    return;
                }
            }
        }
        answer++;
    }

    static class Point{
        char from;
        char to;
        char condition;
        int num;

        public Point(char from, char to, char condition, int num) {
            this.from = from;
            this.to = to;
            this.condition = condition;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "from=" + from +
                    ", to=" + to +
                    ", condition=" + condition +
                    ", num=" + num +
                    '}';
        }
    }
}
