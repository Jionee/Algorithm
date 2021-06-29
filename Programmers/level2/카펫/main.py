def solution(brown, yellow):
    answer = []
    sums = brown+yellow
    #sums로 만들 수 있는 모든 조합의 수 구하기
    #tuple로 가능한 조합 만들기
    #1,2는 제외, 3부터 구하기 시작
    lists = []
    for i in range(3,sums):
        if sums%i == 0:
            a = i
            b = int(sums/i)
            if a > b: #대칭이므로 중단
                break
            lists.append((b,a)) #가로,세로로 저장
    #print(lists)
    #홀수면 1...
    #짝수면 2...
    #가로,세로 곱해서 yellow와 같으면 그게 정답
    isEnd = False
    
    for item in lists:
        list1 = []  
        list2 = []
        list1 = makeList(item[0])
        list2 = makeList(item[1])
        #print(list1,list2)
    
        for one in list1:
            for two in list2:
                if one*two == yellow:
                    answer.append(item[0])
                    answer.append(item[1])
                    isEnd=True
        if isEnd:
            break
    return answer

def makeList(number):
    list1 = []
    if number%2==0:
        for i in range(1,int(number/2)):
            list1.append(i*2)
    else:
        for i in range(0,int(number/2)):
            list1.append(i*2+1)
    return list1
