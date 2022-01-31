import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1759 {
    static int L,C;
    static char[] arr;
    static List<String> result;

    public static void main(String[] args) throws IOException {
        //Input 입력받기
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i=0; st.hasMoreTokens() ; i++){
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr); //리스트 정렬

        //파라미터로는?
        //현재 방문중인 노드, 현재 몇번째 방문중인지, 현재까지 자음.모음 개수
        String pwd = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'a' || arr[i]  == 'e' || arr[i]  == 'i' || arr[i]  == 'o' || arr[i]  == 'u'){
                dfs(i,1, pwd+arr[i],1,0); //시작점 지정
            }
            else{
                dfs(i,1, pwd+arr[i],0,1); //시작점 지정
            }
        }
    }

    private static boolean isVowelConsonant(int vowelNum, int consonantNum){ //모음,자음
        if (vowelNum >= 1 && consonantNum >= 2)
            return true;
        else
            return false;
    }

    private static void dfs(int currentNodeIdx, int currentNum, String pwd , int vowelNum, int consonantNum){
        //1.체크인 - 생략가능
        //2.목적지인가?
        if (currentNum == L && isVowelConsonant(vowelNum,consonantNum)){
            System.out.println(pwd);
            return;
        }

        //3.가지치기 - 나보다 높은 알파벳 돌리기
        for (int i = currentNodeIdx + 1; i < arr.length; i++) {
            char next = arr[i];
            //4. 갈 수 있는가? - 생략가능
            //5. 간다. - currentNode변경, currentNum증가,code에 추가, 자음,모음 분별 => dfs 호출
            if (next == 'a' || next == 'e' || next == 'i' || next == 'o' || next == 'u'){
                dfs(i,currentNum+1,pwd+next,vowelNum+1,consonantNum);
            }
            else{
                dfs(i,currentNum+1,pwd+next,vowelNum,consonantNum+1);
            }
        }
        //6. 체크아웃 - 생략가능
    }
}