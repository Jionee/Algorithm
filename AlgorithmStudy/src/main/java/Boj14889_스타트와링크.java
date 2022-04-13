import java.io.*;
import java.util.*;

public class Boj14889_스타트와링크 {
    static StringTokenizer st;
    static int N;
    static int[][] Map;
    static int[][] halfMap;
    static ArrayList<Integer> teamAArr = new ArrayList<>();
    static ArrayList<Integer> AllSet = new ArrayList<>();
    static int total = 0;
    static int answer = Integer.MAX_VALUE;
    static int factorial;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Map = new int[N+1][N+1];
        halfMap = new int[N+1][N+1];
        factorial = 1;
        for(int i=N;i>N/2;i--){ //어차피 절반까지만 하면 나머지는 teamA,teamB로 해서 다 볼 수 있으니까 nCm 값 구하기 (ex. 6C3이면 6*5*4/(3*2*1))
            factorial *= i;
            factorial /= (N+1)-i;
        }


        for(int i=1;i<N+1;i++){
            AllSet.add(i);
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<N+1;j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<N+1;i++){ //i,j꺼하고 j,i꺼하고 어차피 이 볼거니까 한쪽으로 더해놓기
            for(int j=i+1;j<N+1;j++){
                halfMap[i][j] = Map[i][j] + Map[j][i];
            }
        }

        //START
        for(int i=1;i<N+1;i++){ //1~6까지 돌아가면서 더 큰 애중에 만들 수 있는 조합을 찾을거임
            teamAArr.add(i);
            dfs(i,1);
            teamAArr.remove(teamAArr.size()-1);
        }
        System.out.println(answer);
    }

    static void dfs(int now, int count){
        if(total>factorial/2-1){ //조합의 절반까지 왔으면 그 이상은 안봐도 됨 (어차피 teamA에 속하지 않는애들은 teamB에 속해서, 그 앞의 연산에서 구해줬으므로 중복임)
            return;
        }

        if(count>=N/2){ //N/2명 팀이 구성된 경우
            total++;
            //Arr에 들어있는 애들 sum 하기
            Set<Integer> teamBSet = new HashSet<>(AllSet);
            Set<Integer> set = new HashSet<>(teamAArr);
            teamBSet.removeAll(set); //차집합으로 teamB에 있는 애들 구함
            ArrayList<Integer> teamBArr = new ArrayList<>(teamBSet);

            int sumA = 0, sumB = 0;
            for(int i=1;i<teamAArr.size();i++){ //i는 인덱스라고 생각, i번째 인덱스가 추가됐을 때
                for(int j=i-1;j>=0;j--){ //그 밑의 모든 0까지의 애들과의 스킬을 찾아서 더해야 함
                    sumA += halfMap[teamAArr.get(j)][teamAArr.get(i)]; //teamA꺼는 teamAArr의 j번째 번호인 애랑, i번째 번호인 애꺼를 halfMap(아까 반으로 접어서 합쳐놓은것)에서 찾아야 함
                    sumB += halfMap[teamBArr.get(j)][teamBArr.get(i)];
                }
            }
            answer = Math.min(answer,Math.abs(sumA - sumB));
            return;
        }

        for(int i=now+1;i<N+1;i++){ //지금 나보다 더 큰 인덱스 중에서 가능한 모든 조합 구하기 (now+1~N+1)
            teamAArr.add(i);
            dfs(i,count+1); //dfs고
            teamAArr.remove(teamAArr.size()-1);
        }
    }
}
