import sys

def input():
    return sys.stdin.readline().rstrip()

N = input()
M = input()
R = input().split()

#priority queue처럼 작동하게 함.
#dictionary를 만들어서 학생 이름만큼 배열 만들고 N,1크기로 배열의 두 번째 원소로 정렬함

#dictionary에서 추천 수 관리 / stack에서는 오래 된거 관리해서
#일단 추천 수 제일 작은거 삭제하는데
#만약 동점으로 작으면 -> stack에서 더 오래된거를 삭제(index 찾기?)
#삭제시 추천 수 0으로 update

candidate = {}
for i,r in enumerate(R):
    if r in candidate: #후보가 이미 있으면
        candidate[R] += 1
    else:
        candidate[R] = 1

print(candidate)
