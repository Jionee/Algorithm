from itertools import permutations

def solution(numbers):
    answer = 0

    resultList = []
    for n in range(len(numbers)):
        tmp = list(permutations(numbers,n+1))
        resultList += [int(''.join(i)) for i in tmp]

    resultList = list(set(resultList))

# 잘못된 풀이 - len(numbers) 이하의 모든 길이 만큼씩 다 조합을 구했어야 했는데 len(numbers)로만 구함
#     sortList = list(permutations(numbers,len(numbers)))
#     for i in numbers:
#         sortList.append(i)
#     print("permu ",sortList)

#     # 모든 조합 튜플 stirng으로 연결
#     resultList = []
#     for s in sortList:
#         tmp = ""
#         for i in s:
#             tmp += i
#         resultList.append(tmp)

#     print(resultList)

#     #int로
#     resultList = set(list(map(int,resultList)))
#     print(resultList)

    #소수 판별
    for r in resultList:
        if isDecimal(r) and r > 1:
            print("소수 -", r)
            answer += 1

    return answer

#=========================================
def isDecimal(num):
    for i in range(2,num):
        if num%i == 0: #하나라도 나머지==0이면 소수 아님
            return False
    return True
