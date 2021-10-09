import sys
from itertools import combinations

def input():
    return sys.stdin.readline().rstrip()

L,C = map(int,input().split(" "))
letter = list(input().split(" "))
#print(letter)

#최소 한 개의 모음(a,e,i,o,u)
#최소 두 개의 자음
#알파벳은 오름차순으로 정렬
#-> 조합만 찾아내서 정렬해서 출력하면 됨

#암호는 L개로 구성되어 있음
#주어진 C개의 문자를 이용해서 모음 set, 자음 set을 만든다.
#{a,i} {t,c,s,w}

standard_vowels = {"a","e","i","o","u"}
vowel = set() #모음
consonant = set() #자음

for l in letter: #모음,자음 분류하기
    if l in standard_vowels:
        vowel.add(l)
    else:
        consonant.add(l)
#print(vowel,consonant)

answer = []
for i in range(1,len(vowel)+1):
    if L-i >= 2:
        vowel_collection = list(combinations(vowel,i))
        consonant_collection = list(combinations(consonant,L-i))
        #print(i,L-i,vowel_collection,consonant_collection)
        for vc in vowel_collection:
            for cc in consonant_collection:
                answer.append(vc+cc)
#print(answer)

answer_str = []
for a in answer:
    #print(''.join(a).sort())
    #print(''.join(sorted(a)))
    answer_str.append(''.join(sorted(a)))
answer_str.sort()

for a in answer_str:
    print(a)