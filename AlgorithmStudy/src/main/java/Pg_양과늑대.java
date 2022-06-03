import java.util.ArrayList;
import java.util.Arrays;

public class Pg_양과늑대 {

    public static void main(String[] args) throws Exception{
        //input
        int[] info = {0,1,0,1,1,0,1,0,0,1,0};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
        Solution.solution(info, edges);
    }

    static class Solution {
        /**/
        static Point[] Animal;
        static int N;
        static int answer = 0;
        static boolean[] visit;
        static ArrayList<Integer> root = new ArrayList<>();
        public static int solution(int[] info, int[][] edges) {
            N = info.length;
            Animal = new Point[info.length];
            visit = new boolean[N];

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
            //System.out.println(Arrays.toString(Animal));

            visit[0] = true;
            dfs(0,1,1,0);
            System.out.println(answer);
            return answer;
        }
        /**/
        static void dfs(int index, int count, int sheep, int wolf){
            root.add(index);

            //System.out.println("====="+root);
            if(sheep <= wolf){
                System.out.println("##"+root+" sheep:"+sheep+" ,wolf:"+wolf+" , count:"+count);
                answer = Math.max(answer, sheep);
                return;
            }
            if(count>=25){
                //System.out.println(root+" sheep:"+sheep+" ,wolf:"+wolf+" , count:"+count);
                answer = Math.max(answer, sheep);
                return;
            }
            if(index==0){
                boolean flag = false;
                for(int c:Animal[0].child){
                    if(!visit[c]){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    //System.out.println("XX"+root+" sheep:"+sheep+" ,wolf:"+wolf+" , count:"+count);
                    answer = Math.max(answer, sheep);
                    return;
                }
            }

            ArrayList<Integer> child = Animal[index].child;
            //System.out.println("PARENT:"+index +" => CHILD:"+child);

            for(int c: child){
                //System.out.println("CHILD:"+c + " "+visit[c]);
                //root.add(c);

                if(!visit[c]){
                    int nowSheep = sheep;
                    int nowWolf = wolf;

                    if(Animal[c].whom == 0 && !visit[c]){ //양이면
                        nowSheep += 1;
                    }
                    else if(Animal[c].whom == 1 && !visit[c]){
                        nowWolf += 1;
                    }
                    //System.out.println("CHILD:"+c+"==>"+root+" sheep:"+nowSheep+" ,wolf:"+nowWolf+" , count:"+count);


                    visit[c] = true;
                    dfs(c, count+1,nowSheep,nowWolf);
                    visit[c] = false;
                }
                //root.remove(root.size()-1);
            }

            if(Animal[index].parent!=-1){
                //root.add(Animal[index].parent);
                dfs(Animal[index].parent, count+1, sheep, wolf); //부모
                //root.remove(root.size()-1);
            }
            else{
                for(int c:Animal[0].child){
                    if(c!=index){
                        //root.add(0);
                        dfs(c, count+1, sheep, wolf); //부모
                        //root.remove(root.size()-1);
                    }
                }
            }
            root.remove(root.size()-1);

            return;
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
