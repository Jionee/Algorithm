import heapq
#자바의 priorityqueue. 별도의 자료형이 아니라 리스트를 우선순위 큐로 사용 할 수 있게 하는 모듈이다

def solution(jobs):
    answer = 0
    count,last = 0,-1
    heap = []
    jobs.sort()
    
    time = jobs[0][0]
    
    while count < len(jobs):
        for s,t in jobs:
            if last < s <= time:
                heapq.heappush(heap,(t,s)) #작업 소요 시간으로 min heap 생성
        #바로 수행 가능한 작업이 있을 경우 
        if len(heap)>0:
            count+=1
            last = time
            term,start = heapq.heappop(heap)
            time += term
            answer += (time - start)
        else:
            time +=1
            
    return answer//len(jobs)

# 실패 코드
# from collections import defaultdict
# from operator import itemgetter

# def solution(jobs):
#     answer = 0
    
#     jobDict = defaultdict(int)
#     endDict = defaultdict(int)
#     for jobTuple in jobs:
#         jobDict[jobTuple[0]] = jobTuple[1]
#         endDict[jobTuple[0]] = jobTuple[0] + jobTuple[1] 
#     print(endDict)
#     #끝나는 시간에 따라 정렬
#     endRank = [index for index,value in sorted(endDict.items(),key = itemgetter(1),reverse=False)]
#     print(endRank)
    
#     useTime = defaultdict(int)
#     start = 0
#     for disk in endRank:
#         useTime[disk] = (start + jobDict.get(disk)) - disk
#         start = start + jobDict.get(disk)
    
#     for use in useTime:
#         answer += useTime.get(use)
        
#     return answer / len(jobs)
