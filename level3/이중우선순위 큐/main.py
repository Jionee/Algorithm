import heapq
def solution(operations):
    answer = []
    heap = []
    for op in operations:
        splits = op.split(" ")
        if splits[0] == 'I':
            heapq.heappush(heap,int(splits[1]))
        elif splits[0] == 'D':
            if len(heap) > 0:
                if splits[1] == '1':
                    heap.pop(-1)
                    #최댓값삭제
                elif splits[1] == '-1':
                    heapq.heappop(heap)
                    #최솟값삭제

    #없으면
    heap.sort()
    if len(heap) == 0:
        answer.append(0)
        answer.append(0)
    else:
        answer.append(heap.pop(-1))
        answer.append(heapq.heappop(heap))
    return answer
