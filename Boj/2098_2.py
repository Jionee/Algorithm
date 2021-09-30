import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
W = [list(map(int, input().split(" "))) for _ in range(N)]  # 2차원 배열 입력받기
#print(W)

inf = float('inf')
#풀이2. Dynamic Programming + 비트 마스킹 사용
dp = [[inf] * (1 << N) for _ in range(N)] #자식(비트마스킹) 먼저, 부모(Node) 나중

ALL_VISITED = (1 << N) - 1

#모든 루트를 방문하고, 갈r림길에서는 값을 리턴 받아 최소값을 비교하도록 할것임
def dfs(node,visited):
    if visited == ALL_VISITED: #모두 방문했으면 처음 시작인 0으로 다시 돌아가기
        if W[node][0] == 0:
            return inf
        else:
            return W[node][0]
    if dp[node][visited] != inf : #이미 table이 존재하면 그 값 리턴
        return dp[node][visited]
    #이외에는 새로운 dp테이블 갱신 필요
    #언제? - 길이 존재할 때, 처음 방문하는 도시일 때
    for i in range(1,N): #결국 모든 가능한 노드들을 다 방문해봐야 하긴 함
        if W[node][i] != 0 and visited & (1 << i) == 0:
            dp[node][visited] = min(dp[node][visited], dfs(i, visited | (1 << i)) + W[node][i]) #갈림길에서 받은 값들 중에 min을 뽑아내기 위해 min을 사용했고, 새로운 값 갱신은 dfs재귀호출 + cost로 함
    return dp[node][visited]

print(dfs(0,1)) #0노드에서 출발하고, 0은 방문한 visited를 넘겨줌
