import sys
def input():
    return sys.stdin.readline().rstrip()

N = int(input())
A = set(list(map(int,input().split(" "))))
M = int(input())
num = list(map(int,input().split(" ")))

#print(A,num)

for n in num:
    if n in A:
        print(1)
    else:
        print(0)
