import sys
def input():
    return sys.stdin.readline().rstrip()

sdoku = [list(map(int,input().split(" "))) for _ in range(9)]
#print(sdoku)
zeros = [(i,j) for i in range(9) for j in range(9) if sdoku[i][j] == 0] #빈칸만 뽑아내기
#print(zeros)

#한 행, 한 열에는 1~9까지의 숫자가 하나씩만 나타남
#한 네모에는 1~9까지의 숫자가 하나씩만 나타남

#조건1. 가로 검사 / sdoku[zero[0]][0~8]
#조건2. 세로 검사 / sdoku[0~8][zero[1]]
#조건3. 블락 검사 / for r in range((zero[0]%3)*3) for c in range((zero[1]%3)*3)
#zero마다 1,2,3 조건 검사를 진행해서 가능한 candidate를 추리고, 그 candidate중에 하나를 선택해서 갱신한 sdoku,block 제공
def get_candidate(a,b):
    cand = set()
    # 가로,세로 검사
    for num in range(9):
        cand.add(sdoku[a][num]) # 가로 검사
        cand.add(sdoku[num][b]) # 세로 검사
    # block 검사
    for r in range((a // 3) * 3,(a // 3) * 3 + 3):
        for c in range((b // 3) * 3, (b // 3) * 3 + 3):
            cand.add(sdoku[r][c])
    candidate = list({1, 2, 3, 4, 5, 6, 7, 8, 9} - cand)
    #print(candidate)
    return candidate

#바뀌는 것 zero, sdoku(변경, 원상복구해가며)
def dfs(zero_index):
    #종료조건
    if zero_index == len(zeros):
        #프린트
        [print(*row) for row in sdoku]
        sys.exit()
    #가지치기
    a,b = zeros[zero_index]
    candidate = get_candidate(a,b)
    #print("&",candidate)
    for c in candidate:
        print("====",a,b,"candidate:",c)
        sdoku[a][b] = c #스도쿠 판 바꿔서 전달
        dfs(zero_index+1)
    sdoku[a][b] = 0 #원상복구

dfs(0) #zero 0부터 다 돌면서 확인