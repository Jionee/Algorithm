import sys
def input():
    return sys.stdin.readline().rstrip()

N,S = map(int,input().split(" "))
su = list(map(int,input().split(" ")))
#print(su)

answer = float('inf')
#연속된 수들의 부분합 중에 합이 S이상이 되는 것 중, 가장 짧은 길이
#일단 0부터 각 i까지의 합을 구해놓기
dp = [0 for _ in range(N)]
dp[0] = su[0]

for i in range(1,N):
    dp[i] = dp[i-1] + su[i]
    if dp[i] >= S:
        answer = i + 1
#print(dp)

#합 생성 불가
if dp[-1] < S:
    print(0)
    exit()

#각 자리부터 돌면서 연속된 거 중에 S보다 큰거 찾기
for i in range(1,N):
    for k in range(i,N):
        target = dp[k] - dp[i-1]
        if target >= S:
            answer = min(answer,k-i+1)
            break
    #0이면 그냥 dp
    #1이면 dp[i]-dp[i-1]

print(answer)