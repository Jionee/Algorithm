import sys


def input():
    return sys.stdin.readline().rstrip()

arr = input()

# ( ) [ ]
# 올바른 괄호열 : 제대로 쌍 맞춰서 닫혀야 함
# 스택 활용 -> stack에 넣다가 쌍 발견하면 pop, 값 계산

# (()[[]]) ([])
# 2*((2)+3*(3))
#같은 부호 -  부호 방향 같음 (( )) [[ ]]
        #  부호 방향 다름 () )( [] ][
#다른 부호 - 부호 방향 같음 ([ [( )] ])
        #  부호 방향 다름 (] [) )[ ]( # 열리고 닫히기 전에 쌍이 안나오면 안됨

#괄호 닫고, 새로운 괄호 열었을 때 + 삽입

#  (( () ([ (] [( [) [[ []   )(
#2*괄호 2  +  0  +  0 3*괄호 3  +

#종류마다 여는 괄호 수랑, 닫는 괄호 수랑 다르면

answer = 0
category = { '(':2 , ')':6 , '[':3 , ']':7 }
pre = 1
stck = []

for a in arr:
    if a == '(' or a == '[': #여는 괄호이면
        stck.append(a)
    else: #닫는 괄호이면
        if category(stck[-1]) > 5: #꺼내는게 닫는 괄호
            b = stck.pop()
            if category[a] % 4 == category[b] % 4:  # 같은 종류이면
                pre = pre * category[a]
            else:
                print(0)
                exit()
        else: #꺼내는게 여는 괄호
            answer += pre
            pre = 1

print(answer)