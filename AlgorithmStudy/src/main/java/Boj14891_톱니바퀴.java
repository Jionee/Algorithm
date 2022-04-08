import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14891_톱니바퀴 {
    static StringTokenizer st;
    static int K;
    static int[][] Saw = new int[4+1][8+1]; //Saw[i][j] : 톱니바퀴 i번째의 j번째 톱니
    static int[] sawDir = new int [4+1];
    static int answer;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=1;i<5;i++){
            String string = br.readLine();
            for(int j=0;j<string.length();j++){
                Saw[i][j+1] = Integer.parseInt(String.valueOf(string.charAt(j)));
            }
        }
        K = Integer.parseInt(br.readLine());

        //==========
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int sawNum = Integer.parseInt(st.nextToken()); //0:N극, 1:S극
            int dir = Integer.parseInt(st.nextToken()); //1:시계방향 , -1:반시계방향

            //바뀌기 전에 비교 후 1,2,3,4 각각 무슨 방향으로 돌지 확인해야함 -> sawDir[i] 설정
            int one_3 = getPos(1,3+sawDir[1]);
            int one_7 = getPos(1,7+sawDir[1]); //사실 필요없음
            int two_3 = getPos(2,3+sawDir[2]);
            int two_7 = getPos(2,7+sawDir[2]);
            int three_3 = getPos(3,3+sawDir[3]);
            int three_7 = getPos(3,7+sawDir[3]);
            int four_3 = getPos(4,3+sawDir[4]); //사실 필요없음
            int four_7 = getPos(4,7+sawDir[4]);

            if(sawNum == 1){ //톱니바퀴 1번
                sawDir[1] += dir * (-1);
                if(one_3 != two_7){ //둘이 다르면 방향 바꾸기
                    dir *= -1;
                }
                else{
                    dir = 0; //같으면 그 이후로 안움직임
                }
                sawDir[2] += dir * (-1);
                if(two_3 != three_7){
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[3] += dir * (-1);
                if(three_3 != four_7){
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[4] += dir * (-1);
            }
            //2 -> 1 , 2 -> 3 -> 4
            else if(sawNum == 2){
                sawDir[2] += dir * (-1); //시계방향이면 -1, 반시계방향이면 1을 더함

                //2 -> 1
                int twoToOne = 0;
                if(one_3 != two_7){ //둘이 다르면 방향 바꾸기
                    twoToOne = dir * -1;
                }
                sawDir[1] += twoToOne * (-1);

                //2 -> 3 -> 4
                if(two_3 != three_7){
                    dir *= -1;
                }
                else{ //같으면 그 이후로 안움직임
                    dir = 0;
                }
                sawDir[3] += dir * (-1);
                if(three_3 != four_7){
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[4] += dir * (-1);
            }
            //3 -> 2 -> 1 , 3 -> 4
            else if(sawNum == 3){
                sawDir[3] += dir * (-1); //시계방향이면 -1, 반시계방향이면 1을 더함

                //3 -> 4
                int threeToFour = 0;
                if(three_3 != four_7){
                    threeToFour = dir * -1;
                }
                sawDir[4] += threeToFour * (-1);

                //3 -> 2 -> 1
                if(two_3 != three_7){ //둘이 다르면 방향 바꾸기
                    dir *= -1;
                }
                else{ //같으면 그 이후로 안움직임
                    dir = 0;
                }
                sawDir[2] += dir * (-1);
                if(one_3 != two_7){
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[1] += dir * (-1);
            }
            //4 -> 3 -> 2 -> 1
            else{
                sawDir[4] += dir * (-1); //시계방향이면 -1, 반시계방향이면 1을 더함
                if(three_3 != four_7){
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[3] += dir * (-1);
                if(two_3 != three_7){
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[2] += dir * (-1);
                if(one_3 != two_7){ //둘이 다르면 방향 바꾸기
                    dir *= -1;
                }
                else{
                    dir = 0;
                }
                sawDir[1] += dir * (-1);
            }
        }
        //다 돌림, 점수 계산 고고
        getScore();
        System.out.println(answer);
    }

    static void getScore(){
        for(int i=1;i<5;i++){
            int value = getPos(i,1+sawDir[i]); //1(12시방향)에 있는 값
            if(value==1){ //S극이면
                answer += Math.pow(2,i-1);
            }
        }
    }

    static int getPos(int sawNum, int p){
        int pos = p % 8;
        if(pos == 0){ //나머지가 0이면 8로
            pos = 8;
        }
        else if(pos < 0){ //나머지가 음수면 ex) 나머지가 -1이면 7이 나와야 한다.
            pos *= -1;
            pos = 8 - pos;
        }
        return Saw[sawNum][pos]; //Saw의 pos1칸을 확인하면 값을 얻을 수 있음
    }
}
