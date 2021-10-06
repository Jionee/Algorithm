import sys
sys.setrecursionlimit(10**5)

N,K = sys.stdin.readline().rstrip().split(" ")
#print(N,K)
K = int(K)
M = len(N)
#자연수 N을 K번 교환 했을 때 만들 수 있는 가장 큰 수
#0으로 시작하면 안됨 -> 1번째 자리수가 0이면 안됨 (바꿀 때 i,j번째를 바꾼다고 하면 i==0 && num[j]==0이면 못바꿈(넘어가기)

#연산을 K번할 수 없으면 -1 출력 -> 어떤 경우? (-> 두자리인데 두번째 자리수가 0, 한 자리수)
#N<=1,000,000이므로 dfs라는 것을 생각해볼 수 있음. 다 해보기

if (len(N)==2 and N[1]=='0') or len(N)==1:
    print(-1)
    quit()

def swap(tmp,a,b):
    tmp[a], tmp[b] = tmp[b], tmp[a]
    return tmp

answer = 0
#visited는 필요없음(계속 방문가능하니까)
def dfs(num,k):
    global answer
    #종료조건
    if k >= K:
        answer = max(answer,int(''.join(num)))
        return answer
    #if(조건) dfs시행
    for a in range(M):
        for b in range(M):
            #print(a, b)
            if a!=b:
                #print(a,b)
                if a == 0 and num[b] == '0': #0이 맨 앞에 올 수 없음
                    continue
                # tmp = num
                # tmp[a],tmp[b] = tmp[b],tmp[a] #자리 바꾸기
                dfs(swap(num,a,b),k+1)
                swap(num, a, b) #되돌리기
                #print("(a,b):",a,b, "swap 후 num:",swap(num,a,b),"증가후k:",k,"num은?",num)
    return

dfs(list(N),0)
print(answer)