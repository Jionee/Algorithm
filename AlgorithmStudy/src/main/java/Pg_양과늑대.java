import java.util.ArrayList;
import java.util.Arrays;

public class Pg_양과늑대 {

    public static void main(String[] args) throws Exception{
        //input
        int[] info = {0,1};
        int[][] edges = {{0,1}};
        int answer = Solution.solution(info, edges);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static Point[] Animal;
        static int N;
        static int answer = 0;

        public static int solution(int[] info, int[][] edges) {
            N = info.length;
            Animal = new Point[info.length];

            for(int i=0;i<info.length;i++){
                Animal[i] = new Point(i,info[i]);
            }
            Animal[0].parent = -1;
            for(int i=0;i<edges.length;i++){
                int p = edges[i][0];
                int c = edges[i][1];
                Animal[p].child.add(c);
                Animal[c].parent = p;
            }

            ArrayList<Integer> nextNode = new ArrayList<>();
            nextNode.add(0);
            dfs2(0, nextNode,0,0);

            return answer;
        }

        static void dfs2(int index, ArrayList<Integer> nextNode, int sheep, int wolf){
            //양, 늑대 파악 후 수 늘려주기
            int newSheep = sheep;
            int newWolf = wolf;
            if(Animal[index].whom == 0){ //양이면
                newSheep += 1;
            }
            else if(Animal[index].whom == 1){
                newWolf += 1;
            }

            //어차피 최대 양의 수를 찾는 것이므로 리턴할 때만 갱신하는 것이 아니라 노드를 방문했을 때 항상 갱신한다.
            answer = Math.max(answer, newSheep);

            if(newSheep <= newWolf){ //늑대한테 잡아먹히면 중지
                return;
            }

            //다음에 갈 수 있는 노드 업데이트
            ArrayList<Integer> newNode = new ArrayList<>();
            newNode.addAll(nextNode);
            for(int c: Animal[index].child){
                newNode.add(c); //자식 추가
            }

            //자기 자신은 갔으니까 제거
            newNode.remove(Integer.valueOf(index)); //Integer.valueOf(index)를 써서 list 를 제거하면 값으로 제거된다.

            //갈 수 있는 모든 노드 돌리기
            for(int node: newNode){
                dfs2(node, newNode, newSheep, newWolf);
            }
        }

        static class Point{
            int index;
            int whom;//0:양, 1: 늑대
            int parent;
            ArrayList<Integer> child = new ArrayList<>();

            public Point(int index, int whom) {
                this.index = index;
                this.whom = whom;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "index=" + index +
                        ", whom=" + whom +
                        ", parent=" + parent +
                        ", child=" + child +
                        '}';
            }
        }
    }

}
