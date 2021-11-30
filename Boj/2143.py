import sys
from _collections import defaultdict
import math
import time
start = time.time()

def input():
    return sys.stdin.readline().rstrip()

T = int(input())
N = int(input())
aList = list(map(int,input().split(" ")))
M = int(input())
bList = list(map(int,input().split(" ")))

#부분합이 같은거를 몇 번 사용해도 뭐 사용했는지는 관심 없음 -> dict 사용하기
aDict = defaultdict(int)
for a in range(N):
    for aa in range(a,N):
        aDict[sum(aList[a:aa+1])] += 1

print(aDict)
answer = 0
for b in range(M):
    for bb in range(b,M):
        answer += aDict[T - sum(bList[b:bb+1])]

print(answer)
end = time.time()
print(f"{end - start:.5f} sec")