import sys
import math

def input():
    return sys.stdin.readline().rstrip()

N,M = map(int,input().split(" "))
num = [int(input()) for _ in range(N)]
#print(num)

#세그먼트 트리를 이용해서 최솟값 트리 하나, 최댓값 트리 하나를 만들자
#리프노드 - num 들어가기, start,end 존재하므로 인덱싱할 수 있음, 나누는 조건은 각각 최댓값, 최솟값

def segment_size(node):
    height = int(math.ceil(math.log10(node) / math.log10(2))) + 1 #세그먼트 트리의 높이
    size = int(math.pow(2,height)) #트리 최대 노드 갯수
    return size

def segment_min_init(start,end,index):
    if start == end:
        tree_min[index] = num[start - 1]
        #print("LEAF",start,end,index,tree_min[index])
        return tree_min[index]
    mid = (start + end) // 2
    tree_min[index] = min(segment_min_init(start,mid,index * 2),segment_min_init(mid+1,end,index*2+1))
    #print("@", start, end, index, tree_min[index])
    return tree_min[index]

def segment_max_init(start,end,index):
    if start == end:
        tree_max[index] = num[start - 1]
        return tree_max[index]
    mid = (start + end) // 2
    tree_max[index] = max(segment_max_init(start,mid,index * 2),segment_max_init(mid+1,end,index*2+1))
    return tree_max[index]

def segment_min_target(start,end,index,target_start,target_end):
    #print("%",start,end,tree_min[index])
    if target_start > end or target_end < start:
        return float('inf')
    if target_start <= start and end <= target_end:
        return tree_min[index]

    mid = (start + end) // 2
    return min(segment_min_target(start,mid,index*2,target_start,target_end),segment_min_target(mid+1,end,index*2+1,target_start,target_end))

def segment_max_target(start,end,index,target_start,target_end):
    if target_start > end or target_end < start:
        return 0
    if target_start <= start and end <= target_end:
        return tree_max[index]

    mid = (start + end) // 2
    return max(segment_max_target(start,mid,index*2,target_start,target_end),segment_max_target(mid+1,end,index*2+1,target_start,target_end))


tree_min = [float('inf')] * segment_size(N) #트리 생성
tree_max = [0] * segment_size(N) #트리 생성
segment_min_init(1,N,1)
segment_max_init(1,N,1)
#print(tree_min)
#print(tree_max)

for _ in range(M):
    start,end = map(int,input().split(" "))
    result_min = segment_min_target(1,N,1,start,end)
    result_max = segment_max_target(1,N,1,start,end)
    print(result_min,result_max)