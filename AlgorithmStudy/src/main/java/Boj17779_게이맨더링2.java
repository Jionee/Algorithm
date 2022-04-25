import java.util.*;
import java.io.*;

public class Boj17779_게이맨더링2 {
	static StringTokenizer st;
	static int N;
	static int[][] Map;
	static int answer = Integer.MAX_VALUE;
	static int total = 0;
	
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
				total += Map[i][j];
			}
		}
		
		//r,c쌍
		for(int r=1;r<=N;r++) {
			for(int c=1;c<=N;c++) {
				//d1,d2찾기
				for(int d1=1;d1<=N;d1++) {
					for(int d2=1;d2<=N;d2++) {
						if(r+d1+d2<=N && 1<=c-d1 && c<c+d2 && c+d2<=N){
							//여기서 각 지역의 인구수 구하기
							int small = getSmall(r,c,d1,d2);
							answer = Math.min(answer,small);
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static int getSmall(int row, int col, int d1, int d2) {
		HashMap<Integer,Integer> hashMap = new HashMap<>();

		//경계 설정
		boolean[][] visit = new boolean[N+1][N+1];
		for(int i=0;i<=d1;i++){
			visit[row+i][col-i] = true;
			visit[row+d2+i][col+d2-i] = true;
		}
		for(int i=0;i<=d2;i++){
			visit[row+i][col+i] = true;
			visit[row+d1+i][col-d1+i] = true;
		}


		for(int i=1;i<row+d1;i++){
			for(int j=1;j<=col;j++){
				if(visit[i][j]){
					break;
				}
				if(hashMap.containsKey(1)) {
					hashMap.put(1, hashMap.get(1)+Map[i][j]);
				}
				else {
					hashMap.put(1, Map[i][j]);
				}
			}
		}
		for(int i=1;i<=row+d2;i++){
			for(int j=N;j>col;j--){
				if(visit[i][j]){
					break;
				}
				if(hashMap.containsKey(2)) {
					hashMap.put(2, hashMap.get(2)+Map[i][j]);
				}
				else {
					hashMap.put(2, Map[i][j]);
				}

			}
		}
		for(int i=row+d1;i<=N;i++){
			for(int j=1;j<col-d1+d2;j++){
				if(visit[i][j]){
					break;
				}
				if(hashMap.containsKey(3)) {
					hashMap.put(3, hashMap.get(3)+Map[i][j]);
				}
				else {
					hashMap.put(3, Map[i][j]);
				}

			}
		}
		for(int i=row+d2+1;i<=N;i++){
			for(int j=N;j>=col-d1+d2;j--){
				if(visit[i][j]){
					break;
				}
				if(hashMap.containsKey(4)) {
					hashMap.put(4, hashMap.get(4)+Map[i][j]);
				}
				else {
					hashMap.put(4, Map[i][j]);
				}
			}
		}

		ArrayList<Point> Arr = new ArrayList<>();
		int oneToFour = 0; //5구역 = total - 1~4합(oneToFour)
		for(int key:hashMap.keySet()) {
			Arr.add(new Point(key,hashMap.get(key)));
			oneToFour += hashMap.get(key);
		}
		Arr.add(new Point(5,total - oneToFour));
		Collections.sort(Arr); //합 순으로 정렬

		return Math.abs(Arr.get(Arr.size()-1).num - Arr.get(0).num);
	}

	static class Point implements Comparable<Point>{
		int district;
		int num;

		public Point(int district, int num) {
			super();
			this.district = district;
			this.num = num;
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(num, o.num);
		}
	}
}
