import java.util.*;
import java.io.*;

public class Boj23290_마법상어와복제 {
	static int M,S;
	static StringTokenizer st;
	static int[] dRow = {1,0,-1,-1,-1,0,1,1};
	static int[] dCol = {-1,-1,-1,0,1,1,1,0};
	static int[][] smell = new int[5][5];
	static int[] dSharkRow = {0,-1,0,1,0};
	static int[] dSharkCol = {0,0,-1,0,1};
	static int answer = 0;
	static boolean[][] sharkVisit = new boolean[5][5];

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("시작");
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		ArrayList<Fish>[][] Map = new ArrayList[5][5];
		for(int i=1;i<5;i++) {
			for(int j=1;j<5;j++) {
				Map[i][j] = new ArrayList<>();
			}
		}
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			Map[row][col].add(new Fish(dir%8,false)); //물고기 살아있어요
		}			
		st = new StringTokenizer(br.readLine());
		Shark shark = new Shark(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		
		//START
		dfs(0,Map,shark);
		System.out.println(answer);
	}
	
	static void dfs(int count, ArrayList<Fish>[][] Map, Shark shark) {
		if(count>=S) {
			//TODO : 물고기 총 수 세기
			int countFish = 0;
			for(int i=1;i<5;i++) {
				for(int j=1;j<5;j++) {
					for(Fish fish:Map[i][j]) {
						if(!fish.isDie) {
							countFish++;
						}
					}
				}
			}
			answer = Math.max(answer, countFish);
			return;
		}
		
		
		//0.물고기 복제
		ArrayList<Fish>[][] copyMap = copyMap(Map);
		
//		System.out.println("=="+count+"==SMELL=====");
//		for(int i=1;i<5;i++) {
//			for(int j=1;j<5;j++) {
//				System.out.print(smell[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("=="+count+"==처음MAP=====");
//		printMap(Map);
		
		//1. Map의 물고기 이동
		
		ArrayList<Fish>[][] moveMap = moveFish(Map,shark);
//		System.out.println("=="+count+"==물고기이동끝=====");
//		printMap(moveMap);
		
		//true인애들 삭제
//		for(int i=1;i<5;i++) {
//			for(int j=1;j<5;j++) {
//				for(int k=moveMap[i][j].size()-1;k>=0;k--) {
//					if(moveMap[i][j].get(k).isDie) {
//						moveMap[i][j].remove(k);
//					}
//				}
//			}
//		}
		
		
		//2. 상어 연속 3칸 이동
		//2-1. 상어 이동하는 경로 구하고, 그 중에 가장 많은 물고기 먹는 루트 구하기
		
		PriorityQueue<SharkDirection> queue = new PriorityQueue<>();
		sharkVisit = new boolean[5][5];
		sharkVisit[shark.row][shark.col] = true;
		moveShark(0,moveMap,shark.row,shark.col,0,queue,"");
		//Collections.sort(Arr);
		//System.out.println(Arr);
		String track = queue.poll().track;
		queue = new PriorityQueue<>();
		
		//2-2. 상어에서 뽑은거대로 진짜 3번 이동
		
		
		Shark newShark = realMoveShark(track, shark.row, shark.col, moveMap);
//		System.out.println("=="+count+"==상어진짜이동끝=====");
//		System.out.println(shark +"-->"+newShark);
//		printMap(moveMap);

		
		//3. smell 돌면서 1,2면 -1씩
		for(int i=1;i<5;i++) {
			for(int j=1;j<5;j++) {
				if(smell[i][j] == 1 || smell[i][j]==2 || smell[i][j]==3) {
					smell[i][j]--;
				}
			}
		}
		
		//4. 지금 moveMap에 copyMap 넣어서 복제본 만들기
		for(int i=1;i<5;i++) {
			for(int j=1;j<5;j++) {
				for(Fish fish:copyMap[i][j]) {
					moveMap[i][j].add(fish);
				}
			}
		}
		
		releaseMemory(copyMap);
		
//		System.out.println("=="+count+"==복제마법고고=====");
//		printMap(moveMap);
//		System.out.println();
//		System.out.println(newShark);
		dfs(count+1,moveMap,newShark);
	}
	
	static void releaseMemory(ArrayList<Fish>[][] Map) {
		Map = new ArrayList[0][0];
//		for(int i=1;i<5;i++) {
//			for(int j=1;j<5;j++) {
//				Map[i][j] = new ArrayList<>();
//			}
//		}
	}
	
	static Shark realMoveShark(String track, int row, int col, ArrayList<Fish>[][] moveMap) {
		int newRow = row;
		int newCol = col;
		//System.out.println(newRow + " , "+newCol);

		for(int s=0;s<3;s++) {
			int dir = Integer.parseInt(String.valueOf(track.charAt(s)));
			newRow += dSharkRow[dir];
			newCol += dSharkCol[dir];
			//System.out.println(dir+" -> "+newRow + " , "+newCol);
			if(moveMap[newRow][newCol]!=null) {
				for(Fish fish:moveMap[newRow][newCol]) {
					if(!fish.isDie) {
						smell[newRow][newCol] = 3; //물고기가 있으면 물고기 잡아먹고 냄새 남기기
						break;
					}
				}
			}
			
			moveMap[newRow][newCol] = new ArrayList<>(); //지워주기
		}
		Shark shark = new Shark(newRow,newCol);
		return shark;
	}
	
	static void moveShark(int count, ArrayList<Fish>[][] Map, int row, int col , int sumFish, PriorityQueue<SharkDirection> queue, String track) {
		if(count>=3) {
			//상어 이동 끝
			queue.add(new SharkDirection(sumFish,track));
			return;
		}
		for(int i=1;i<5;i++) {
			int newRow = row + dSharkRow[i];
			int newCol = col + dSharkCol[i];
			 //ArrayList<Fish>[][] CopyMap = copyMap(Map);
			 if(1<=newRow && newRow<5 && 1<=newCol && newCol<5) {
				 int fishNum = 0;
				 if(!sharkVisit[newRow][newCol]) {
					 fishNum+=Map[newRow][newCol].size();
				 }
//				 for(Fish fish:CopyMap[newRow][newCol]) {
//					 if(!sharkVisit[newRow][newCol]) {
//					 //if(!fish.isDie && !sharkVisit[newRow][newCol]) {
//						 fishNum++; 
//					 }
//				 }
				 sharkVisit[newRow][newCol] = true;
				 ArrayList<Fish> copy = new ArrayList<>(Map[newRow][newCol]);
				 Map[newRow][newCol] = new ArrayList<>();
				 
				 moveShark(count+1,Map,newRow,newCol,sumFish+fishNum,queue,track+i);
				 Map[newRow][newCol].addAll(copy);
				 sharkVisit[newRow][newCol] = false;
			 }
			//releaseMemory(CopyMap);
		}
	}
	
	static ArrayList<Fish>[][] moveFish(ArrayList<Fish>[][] Map, Shark shark) {
		ArrayList<Fish>[][] copyMap = copyMap(Map);

		//System.out.println("SHARK!!! "+shark);
		for(int i=1;i<5;i++) {
			for(int j=1;j<5;j++) {
				for(int f=Map[i][j].size()-1;f>=0;f--) {
					Fish fish = Map[i][j].get(f);
					if(!fish.isDie) {
						for(int d=0;d<8;d++) {
							int nowDir = (fish.dir - d) % 8 < 0 ? 8 + (fish.dir - d) % 8 : (fish.dir - d) % 8;
							int newRow = i + dRow[nowDir];
							int newCol = j + dCol[nowDir];

							if(1<=newRow && newRow<5 && 1<=newCol && newCol<5) {
								if(!(newRow==shark.row && newCol==shark.col)) {
									if(smell[newRow][newCol]==0) {
										//System.out.println(fish+"---->"+newRow+","+newCol+"   DIR:"+nowDir);
										copyMap[i][j].remove(f); //기존 물고기 없애기
										//copyMap[i][j].get(f).isDie = true;
										copyMap[newRow][newCol].add(new Fish(nowDir,false)); //새로운 물고기 생성
										break;
									}
								}
							}
						}
					}
					//printMap(copyMap);
				}
			}
		}
		
		return copyMap;
	}
	
	static ArrayList<Fish>[][] copyMap(ArrayList<Fish>[][] Map){
		ArrayList<Fish>[][] copyMap = new ArrayList[5][5];
		for(int i=1;i<5;i++) {
			for(int j=1;j<5;j++) {
				copyMap[i][j] = new ArrayList<>();
				for(Fish fish:Map[i][j]) {
					copyMap[i][j].add(new Fish(fish.dir,fish.isDie));
				}
			}
		}
		return copyMap;
	}
	
	static void printMap(ArrayList<Fish>[][] Map) {
		for(int i=1;i<5;i++) {
			for(int j=1;j<5;j++) {
				System.out.print(Map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static class Fish{
		int dir;
		boolean isDie;
		
		public Fish( int dir, boolean isDie) {
			super();
			this.dir = dir;
			this.isDie = isDie;
		}

		@Override
		public String toString() {
			return "Fish [dir=" + dir + ", isDie=" + isDie + "]";
		}
		
	}
	static class Shark{
		int row;
		int col;
		public Shark(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		@Override
		public String toString() {
			return "Shark [row=" + row + ", col=" + col + "]";
		}
	}
	
	static class SharkDirection implements Comparable<SharkDirection>{
		int sumFish;
		String track;
		
		@Override
		public String toString() {
			return "SharkDirection [sumFish=" + sumFish + ", track=" + track + "]";
		}

		public SharkDirection(int sumFish, String track) {
			super();
			this.sumFish = sumFish;
			this.track = track;
		}

		@Override
		public int compareTo(SharkDirection o) {
			// TODO Auto-generated method stub
			int cmp = Integer.compare(o.sumFish, sumFish);
			if(cmp==0) {
				cmp = track.compareTo(o.track);
			}
			return cmp;
		}
	}
}
