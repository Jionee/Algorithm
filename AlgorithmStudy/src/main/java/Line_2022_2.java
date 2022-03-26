import java.io.*;
import java.util.*;

public class Line_2022_2 {
    static StringTokenizer st;
    static int N,M;
//    static String[] remote_tasks;
//    static String[] office_tasks;
//    static String[] employees;
    static ArrayList<Person> PeopleArr = new ArrayList<>();
    static ArrayList<Integer>[] Team;

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //=&&&
        N = 2;
        String[] remote_tasks = {"design"};
        String[] office_tasks = {"building","supervise"};
        String[] employees = {"2 design","1 supervise building design","1 design","2 design"};
        //=&&&
        Team = new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            Team[i] = new ArrayList<>();
        }
        //처음에 Person 구성하기
        Set<String> remoteSet = new HashSet<String>(Arrays.asList(remote_tasks));
        Set<String> officeSet = new HashSet<String>(Arrays.asList(office_tasks));

        for(int i=0;i< employees.length;i++){
            st = new StringTokenizer(employees[i]);
            int team = Integer.parseInt(st.nextToken());
            boolean isHome = false;
            while(st.hasMoreTokens()){
                if(officeSet.contains(st.nextToken())) {//하나라도 office면 true로
                    isHome = true;
                }
            }
            PeopleArr.add(new Person(i+1, team,isHome));
        }
//        for(Person p:PeopleArr){ ///////////
//            System.out.println(p);
//        }

        int[] teamNum = new int[N+1];
        //팀단위로 false인 애 다 넣기
        for(int i=0;i<PeopleArr.size();i++){
            Person now = PeopleArr.get(i);
            if(!now.isHome){
                Team[now.team].add(now.num);
            }
            teamNum[now.team] += 1;
        }

//        for(ArrayList a:Team){ ///////////
//            System.out.println(a);
//        }
//        for(int a:teamNum){
//            System.out.println(a);
//        }

        //팀으로 돌면서 다 false인지 알려면, 그 전 갯수랑 다른지 확인하기
        for(int i=1;i<N+1;i++){
            if(teamNum[i]==Team[i].size()){ //다 false라는 뜻 -> 제일 빠른 애는 빼기
                Collections.sort(Team[i]);
                Team[i].remove(0);
            }
        }
//        for(ArrayList a:Team){ ///////////
//            System.out.println(a);
//        }
        ArrayList<Integer> ansArr = new ArrayList<>();
        for(int i=1;i<N+1;i++){
            for(int a:Team[i]){
                ansArr.add(a);
            }
        }
        int[] answer = new int[ansArr.size()];
        for(int i=0;i<ansArr.size();i++){
            answer[i] = ansArr.get(i);
        }

        for(int i:answer){
            System.out.println(i);
        }
    }
    static class Person{
        int num;
        int team;
        boolean isHome;

        public Person(int num, int team, boolean isHome) {
            this.num = num;
            this.team = team;
            this.isHome = isHome;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "num=" + num +
                    ", team=" + team +
                    ", isHome=" + isHome +
                    '}';
        }
    }
}
