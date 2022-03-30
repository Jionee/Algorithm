import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Pg_입국심사 {
    public static void main(String[] args) {
        //sum > n -> 심사받는 사람이 더 많으니까 초 줄이기
        //sum == n -> 정확히 같으니까 그거 리턴
        //sum < n

        //sum < n
        //sum == n
        //sum > n
        int num = 10;
        String str = String.valueOf(num);


        int n=0;
        int[] times=null;

        long answer = 0;
        //시간이 순차적으로 간다는 생각 버리기
        //시간이 말도 안되게 크다 -> 호옥시 이분탐색? 말안되지만 그래도 호옥시 가능?
        Arrays.sort(times);
        long start = 0;
        long end = (long) n * times[times.length-1];
        while(start<=end){
            long mid = (start+end)/2;
            long sum = 0;
            for(int i=0;i<times.length;i++){
                sum += mid / times[i];
            }
            System.out.println(sum+" "+start+","+end);
            if(sum < n){
                start = mid+1;
            }
            else{ //심사받는 사람이 더 많으니까 초 줄이기
                end = mid-1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }
}
