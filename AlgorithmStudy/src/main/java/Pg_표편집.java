import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Pg_표편집 {

    public static void main(String[] args) throws Exception{
        //input
        int n = 8;
        int k = 7;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        String answer = Solution.solution(n,k,cmd);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static StringTokenizer st;
        static StringBuilder sb = new StringBuilder();
        static ArrayList<Node> NodeArr = new ArrayList<>();
        static Stack<Node> deleteStack = new Stack<>();

        static Node now;
        public static String solution(int n, int k, String[] cmd) {
            Node root = new Node(0);
            NodeArr.add(root);

            for(int i=1;i<n;i++){
                NodeArr.add(new Node(i));
                NodeArr.get(i).prev = NodeArr.get(i-1);
                NodeArr.get(i-1).next = NodeArr.get(i);
            }

            now = NodeArr.get(k);

            //node 간 관계는 노드로, 인덱스 관리는 ArrayList에서
            for(int c = 0;c<cmd.length;c++){
                st = new StringTokenizer(cmd[c]);
                char command = st.nextToken().charAt(0);
                if(command=='U'){
                    int diff = Integer.parseInt(st.nextToken()); //diff 칸만큼 이동해야함

                    for(int i=0;i<diff;i++){
                        now = now.prev;
                    }
                }
                else if(command=='D') {
                    int diff = Integer.parseInt(st.nextToken()); //diff 칸만큼 이동해야함
                    for(int i=0;i<diff;i++){
                        if(now.next==null){
                            break;
                        }
                        now = now.next;
                    }
                }
                else if(command=='C') {
                    //제거
                    System.out.println("제거 NOW -> "+now);
                    now.isDelete = true;
                    deleteStack.add(now);
                    //앞뒤관계 수정하기
                    Node before = now.prev;
                    Node after = now.next;
                    if(before!=null){ //now가 첫 행이 아닐 때
                        before.next = after;
                    }
                    if(after!=null){ //now가 마지막 행이 아닐 때
                        after.prev = before;
                    }

                    //마지막 행이면 윗 행 선택
                    if(after==null){
                        now = before;
                    }
                    //나머지는 아래 행 선택
                    else{
                        now = after;
                    }
                }
                else if(command=='Z') {
                    //이전에 제거했던 거에서 pop해서 다시 관계 살리기
                    Node pop = deleteStack.pop();
                    Node before = pop.prev;
                    Node after =  pop.next;
                    pop.isDelete = false;

                    if(before!=null){
                        before.next = pop;
                    }
                    if(after!=null){
                        after.prev = pop;
                    }
                }
            }

            for(Node node:NodeArr){
                if(node.isDelete){
                    sb.append("X");
                }
                else{
                    sb.append("O");
                }
            }
//            System.out.println("=========");
//            for(int i=0;i<NodeArr.size();i++){
//                System.out.println(NodeArr.get(i));
//            }
            return sb.toString();
        }
        /**/
        static class Node{
            int idx;
            Node prev = null;
            Node next = null;
            boolean isDelete;

            public Node(int idx) {
                this.idx = idx;
            }

            @Override
            public String toString() {
                int a = -1;
                int b = -1;
                if(prev!=null){
                    a = prev.idx;
                }
                if(next!=null){
                    b = next.idx;
                }
                return "Node{" +
                        "idx=" + idx +
                        ", prev=" + a +
                        ", next=" + b +
                        ", isDelete=" + isDelete +
                        '}';
            }
        }
    }
}
