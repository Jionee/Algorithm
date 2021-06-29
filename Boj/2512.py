# 4
# 120 110 140 150
# 485
import sys
n = int(sys.stdin.readline())
money = sorted(list(map(int,sys.stdin.readline().split())))
m = int(sys.stdin.readline())

#이진탐색
#기준 : 상한 금액
start = 0
end = max(money)

def payment(money,mid):
    pay = 0
    for mo in money:
        if mo >= mid:
            pay += mid
        else:
            pay += mo
    return pay

while True:
    mid = (start+end) // 2
    #최대예산m을 충족하는지
    pay = payment(money,mid)

    if pay > m:
        end = mid - 1
    else:
        start = mid + 1

    if mid == end:
        print(mid)
        break

