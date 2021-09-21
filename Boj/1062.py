import sys
from itertools import combinations

def input():
    return sys.stdin.readline().rstrip()

candidates = ['b','d','e','f','g','h','j','k','l','m','o','p','q','r','s','u','v','w','x','y','z'] #총 21개
must = {'a','n','t','i','c'}

N, K = map(int, input().split())
input_word = [input() for _ in range(N)]
#print("word",word,N,K)

word = []
for i in range(N):
    word.append(list(set(input_word[i]) - must))
#print("sdfsdf",word)

bit_word = [0] * N
for i, W in enumerate(word):
    for w in W:
        bit_word[i] |= 1 << ord(w) - ord('a') #해당하는 각 비트에 1 세팅 #1이 하나라도 존재하면 1로 세팅
#print(bit_word)
if K < 5:
    print(0)
elif K == 26:
    print(N)
else:
    #combination사용
    #가능한 k-5개의 알파벳들의 조합을 구하려면 2의 n승들의 집합에서 k-5개를 뽑은 후 sum 시킨 애를 구하면 됨
    power_of_2 = [2 ** i for i in range(26)]
    max_count = 0

    for comb in combinations(power_of_2 , K-5):
        current = sum(comb)
        count = 0
        for bit in bit_word:
            #test가 얼마나 만족하는지 확인
            if bit & current == bit:
                count += 1
        if count > max_count:
            max_count = count
    print(max_count)
