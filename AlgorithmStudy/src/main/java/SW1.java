import java.util.*;
import java.io.*;

class SW1 {
    static int N,M;
    static MyTrie myTrie = new MyTrie();

    public static void main(String args[]) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=1;i<N+1;i++){
            String str = br.readLine();
            //trie 사전에 넣기
            myTrie.add(str);
        }

        M = Integer.parseInt(br.readLine());

        for(int i=1;i<M+1;i++){
            //사전에 있는지 확인
            String str = br.readLine();
            //trie 사전에 넣기
            myTrie.find(str);
        }

    }
    public static class MyTrie{
        TrieNode root = new TrieNode();
        void add(String str){
            TrieNode now = root;
            for(int i=0;i<str.length();i++){
                if(!now.hasChild(str.charAt(i))){ //없으면 생성
                    now.addChild(str.charAt(i));
                }
                //있으면 다음꺼 진행
                now.count+=1;
                now = now.getChild(str.charAt(i));
            }
            now.isEnd = true;
        }
        void find(String str){
            TrieNode now = root;
            for(int i=0;i<str.length();i++){
                if(!now.hasChild(str.charAt(i))){
                    return;
                }
                //있으면 계속 진행, isEnd만나면 count 프린트하기
                now = now.getChild(str.charAt(i));
            }
            if(now.isEnd){
                System.out.println(now);
                System.out.println(now.count);
                return;
            }
        }

    }

    public static class TrieNode{
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
        int count;

        @Override
        public String toString() {
            return "TrieNode{" +
                    "child=" + Arrays.toString(child) +
                    ", isEnd=" + isEnd +
                    ", count=" + count +
                    '}';
        }

        public boolean hasChild(char ch){
            if(child[ch-'A']==null){
                return false;
            }
            return true;
        }
        public TrieNode getChild(char ch){
            return child[ch-'A'];
        }

        public void addChild(char ch){
            child[ch-'A'] = new TrieNode();
        }

        public TrieNode() {
        }
    }

}

class SW2 {
    static int N;
    static int[] Menu;
    static int answer;
    static boolean[] visit;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Menu = new int[N+1];
        visit = new boolean[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            Menu[i] = Integer.parseInt(st.nextToken());
        }

        //2000<= x<=2500, 세개를 골라서 중복되지 않게
        //순서가 달라도 안됨, 모든 조합을 고려해야 함
        //menu를 줄여가면서 dfs 고고
        for(int i=1;i<N+1-3;i++){
            visit[i] = true;
            dfs(i,1,Menu[i]); //i부터 시작할거고 i이전꺼는 갖다쓰지말기
        }
        System.out.println(answer);
    }

    public static void dfs(int index,int count,int calc){
        System.out.println(index+" "+count+" "+calc);
        if(count >= 3 && 2000<=calc && calc<=2500){
            answer ++;
            return;
        }
        for(int i=index+1;i<N+1;i++){
            if(!visit[i]){
                visit[i] = true;
                dfs(index,count+1,calc+Menu[i]);
                visit[i] = false;
            }
        }
    }
}