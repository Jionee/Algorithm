def solution(answers):
    su1 = [1,2,3,4,5]
    su2 = [2,1,2,3,2,4,2,5]
    su3 = [3,3,1,1,2,2,4,4,5,5]
    
    score = [0,0,0]
    for i,a in enumerate(answers):
        if su1[i%len(su1)] == a:
            score[0] += 1
        if su2[i%len(su2)] == a:
            score[1] += 1
        if su3[i%len(su3)] == a:
            score[2] += 1
            
    answer = []
    score_max = max(score)
    for index,s in enumerate(score):
        if score_max == s:
            answer.append(index+1)
    return answer
