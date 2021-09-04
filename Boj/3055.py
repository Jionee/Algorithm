import sys

# 3 3
# D.*
# ...
# .S.

input = sys.stdin.readline

row, column = map(int, sys.stdin.readline().split(" "))
qMap = [list(sys.stdin.readline().strip()) for _ in range(row)]
print(qMap)

# 상하좌우 (행,열 순)
arrow = [[-1, 0], [1, 0], [0, -1], [0, 1]]
answer = 0


# 한 턴 지날 때마다 answer ++
# 물 확장 가능한지 확인 -> 다음 비버 이동 가능한 경우 모두 재귀 돌리기
# 물은 돌X,DX / S만나면 return
# 비버는 돌X,물X
# 끝나는 조건은 비버가 D만났을 때 / 비버 죽었을 때 (return)

def widenWater(qMap):
    for r in range(row):
        for c in range(column):
            if qMap[r][c] == '*':
                # 물 상하좌우 비교
                for a in arrow:
                    ro = r + a[0]
                    co = c + a[1]
                    # 벽
                    if ro < 0 or ro > r - 1 or co < 0 or co > c - 1:
                        continue
                    # 돌(X),굴(D),물(*)
                    elif qMap[ro][co] == 'X' or qMap[ro][co] == 'D' or qMap[ro][co] == '*':
                        continue
                    # 고슴도치 만남
                    elif qMap[ro][co] == 'S':
                        return "DEATH"
                    else:
                        qMap[ro][co] = '*'
    return qMap


def moveBeaver(qMap, pAnswer):
    for r in range(row):
        for c in range(column):
            if qMap[r][c] == 'S':
                # 비버 상하좌우 비교
                for a in arrow:
                    ro = r + a[0]
                    co = c + a[1]
                    # 벽
                    if ro < 0 or ro > r - 1 or co < 0 or co > c - 1:
                        continue
                    # 돌(X),물(*)
                    elif qMap[ro][co] == 'X' or qMap[ro][co] == '*':
                        continue
                    # 굴(D)
                    elif qMap[ro][co] == 'D':
                        return 'SUCCESS'
                    # 빈칸 - 이동 가능
                    elif qMap[ro][co] == '.':
                        qMap[r][c] = '.'
                        qMap[ro][co] = 'S'
                        # 재귀함수
                        pAnswer += 1
                        doGame(qMap, pAnswer)
                    # 더 이상 이동 불가
                    else:
                        return 'KAKTUS'


def doGame(Map, pAnswer):
    # 물 확장
    wWater = widenWater(Map)
    # answer 줄이기
    if wWater == "DEATH":
        return "DEATH"
    mBeav = moveBeaver(wWater, pAnswer)
    if mBeav == "SUCCESS":
        print(pAnswer)
        quit()


# main함수
answer = 0
doGame(qMap, answer)
