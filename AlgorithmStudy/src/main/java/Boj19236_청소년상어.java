import java.util.*;
import java.io.*;

public class Boj19236_청소년상어 {
	static StringTokenizer st;
	static Point[][] Map = new Point[4][4];
	static int answer = 0;
	static int[] dRow = {-1,-1,-1,0,1,1,1,0};
	static int[] dCol = {1,0,-1,-1,-1,0,1,1};
	static HashMap<Integer,Fish> hashMap = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<4;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				if(i==0 && j==0) {
					Map[0][0] = new Point(1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())%8);
					hashMap.put(-1, new Fish(i,j)); //상어 표시
					continue;
				}
				Map[i][j] = new Point(0,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())%8);
				hashMap.put(Map[i][j].size,new Fish(i,j));
			}
		}
		//printMap();
		
		//START
		dfs(0);
		System.out.println(answer);
	}
	
	
	
	static void dfs(int count) {
		System.out.println("지금 상어 "+hashMap.get(-1).row+","+hashMap.get(-1).col+" "+Map[hashMap.get(-1).row][hashMap.get(-1).col]);
		System.out.println(Map[hashMap.get(-1).row][hashMap.get(-1).col].size);
		answer = Math.max(answer,Map[hashMap.get(-1).row][hashMap.get(-1).col].size); //최댓값 갱신 

//		if(count>=5) {
//			return;
//		}
//		

		moveFish(); //물고기 이동
		System.out.println("물고기 이동 끝 > "+hashMap);

		HashMap<Integer,Fish> CopyHashMap = new HashMap<>();
		Point[][] CopyMap = new Point[4][4];
		copy(CopyHashMap, CopyMap);

		printMap();

		int sharkRow = CopyHashMap.get(-1).row;
		int sharkCol = CopyHashMap.get(-1).col;
		Point shark = new Point(1,Map[sharkRow][sharkCol].size,Map[sharkRow][sharkCol].dir); //원래 상어가 있던 곳
		//System.out.println("SHARK <"+sharkRow+","+sharkCol+">");
		for(int i=1;i<=3;i++) {
			int newRow = sharkRow + i * dRow[shark.dir];
			int newCol = sharkCol + i * dCol[shark.dir];
			System.out.println("COUNT : "+count+"--for문들어옴----"+i+"-----> "+" 지금 상어 좌표 : "+sharkRow+","+sharkCol+" 새로운 상어 좌표 : "+newRow+" "+newCol);
			if(!(0<=newRow && newRow<4 && 0<=newCol && newCol<4) || Map[newRow][newCol]==null) {
				continue;
			}
			if(0<=newRow && newRow<4 && 0<=newCol && newCol<4) {
				if(Map[newRow][newCol]!=null && Map[newRow][newCol].type==0) { //물고기가 존재해 -> 먹어
					Point fish = Map[newRow][newCol];
					hashMap.put(-1, new Fish(newRow,newCol)); //shark -> new
					hashMap.remove(fish.size); //fish -> null
					Map[newRow][newCol] = new Point(1,shark.size+fish.size,fish.dir); //상어가 물고기 칸으로 들어감 //fish -> shark
					Map[sharkRow][sharkCol] = null; //빈칸만들어주기 //shark -> 빈칸
					System.out.println("상어이동");
					printMap();
					dfs(count+1);
					rollback(CopyHashMap, CopyMap);
//
//					hashMap.put(-1, new Fish(sharkRow,sharkCol)); //new -> shark
//					hashMap.put(fish.size, new Fish(newRow,newCol)); //null -> fish
//					Map[newRow][newCol] = new Point(0,fish.size,fish.dir); //shark -> fish
//					Map[sharkRow][sharkCol] = new Point(1,shark.size,shark.dir); //빈칸 -> shark
				}
			}
		}
		
		//printMap();

		//상어 이동

	}

	private static void rollback(HashMap<Integer, Fish> CopyHashMap, Point[][] CopyMap) {
		hashMap = new HashMap<>();
		for(Map.Entry<Integer,Fish>entry : CopyHashMap.entrySet()) {
			hashMap.put(entry.getKey(), new Fish(entry.getValue().row,entry.getValue().col));
		}
//		for(int key:CopyHashMap.keySet()) {
//			hashMap.put(key, CopyHashMap.get(key));
//		}
		for(int k=0;k<4;k++) {
			for(int j=0;j<4;j++) {
				Map[k][j] = CopyMap[k][j];
				
				
			}
		}
	}

	private static void copy(HashMap<Integer, Fish> CopyHashMap, Point[][] CopyMap) {
		for(int i=1;i<=16;i++) {
			if(hashMap.containsKey(i)) {
				CopyHashMap.put(i, hashMap.get(i));
			}
		}
		CopyHashMap.put(-1, hashMap.get(-1));
		
		//		
//		for(int key:hashMap.keySet()) {
//			CopyHashMap.put(key, hashMap.get(key));
//		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				CopyMap[i][j] = new Point();
			}
		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				CopyMap[i][j] = Map[i][j];
			}
		}
	}
	
	private static void moveFish() {
		for(int i=1;i<=16;i++) {
			if(hashMap.get(i)!=null) {
				//System.out.println("물고기 '''"+i+"''' 이동");
				//printMap();
				Fish fish = hashMap.get(i);
				Point now = Map[fish.row][fish.col];
				int nowDir = 0;
				//System.out.println(fish+" "+now);
				for(int d=0;d<=7;d++) {
					nowDir = (now.dir+d)%8; //물고기 방향 재설정
					int newRow = fish.row + dRow[nowDir];
					int newCol = fish.col + dCol[nowDir];
					if(0<=newRow && newRow<4 && 0<=newCol && newCol<4) {
						if(Map[newRow][newCol]==null) { //빈칸이면
							now.dir = nowDir;
							Map[newRow][newCol] = new Point(now.type,now.size,now.dir);
							Map[fish.row][fish.col] = null;
							hashMap.put(i,new Fish(newRow,newCol)); //현재 물고기 새로운 곳으로 이동시켜주기
							break;
						}
						else if(Map[newRow][newCol].type==0) { //다른 물고기면 SWAP
							now.dir = nowDir;
							Point newfish = Map[newRow][newCol];
							Map[fish.row][fish.col] = new Point(newfish.type, newfish.size, newfish.dir);
							Map[newRow][newCol] = new Point(now.type,now.size,now.dir);
							hashMap.put(i, new Fish(newRow,newCol)); //현재물고기와 새로운 물고기 자리 바꿔주기
							hashMap.put(newfish.size, new Fish(fish.row,fish.col));
							break;
						}
					}
				}
			}
		}
	}
	
	static void printMap() {
		System.out.println("===========");
		for(int i=0;i<4;i++) {
			System.out.println(Arrays.toString(Map[i]));
		}
	}
	static class Point{
		int type; // 0이면 물고기, 1이면 상어
		int size;
		int dir;
		public Point(int type, int size, int dir) {
			super();
			this.type = type;
			this.size = size;
			this.dir = dir;
		}
		public Point() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Point [size=***" + size + "*** , dir=" + dir + ", type=" + type + "]";
		}
	}

	static class Fish{
		int row;
		int col;
		@Override
		public String toString() {
			return "Fish [row=" + row + ", col=" + col + "]";
		}
		public Fish(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}
