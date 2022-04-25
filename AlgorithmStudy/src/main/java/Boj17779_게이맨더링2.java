import java.util.*;
import java.io.*;

public class Boj17779_게이맨더링2 {
	static StringTokenizer st;
	static int N;
	static int[][] Map;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Map = new int[N+1][N+1];
		
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<N+1;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//2<=r<=N-1, 1<=c<=N-2를 만족하는 r,c쌍
		for(int r=2;r<=N-1;r++) {
			for(int c=1;c<=N-1;c++) {
				//d1,d2찾기
				//1<=d1<r, 1<=d2<=N-r,d2<=N-c-d1
				for(int d1=1;d1<r;d1++) {
					for(int d2=1;d2<=N-r;d2++) {
						if(d2<=N-c-d1) {
							//여기서 각 지역의 인구수 구하기
							System.out.println("r,c : "+r+","+c+" d1,d2 : "+d1+","+d2);
							int small = getSmall(r,c,d1,d2);
							System.out.println(small);
							answer = Math.min(answer,small);
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static int getSmall(int row, int col, int d1, int d2) {
//		1번 선거구: 1 ≤ r < col+d1, 1 ≤ c ≤ row
//		2번 선거구: 1 ≤ r ≤ col+d2, row < c ≤ N
//		3번 선거구: col+d1 ≤ r ≤ N, 1 ≤ c < row-d1+d2
//		4번 선거구: col+d2 < r ≤ N, row-d1+d2 ≤ c ≤ N
		HashMap<Integer,Integer> hashMap = new HashMap<>();
		
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				if(1<=i && i<col+d1 && 1<=j && j<=row) { //1번선거구
					System.out.println("1번 - "+i+","+j);
					if(hashMap.containsKey(1)) {
						hashMap.put(1, hashMap.get(1)+Map[i][j]);
					}
					else {
						hashMap.put(1, Map[i][j]);
					}
					
				}
				else if(1<=i && i<=col+d2 && row<j && j<=N){
					System.out.println("2번 - "+i+","+j);
					if(hashMap.containsKey(2)) {
						hashMap.put(2, hashMap.get(2)+Map[i][j]);
					}
					else {
						hashMap.put(2, Map[i][j]);
					}
				}
				else if(col+d1 <=i && i<=N && 1<=j && j<row-d1+d2) {
					System.out.println("3번 - "+i+","+j);
					if(hashMap.containsKey(3)) {
						hashMap.put(3, hashMap.get(3)+Map[i][j]);
					}
					else {
						hashMap.put(3, Map[i][j]);
					}
				}
				else if(col+d2<i && i<=N && row-d1+d2<=j && j<=N) {
					System.out.println("4번 - "+i+","+j);
					if(hashMap.containsKey(4)) {
						hashMap.put(4, hashMap.get(4)+Map[i][j]);
					}
					else {
						hashMap.put(4, Map[i][j]);
					}
				}
				else {
					System.out.println("5번 - "+i+","+j);
					if(hashMap.containsKey(5)) {
						hashMap.put(5, hashMap.get(5)+Map[i][j]);
					}
					else {
						hashMap.put(5, Map[i][j]);
					}
				}
				
			}
		}
		ArrayList<Point> Arr = new ArrayList<>();
		for(int key:hashMap.keySet()) {
			Arr.add(new Point(key,hashMap.get(key)));
		}
		Collections.sort(Arr);
		System.out.println(Arr);
		int result = Math.abs(Arr.get(Arr.size()-1).num - Arr.get(0).num);
		return result;
	
	}
	static class Point implements Comparable<Point>{
		int district;
		@Override
		public String toString() {
			return "Point [district=" + district + ", num=" + num + "]";
		}
		int num;
		public Point(int district, int num) {
			super();
			this.district = district;
			this.num = num;
		}
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return Integer.compare(num, o.num);
		}
		
	}
	
}
