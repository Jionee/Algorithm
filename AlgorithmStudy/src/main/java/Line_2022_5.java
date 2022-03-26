import java.io.*;
import java.util.*;

public class Line_2022_5 {
    static Integer[] abilities = {2, 8, 3, 6, 1, 9, 1, 9};
    static int k = 2;
    static ArrayList<Point> Arr = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        long answer = 0;
        //일단 sorting 하고, 맨 앞에서부터 삭제하기
        Arrays.sort(abilities, Collections.reverseOrder());
        for(int i=0;i<abilities.length;){
            if(i== abilities.length-1){
                Arr.add(new Point(abilities[i],0));
            }
            else{
                int a = i;
                int b = i+1;
                Arr.add(new Point(abilities[b],abilities[a]-abilities[b]));
            }

            i+=2;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2,o1);
            }
        });

        for(Point pt : Arr){
            System.out.println(pt);
            answer+=pt.base;
            queue.add(pt.diff);
        }

        for(int i=0;i<k;i++){
            int s = queue.poll();
            answer+=s;
        }
        System.out.println(answer);
        //return answer;
    }
    static class Point{
        int base;
        int diff;

        public Point(int base, int diff) {
            this.base = base;
            this.diff = diff;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "base=" + base +
                    ", diff=" + diff +
                    '}';
        }
    }
}
