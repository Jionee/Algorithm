import java.io.*;
import java.util.*;

public class Pg_단속카메라 {
    static StringTokenizer st;
    static int N;
    static int[][] routes;
    static ArrayList<Point> point = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        routes = new int[N][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            routes[i][0] = Integer.parseInt(st.nextToken());
            routes[i][1] = Integer.parseInt(st.nextToken());
        }

        //=====
        //일단 출발점으로 정렬 한 후, point = 끝점, 다음거가 겹치면 다음거로 넘어가기, 안겹치면 answer++,point=next꺼 끝점
        for(int i=0;i<routes.length;i++){
            point.add(new Point(routes[i][0],routes[i][1]));
        }
        Collections.sort(point);

        int answer = 1;
        int camera = point.get(0).end;
        for(int i=0;i<point.size();i++){
            if(!(point.get(i).start<=camera && camera<=point.get(i).end)){
                answer++;
                camera = point.get(i).end;
            }
        }
        System.out.println(answer);
    }
    static class Point implements Comparable<Point>{
        int start;
        int end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(end,o.end);
        }
    }
}
