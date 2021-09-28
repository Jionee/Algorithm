import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
W = [list(map(int, input().split(" "))) for _ in range(N)]  # 2차원 배열 입력받기
print(W)

#풀이2. Dynamic Programming + 비트 마스킹 사용