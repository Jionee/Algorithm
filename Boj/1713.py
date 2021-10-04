import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
M = input()
R = map(int,input().split())

#priority queue처럼 작동하게 함.
#dictionary를 만들어서 학생 이름만큼 배열 만들고 N,1크기로 배열의 두 번째 원소로 정렬함

#dictionary에서 추천 수 관리 / stack에서는 오래 된거 관리해서
#일단 추천 수 제일 작은거 삭제하는데
#만약 동점으로 작으면 -> stack에서 더 오래된거를 삭제(index 찾기?)
#삭제시 추천 수 0으로 update -> (아예 dic에서 pop시켜버리자)

candidate = {}

for i,r in enumerate(R):
    #액자가 가득 찼을 때
    if len(candidate) >= N and r not in candidate:
        #print(candidate)
        #추천수가 가장 적으면서, 오래된거 삭제하기(dictionary에서 pop)
        sorted_cand = sorted(candidate.items(), key = lambda item: item[1][0])
        min_like = sorted_cand[0][1][1]
        delete_cand = sorted_cand[0]

        same_like_cand = []
        for cand in sorted_cand:
            if cand[1][1] == min_like:
                same_like_cand.append(cand[0]) #같은 애들 담음
        #오래된거 뽑기
        if len(same_like_cand) != 0:
            #print(same_like_cand)
            sorted_old_cand = sorted(same_like_cand)
            delete_cand = sorted_old_cand[0]

        #dictionary에서 delete_cand삭제하고, stack에서도 pop하기
        del candidate[delete_cand]

    #추천 수 올리기
    if r in candidate: #후보가 이미 있으면
        candidate[r][0] += 1
    else:
        tmp = [1,i]
        candidate[r] = tmp
#print(candidate)

#정답내기
answer = sorted(candidate.keys())
for a in answer:
    print(str(a) + " ", end='')