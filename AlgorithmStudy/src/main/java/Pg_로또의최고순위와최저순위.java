import java.util.*;

public class Pg_로또의최고순위와최저순위 {

    public static void main(String[] args) throws Exception{
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

        Solution.solution(lottos,win_nums);
    }

    static class Solution {

        public static int[] solution(int[] lottos, int[] win_nums) {
            int[] answer = new int[2];
            int zeroCount = 0;
            int winCount = 0;
            Set<Integer> winNumsSet = new HashSet<>();
            for(int win:win_nums){
                winNumsSet.add(win);
            }

            for(int i=0;i<lottos.length;i++){
                if(lottos[i]==0){
                    zeroCount++;
                }
                else{
                    if(winNumsSet.contains(lottos[i])){
                        winCount++;
                    }
                }
            }

            int MaxCount = winCount + zeroCount;
            int minCount = winCount;
            Map<Integer,Integer> ScoreMap = new HashMap<>();
            ScoreMap.put(6,1); //개수, 등수
            ScoreMap.put(5,2);
            ScoreMap.put(4,3);
            ScoreMap.put(3,4);
            ScoreMap.put(2,5);
            ScoreMap.put(1,6);
            ScoreMap.put(0,6);

            answer[1] = ScoreMap.get(minCount);
            answer[0] = ScoreMap.get(MaxCount);

            //System.out.println(Arrays.toString(answer));
            return answer;
        }
    }
}
