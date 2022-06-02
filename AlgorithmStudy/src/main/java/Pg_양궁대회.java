import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pg_양궁대회 {

    public static void main(String[] args) throws Exception{
        //input
        int n = 10;
        int[] info = {0,0,0,0,0,0,0,0,3,4,3};

        int[] answer = Solution.solution(n, info);
        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        /**/
        static int[] Arr;
        static ArrayList<int[]> AnswerArr = new ArrayList<>();
        static int Answer = 0;

        public static int[] solution(int n, int[] info) {

            Arr = new int[11];
            dfs(0,0,n,info);

            ArrayList<Point> pointArr = new ArrayList<>();
            for(int[] a:AnswerArr){
                pointArr.add(new Point(a.clone()));
            }

            Collections.sort(pointArr);

            if(pointArr.size()>0){
                int[] answer = {};
                answer = pointArr.get(0).scoreArr;
                return answer;
            }
            else{
                int[] answer = new int[1];
                answer[0] = -1;
                return answer;
            }
        }
        /**/

        static void dfs(int index, int count, int n, int[] info){
            if(count==n){
                int diff = getDiffScore(info);

                if(diff>=0){ //라이언이 이김
                    if(diff>Answer){ //새로 갱신
                        Answer = diff;
                        AnswerArr.clear();
                        AnswerArr.add(Arr.clone());
                    }
                    else if(diff==Answer){ //추가
                        if(diff!=0){
                            AnswerArr.add(Arr.clone());
                        }
                    }
                }
                return;
            }
            if(count>n || index>=11){
                return;
            }

            for(int i=0;i<=n;i++){
                Arr[index] = i;
                dfs(index+1,count+i,n,info);
                Arr[index] = 0;
            }
        }

        static int getDiffScore(int[] info){
            int lionScore = 0;
            int peachScore = 0;

            for(int i=0;i<=10;i++){
                //System.out.println(info[i]+" , "+Arr[i]);
                if(info[i]>Arr[i]){
                    peachScore+=(10-i);
                }
                else if(info[i] == Arr[i]){
                    if(!(info[i]==0 && Arr[i]==0)) {
                        peachScore+=(10-i);
                    }
                }
                else if(info[i]<Arr[i]){
                    lionScore+=(10-i);
                }
            }
            //System.out.println("LION : "+lionScore+" | PEACH : "+peachScore);
            if(lionScore - peachScore <= 0){
                return -1;
            }
            return lionScore - peachScore;
        }
    }

    static class Point implements Comparable<Point>{
        int[] scoreArr;

        public Point(int[] scoreArr) {
            this.scoreArr = scoreArr;
        }

        @Override
        public int compareTo(Point o) {
            int cmp = 0;
            for(int i=10;i>=0;i--){
                if(scoreArr[i] > o.scoreArr[i]){
                    cmp = -1;
                    break;
                }
                else if(scoreArr[i] < o.scoreArr[i]){
                    cmp = 1;
                    break;
                }
            }
            return cmp;
        }
    }

}
