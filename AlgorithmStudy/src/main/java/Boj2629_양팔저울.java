import java.io.*;
import java.util.StringTokenizer;

public class Boj2629_양팔저울 {
    static StringTokenizer st;
    static int N,K;
    static int[] weights;
    static boolean[][] isPossible;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        weights = new int[N];
        isPossible = new boolean[N+1][30001]; //추 무게의 최대 총합은 15000, -도 고려

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            weights[i] = Integer.parseInt(st.nextToken());
        }

        //같은 무게의 추가 여러 개 있을 수도 있음
        dfs(0,15000);

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int k=0;k<K;k++){
            int num = Integer.parseInt(st.nextToken())+15000;
            //Y,N 출력하기
            if(num>15000+15000){
                sb.append("N ");
            }else{
                if(isPossible[N][num]){
                    sb.append("Y ");
                }
                else{
                    sb.append("N ");
                }
            }

        }
        System.out.println(sb.toString());
    }

    static void dfs(int count, int sum){
        if(isPossible[count][sum]){
            return;
        }
        isPossible[count][sum] = true;

        if(count>=N){
            return;
        }

        //오른쪽에 놓은 경우
        dfs(count+1,sum+weights[count]);
        //둘 다 안놓은 경우
        dfs(count+1,sum);
        //왼쪽에 놓은 경우(타깃과 같이)
        dfs(count+1,sum-weights[count]);
    }

//    static void dfs(int index,String str){
//        System.out.println(str);
//        for(int i=index;i<N;i++){
//            if(!visit[i]){
//                visit[i] = true;
//                dfs(index+1,str +" "+i);
//                visit[i] = false;
//            }
//        }
//    }
}
