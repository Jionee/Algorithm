import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_basic {
    static StringTokenizer st;
    static int N;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

    }
}
