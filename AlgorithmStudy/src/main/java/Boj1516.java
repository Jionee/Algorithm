import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1516 {
    static int N;
    static Building[] building;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        building = new Building[N+1];

//        for(int n=1;n<N+1;n++){
//            StringTokenizer st = new StringTokenizer(br.readLine());
//
//            building[n] = new Building(list,value);
//        }
    }
}
class Building{
    ArrayList<Integer> list = new ArrayList<>();
    int value;

    public Building(int value) {
        this.value = value;
    }
}
