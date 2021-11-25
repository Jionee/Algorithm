import sys
sys.setrecursionlimit(10**5)

def input():
    return sys.stdin.readline().rstrip()


N = int(input())
num = [list(map(int, input().split(" "))) for _ in range(N)]
#print(num)

# 모든 경우의 수 할 수 있나? 줄이 100,000개인데?
godown = [[0, 1], [0, 1, 2], [1, 2]]  # 각각0,1,2 갈 수 있는 곳

cum_min = float('inf')
cum_max = 0

dp = [[0 for _ in range(3)] for _ in range(N)]
print(dp)
def dfs(a, b, i, cum_sum):
    global cum_max
    global cum_min
    #print(a,b,i,cum_sum)
    # 종료조건
    if i == N:  # N줄 다 돌았으면
        return cum_sum, cum_sum

    cum_sum += num[a][b]
    i += 1
    # 가지치기
    for g in godown[b]: #각각 가기
        tmp_max,tmp_min = dfs(a+1,g,i,cum_sum)
        cum_max = max(cum_max,tmp_max)
        cum_min = min(cum_min,tmp_min)
    return cum_max,cum_min

for k in range(N):
    dfs(0, k, 0, 0)

print(cum_max,cum_min)