import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Pg_dev1 {

    public static void main(String[] args) throws Exception{
        //input
        int[][] dist = {{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}};
        Solution.solution(dist);
    }

    static class Solution {
        static ArrayList<ArrayList<Integer>> AnswerArray = new ArrayList<>();

        static int[] load;
        static int[][] answer;
        static ArrayList<Point> loadArr = new ArrayList<>();

        public static int[][] solution(int[][] dist) {
            load = new int[dist.length];

            //0에서부터 무조건 시작함 //0은 좌표 0
            //
            dfs(1,0,dist);

            Collections.sort(AnswerArray, new Comparator<ArrayList<Integer>>() {
                @Override
                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                    return Integer.compare(o1.get(0),o2.get(0));
                }
            });
            answer = new int[AnswerArray.size()][dist.length];
            for(int i=0;i< AnswerArray.size();i++){
                for(int j=0;j<AnswerArray.get(i).size();j++){
                    answer[i][j] = AnswerArray.get(i).get(j);
                }
            }

            for(int[] A:answer){
                System.out.println(Arrays.toString(A));
            }
            return answer;
        }

        static void dfs(int count, int newNode,int[][] dist){
            System.out.println(count+" 새로운좌표 : "+ newNode);
            if(!check(count-1,dist)){
                return;
            }
            if(count>=dist.length){
                ArrayList<Point> temp = new ArrayList<>();
                for(int i=0;i<load.length;i++){
                    temp.add(new Point(i,load[i]));
                }
                Collections.sort(temp);
                ArrayList<Integer> tttemp = new ArrayList<>();
                for(Point p:temp){
                    tttemp.add(p.node);
                }
                AnswerArray.add(tttemp);
                return;
            }
            //5개인 경우
            //0 1 count 0
            //0 2 , 2랑 1 확인 count 1
            //0 3 , 3이랑 1,2 확인
            //0 4 , 4랑 1,2,3 확인
            int nextDiff = dist[0][count]; //0이랑 count(다음노드)사이의 diff
            load[count] = nextDiff; //양수쪽으로
            dfs(count+1, + nextDiff,dist);
            load[count] = -nextDiff; //음수쪽으로
            dfs(count+1, - nextDiff,dist);
            load[count] = 0; //원상복귀
        }

        static boolean check(int count,int[][] dist){
            //1~count+1까지 확인
            for(int i=1;i<count+1;i++){
                if(!(dist[i][count] == Math.abs(load[i] - load[count]))){
                    return false;
                }
            }
            return true;
        }

        static class Point implements Comparable<Point>{
            int node;
            int coordinate;

            public Point(int node, int coordinate) {
                this.node = node;
                this.coordinate = coordinate;
            }

            @Override
            public int compareTo(Point o) {
                return Integer.compare(coordinate,o.coordinate);
            }
        }
    }
}
