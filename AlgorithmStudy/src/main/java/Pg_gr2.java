import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Pg_gr2 {

    public static void main(String[] args) throws Exception{
        //input
        String[] replies ={"AFFDEFDEFDEEC", "ABABABABBCCEF", "FFFFFFFFFFFFF", "FCBBBFCBBECBB"};
        int n = 2;
        int k = 4;
        int[] answer = Solution.solution(replies,n,k);
        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        /**/
        static int N;
        public static int[] solution(String[] replies, int n, int k) {
            int[] answer = new int[replies.length];
            Arrays.fill(answer,1);
            for(int r=0;r<replies.length;r++){
                N = replies[r].length();
                ArrayList<String>[] pattern = new ArrayList[N];
                for(int i=0;i<N;i++){
                    pattern[i] = new ArrayList<>();
                }
                for(int a = n;a<=N;a++){ //3개부터 N개까지의 패턴을 다 만들어 낼것임
                    //System.out.println(a);
                    for(int i=a-1;i<N;i++){
                        StringBuilder sb = new StringBuilder();
                        for(int c=i-(a-1);c<=i;c++){
                            sb.append(replies[r].charAt(c));
                        }
                        pattern[i].add(sb.toString());
                    }
                }

                //System.out.println(Arrays.toString(pattern));
                boolean flag = false;
                for(int a = n;a<=N;a++){ //3개, 4개, 5개, ... N개까지 살펴볼거야
                    if(flag){
                        break;
                    }
                    for(int i=a-1;i<N;i++){ //모든 패턴에 대해서
                        String cmp = pattern[i].get(a-n);
                        //System.out.println(cmp);
                        int count = 0;
                        for(int j=0;j<=k;j++){
                            int newIdx = i + a * j;
                            if(newIdx>=N){
                                break;
                            }
                            //System.out.println(i+"->"+newIdx);
                            if(!cmp.equals(pattern[newIdx].get(a-n))){ //중간에 다르면 정상, 다음 패턴 같은거 찾아보자
                                break;
                            }
                            else{
                                count++;
                            }
                        }
                        //System.out.println("COUNT : "+count);
                        if(count>=k){
                            //System.out.println("나 지금 변했어! -> "+cmp);
                            flag = true;
                            answer[r] = 0;
                            break;
                        }
                    }
                }


            }



            return answer;
        }
        /**/
    }
}
