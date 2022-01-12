import itertools

def solution(numbers):
    answer = 0
    
    #일단 모든 경우의 수를 다 해봐야 하긴 하니까 완전탐색
    numList = list(numbers)
    permList = []
    for i in range(1,len(numList)+1):
        for perm in itertools.permutations(numList,i):
            num = int(''.join(perm))
            permList.append(num)
    permList = list(set(permList)) #중복제거
    
    for num in permList:
        if isPrime(num):
            answer += 1
    
    return answer

def isPrime(num): #소수판별
    if num == 1 or num == 0:
        return False
    for i in range(2,num):
        if num % i == 0: #나머지가 0이면
            return False
    return True
