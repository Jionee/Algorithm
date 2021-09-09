import sys
from itertools import combinations

def input():
    return sys.stdin.readline().rstrip()

N, K = map(int, input().split())
word = [input() for _ in range(N)]
#print("word",word)

basic = {"a","n","t","i","c"} #무조건 배워야 하는 글자 수
learnNum = K - len(basic)
#print("learnNum",learnNum)
if learnNum < 0:
    print(0)
    exit()
if K == 26:
    print(N)
    exit()

wordSetList = []
for w in word:
    if len(set(list(w))-basic) > learnNum:
        continue
    else:
        wordSetList.append(set(list(w))-basic)

#print("wordSetList",wordSetList)

toLearn = set()
for wo in wordSetList:
    toLearn = toLearn | wo #합집합
#print("toLearn",toLearn)

if len(toLearn) <= learnNum: #무조건 다 배울 수 있음
    print(N)
    exit()

alphComb = combinations(toLearn,learnNum)
#print("alphComb",alphComb)

cntList = []
for comb in alphComb:
    #print(set(comb))
    cnt = 0
    for wordSet in wordSetList:
        if len(wordSet - set(comb)) == 0:
            cnt += 1
    cntList.append(cnt)

print(max(cntList))

# 4 8
# antartica
# antarbtica
# antaelhtica
# antabtica