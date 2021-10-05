import sys
N,K = sys.stdin.readline().rstrip().split(" ")
#print(N,K)
K = int(K)
#K번 교환을 했을 때 만들 수 있는 가장 큰 수
#0으로 시작하면 안됨 -> 1번째 자리수가 0이면 안됨

#연산을 K번할 수 없으면 -1 출력 -> 어떤 경우?(-> 두자리인데 0이 포함되어 있고 K가 홀수일때)
if (len(N) == 2 and N[1] == '0') or (len(N)==1 and K > 1):
    print(-1)
    exit()

num = list(N)
#print(num)

#리스트에서 최대 수를 찾아서 i번째랑 그 max번째랑 swap #i는 0번째부터 바꾸기
#근데 만약 i랑 max랑 같으면 k증가 안하고 넘어감
#k번 다하면 종료
k = 0

for i in range(K*2):
    if k >= K: #k번 다 함
        break
    criteria = num[i:]
    #print("****",criteria)
    if len(criteria) <= 1:
        #swap
        num[len(num)-1],num[len(num)-2] = num[len(num)-2],num[len(num)-1]
        #print("criteria<=1",num,k)
    else:
        maxIndex = i + criteria.index(max(criteria))
        if i == maxIndex:
            #print("i==maxindex", i,maxIndex,num,k)
            continue
        #swap, k 증가
        num[i],num[maxIndex] = num[maxIndex],num[i]
        #print("swap하기",num,"k = ",k,"maxIndex = ",maxIndex,"i = ",i)
    k += 1

print(''.join(num))