import sys


def input():
    return sys.stdin.readline().rstrip()

arr = input()

# ( ) [ ]
# 올바른 괄호열 : 제대로 쌍 맞춰서 닫혀야 함
# 스택 활용 -> stack에 넣다가 쌍 발견하면 pop, 값 계산

answer = 0
brackets = ["(",")","[","]"]
pre = 1
stck = []
brackets_dict = {"(":0,")":0,"[":0,"]":0}

for i,a in enumerate(arr):
    if i < len(arr)-1:
        if (arr[i] == '(' and arr[i+1] == ']') or (arr[i] == '[' and arr[i+1] == ')'):
            print(0)
            exit()
    brackets_dict[a]+=1
#print(brackets_dict)
if not (brackets_dict["("] == brackets_dict[")"] and brackets_dict["["] == brackets_dict["]"]):
    print(0)
    exit()

def function(a,stck):
    if a == '(' or a == '[': #여는 괄호이면
        stck.append(a)
    else: #닫는 괄호이면
        if stck[-1] not in brackets: #숫자면
            if stck[-2] not in brackets: #두번째꺼도 숫자면
                stck.append(stck.pop() + stck.pop()) #더하기
                #stck.append(a)
                function(a,stck)
            else: #맞는 짝이면 해당 숫자
                if stck[-2] == '(' and a == ')':
                    f, s = stck.pop(), stck.pop()
                    stck.append(f * 2)
                elif stck[-2] == '[' and a == ']':
                    f, s = stck.pop(), stck.pop()
                    stck.append(f * 3)
        else: #숫자가 아니라 닫는 괄호 중 맞는 괄호면
            if stck[-1] == '(' and a == ')':
                stck.pop()
                stck.append(2)
            elif stck[-1] == '[' and a ==']':
                stck.pop()
                stck.append(3)
    #print("===", a, "===", stck)
def isNumber(stck):
    for s in stck:
        if s in brackets:
            return True
    return False

for a in arr:
    function(a,stck)

while isNumber(stck):
    function(stck[-1],stck)

print(sum(stck))

#괄호 열렸으면 닫혀야함 #stack에 담다가 닫는거 나왔을 때 맨 위에꺼가 짝이랑 맞으면 (숫자면 숫자 * 로 계산) (숫자 다음에 맞는 괄호면 *, 숫자 다음에 또 숫자면 +)
# ( ( )
# ( 2 9