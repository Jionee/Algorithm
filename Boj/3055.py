import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

R, C = map(int, input().split())

board = []
visited = [[False for _ in range(C)] for _ in range(R)]
#물, 고슴도치를 큐에 넣고 움직이면서 굴에 들어갔는지 확인할거야
#visited 즉 고슴도치가 움직인 곳은 볼 필요 없겠지?

for _ in range(R):
    board.append(list(input()))

cnt = 0
success = False #굴을 발견했는지
q = deque([])
direction = [(-1,0),(1,0),(0,1),(0,-1)]

#큐에 물, 고슴도치 순으로 넣는다.
for r in range(R):
    for c in range(C):
        if board[r][c] == "*": #물이면
            q.append(("*",0,r,c))

for r in range(R):
    for c in range(C):
        if board[r][c] == "S": #고슴도치면
            q.append(("S",0,r,c))
            visited[r][c] = True

while q: # 큐가 빌 때까지 진행
    what,cnt,now_r,now_c = q.popleft()
    for _r, _c in direction:
        nxt_r, nxt_c = now_r + _r, now_c + _c
        if not (0 <= nxt_r < R) or not (0 <= nxt_c <C): #벽이면
            continue
        if what == "*": #물
            if board[nxt_r][nxt_c] == ".":
                board[nxt_r][nxt_c] = "*"
                visited[nxt_r][nxt_c] = True
                q.append((what,cnt+1,nxt_r,nxt_c))
        else: #고슴도치
            if visited[nxt_r][nxt_c]: #이미 방문했으면
                continue
            if board[nxt_r][nxt_c] == ".": #이동가능
                q.append((what,cnt+1,nxt_r,nxt_c))
                visited[nxt_r][nxt_c] = True
            elif board[nxt_r][nxt_c] == "D": #굴 도착
                success = True
                break
    if success:
        break
if success:
    print(cnt+1)
else:
    print("KAKTUS")