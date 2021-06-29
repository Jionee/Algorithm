from itertools import permutations
def solution(n):
    a = set()
    for i in range(len(n)):
        #set 사용, join, permutatins 사용
        a |= set(map(int, map("".join, permutations(list(n), i + 1))))
        
    # set의 중복 제거 기능 활용하여 0,1 제거
    a -= set(range(0, 2))
    
    #에라토스테네스 체 사용하여 소수 판별
    for i in range(2, int(max(a) ** 0.5) + 1):
        a -= set(range(i * 2, max(a) + 1, i))
    return len(a)
