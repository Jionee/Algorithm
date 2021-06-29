def solution(people, limit):
    answer = 0

    people.sort()
    people.reverse()
    #print(people)
    
    #무거운 순으로 정렬한 후 
    #가장 무거운 애랑 가장 가벼운 애랑 더해서 limit 이랑 비교, 같이 탈 수 있으면 같이 태우고
    #같이 못 타면 무거운 애만 태움
    
    i = 0
    j = len(people)-1
    while i <= j:
        answer += 1
        if people[i]+people[j] <= limit: #둘 다 태울 수 있으면 j도 감소
            j-=1
        i+=1 #무거운 애는 무조건 빠짐
    return answer


#효율성 테스트 XX - pop,remove 등을 쓰면 효율성 테스트를 통과하지 못한다. index비교를 통해 풀어야 함
def solution(people, limit):
    answer = 0

    while len(people) > 1:
        maxNum = people[0]
        for p in people:
            if maxNum == 240:   break
            if maxNum < p:  maxNum = p
        #maxNum = max(people)
        
        maxIndex = 0
        isTrue = False
        number = []
        for index,p in enumerate (people):
            if p <= limit - maxNum:
                number.append(p)
                isTrue = True
        mins = 0
        if len(number)>0:   mins = min(number)
            
        if isTrue:  people.remove(mins)
        people.remove(maxNum)
        answer+=1
        
    if len(people) == 1:
        answer+=1
        
    return answer
