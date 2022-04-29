import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17825_주사위윷놀이2 {
    static StringTokenizer st;
    static int N;
    static int answer;
    static int[] Dice = new int[10+1]; //주사위 1~10
    static int[] Order = new int[10+1]; //말 순서 1~10
    static ArrayList<Integer> OrderArr = new ArrayList<>();
    static Node root = new Node();

    static Node[] Mal = new Node[4+1]; //말 위치 1~4

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<11;i++){
            Dice[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(Mal,root); //처음 말들 초기화

        //주사위는 주어졌고, Order만 구하면 됨
        //Order를 하나씩 구하면서 단계를 진행하면 안됨 (백트래킹 어려움) -> Order를 다 구하고 하자
        initMap();
        permutation(1);
        System.out.println(answer);
    }

    static void permutation(int count){
        if(count>=11){
            Arrays.fill(Mal,root); //처음 말들 초기화
            answer = Math.max(answer,gameStart());
            for(int i=1;i<5;i++){ //말들 초기화
                Mal[i].isHere = false;
            }
            return;
        }
        for(int i=1;i<5;i++){ //말 1~4
            Order[count] = i; //말i가 Order 순서에 들어감
            permutation(count+1);
        }
    }

    static int gameStart(){
        int score = 0;
        //Order, Dice 가 주어짐. Order[i] i번째에 존재하는 말(1~4)이 Dice만큼 이동
        //이동하는 index가 뭐냐에 따라 blue인지 그냥인지가 결정됨
        boolean[] Goal = new boolean[5];


        for(int t=1;t<11;t++){
            //System.out.println(Arrays.toString(Mal));
            int dice = Dice[t]; //1~5
            int nowMalIndex = Order[t]; //1~4
            Node nowMal = Mal[nowMalIndex]; //지금 어느 노드에 있는지 -> dice만큼 이동시키기

            nowMal.isHere = false; //이동시킬거니까 말 없애주기

            //이미 goal 들어간 말인지 확인
            if(Goal[nowMalIndex]){
                return 0;
            }
            for(int i=0;i<dice;i++){ //dice만큼 반복하면서 노드 이동
                if(i==0 && nowMal.isBlue){ //맨 처음이 blue면 blue로
                    nowMal = nowMal.getChild(true);
                }
                else{
                    nowMal  = nowMal.getChild(false);
                }

                if(nowMal.isEnd){ // 골이면 바로 break
                    Goal[nowMalIndex] = true;
                    break;
                }
                if(i==dice-1){ //마지막이면
                    if(!nowMal.isHere) { //그 자리에 말이 있는지 확인
                        score += nowMal.value;
                        nowMal.isHere = true;
                        Mal[nowMalIndex] = nowMal;
                    }
                    else{
                        return 0;
                    }
                }
            }
        }
        return score;
    }

    static void initMap(){
        Node now = root;
        //40 -> 도착
        Node last40 = new Node(40);
        last40.child = new Node(0);
        last40.child.isEnd = true;

        //25 -> 30 -> 35 -> 40
        Node middle25 = new Node(25);
        Node tmp = middle25.child = new Node(30);
        tmp = tmp.child = new Node(35);
        tmp.child = last40;

        //2,4,6,8,10,13,16,19,25
        now = now.child = new Node(2);
        now = now.child = new Node(4);
        now = now.child = new Node(6);
        now = now.child = new Node(8);
        now = now.child = new Node(10);
        now.isBlue = true;

        tmp = now.blueChild = new Node(13);
        tmp = tmp.child = new Node(16);
        tmp = tmp.child = new Node(19);
        tmp.child = middle25;

        now = now.child = new Node(12);
        now = now.child = new Node(14);
        now = now.child = new Node(16);
        now = now.child = new Node(18);
        now = now.child = new Node(20);
        now.isBlue = true;

        tmp = now.blueChild = new Node(22);
        tmp = tmp.child = new Node(24);
        tmp.child = middle25;

        now = now.child = new Node(22);
        now = now.child = new Node(24);
        now = now.child = new Node(26);
        now = now.child = new Node(28);
        now = now.child = new Node(30);
        now.isBlue = true;

        tmp = now.blueChild = new Node(28);
        tmp = tmp.child = new Node(27);
        tmp = tmp.child = new Node(26);
        tmp.child = middle25;

        now = now.child = new Node(32);
        now = now.child = new Node(34);
        now = now.child = new Node(36);
        now = now.child = new Node(38);
        now.child = last40;
    }

    static class Node{
        int value; //점수
        boolean isEnd; //골인지
        boolean isBlue; //파란색 부분인지
        boolean isHere; //말이 여기 서있는지
        Node child;
        Node blueChild;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }

        Node getChild(boolean imBlue){
            if(!imBlue)
                return child; //그냥 길
            else
                return blueChild; //파란길
        }
    }
}
