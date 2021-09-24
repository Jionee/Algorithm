import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
W = [list(map(int, input().split(" "))) for _ in range(N)]  # 2차원 배열 입력받기
print(W)

# 풀이1. 완전탐색 + 비트마스킹
# 어느 도시에서 출발할지 다 해보기
# 한번 들른 도시는 다시 방문하지 않음 -> visited 비트마스킹 사용
# 종료조건 : 모든 도시를 다 방문했을 때
inf = float('inf')
ans = inf

def find_path(start,end,visited,cost):
    global ans
    VISITED_ALL = (1 << N) -1
    #종료조건 : 모든 도시를 다 방문했을 때
    if visited == VISITED_ALL:
        current_cost = W[end][start] or inf #왜 이렇게 사용??
        ans = min(ans, cost + current_cost)
        return

    else:
    #아직 도시를 다 방문하지 않았을 때
    #완전탐색이니까 모든 가능한 곳을 다 헤집고 다녀야 함
        for city in range(N):
            #조건 : visited 안했고 & W가 0이 아닌 곳
            if (visited & (1 << city)) == 0 and W[end][city] != 0:
                #visited 갱신 visited|1<<m 사용
                #visited |= (1 << city) #여기서 visited를 갱신하게 되면 다른 애들도 다 얘를 쓰므로 무용지물이 된다.
                current_cost = cost + W[end][city]
                find_path(start,city, visited | (1 << city) ,current_cost)

# for문 돌리기 N개의 도시
# find_path(시작,현재방문,visited,비용)
for startCity in range(N):
    find_path(startCity, startCity, (1 << startCity), 0)

print(ans)