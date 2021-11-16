import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input()) #2<= N <=100,000
liquid = sorted(list(map(int,input().split(" "))))
#print(liquid)

#임의의 두 개를 더해서 0에 가장 가까운 값을 만들어야 함
#다 하는 것 밖에 답이 없는데 그러면 시간 초과가 뜸
#이진 탐색?

#적합 : 음수? 양수?
start = 0
end = len(liquid)-1
s,e = -1,-1
abs_min = float('inf')

while start < end:
    fit = liquid[start] + liquid[end]
    if abs_min > abs(fit): #최솟값이면 갱신
        s, e = start, end
        abs_min = abs(fit)

    if fit == 0:
        break
    else:
        if fit > 0:
            end -= 1
        else:
            start += 1

print(liquid[s], liquid[e])
