import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj16637_괄호추가하기 {
    static StringTokenizer st;
    static int N;
    static ArrayList<Character> ops = new ArrayList<>();
    static ArrayList<Integer> nums = new ArrayList<>();
    static int answer = Integer.MIN_VALUE;

    static Formula[] input2;
    static int answer2 = Integer.MIN_VALUE;
    static boolean[] brackets;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //연산자 우선순위가 동일하다 -> 왼쪽에서부터 계산하면 됨
        //괄호 백트래킹으로 추가하기 -> 실제로 추가하는 방식도 가능하지만 배열을 가지고 표현해보자
        //A+B+C+D 에서 괄호는 중복으로 씌울 수 없으니까, 연산자 하나 당 씌운다고 생각할 수 있음
        //이전 연산자에 씌웠으면 다음 연산자에 괄호 씌울 수 없음
        //괄호를 씌웠으면 A+(B+C)+D (괄호 안 먼저 진행) + 이전까지 sum 결과

        N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        for(int n=0;n<N;n++){
            if(n%2==0){
                nums.add(Integer.parseInt(String.valueOf(input[n])));
            }
            else{
                ops.add(input[n]);
            }
        }
      //  System.out.println(nums);
      //  System.out.println(ops);

        //괄호 백트래킹 하기
//        dfs(nums.get(0),0);
//        System.out.println(answer);

        input2 = new Formula[N];
        for(int n=0;n<N;n++){
            if(n%2==0){
                input2[n] = new Formula(input[n],0);
            }
            else{
                input2[n] = new Formula(input[n],2);
            }
        }
        brackets = new boolean[N];
        dfs2(1);
        System.out.println(answer2);

    }

    static void dfs2(int count){
        if(count>=N){
            //계산하기
            int res = calculate();
            answer2 = Math.max(answer2,res);
            return;
        }
        if(count==1){ //첫번째 연산자
            brackets[count] = true;
            dfs2(count+2);
            brackets[count] = false;
        }
        else{
            if(!brackets[count-2]){
                brackets[count] = true;
                dfs2(count+2);
                brackets[count] = false;
            }
        }

        dfs2(count+2);
    }

    static int calculate(){
        Formula[] copy = new Formula[N];
        for(int i=0;i<N;i++){
            copy[i] = new Formula(input2[i].character,input2[i].priority);
        }

        for(int i=0;i<N;i++){
            if(brackets[i]){
                copy[i] = new Formula(copy[i].character, 1); //괄호 우선순위 바꿔주기
            }
        }
        //System.out.println(Arrays.toString(copy));
        //후위표기식으로 바꾸기
        ArrayList<Formula> postFix = inFixToPostFix(copy);

        //계산 고고
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<N;i++){
            int res = 0;
            if(postFix.get(i).priority==0){
                stack.push(postFix.get(i).character - '0');
            }
            else{
                int num1 = stack.pop();
                int num2 = stack.pop();
                res = calc(postFix.get(i).character,num2,num1);
                stack.push(res);
            }
        }
        return stack.pop();
    }

    static ArrayList<Formula> inFixToPostFix(Formula[] copy){
        ArrayList<Formula> postFix = new ArrayList<>();
        Stack<Formula> stack = new Stack<>();

        for(int i=0;i<N;i++){
            Formula now = copy[i];
            if(now.priority==0){
                postFix.add(now);
            }
            else{
                while(!stack.isEmpty() && stack.peek().priority<=now.priority){
                    postFix.add(stack.pop());
                }
                stack.push(now);
            }
        }

        while(!stack.isEmpty()){
            postFix.add(stack.pop());
        }
        return postFix;
    }

    static void dfs(int sum, int idx){
        if(idx>=ops.size()){
            answer = Math.max(answer,sum);
            return;
        }
        //괄호 없는 버전 sum+A
        int res = calc(ops.get(idx),sum,nums.get(idx+1));
        dfs(res,idx+1);

        //괄호 있는 버전 sum+(A+B)
        if(idx + 1 < ops.size()){ //다음 연산자가 있어야 괄호 연산 가능
            int bracket = calc(ops.get(idx+1),nums.get(idx+1),nums.get(idx+2));
            res = calc(ops.get(idx),sum,bracket);
            dfs(res,idx+2);
        }
    }

    static int calc(char op, int num1, int num2){
        if (op == '+') {
            return num1 + num2;
        }
        else if(op == '-'){
            return num1 - num2;
        }
        else if(op == '*'){
            return num1 * num2;
        }
        return -1;
    }

    static class Formula{
        char character;
        int priority;

        public Formula(char character, int priority) {
            this.character = character;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Formula{" +
                    "character=" + character +
                    ", priority=" + priority +
                    '}';
        }
    }
}
