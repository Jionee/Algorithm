
import java.util.*;
import java.io.*;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Boj17140_이차원배열과연산 {
	static StringTokenizer st;
	static int R,C,K;
	static int[][] Map = new int[101][101];
	static int[][] Copy;
	static int rowNum = 3;
	static int colNum = 3;
	static boolean flag = false;
	
	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<rowNum+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<colNum+1;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//printMap();
		if(Map[R][C]==K) {
			System.out.println(0);
			System.exit(0);
		}
		
		//start
		for(int t=1;t<101;t++) { //100초까지
			
			Copy = new int[101][101];
			if(rowNum>=colNum) { //행 돌면서 연산
				colNum = 0;
				for(int i=1;i<rowNum+1;i++) {
					HashMap<Integer,Integer> hashMap = new HashMap<>();
					for(int j=1;j<101;j++) {
						int num = Map[i][j];
						if(num!=0) {
							if(hashMap.containsKey(num)) {
								hashMap.put(num,hashMap.get(num)+1);
							}
							else {
								hashMap.put(num, 1);
							}
						}
						
					}
					//끝나고 Copy갱신
					PriorityQueue<Point> queue = new PriorityQueue<>();
					for(int key:hashMap.keySet()) {
						queue.add(new Point(key,hashMap.get(key)));
					}
					//System.out.println(queue);
					int col = 1;
					
					//System.out.println("col 바꿈 -> "+colNum);
					while(!queue.isEmpty()) {
						if(col>=101) {
							col--;
							break;
						}
						Point now = queue.poll();
						Copy[i][col++] = now.num;
						Copy[i][col++] = now.count;
					}
					colNum = Math.max(colNum, col);
				}
			}
			else { //열 돌면서 연산
				rowNum = 0;
				for(int j=1;j<colNum+1;j++) {
					HashMap<Integer,Integer> hashMap = new HashMap<>();
					for(int i=1;i<101;i++) {
						int num = Map[i][j];
						if(num!=0) {
							if(hashMap.containsKey(num)) {
								hashMap.put(num,hashMap.get(num)+1);
							}
							else {
								hashMap.put(num, 1);
							}
						}
						
					}
					//끝나고 Copy 갱신
					PriorityQueue<Point> queue = new PriorityQueue<>();
					for(int key:hashMap.keySet()) {
						queue.add(new Point(key,hashMap.get(key)));
					}
					
					//System.out.println("row 바꿈 -> "+rowNum);
					//System.out.println(queue);
					int row = 1;
					while(!queue.isEmpty()) {
						if(row>=101) {
							row--;
							break;
						}
						Point now = queue.poll();
						Copy[row++][j] = now.num;
						Copy[row++][j] = now.count;
					}
					rowNum = Math.max(rowNum, row);
				}
			}
			
			//Copy복사하기
			for(int i=1;i<101;i++) {
				for(int j=1;j<101;j++) {
					Map[i][j] = Copy[i][j];
				}
			}
			//System.out.println(rowNum+" , "+colNum);
			//printMap();
			
			if(Map[R][C]==K) {
				flag = true;
				System.out.println(t);
				break;
			}
			
		}
		
		if(!flag) {
			System.out.println(-1);
		}
	}
	
	static void printMap() {
		System.out.println("=====================");
		for(int i=1;i<101;i++) {
			for(int j=1;j<101;j++) {
				System.out.print(Map[i][j]);
			}
			System.out.println();
		}
	}
	
	static class Point implements Comparable<Point>{
		int num;
		int count;
		
		@Override
		public String toString() {
			return "Point [num=" + num + ", count=" + count + "]";
		}

		public Point(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			int comp = Integer.compare(count, o.count);
			if(comp==0) {
				comp = Integer.compare(num,o.num);
			}
			return comp;
		}
		
	}
}