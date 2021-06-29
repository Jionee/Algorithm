import sys

n, m, k = map(int,sys.stdin.readline().split(" "))
arr = sorted(list(map(int,sys.stdin.readline().split())))
# 5 8 3
# 2 4 5 4 6
print(arr)
result = 0
num = 0
#같은 숫자 k번이상 더해지기 불가, m번 더해야함
while m > 0:
    m -= 1
    if num == k:
        result += arr[-2] #가장 큰 수 더하기
        num = 0
    else:
        result += arr[-1]
        num += 1
print(result)