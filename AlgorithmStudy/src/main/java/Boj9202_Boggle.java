import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9202_Boggle {
    static int[] mCol = {-1, 1, 0, 0, -1, 1, -1, 1};//왼,오,위,아래,대각선왼쪽위,대각선오른쪽위,대각선왼쪽아래,대각선오른쪽아래
    static int[] mRow = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    static int W,B;
    static Trie trie = new Trie();
    static char[][] map;
    static boolean[][] visit;
    //정답
    static int sum,count;
    static String longest;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());
        //트라이 구성하기
        for(int i=0;i<W;i++){
            trie.insert(br.readLine());
        }
        br.readLine();

        StringBuilder resultSb = new StringBuilder();
        B = Integer.parseInt(br.readLine());
        //단어 찾기 - b번
        for(int b=0;b<B;b++){
            //map 구성
            map = new char[4][4];
            visit = new boolean[4][4]; //한 판에 대해 리셋
            sum = 0;
            count = 0;
            longest = "";
            sb = new StringBuilder();

            for(int i=0;i<4;i++){
                String s = br.readLine();
                for(int j=0;j<4;j++){
                    map[i][j] = s.charAt(j);
                }
            }
            //각 맵에 대해서 단어 찾기
            //dfs를 이용하여 모든 char를 시작으로 하는 단어가 존재하는지 확인한다.
            for(int row = 0;row<4;row++){
                for(int col=0;col<4;col++){
                    //자식이 존재하면
                    if(trie.root.hasChild(map[row][col])){
                        System.out.println("=======dfs 새로 호출 ======= row : "+row+",col : "+col);
                        dfs(row,col,1,trie.root.getChild(map[row][col]));
                    }
                }
            }
            //정답 출력

            resultSb.append(sum).append(" ").append(longest).append(" ").append(count).append("\n");
            //맵 변경 시 hit 클리어
            trie.root.clearHit();
            br.readLine();
        }
        System.out.println(resultSb);

    }

    static void dfs(int row, int col, int length, TrieNode node){
        //1. 체크인 -> visit = true
        visit[row][col] = true;
        sb.append(map[row][col]);
        System.out.println("----> CheckIn "+sb.toString());

        //2. 목적지인가? -> Trie.search가 true이면 목적지 && isHit == false
        // -> isHit = true 바꾸기, 점수 갱신, count 갱신, 가장 긴 단어 갱신
        if(trie.search(sb.toString()) && node.isHit == false){
            node.isHit = true;
            sum += score[length];
            count++;
            if(compare(longest,sb.toString()) > 0){
                longest = sb.toString();
            }
        }
        //3. 인접한 곳 순회
        for(int i=0;i<8;i++){//왼,오,위,아래,대각선왼쪽위,대각선오른쪽위,대각선왼쪽아래,대각선오른쪽아래
            int nextRow = row + mRow[i];
            int nextCol = col + mCol[i];
            //4. 갈수있는가? -> 벽 안됨, visit==false만, 자식이 있으면
            if(0 <= nextRow && nextRow < 4 && 0<= nextCol && nextCol < 4){
                if(visit[nextRow][nextCol] == false && node.hasChild(map[nextRow][nextCol])){
                    //5. 간다 dfs
                    dfs(nextRow,nextCol,length+1,node.getChild(map[nextRow][nextCol]));
                }
            }
        }

        // 6. 체크아웃 -> visit = false
        visit[row][col] = false;
        sb.deleteCharAt(length-1);
        System.out.println("CheckOut ----->"+sb.toString());
    }

    static int compare(String str1,String str2){
        int result = Integer.compare(str2.length(), str1.length());
        if(result == 0){
            return str1.compareTo(str2);
        }
        else{
            return result;
        }
    }

    static class TrieNode{
        TrieNode[] child = new TrieNode[26]; //자식
        boolean isEnd; //단어가 끝났는지
        boolean isHit; //단어 찾았는지

        void clearHit(){ //루트에서 시작해서 자식 모두 false로 바꿔주기
            isHit = false;
            for(int i=0;i<child.length;i++){
                if(child[i]!=null){
                    child[i].clearHit();
                }
            }
        }

        //자식이 있는지 리턴
        boolean hasChild(char c){
            return child[c-'A'] != null;
        }
        //자식 리턴
        TrieNode getChild(char c){
            return child[c-'A'];
        }
    }

    static class Trie{
        //Trie자료구조 생성 시 rootNode는 자동으로 생성
        TrieNode root = new TrieNode();

        void insert(String str){
            //Trie 자료궂는 항상 root부터 시작
            TrieNode node = this.root;

            //문자열의 각 단어마다 가져와서 자식 노드 중에 있는지 체크
            for(int i=0;i<str.length();i++){
                int wordIndex = str.charAt(i) - 'A';
                //없으면 새로 생성
                if(node.child[wordIndex] == null){
                    node.child[wordIndex] = new TrieNode();
                }
                //다음 노드 갱신
                node = node.child[wordIndex];
            }
            //단어가 끝났으면 표시
            node.isEnd = true;
        }

        boolean search(String str){
            TrieNode node = this.root;

            //문자열의 각 단어마다 자식 노드가 존재하는지 체크
            for(int i=0;i<str.length();i++){
                int wordIndex = str.charAt(i) - 'A';
                //없으면 종료
                if(node.child[wordIndex] == null){
                    return false;
                }
                //있으면 노드 갱신
                node = node.child[wordIndex];
            }
            //다 찾았는데 isEnd까지 맞으면 true 리턴
            if(node.isEnd == false){
                return false;
            }
            return true;
        }
    }
}

