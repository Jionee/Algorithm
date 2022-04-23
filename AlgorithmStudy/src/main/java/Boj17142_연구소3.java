
import java.util.*;
import java.io.*;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Boj17142_연구소3 {
	static StringTokenizer st;
	static int N,M;
	static int[][] Map;
	static ArrayList<Point> Blank = new ArrayList<>();
	static ArrayList<Point> Virus = new ArrayList<>();
	static boolean[] VirusVisit;
	static ArrayList<Point> ActiveVirus = new ArrayList<>();
	static int blankNum = 0;
	static int[][] visit;
	static int[] dRow = {-1,1,0,0};
	static int[] dCol = {0,0,-1,1};
	static boolean flag = false;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String args[]) throws Exception {
		
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new int[N+1][N+1];
		
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<N+1;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == 0) {//빈칸
					Blank.add(new Point(i,j));
				}
				else if(Map[i][j] == 2) { //바이러스
					Virus.add(new Point(i,j));
				}
			}
		}
		blankNum = Blank.size();
		//System.out.println(blankNum);
		if(blankNum==0) {
			System.out.println(0);
			System.exit(0);
		}
		
		//start
		VirusVisit = new boolean[Virus.size()];
		//M개의 바이러스 고르기
		dfs(0,0);
		
		if(!flag) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}
	
	static void dfs(int index,int count) {
		//System.out.println(ActiveVirus);
		if(count>=M) {
			//spread
			int sp = spread();
			if(sp!=-100) {
				//System.out.println("갱신 : "+sp);
				answer = Math.min(answer, sp);
				flag = true;
			}
			return;
		}
		for(int i=index;i<Virus.size();i++) {
			if(!VirusVisit[i]) {
				VirusVisit[i] = true;
				ActiveVirus.add(Virus.get(i));
				dfs(i+1,count+1);
				VirusVisit[i] = false;
				ActiveVirus.remove(ActiveVirus.size()-1);
			}
		}
	}
	
	static int spread() {
		//visit 방문처리
		int total = 0;
		
		visit = new int[N+1][N+1]; //각 조합마다 따로 쓰여야 함, 원본 Map은 건드리면 안됨. (다른곳에서도 써야함)
		
		for(int i=1;i<N+1;i++) { //visit 초기화
			for(int j=1;j<N+1;j++) {
				int tmp = Map[i][j];
				if(tmp==2) {
					tmp = -99; //비활성 바이러스
				}
				visit[i][j] = tmp;
			}
		}
		for(Point now:ActiveVirus) {
			visit[now.row][now.col] = 99; //활성바이러스
		}

		int time = -1;
		Queue<Point> queue = new LinkedList<>();
		for(Point active:ActiveVirus){
			queue.add(new Point(active.row,active.col,1)); //count 1로 초기화
		}

		while(!queue.isEmpty()) {
			Point nowVirus = queue.poll();
			if(total>=blankNum) { //먼저 만족했는지 확인하고
				return time;
			}
			time = Math.max(time,nowVirus.count); //time 갱신하기

			for(int i=0;i<4;i++) {
				int newRow = nowVirus.row + dRow[i];
				int newCol = nowVirus.col + dCol[i];
				if(0<newRow && newRow<=N && 0<newCol && newCol<=N) {
					if(visit[newRow][newCol]==0) { //빈칸이라면
						visit[newRow][newCol] = visit[nowVirus.row][nowVirus.col]+1; //빈칸 들름
						total++;
						queue.add(new Point(newRow,newCol,nowVirus.count+1));
					}
					else if(visit[newRow][newCol]==-99) { //비활성바이러스라면
						visit[newRow][newCol] = 99; //빈칸 들름
						queue.add(new Point(newRow,newCol,nowVirus.count+1)); //비활성바이러스의 경우 큐에 추가
					}
				}
			}
		}
		return -100;
	}

	static void printMap() {
		System.out.println("=========");
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				System.out.print(visit[i][j]+" ");
			}
			System.out.println();
		}
	}
	static class Point{
		int row;
		int col;
		int count;
		
		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", count=" + count + "]";
		}

		public Point(int row, int col, int count) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
		}

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
	
}