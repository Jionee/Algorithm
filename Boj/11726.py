import sys
n = int(sys.stdin.readline())
#n은 1부터 1000까지

if n == 1 :
    ans = 1
else :
    dp = [0] * n

    dp[0] = 1
    dp[1] = 2

    for i in range(2, n):
        dp[i] = dp[i - 1] + dp[i - 2]
    ans = dp[n-1]

print(ans%10007)

# def dpFunc(x):
#     if x==1 : return 1
#     if x==2 : return 2
#     if(dp[x] != 0): return dp[x]
#     dp[x] = (dp(x-1)+dp(x-2)) % 10007
#     return dp[x]
#
# print(dpFunc(n))
