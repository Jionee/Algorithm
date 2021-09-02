import sys
# 10 5
# 1 2 3 4 2 5 3 1 1 2
n,m = map(int,sys.stdin.readline().split(" "))
num = list(map(int,sys.stdin.readline().split(" ")))
#print(num)

answer = 0

for a in range(0,len(num)):
    sum = 0
    for b in range(a,len(num)):
        sum += num[b]
        if sum == m:
            answer += 1
            break

print(answer)
