import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj16638_괄호추가하기2 {
    static StringTokenizer st;
    static int N;
    static boolean[] brackets; //괄호를 표시하는 배열
    static Formula[] input; //수식의 문자와 우선순위를 저장
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        brackets = new boolean[N];
        input = new Formula[N];

        String str = br.readLine();
        for(int s=0;s<str.length();s++){
            char c = str.charAt(s);
            //우선순위 부여해서 저장하기
            if(c=='*'){
                input[s] = new Formula(c,2);
            }
            else if(c=='+' || c=='-'){
                input[s] = new Formula(c,3);
            }
            else{
                input[s] = new Formula(c,0);
            }
        }

        //START
        //연산자를 기준으로 괄호를 씌운다.
        makeBrackets(1);
        System.out.println(answer);
    }

    static void makeBrackets(int count){
        if(count>=N){
            //후위연산 계산 수행
            //System.out.println(Arrays.toString(brackets));
            ArrayList<Formula> postFix = inFixToPostFix();
            int res = postFixToResult(postFix);
            answer = Math.max(answer,res);
            return;
        }
        if(count==1){ //첫번째 연산자일 경우
            brackets[count] = true;
            makeBrackets(count+2);
            brackets[count] = false;
        }
        else{
            if(!brackets[count-2]){ //두번째 이상 연산자의 경우, 직전 연산자에 괄호가 없어야 괄호를 넣을 수 있다.
                brackets[count] = true;
                makeBrackets(count+2);
                brackets[count] = false;
            }
        }

        //괄호를 안넣는 경우
        makeBrackets(count+2);

    }
    //후위표기식 계산
    static int postFixToResult(ArrayList<Formula> postFix){
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i=0;i<N;i++){
            Formula now = postFix.get(i);
            if(now.priority==0){ //숫자면 stack에 push
                stack.push(now.character - '0');
            }
            else{ //연산자면 stack에서 pop 해와서 계산하기
                int num1 = stack.pop();
                int num2 = stack.pop();
                res = calc(num2,num1,now.character);
                stack.push(res);
            }
        }
        return stack.pop();
    }

    static int calc(int num1, int num2, char op){
        if(op=='+'){
            return num1+num2;
        }
        else if(op=='-'){
            return num1-num2;
        }
        else if(op=='*'){
            return num1*num2;
        }
        return -1;
    }

    //중위 표기식 -> 후위 표기식
    static ArrayList<Formula> inFixToPostFix(){
        ArrayList<Formula> postFix = new ArrayList<>();
        Stack<Formula> stack = new Stack<>();

        for(int i=0;i<N;i++){
            Formula now = input[i];
            if(now.priority==0){ //숫자면 바로 넣기
                postFix.add(now);
            }
            else{
                int priority = now.priority;
                if(brackets[i]){ //괄호를 씌운 연산자라면 우선순위 변경
                    priority = 1;
                }
                while(!stack.isEmpty() && stack.peek().priority <= priority){ //스택이 차있고, 지금 우선순위가 더 높을때까지 계속 진행
                    postFix.add(stack.pop());
                }
                stack.push(new Formula(now.character,priority));
            }
        }
        //남은거 다 뽑기
        while(!stack.isEmpty()){
            postFix.add(stack.pop());
        }
        return postFix;
    }

    static class Formula{
        char character;
        int priority; //숫자:0, 괄호:1, *:2, +,-:3

        public Formula(char character, int priority) {
            this.character = character;
            this.priority = priority;
        }
    }
}
