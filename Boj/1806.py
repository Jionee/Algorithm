import sys
def input():
    return sys.stdin.readline().rstrip()

N,S = map(int,input().split(" "))
su = list(map(int,input().split(" ")))
#print(su)

#투 포인터 사용
#start,end를 0부터 시작하도록 한다.
#0부터 N-1까지 start를 증가시키면서 연속된 수의 합을 구할 건데, '연속된 수들의 합'이므로 양 끝 포인터들을 더하고 빼면서 시간 초과를 피할 수 있다.
#한 start에서 end가 끝까지(N-1)까지 갔다면 그 다음 start에서는 절대 S이상을 낼 수 없다.

answer = float('inf')
cum_sum = su[0]
start,end = 0,0
while start < N:
    if cum_sum >= S: #S이상일 때
        answer = min(answer, end - start + 1)
        cum_sum -= su[start]
        start += 1
    else: #합이 S이하일 때
        end += 1
        if end == N: #end가 끝까지 왔으면
            break
        cum_sum += su[end]

if answer == float('inf'):
    print(0)
else:
    print(answer)