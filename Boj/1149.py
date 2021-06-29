import sys
input = sys.stdin.readline
n = int(input())

rgb = [list(map(int,input().split())) for _ in range(n)]

dp = [([0]*3) for _ in range(n)]

#초기값 세팅
dp[0][0] = rgb[0][0]
dp[0][1] = rgb[0][1]
dp[0][2] = rgb[0][2]

for i in range(1,n):
    dp[i][0] = rgb[i][0] + min(dp[i-1][1],dp[i-1][2])
    dp[i][1] = rgb[i][1] + min(dp[i-1][0],dp[i-1][2])
    dp[i][2] = rgb[i][2] + min(dp[i-1][0],dp[i-1][1])

#print(dp)
print(min(dp[n-1]))