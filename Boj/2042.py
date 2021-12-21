import sys
import math


def input():
    return sys.stdin.readline().rstrip()


# 세그멘테이션 트리 사용
# 부분합을 구해야 하고, 특정 부분에 대한 수의 변경이 빈번하게 일어날 때, segmentation tree를 사용한다.

def segmentation_size(node):
    height = int(math.ceil(math.log10(node) / math.log10(2))) + 1  # 세그먼트 트리의 높이 구하기 (log노드수/log2 + 1)
    size = int(math.pow(2, height)) #트리 최대 노드 개수
    return size


def segmentation_init(start, end, index):
    # print("(",start,end,")",index)
    # print("==",tree)
    if start == end:
        tree[index] = num[start-1]  # leaf 노드에 도달했으면 배열 값 삽입
        return tree[index]

    mid = (start + end) // 2  # leaf노드가 아니면 좌,우 가지치기 진행
    tree[index] = segmentation_init(start, mid, index * 2) + segmentation_init(mid + 1, end, index * 2 + 1)
    return tree[index]

def segmentation_target(start, end, index, target_start, target_end):
    #print("target_sum : (", start, end, ")", index)
    if target_start > end or target_end < start:  # target 범위가 겹치지 않을 때
        return 0

    if target_start <= start and end <= target_end:  # tree특정 노드의 범위가 target 범위에 포함될 때
        return tree[index]

    mid = (start + end) // 2  # target의 범위가 일부만 걸쳐있으면 다 찾아내야 하니까 가지치기 진행
    return segmentation_target(start, mid, index * 2, target_start, target_end) + segmentation_target(mid + 1, end,
                                                                                                      index * 2 + 1,
                                                                                                      target_start,
                                                                                                      target_end)

def segmentation_update(start, end, index, updateIdx, diff):
    if updateIdx < start or end < updateIdx:  # 범위에서 벗어나면 할 필요 없음
        return
    #print("update : (", start, end, ")", index)
    tree[index] += diff  # 범위에 해당하는 거니까 업데이트

    if start == end: #가지 끝까지 갔으면 가지치기X
        return

    mid = (start + end) // 2  # 가지치기 해서 updateIdx에 해당하는 부분의 노드 업데이트 진행
    segmentation_update(start, mid, index * 2, updateIdx, diff)
    segmentation_update(mid + 1, end, index * 2 + 1, updateIdx, diff)
    return

# input
N, M, K = map(int, input().split(" "))
num = [int(input()) for _ in range(N)]

command = [list(map(int, input().split(" "))) for _ in range(M + K)]
# print(num)
# print(command)

tree = [0] * segmentation_size(len(num))
segmentation_init(1, len(num), 1)  # start,end,index
#print("init : ",tree)
for co in command:
    if co[0] == 1:  # co[1]자리를 co[2]으로 변경
        diff = co[2] - num[co[1]-1]
        num[co[1]-1] = co[2] #num도 업데이트 해줘야 함
        #print("diff - ",tree[co[1]], co[2],diff)
        segmentation_update(1,len(num),1,co[1],diff)
        #print("update complete : ", tree)
    elif co[0] == 2:  # co[1]부터 co[2]까지 더해서 출력
        print(segmentation_target(1,len(num),1,co[1],co[2]))
