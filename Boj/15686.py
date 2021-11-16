import sys
from itertools import combinations

def input():
    return sys.stdin.readline().rstrip()

N,M = map(int,input().split(" "))
maps = [list(map(int,input().split(" "))) for _ in range(N)]
house = [(a+1,b+1) for a in range(N) for b in range(N) if maps[a][b] == 1]
chicken = [(a+1,b+1) for a in range(N) for b in range(N) if maps[a][b] == 2]

#치킨거리 계산
def calculate(house,chicken):
    return abs(house[0] - chicken[0]) + abs(house[1] - chicken[1])

#모든 경우의 수 다 해서 치킨거리의 합이 가장 작은걸 채택
sum_min = float('inf')
for cand in combinations(chicken,M): #치킨집 후보들을 골라서 치킨거리 최소 구하기
    sum = 0
    for h in house: #각 집에서 치킨집까지의 치킨거리 구하기
        sum += min(calculate(h,c) for c in cand)
    sum_min = min(sum,sum_min) #후보 중 치킨거리 최솟값
print(sum_min)