def solution(bridge_length, weight, truck_weights):
    answer = 0
    
    # 0,0...0 bridge_length만큼 list생성
    q = [0] * bridge_length
    
    while q:
        #print(answer," : ",q)
        answer += 1
        q.pop(0) #처음 들어간 애 pop
        
        if truck_weights: #안건넌애가 존재할 때
            if sum(q) + truck_weights[0] <= weight: #10kg안넘으면 안건넌애 추가
                q.append(truck_weights.pop(0))
            else: #아니면 0 추가
                q.append(0)
    
    return answer #다 건넜을 때
