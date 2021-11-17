import sys
def input():
    return sys.stdin.readline().rstrip()

N = int(input()) + 1

dp = [0 for _ in range(N)]
dp[0] = 0
dp[1] = 1

for n in range(2,N):
    dp[n] = dp[n-1]+dp[n-2]
print(dp[N-1])