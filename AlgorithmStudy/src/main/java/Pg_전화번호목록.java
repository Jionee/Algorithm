import java.io.*;
import java.util.*;

public class Pg_전화번호목록 {
    static StringTokenizer st;
    static String[] phone_book = {"123","12","21"} ;
    static boolean answer;
    static MyTrie trie = new MyTrie();

    public static void main(String[] args) throws Exception{
        answer = true;
        Arrays.sort(phone_book);

        System.out.println(Arrays.toString(phone_book));
        for(String str:phone_book){
            trie.insert(str);
        }

        System.out.println(answer);
        //return answer;
    }

    static class MyTrie{
        TrieNode root = new TrieNode();

        public void insert(String input){
            TrieNode now = root;

            for(int i=0;i<input.length();i++){
                char ch = input.charAt(i);
                if(now.isEnd){ //지금 노드가 마지막이라는 것은 이미 거쳤다는 뜻
                    answer = false;
                    return;
                }
                if(now.hasChild(ch)){ //있으면 false 리턴, 없으면 true 끝까지 계속
                    now = now.getChild(ch);
                }
                else{
                    now.child.put(ch,new TrieNode());
                    now = now.getChild(ch);
                }
            }
            now.isEnd = true;
            //System.out.println(root);
        }
    }

    static class TrieNode{
        Map<Character, TrieNode> child = new HashMap<>();
        boolean isEnd = false;

        boolean hasChild(char node){
            if(child.containsKey(node)){
                return true;
            }
            else{
                return false;
            }
        }

        TrieNode getChild(char node){
            return child.get(node);
        }
    }
}
