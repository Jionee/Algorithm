#testcase 9,10 시간초과

def solution(number, k):
    answer = ''
    
    numberList = list(map(int,number))
    require = len(number)-k
    answerList = []
    while len(numberList) != require or require ==0 : #list 길이와 남은 숫자 자릿수가 같을 때까지     
        maxNum = numberList[0]
        for i in range(len(numberList)-require) :
            maxNum = numberList[i+1] if maxNum < numberList[i+1] else maxNum
            if maxNum == 9:
                break
        maxIndex = numberList.index(maxNum) #최대 인덱스
    
        for j in range(0,maxIndex+1): #list 재구성
            numberList.pop(0)
        
        answerList.append(maxNum)
        require-=1
        
        if require == 0:
            break
        elif len(numberList) == require: 
            while len(numberList)!=0:
                answerList.append(numberList.pop(0))
            break
    
    answer = "".join(map(str,answerList))
    return answer
