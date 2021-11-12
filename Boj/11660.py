import sys
def input():
    return sys.stdin.readline().rstrip()

N, M = map(int,input().split(" "))
maps = [list(map(int,input().split(" "))) for _ in range(N)]
#print(maps)
toCal = [list(map(int,input().split(" "))) for _ in range(M)]
print("cal : ",toCal)

# (0,0)부터 (N,N)까지의 합을 구해놓기
dp = [[0 for _ in range(N)]  for _ in range(N)]
dp[0][0] = maps[0][0]

for r in range(N):
    for c in range(N):
        if c == 0:
            dp[r][c] = dp[r-1][N-1] + maps[r][c]
        else:
            dp[r][c] = dp[r][c-1] + maps[r][c]
print("dp테이블 : ",dp)

def calculate(cal):
    a,b,c,d = cal
    a -= 1
    b-=1
    c-=1
    d-=1
    if a==c and b==d:
        return dp[a][c]
    sum = 0
    for row in range(a,c+1):

        sum += dp[row][d] - dp[row][b-1]
        print("===", row, d, "-", row, b - 1,"sum : ",sum)
        print(dp[row][d] ,dp[row][b-1])
    return sum

for cal in toCal:
    print(calculate(cal))