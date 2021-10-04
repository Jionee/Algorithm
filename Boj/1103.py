import sys

sys.setrecursionlimit(10**6) #python3는 10**6까지 , pypy3는 10**5까지 / 아니면 메모리초과
def input():
    return sys.stdin.readline().rstrip()

# 다 해봐야 되니까 완전탐색. 첫 시작점에서 시작해서 위,아래,왼,오 다 해보기
# 비트마스킹으로 가능?
# H를 만나거나 / not(0<= <=N-1), not(0<= <=M-1) 이면 게임 끝
# 움직인 max를 저장하기
# 게임을 무한히 진행할 수 있다는 걸 어떻게 생각하지?

N,M = map(int,input().split(" "))
B = [list(input()) for _ in range(N)]
#print(B)
visited = [[False]*M for _ in range(N)] #한 루트에서 사이클이 형성됐는지를 체크하는 변수
dp = [[0]*M for _ in range(N)] #어차피 한 번만 가니까 dp테이블 사용 가능

#맨처음은 0,0에서 시작
dir = [[-1,0],[1,0],[0,-1],[0,1]] #위,아래,왼,오

def dfs(a,b):

    #종료조건
    if (0 > a or a > N - 1) or (0 > b or b > M - 1): #범위를 벗어날 수 있는 조건 검사는 항상 맨 처음에
        return 0
    if visited[a][b] == True: # 사이클을 형성할 경우
        print(-1)
        exit()
    if B[a][b] == "H":
        return 0
    if dp[a][b] != 0: #이미 방문했었으면 다시 계산하지 않고 바로 max값 리턴
        return dp[a][b]

    visited[a][b] = True
    #갱신
    for d in dir:
        dp[a][b] = max(dp[a][b] , dfs(a + d[0] * int(B[a][b]), b + d[1] * int(B[a][b])) + 1)
    visited[a][b] = False

    return dp[a][b] #가지에서 루트까지 다 돌았을 때

print(dfs(0,0))
#모든 애들의 return이 끝났을 때