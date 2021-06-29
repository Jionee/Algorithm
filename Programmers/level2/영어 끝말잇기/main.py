def solution(n, words):
    answer = []

    # 각 사람마다 list생성
    lists = []
    for i in range(n):
        tmp = []
        lists.append(tmp)

    #set 생성 (중복제거)
    wordSet = list(set(words))

    index = 1

    end = words[0][len(words[0])-1]
    t = words.pop(0) #첫 단어
    lists[0].append(t)
    wordSet.remove(t)

    while(len(words)>1):
        front = words[0][0]
        tmp = ""

        #앞에꺼랑 뒤에꺼 같으면
        if end == front and (words[0] in wordSet) == True:
            #stack 쌓기
            tmp = words.pop(0)
            wordSet.remove(tmp)
            lists[index].append(tmp)
        else:
            answer.append(index+1 if n!=0 else n)
            answer.append(len(lists[index])+1)
            break
            
        bIndex = index #이전 index
        #list index 정해주기
        index+=1
        if index==n:
            index=0

        end = tmp[len(tmp)-1]

        if len(words) <= 1: #하나 남으면 
            if (words[0] in wordSet) == False: #중복 체크
                answer.append(index+1 if n!=0 else n)
                answer.append(len(lists[bIndex]))
            else: #다통과
                answer.append(0)
                answer.append(0)

    return answer
