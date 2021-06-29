# 정상작동코드
def solution(routes):
    answer = 0
    routes.sort(key = lambda x:x[1]) #sorting 제일 먼저 빠져 나간 애 순서대로
    #가장 진출이 빠른 애 순으로 정렬한 이유 > 가장 먼저 나간애의 진출로에 카메라를 설치해야 걔가 찍힐 수 있음. 
    #그 후 반복문을 이용하여 진출 순서대로 설정된 카메라가 진입보다 빨리 있으면 answer증가시키고 카메라 초기화
    
    camera = -300000
    for car in routes:
        if camera < car[0]:
            answer+=1
            camera = car[1]
    
    return answer

# 실패,,
def solution(routes):
    answer = 0
    
    routes.sort(key = lambda x:x[0]) #sorting
    print(routes)
    
    routesNum = len(routes)
    saw = 0 #검사한 개수
    
    #앞에서부터 겹치는게 있는지 확인 후 
    #겹치면 - answer증가시키고 a,b,c,d 중 넘어가서 c랑 d비교
    #안겹치면 - a,b,c,d중 b랑 c 비교
    #언제까지? - 검사한 개수랑 routes길이랑 같을 때까지
    a,b = 0,1
    while saw < routesNum:
        print(isOverlap(routes[a],routes[b]),"(",a,b,")",saw)
        if isOverlap(routes[a],routes[b]):
            a+=2
            b+=2
            saw+=2
            answer+=1
        else:
            a+=1
            b+=1
            saw+=1
    return answer

def isOverlap(a,b):
    if a[0] == b[0]:
        return True
    elif a[0] < b[0] and b[0] <= a[1]:
        return True
    else:
        return False
