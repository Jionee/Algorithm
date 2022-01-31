import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2143 {
    static Long T;
    static int N,M;
    static int[] A,B;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Long.parseLong(br.readLine());
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0;n<N;n++){
            A[n] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int m=0;m<M;m++){
            B[m] = Integer.parseInt(st.nextToken());
        }

        List<Long> subA = new ArrayList<>();
        List<Long> subB = new ArrayList<>();
        //부배열 구하기
        for(int i=0;i<N;i++){
            long sum = A[i];
            subA.add(sum);
            for(int j=i+1;j<N;j++){
                sum += A[j];
                subA.add(sum);
            }
        }
        for(int i=0;i<M;i++){
            long sum = B[i];
            subB.add(sum);
            for(int j=i+1;j<M;j++){
                sum += B[j];
                subB.add(sum);
            }
        }
        //정렬
        Collections.sort(subA);//오름차순
        Collections.sort(subB,Comparator.reverseOrder());//내림차순
        //Collections.reverse(subB);//내림차순

        //투포인터 사용
        int pointA=0, pointB=0;
        long result = 0;
        while(pointA < subA.size() && pointB < subB.size()){
            long currentA = subA.get(pointA);
            long target = T - currentA;
            //currentB == target -> subA,subB같은 수 개수 체크 -> 답 도출, pointA+,pointB+
            if(subB.get(pointB) == target){
                long countA = 0;
                long countB = 0;
                while (pointA < subA.size() && subA.get(pointA) == currentA){
                    countA++;
                    pointA++;
                }
                while (pointB < subB.size() && subB.get(pointB) == target){
                    countB++;
                    pointB++;
                }
                result += countA * countB;
            }
            //currentB > target -> pointB++
            else if(subB.get(pointB) > target){
                pointB++;
            }
            //currentB < target -> pointA++
            else{
                pointA++;
            }
        }
        System.out.println(result);
    }
}
