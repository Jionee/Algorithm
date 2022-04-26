import java.util.*;
import java.io.*;

public class Boj17837_새로운게임2 {
	static StringTokenizer st;
	static int N, K;
	static int[][] Color;
	static ArrayList<Point> Horse = new ArrayList<>();
	static ArrayList<Integer>[][] Map;
	static int[] dRow = { 0, 0, 0, -1, 1 };
	static int[] dCol = { 0, 1, -1, 0, 0 };
	static HashMap<Integer, Integer> HorseDir = new HashMap<>();
	static HashMap<Integer, Point> HorseArr = new HashMap<>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/main/java/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Color = new int[N + 1][N + 1];
		Map = new ArrayList[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				Map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				Color[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < K + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			Map[row][col].add(i);
			HorseDir.put(i, dir);
			HorseArr.put(i, new Point(row, col, i, dir));
		}
		// printMap();

		// START
		int answer = 0;
		while (true) {
			// .out.println("@@@@@@@@@ANSWER "+answer);
			answer++;
			if (answer > 1000) {
				System.out.println(-1);
				System.exit(0);
			}

			for (int i = 1; i < HorseArr.size() + 1; i++) {
				//printMap();
				Point now = HorseArr.get(i);
				ArrayList<Integer> nowArr = Map[now.row][now.col];
				int index = 0;
				for (int k = 0; k < nowArr.size(); k++) {
					if (nowArr.get(k) == now.num) {
						index = k;
						break;
					}
				}

				Map[now.row][now.col] = new ArrayList<>(nowArr.subList(0, index));
				ArrayList<Integer> nextArr = new ArrayList<>(nowArr.subList(index, nowArr.size()));
				//System.out.println("### TURN : " + i + " BEFORE : " + Map[now.row][now.col] + " / AFTER : " + nextArr);
				int newRow = now.row + dRow[now.dir];
				int newCol = now.col + dCol[now.dir];
				if (!(0 < newRow && newRow <= N && 0 < newCol && newCol <= N) || Color[newRow][newCol] == 2) { //파란색 혹은 벽
					int rDir = reverseDir(now.dir);
					HorseDir.put(now.num, rDir);

					int rNewRow = now.row + dRow[rDir];
					int rNewCol = now.col + dCol[rDir];
					if (!(0 < rNewRow && rNewRow <= N && 0 < rNewCol && rNewCol <= N) || Color[rNewRow][rNewCol] == 2) {// 또 파란색 혹은 벽이면 now에다가 놓기
//						if (Color[now.row][now.col] == 1) { // 왔는데 거기가 빨간색이면 뒤집기
//							Collections.reverse(nextArr);
//						}
						Map[now.row][now.col].addAll(nextArr);
						for (int k = 0; k < nextArr.size(); k++) {
							HorseArr.put(nextArr.get(k),
									new Point(now.row, now.col, nextArr.get(k), HorseDir.get(nextArr.get(k))));
						}
					} else {// 파란색 혹은 벽 아니면 다음칸으로 이동
						if (Color[rNewRow][rNewCol] == 1) { // 왔는데 거기가 빨간색이면 뒤집기
							Collections.reverse(nextArr);
						}
						Map[rNewRow][rNewCol].addAll(nextArr);
						for (int k = 0; k < nextArr.size(); k++) {
							HorseArr.put(nextArr.get(k),
									new Point(rNewRow, rNewCol, nextArr.get(k), HorseDir.get(nextArr.get(k))));
						}
					}
				}
				else if (0 < newRow && newRow <= N && 0 < newCol && newCol <= N) {
					if (Color[newRow][newCol] == 1) { // 빨간색
						Collections.reverse(nextArr);
					}
					Map[newRow][newCol].addAll(nextArr);
					for (int k = 0; k < nextArr.size(); k++) {
						HorseArr.put(nextArr.get(k),
								new Point(newRow, newCol, nextArr.get(k), HorseDir.get(nextArr.get(k))));
					}
				}
				// printMap();
				// System.out.println("NEW -> "+HorseArr);
				for (int a = 1; a < N + 1; a++) {
					for (int b = 1; b < N + 1; b++) {
						if (Map[a][b].size() >= 4) {
							System.out.println(answer);
							System.exit(0);
						}
					}
				}
			}

			
		}

	}

	static void printMap() {
		System.out.println("===============");
		System.out.println(HorseDir);
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				System.out.print(Map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int reverseDir(int dir) {
		switch (dir) {
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

	static class Point {
		int row;
		int col;
		int num;
		int dir;

		public Point(int row, int col, int num, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", num=" + num + ", dir=" + dir + "]";
		}

	}

}
