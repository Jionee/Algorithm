import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
#lines = [list(map(int, input().split(" "))) for _ in range(N)] #이걸 저장하는 것 자체가 메모리 부족이구나
#print(lines)

# 0번째가 더 커서 선택했는데, 그다다음꺼는 3번째가 진짜 대따 커버리면 그건 선택 못하는거 아닌가? -> 어쨌든 못 가는 곳이라서 필요없네 그냥 큰 것만 생각하면 되는구나
# 처음 했을 때 dp테이블을 3*N 크기로 했으므로 각 맵의 dp값을 저장하는 것으로 했었음
# -> 근데 그 맵 자체의 배열의 최소,최대값을 구하는 것이 아니라 (이렇게 하게 되면 그 배열을 또 지나는 경우가 있을텐데 dp테이블이 존재하기 때문에 그걸 그냥 그대로 사용하는 일이 생긴다.)
# -> 한 흐름에서 최소,최대 값을 구하는 것이므로 + 그리디하게 최소,최대값을 구할 수 있으므로 한 줄 씩 비교해가며 최대,최소 값을 완성시킨다.

#whatSPrev = [[0,1],[0,1,2],[1,2]] #각 위치마다 이전에 거친 위치들

# 어떤 루트로 왔는지는 관심 없고 결과에만 관심 있으므로, 결과만 저장하면 된다.
# 한 줄 내려가는 것을 반복하면서, 최대 인것을 더하고 / 최소 인것을 더하는 과정을 반복한다.

firstLine = list(map(int,input().split(" ")))
ans_max,ans_min = firstLine, firstLine

for _ in range(N-1): #각 줄 돌리기
    line = list(map(int,input().split(" ")))
    tmp_max,tmp_min = [0,0,0],[0,0,0]

    #한 줄에 대해서(한 단계) 0,1,2 각각 다 하기
    tmp_max[0] = max(ans_max[0],ans_max[1]) + line[0] #이전 것에서 최대인 것 택하고 + 현재 자신의 위치 값 더하기
    tmp_min[0] = min(ans_min[0],ans_min[1]) + line[0] #         최소

    tmp_max[1] = max(ans_max[0], ans_max[1], ans_max[2]) + line[1]
    tmp_min[1] = min(ans_min[0], ans_min[1], ans_min[2]) + line[1]

    tmp_max[2] = max(ans_max[1], ans_max[2]) + line[2]
    tmp_min[2] = min(ans_min[1], ans_min[2]) + line[2]

    ans_max, ans_min = tmp_max, tmp_min #각 줄마다 ans 갱신

print(max(ans_max),min(ans_min))
