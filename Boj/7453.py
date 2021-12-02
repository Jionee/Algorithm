import sys
def input():
    return sys.stdin.readline().rstrip()

N = int(input())
A,B,C,D = [],[],[],[]

for n in range(N):
    a,b,c,d = map(int,input().split(" "))
    A.append(a)
    B.append(b)
    C.append(c)
    D.append(d)

#몇번째 애를 가지고 했는지는 중요하지 않아
#a+b+c+d==0
#각 하나씩을 뽑아야 하는 거잖아?
#AB,CD 짝짓기
AB = {}
for a in A:
    for b in B:
        AB[a+b] = AB.get(a+b,0) + 1 #dictionary에 이미 존재하면 그 값을, 존재하지 않으면 default값으로 0을
        # if A[i]+B[k] not in AB:
        #     AB[A[i]+B[k]] = 0
        # else:
        #     AB[A[i]+B[k]] += 1

answer = 0
for c in C:
    for d in D:
        answer += AB.get(-(c+d),0)

print(answer)