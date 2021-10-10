import sys
def input():
    return sys.stdin.readline().rstrip()
sdoku = []
sdoku.append(input().split(" "))
for _ in range(len(sdoku[0])-1):
    sdoku.append(input().split(" "))
#print(sdoku)

block = [[] for _ in range(9)]
#0 1 2
#3 4 5
#6 7 8

#한 행, 한 열에는 1~9까지의 숫자가 하나씩만 나타남
#한 네모에는 1~9까지의 숫자가 하나씩만 나타남
zero = []
for r,sdo in enumerate(sdoku):
    for c,s in enumerate(sdo):
        if s == '0': #빈칸
            zero.append((r,c))
        else: #블락 만들기
            #0,1,2/3,4,5/6,7,8
            if r>=0 and r<3:
                if c>=0 and c<3:
                    block[0].append(s)
                elif c>=3 and c<6:
                    block[1].append(s)
                elif c>=6 and c<9:
                    block[2].append(s)
            if r>=3 and r<6:
                if c>=0 and c<3:
                    block[3].append(s)
                elif c>=3 and c<6:
                    block[4].append(s)
                elif c>=6 and c<9:
                    block[5].append(s)
            if r>=6 and r<9:
                if c>=0 and c<3:
                    block[6].append(s)
                elif c>=3 and c<6:
                    block[7].append(s)
                elif c>=6 and c<9:
                    block[8].append(s)

print(zero)
print(block)
#candidate set을 만들어서 안되는 것은 제외하는 방식으로 가다가 하나
def dfs(sdo,zero,block):
    #종료조건
    #0이 있는 list 크기가 0

    #가지치기
    pass


dfs(sdoku,zero,block)