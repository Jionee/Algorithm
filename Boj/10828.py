import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
stck = []

for _ in range(N):
    line = input().split(" ")
    if line[0] == "push": #stack에 하나씩 넣기
        stck.append(line[1])
    elif line[0] == "pop": #stack가장 위에 있는 정수 빼고 출력(Last In First Out)
        if len(stck) <= 0:
            print(-1)
        else:
            print(stck.pop())
    elif line[0] == "size":
        print(len(stck))
    elif line[0] == "empty":
        if len(stck) > 0:
            print(0)
        else:
            print(1)
    elif line[0] == "top": #stack가장 위에 있는 정수 출력 = 가장 마지막에 넣은 정수 출력
        if len(stck) <= 0:
            print(-1)
        else:
            print(stck[-1])
    else:
        print("ERROR")