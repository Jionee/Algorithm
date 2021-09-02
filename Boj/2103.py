import sys
from itertools import combinations

# 5
# 4
# 1 3 1 2
# 3
# 1 3 2

t = int(input())
n = int(input())
a = list(map(int, sys.stdin.readline().split(" ")))
m = int(input())
b = list(map(int, sys.stdin.readline().split(" ")))

subA = []
subB = []
for i in range(n):
    sums = 0
    for j in range(i,n):
        sums += a[j]
        subA.append(sums)
print(subA)

for i in range(m):
    sums = 0
    for j in range(i,m):
        sums += b[j]
        subB.append(sums)
print(subA)

answer = 0

print(answer)
