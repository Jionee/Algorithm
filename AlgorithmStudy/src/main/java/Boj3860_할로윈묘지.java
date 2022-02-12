import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj3860_할로윈묘지 {
    static StringTokenizer st;
    static int W,H;
    static int G; //묘비의 개수 : -1
    static int E; //귀신 구멍 : -2
    static int[][] map;
    static ArrayList<Edge>[][] edge; //단방향 간선
    static ArrayList<EdgeInfo> edgeList = new ArrayList<>(); //단방향 간선리스트 edgeList[i][j][k] = i,j번에 Edge가 저장되어 있음
    static int[] dRow = {-1,1,0,0};//상하좌우
    static int[] dCol = {0,0,-1,1};
    static int[][] cost; //플로이드 워셜 최단거리 값 기록
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            if(row == 0 && col == 0){
                break;
            }
            map = new int[row+1][col+1];
            //edge = new ArrayList[row+1][col+1];
            cost = new int[row+1+1][col+1+1];

//            for(int i=1;i<row+1;i++){
//                for(int j=1;j<col+1;j++){
//                    edge[i][j] = new ArrayList<>();
//                }
//            }

            //묘비 입력
            G = Integer.parseInt(br.readLine());
            for(int i=1;i<G+1;i++){
                //묘비 매핑 -> Map에 -1로 기록
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y+1][x+1] = -1;
            }
            //귀신 구멍 입력
            E = Integer.parseInt(br.readLine());
            for(int i=1;i<E+1;i++){
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                map[y1+1][x1+1] = -2; //귀신구멍
                //귀신구멍 먼저 edge 연결
                //edge[y1+1][x1+1].add(new Edge(new Point(y2+1,x2+1),T));
                edgeList.add(new EdgeInfo(new Point(y1+1,x1+1),new Point(y2+1,x2+1),T));
            }
//            for (int[] ints : map) {
//                for (int anInt : ints) {
//                    System.out.print(anInt +" ");
//                }
//                System.out.println();
//            }
            //edge를 구성하자.
            //상하좌우, 다음꺼가 묘비면 edge X, 내가 귀신구멍일 경우 연결
            for(int i=1;i<row+1;i++){
                for(int j=1;j<col+1;j++){
                    if(map[i][j]!=-1 && map[i][j]!=-2 && !(i==row&&j==col)){ //잔디일때
                        //상하좌우 연결 -> 벽 제외
                        for(int k=0;k<4;k++){
                            int nextRow = i+dRow[k];
                            int nextCol = j+dCol[k];
                            if(1<=nextRow && nextRow <=row && 1<=nextCol && nextCol <= col){ //벽이 아닐 때 연결
                                if(map[nextRow][nextCol]!=-1){ //묘비가 아닐 때, 도착지점에 도착하지 않았을 때
                                    //edge[i][j].add(new Edge(new Point(nextRow,nextCol),1));
                                    edgeList.add(new EdgeInfo(new Point(i,j),new Point(nextRow,nextCol),1));
                                }
                            }
                        }
                    }
                }
            }
//            for (EdgeInfo edgeInfo : edgeList) {
//                System.out.println(edgeInfo);
//            }
            //여기서 밸만포드 -> 각 정답 출력하기
//            for (int i=1;i<edge.length;i++) {
//                for(int j=1;j<edge[i].length;j++){
//                    System.out.println(i+","+j+"->"+edge[i][j]);
//                }
//            }

            //시작점설정
            for(int i=1;i<row+1;i++){
                for(int j=1;j<col+1;j++){
                    cost[i][j] = INF;
                }
            }
            cost[1][1] = 0;

            //row*col - 1번 모든 edge를 돌면서 확인
//            for(int i=0;i<row*col-1;i++){
//                for(int r=1;r<edge.length;r++){
//                    for(int c=1;c<edge[r].length;c++){
//                        for(Edge e:edge[r][c]){ //모든 엣지들에 대하여
//                            if(cost[r][c]!=INF){
//                                int initToStartToEnd = cost[r][c] + e.weight;
//                                int initToEnd = cost[e.end.row][e.end.col];
//                                if(initToStartToEnd < initToEnd){
//                                    cost[e.end.row][e.end.col] = initToStartToEnd;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            for(int r=1;r<edge.length;r++){
//                for(int c=1;c<edge[r].length;c++){
//                    for(Edge e:edge[r][c]){ //모든 엣지들에 대하여
//                        if(cost[r][c]!=INF){
//                            int initToStartToEnd = cost[r][c] + e.weight;
//                            int initToEnd = cost[e.end.row][e.end.col];
//                            if(initToStartToEnd < initToEnd){
//                                System.out.println("Never");
//                                return;
//                            }
//                        }
//                    }
//                }
//            }
            for(int i=0;i<row*col-1;i++){
                for(EdgeInfo edgeInfo:edgeList){
                    if(cost[edgeInfo.start.row][edgeInfo.start.col]!=INF){
                        int initToStartToEnd = cost[edgeInfo.start.row][edgeInfo.start.col] + edgeInfo.weight;
                        int initToEnd = cost[edgeInfo.end.row][edgeInfo.end.col];
                        if(initToStartToEnd < initToEnd){
                            cost[edgeInfo.end.row][edgeInfo.end.col] = initToStartToEnd;
                        }
                    }
                }
            }

            for(EdgeInfo edgeInfo:edgeList){
                if(cost[edgeInfo.start.row][edgeInfo.start.col]!=INF){
                    int initToStartToEnd = cost[edgeInfo.start.row][edgeInfo.start.col] + edgeInfo.weight;
                    int initToEnd = cost[edgeInfo.end.row][edgeInfo.end.col];
                    if(initToStartToEnd < initToEnd){
                        System.out.println("Never");
                        return;
                    }
                }
            }



            if(cost[row][col] == INF){
                System.out.println("Impossible");
            }
            else{
                System.out.println(cost[row][col]);
            }
        }
    }
    static class EdgeInfo{
        Point start;
        Point end;
        int weight;

        public EdgeInfo(Point start, Point end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class Edge{
        Point end;
        int weight;

        public Edge(Point end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }


}
