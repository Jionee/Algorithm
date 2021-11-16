import sys
def input():
    return sys.stdin.readline().rstrip()

N,M = map(int,input().split(" "))
tree_height = list(map(int,input().split(" ")))

#tree - height 들의 sum >= M
#이분탐색 이용 #cut을 이분탐색
start = 0
end = max(tree_height)

def calculate(cut):
    sum = 0
    for tree in tree_height:
        if tree > cut:
            sum += tree - cut
    return sum
answer = 0

while start <= end:
    mid = (start + end) // 2
    if calculate(mid) == M:
        answer = mid
        break
    else:
        if calculate(mid) > M: #더 많이 잘렸으므로 cut높이를 높이기
            start = mid + 1
        else: #덜 잘렸으므로 cut높이를 낮추기
            end = mid - 1
    answer = start

print(answer)