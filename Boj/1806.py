import sys
from itertools import combinations

n,m = map(int,sys.stdin.readline().split(" "))
num = list(map(int,sys.stdin.readline().split(" ")))

print(num)
for i in range(len(num)):
    comb = list(combinations(num,i))
    print(comb)
