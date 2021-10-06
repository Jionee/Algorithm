import sys
sys.setrecursionlimit(10**6)

N,K = sys.stdin.readline().rstrip().split(" ")
#print(N,K)
K = int(K)
M = len(N)
#자연수 N을 K번 교환 했을 때 만들 수 있는 가장 큰 수
#0으로 시작하면 안됨 -> 1번째 자리수가 0이면 안됨 (바꿀 때 i,j번째를 바꾼다고 하면 i==0 && num[j]==0이면 못바꿈(넘어가기)

#연산을 K번할 수 없으면 -1 출력 -> 어떤 경우? (-> 두자리인데 두번째 자리수가 0, 한 자리수)
#N<=1,000,000이므로 dfs라는 것을 생각해볼 수 있음. 다 해보기

#dp = [[[0 for i in range(1,int(N)+1)] for _ in range(M)] for _ in range(M)]#i,j,num (크기 N*M*M)
dp = [{} for _ in range(K+1)] #num,k (크기 N*K) #num이랑, 몇 번 더 해야 하는지에 최대값 저장해두고 꺼내서 쓰기
#3d_array = [[[0 for _ in range(column)] for _ in range(row)] for _ in range(level)] #3차원 배열 dp테이블 생성
#print(dp)

if (len(N) == 2 and N[1] == '0') or len(N) == 1:
    print(-1)
    quit()

def swap(tmp,a,b):
    tmp[a], tmp[b] = tmp[b], tmp[a]
    return tmp

#visited는 필요없음(계속 방문가능하니까)
def dfs(num,k):
    #종료조건
    if k >= K:
        return int(''.join(num))
    #print(k,num)
    if int(''.join(num)) in dp[k]:
    #if dp[k][int(''.join(num))] != 0:
        return dp[k][int(''.join(num))]
    #if(조건) dfs시행
    for a in range(M):
        for b in range(M):
            #print(a, b)
            if a!=b:
                #print(a,b)
                if a == 0 and num[b] == '0': #0이 맨 앞에 올 수 없음
                    continue
                tmp = dfs(swap(num,a,b),k+1)
                swap(num, a, b)  # 되돌리기
                #print("###(a,b):",a,b, "num:",tmp,"증가k:",k,"num은?",num,"/////dp : ",dp)
                #if len(dp[k].items()) <= 0:
                if int(''.join(num)) not in dp[k]:
                    dp[k][int(''.join(num))] = tmp
                else:
                    dp[k][int(''.join(num))] = max(dp[k][int(''.join(num))], tmp)
                #print("(a,b):",a,b, "swap 후 num:",tmp,"증가후k:",k,"num은?",num,"/////dp : ",dp)
    return dp[k][int(''.join(num))]

print(dfs(list(N),0))
#print(answer)