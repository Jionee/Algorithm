import java.io.*;
import java.util.*;

public class Boj17143_낚시왕 {
	static StringTokenizer st;
	static int R,C,M;
	static PriorityQueue<Shark>[][] Map;
	static PriorityQueue<Shark> [][] Copy;
	static int[] dRow = {0,-1,1,0,0};//0,상하우좌
	static int[] dCol = {0,0,0,1,-1};
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new PriorityQueue[R+1][C+1];
		for(int i=1;i<R+1;i++) {
			for(int j=1;j<C+1;j++) {
				Map[i][j] = new PriorityQueue<>();
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if(s!=0) {
				if(d==1 || d==2) { //상하
					s = s % (R+(R-2));
				}
				else { //좌우
					s = s % (C+(C-2));
					//System.out.println(C+(C-1)+" "+s);
				}
			}
			
			Map[r][c].add(new Shark(s,d,z));
			//System.out.println(Map[r][c]);
		}
		
		//start
		for(int c=1;c<=C;c++) { //낚시꾼 이동
			Copy = new PriorityQueue[R+1][C+1];
			for(int i=1;i<R+1;i++) {
				for(int j=1;j<C+1;j++) {
					Copy[i][j] = new PriorityQueue<>();
				}
			}
			
//			System.out.println("낚시꾼 이동 전");
//			printMap();
			
			//가장 가까운 상어 제거
			for(int r=1;r<=R;r++) {
				if(Map[r][c].size()!=0) {
					Shark getShark = Map[r][c].poll(); //상어 제거
					//System.out.println(r+","+c+" ->"+getShark);
					answer += getShark.size;
					break;
				}
			}
			
			//상어 이동
			for(int i=1;i<R+1;i++) {
				for(int j=1;j<C+1;j++) {
					//상어가 있으면 dfs
					if(Map[i][j].size()!=0) {
						Shark shark = Map[i][j].poll();
						dfs(i,j,shark,0);
					}
				}
			}
		
			//copy -> Map 넣기
			for(int i=1;i<R+1;i++) {
				for(int j=1;j<C+1;j++) {
					if(Copy[i][j].size()>0) {
						Map[i][j] = new PriorityQueue<>(Copy[i][j]);
					}
				}
			}
//			System.out.println("상어 이동 후");
//			printMap();
			
			//상어 잡아먹기
			for(int i=1;i<R+1;i++) {
				for(int j=1;j<C+1;j++) {
					//상어가 두 마리 이상이면
					if(Map[i][j].size()>=2) {
						Shark big = Map[i][j].poll();
						Map[i][j] = new PriorityQueue<>();
						Map[i][j].add(big);
						//System.out.println(c+"  두마리이상 "+Map[i][j]);
//						int size = Map[i][j].size();
//						for(int s=0;s<size-1;s++) {
//							//System.out.println(Map[i][j].size() + "S제거!!! : "+s+"  "+Map[i][j].poll());
//							Map[i][j].poll(); //제일큰거만 남기기
//						}
						//System.out.println("제일큰것만 남기기"+Map[i][j]);
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int row, int col, Shark shark, int count) {
		//System.out.println("###"+shark +"("+ row +"," + col +") COUNT : "+count);
		if(count>=shark.speed) {
			Copy[row][col].add(shark);
			return;
		}
		int newRow = row + dRow[shark.dir];
		int newCol = col + dCol[shark.dir];
		if(0<newRow && newRow<=R && 0<newCol && newCol<=C) {
			dfs(newRow,newCol,shark,count+1);
		}
		else {
			Shark newShark = new Shark(shark.speed,changeDir(shark.dir),shark.size); //방향바꾸기
			dfs(row,col,newShark,count);
		}
	}
	
	static int changeDir(int dir) {
		switch(dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;	
		}
		return -1;
	}
	
	static void printMap() {
		System.out.println("=================");
		for(int i=1;i<R+1;i++) {
			for(int j=1;j<C+1;j++) {
				System.out.println(i+","+j+" -> "+Map[i][j]);
			}
		}
		System.out.println("=================");
	}
	
	static class Shark implements Comparable<Shark>{
		int speed;
		int dir;
		int size;
		
		@Override
		public String toString() {
			return "Shark [speed=" + speed + ", size=" + size + ", dir=" + dir + "]";
		}

		public Shark(int speed, int dir, int size) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.size,size);
		}
	}

}
