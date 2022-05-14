import java.util.*;

public class Pg_nav {

    public static void main(String[] args) throws Exception{
        //input
//        int[][] recruits = {{1,50},{1,60},{3,70},{0,65},{2,50},{1,90}};
//        int s1 = 2;
//        int s2 = 70;
//        Solution.solution(recruits,s1,s2);
        String[] cities = {"a","b","c","d","e","f","g"};
        String[] roads = {"a b 1","a c 1","c d 3","b d 5","b e 6","d e 2","f g 8"};
        String[] cars = {"a 100 10","a 200 15","b 100 5","c 20 2","c 300 30","d 200 20","e 500 100","f 500 50","g 100 40"};
        String[] customers = {"g f 200","c e 50","d a 500","a b 50"};
        String[] answer = Solution.solution(cities,roads,cars,customers);
        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        /**/
        static StringTokenizer st;
        static HashMap<Integer,ArrayList<Point>> HashMap = new HashMap<>();
        static int INF = 1000000000;
        static int N;
        static ArrayList<Integer> answerArr = new ArrayList<>();

        public static String[] solution(String[] cities, String[] roads, String[] cars, String[] customers) {
            N = cities.length;
            int[][] adjacentMatrix = new int[N+1][N+1];

            for(int c=0;c<cars.length;c++){
                st = new StringTokenizer(cars[c]);
                int city = st.nextToken().charAt(0)-'a'+1;
                int weight = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());
                if(HashMap.containsKey(weight)){
                    ArrayList<Point> points = HashMap.get(weight);
                    points.add(new Point(city,price));
                    HashMap.put(weight,points);
                }
                else{
                    ArrayList<Point> points = new ArrayList<>();
                    points.add(new Point(city,price));
                    HashMap.put(weight,points);
                }
            }

            for(int i=1;i<N+1;i++){
                for(int j=1;j<N+1;j++){
                    if(i==j)
                        adjacentMatrix[i][j] = 0; //출발지-도착지 같은 경우 0
                    else
                        adjacentMatrix[i][j] = INF; //최단 경ㄹ를 구하고자 ㅇ하는 것이므로 INF로 초기화
                }
            }

            for(int r=0;r<roads.length;r++){
                st = new StringTokenizer(roads[r]);
                int x = st.nextToken().charAt(0)-'a'+1;
                int y = st.nextToken().charAt(0)-'a'+1;
                int z = Integer.parseInt(st.nextToken());
                //System.out.println(x+" "+y+" -> "+z);
                adjacentMatrix[x][y] = z;
                adjacentMatrix[y][x] = z;
            }


            //플로이드 워셜
            for(int middle=1;middle<N+1;middle++){ //경유지 선택
                for(int start = 1;start<N+1;start++){ //모든 쌍 만들기
                    for(int end=1;end<N+1;end++){
                        //1-1. start->middle->end가 start->end보다 작다면 갱신
                        if(start!=end){
                            int startToMiddleToEnd = adjacentMatrix[start][middle] + adjacentMatrix[middle][end];
                            int startToEnd = adjacentMatrix[start][end];
                            if( startToMiddleToEnd < startToEnd){
                                adjacentMatrix[start][end] = startToMiddleToEnd;
                            }
                        }
                    }
                }
            }

//            for(int i=1;i<N+1;i++){
//                for(int j=1;j<N+1;j++){
//                    if(adjacentMatrix[i][j] == INF){ //갈 수 없으면 0 출력
//                        System.out.print("X" +" ");
//                    }
//                    else{
//                        System.out.print(adjacentMatrix[i][j] +" ");
//                    }
//                }
//                System.out.println();
//            }
            //System.out.println(HashMap);
            //customers 돌면서 구하기
            for(int c=0;c<customers.length;c++){
                st = new StringTokenizer(customers[c]);
                int city1 = st.nextToken().charAt(0)-'a'+1;
                int city2 = st.nextToken().charAt(0)-'a'+1;
                int w = Integer.parseInt(st.nextToken());

                int cmp = Integer.MAX_VALUE;
                ArrayList<Integer> arr = new ArrayList<>();
                for(int key:HashMap.keySet()){
                    if(key>=w){
                        //비교 진행
                        ArrayList<Point> points = HashMap.get(key);
                        //System.out.println(points);
                        //System.out.println(key + ">= "+w);
                        for(int i=0;i<points.size();i++){
                            int a = adjacentMatrix[points.get(i).city][city1];
                            int b = adjacentMatrix[city1][city2];
                            if(a==INF || b==INF){
                                continue;
                            }
                            int total = (a+b) * points.get(i).price;
                            if(cmp>total){
                                arr = new ArrayList<>();
                                arr.add(points.get(i).city);
                                cmp = total;
                            }
                            else if(cmp==total){
                                arr.add(points.get(i).city);
                            }

                            //System.out.println(arr);

                        }
                        //System.out.println(arr);

                    }
                }
                Collections.sort(arr);
                answerArr.add(arr.get(0));
            }
            String[] answer = new String[customers.length];
            //System.out.println(answerArr);

            for(int i=0;i<answerArr.size();i++){
                answer[i] = String.valueOf((char) ((int)answerArr.get(i)+'a'-1));
            }
            return answer;
        }
        static class Point{
            int city;
            int price;

            @Override
            public String toString() {
                return "Point{" +
                        "city=" + city +
                        ", price=" + price +
                        '}';
            }

            public Point(int city, int price) {
                this.city = city;
                this.price = price;
            }
        }
        /**/
        static class Info implements Comparable<Info>{
            int end;
            int weight;

            @Override
            public String toString() {
                return "Info{" +
                        "end=" + end +
                        ", weight=" + weight +
                        '}';
            }

            public Info(int end, int weight) {
                this.end = end;
                this.weight = weight;
            }

            @Override
            public int compareTo(Info o){
                return Integer.compare(weight,o.weight);
            }
        }
    }
}
