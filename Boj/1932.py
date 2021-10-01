import sys

def input():
    return sys.stdin.readline().rstrip()

#일단 모든 경로를 다 돌긴 해야하는데, dp테이블 사용해서 시간 줄이기 가능
#삼각형크기는 1 이상 500 이하
#대각선 왼쪽 혹은 대각선 오른쪽만 이동 가능 ->
    # N층 이동하면서 다 더할건데, N층 이동하면 return

N = int(input())
T = [list(map(int,input().split())) for _ in range(N)]
#print(T)

dp = [([0] * N) for _ in range(N)] #n층,n개 만큼의 캐시를 만들어 놓음
visited = 0

def dfs(floor, num):
    #종료조건 : 마지막 층, 마지막 애까지 다 했을 떄
    if floor == (N-1):
        return T[floor][num]

    #dp테이블 이용
    if dp[floor][num] != 0:
        return dp[floor][num]

    #가지치기
    dp[floor][num] = max(dp[floor][num], dfs(floor + 1, num) + T[floor][num])
    dp[floor][num] = max(dp[floor][num], dfs(floor + 1, num + 1) + T[floor][num])

    return dp[floor][num]

#Top-down으로 0층에 0번째 애부터 시작한다고 알림
print(dfs(0,0))