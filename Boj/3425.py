import sys
from collections import deque

input = sys.stdin.readline

def goStack(pList, num):
    goStack = deque([num])
    # 연산
    for pL in pList:
        if pL.split(" ")[0] == "NUM":  # 스택 최상위에 저장하기 0
            goStack.appendleft(int(pL.split(" ")[1]))
        elif not goStack:
            return "ERROR"
        elif pL == "POP":  # 스택 가장 위 숫자 제거하기 1
            goStack.popleft()
        elif pL == "INV":  # 첫 번째 수의 부호 바꾸기 1
            f = goStack.popleft()
            f = f * (-1)
            goStack.appendleft(f)
        elif pL == "DUP":  # 첫 번째 숫자를 하나 더 스택의 최상위에 저장하기 1
            goStack.appendleft(goStack[0])
        elif len(goStack) == 1:
            return "ERROR"
        elif pL == "SWP":  # 첫 번째 숫자와 두 번째 숫자 위치 바꾸기 2
            # tmp = goStack[0]
            # goStack[0] = goStack[1]
            # goStack[1] = tmp
            goStack[0],goStack[1] = goStack[1],goStack[0]
        elif pL == "ADD":  # 첫 번째 숫자와 두 번째 숫자 더하기 2
            result = int(goStack.popleft()) + int(goStack.popleft())
            if abs(result) > 10 ** 9:
                return "ERROR"
            goStack.appendleft(result)
        elif pL == "SUB":  # 첫 번째 숫자와 두 번째 숫자 빼기  (두 - 첫) 2
            result = (-1)* int(goStack.popleft()) + int(goStack.popleft())
            if abs(result) > 10 ** 9:
                return "ERROR"
            goStack.appendleft(result)
        elif pL == "MUL":  # 첫 번째 숫자와 두 번째 숫자 곱하기 2
            result = int(goStack.popleft()) * int(goStack.popleft())
            if abs(int(result)) > 10 ** 9:
                return "ERROR"
            goStack.appendleft(result)

        elif pL == "DIV":  # 나눈 몫 저장  ex) 13 div -4 = -3 #음수가 한 개 일 경우만 몫이 음수 2
            f = int(goStack.popleft())
            s = int(goStack.popleft())
            if f == 0:
                return "ERROR"
            result = divmod(abs(s), abs(f))[0]  # 몫
            if f * s < 0:
                result = result * (-1)
            if abs(int(result)) > 10 ** 9:
                return "ERROR"
            goStack.appendleft(result)
        elif pL == "MOD":  # 나눈 나머지 저장 ex) -13 mod 4 = -1, -13 mod -4 = -1 #나머지는 첫 수 부호 따라감 2
            f = int(goStack.popleft())
            s = int(goStack.popleft())
            if f == 0:
                return "ERROR"
            result = divmod(abs(s), abs(f))[1]  # 나머지
            if s < 0:
                result = result * (-1)
            if abs(int(result)) > 10 ** 9:
                return "ERROR"
            goStack.appendleft(result)
        else:
            return "ERROR"

    if len(goStack) == 1:
        return goStack[0]

    return "ERROR"


while True:
    programList = []

    while True:
        programTmp = input().strip()
        if programTmp == "QUIT":
            quit()  # 종료
        if programTmp == "END":
            break
        programList.append(programTmp)

    n = int(input())
    for _ in range(n):
        num = int(input())
        print(goStack(programList, num))

    print()
    input()  # 빈 줄 읽기

