public class Pg_gr3 {

    public static void main(String[] args) throws Exception{
        //input
        int n = 7;
        String text = "abcdefghij";
        int second = 8;

        String answer = Solution.solution(n,text,second);
        System.out.println(answer);
    }

    static class Solution {
        /**/
        static int T,N,Sum;
        static StringBuilder sb = new StringBuilder();
        public static String solution(int n, String text, int second) {
            String answer = "";
            T = text.length();
            N = n;
            Sum = T+N;

            int time = second % Sum;
            //System.out.println(time);
            if(time<=T){
                //time이 맨 마지막에 있는 애 인덱스
                int count = 0;
                for(int t=time;t>0;t--){
                    if(count>=N){
                        break;
                    }
                    count++;
                    char ch = text.charAt(t-1);
                    if(ch==' '){
                        ch = '_';
                    }
                    sb.append(ch);
                    //System.out.println(sb.toString());
                }
                //System.out.println(sb.toString()+"->"+count);
                for(int c=count;c<N;c++){
                    if(count==N){
                        break;
                    }
                    sb.append("_");
                }
            }
            else{
                time = time%Sum - T%Sum;
                System.out.println(time);
                //time개만큼 _ 연결
                int count = 0;
                for(int t=time;t>0;t--){
                    sb.append("_");
                    count++;
                }
                int idx = text.length()-1;
                for(int c=count;c<N;c++){
                    //System.out.println(idx);
                    char ch = text.charAt(idx);
                    if(ch==' '){
                        ch = '_';
                    }
                    sb.append(ch);
                    idx--;
                    if(idx<0){
                        count = c+1;
                        for(int c2=count;c2<N;c2++){
                            sb.append("_");
                        }
                        break;
                    }
                }

            }
            answer = sb.reverse().toString();

            return answer;
        }
        /**/
    }
}
