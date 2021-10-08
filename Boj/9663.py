import sys
sys.setrecursionlimit(10**6)

N = int(sys.stdin.readline().rstrip())

# 퀸이 놓여져 있을 때 가로,세로,대각선 방향에는 퀸이 놓여지면 안됨
# = 각 행에는 딱 하나만 놓을 수 있음
# set을 사용하기

answer = 0

dir = [[-1,-1],[-1,1],[1,-1],[1,1]]#대각선 왼위,오위,왼아래,오아래

def dfs(queen,next_queen):
    global answer
    #종료조건
    #열 검사
    #print(queen,next_queen)
    if next_queen in queen:
        return #이미 있으면 안됨

    대각선 검사
    for d in dir:
        for k in range(1,N+1):
            dia_row, dia_column = len(queen) + d[0]*k, next_queen + d[1]*k
            if (dia_row>=0 and dia_row<N) and (dia_column>=0 and dia_column<N):
                for queen_row,queen_column in enumerate(queen):
                    if (dia_row == queen_row) and (dia_column == queen_column): #대각선에 있으면
                        return
    # diagonal check
    # for row, column in enumerate(queen):
    #     h = len(queen) - row
    #     if next_queen == column + h or next_queen == column - h:
    #         return

    queen.append(next_queen) #대각선에 없으면 queen에 추가, 다음 진행

    if len(queen)==N:
        answer += 1
        return

    #가지치기
    for n in range(N): # 열기준
        dfs(queen[:],n) #깊은 복사를 통해 list가 재사용되지 않게 한다.

for n in range(N):
    queen = []
    dfs(queen,n) # 행우선

print(answer)