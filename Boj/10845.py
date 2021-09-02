import sys

n = int(input()) #명령의 수
que = []

for i in range(n):
    line = sys.stdin.readline().split()
    if line[0] == "push":
        que.append(line[1])

    elif line[0] == "pop":
        if len(que) < 1:
            print(-1)
        else:
            print(que[0])
            del que[0]

    elif line[0] == "size":
        print(len(que))

    elif line[0] == "empty":
        if len(que) < 1:
            print(1)
        else:
            print(0)

    elif line[0] == "front":
        if len(que) < 1:
            print(-1)
        else:
            print(que[0])

    elif line[0] == "back":
        if len(que) < 1:
            print(-1)
        else:
            print(que[-1])

    else:
        print("else")
