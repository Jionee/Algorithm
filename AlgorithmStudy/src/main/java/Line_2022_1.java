import java.io.*;
import java.util.*;

public class Line_2022_1 {
    static StringTokenizer st;
    static int N,M;
    static String[] sentences;
    static ArrayList<Point> Arr = new ArrayList<>();
    static Map<String,Integer> map = new HashMap<>();
    static int mapSize;
    static int answer;
    static boolean[] visit;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sentences = new String[N];
        for(int i=0;i<N;i++){
            sentences[i] = br.readLine();
        }

        //====
        answer = 0;
        for(String str:sentences){
            //A 101
            //a 141
            //공백 32
            int score = 0;
            Point pt = new Point(str);

            char[] strChar = str.toCharArray();
            Set<Character> set = new HashSet<>();
            for(char c: strChar){
                if(Character.compare(c,' ')!=0){
                    set.add(c);
                }
                pt.score+=1;
            }

            for(char c: set){
                if('A'<=c && c<='Z'){ //대문자
                    if(!pt.key.contains("shift")){
                        pt.key.add("shift");
                    }
                    pt.score+=1;
                    pt.key.add(String.valueOf((char)('a'+c-'A')));
                }
                else{
                    pt.key.add(String.valueOf(c));
                }
            }
            Arr.add(pt); //각 포인트 저장하기

            for(String s:pt.key){ //전체 set 저장하기 (visit배열로 쓸것임)
                if(!map.containsKey(s)){
                    map.put(s,0);
                }
            }

            //str을 하나씩 다 분해해서 set에 넣고, 그 set에 있는 애들 for문 돌려가면서 대문자면 shift key에 추가 + 1, 소문자면 그냥 해당 char key에 추가하기 ,공백이면 무시
        }
        visit = new boolean[Arr.size()];
//        for(Point pt:Arr){
//            System.out.println(pt);
//        }

        //dfs시작
        //mapSize = map.size();
        dfs(0,0,0);
        System.out.println(answer);
    }
    static void dfs(int level,int usedKey,int sumScore){
        if(usedKey<=M){ //작으면 계속 돌아야 함
            //System.out.println("!!!!!!level = "+level+" usedKey = "+usedKey+" sumScore = "+sumScore);
            answer = Math.max(answer,sumScore);
        }
        else{ //크면 더이상 돌려도 의미 없음

        }
        if(level>=Arr.size()){
            return;
        }
        //System.out.println("level = "+level+" usedKey = "+usedKey+" sumScore = "+sumScore);

        for(int i=0;i<Arr.size();i++){
            if(!visit[i]){
                //System.out.println(Arr.get(i).str);
                visit[i] = true;
                int num = 0;
                for(String s:Arr.get(i).key){ //key
                    //쓰였으면 그냥, 처음 쓰는거면 num++
                    if(map.get(s)==0){ //처음
                        num++;
                    }
                    map.put(s,1);
                }
                dfs(level+1,usedKey+num,sumScore+Arr.get(i).score);
                visit[i] = false;
                for(String s:Arr.get(i).key){
                    map.put(s,0);
                }
            }
        }
    }

    static class Point{
        String str;
        ArrayList<String> key;
        int score;

        public Point(String str) {
            this.str = str;
            key = new ArrayList<String>();
        }

        @Override
        public String toString() {
            return "Point{" +
                    "str='" + str + '\'' +
                    ", key=" + key +
                    ", score=" + score +
                    '}';
        }
    }
}
