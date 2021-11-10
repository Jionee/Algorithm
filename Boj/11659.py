import sys
sys.setrecursionlimit(10**5)

def input():
    return sys.stdin.readline().rstrip()

N,M = map(int,input().split(" ")) #각 1,000,000개씩
num = list(map(int,input().split(" ")))
mul = [tuple(list(map(int,input().split(" ")))) for _ in range(M)]

accum_sum = [num[0]] * N
for i in range(1,N):
    accum_sum[i] = accum_sum[i-1] + num[i]
#print(accum_sum)

for m in mul:
    a,b = m[0]-1,m[1]-1
    if a == b:
        print(num[a])
    elif a == 0:
        print(accum_sum[b])
    else:
        sum = accum_sum[b] - accum_sum[a-1]
        print(sum)