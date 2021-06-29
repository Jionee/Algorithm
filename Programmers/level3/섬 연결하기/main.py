#union-find & Kruskal 알고리즘 사용 
def solution(n, costs):
    answer = 0
    parent = list(map(lambda x:x,range(n))) #0...n까지 자기 자신을 담은 리스트 만들기

    costs.sort(key = lambda x:x[2]) #index 2로 리스트 정렬

    for item in costs:
        if findParent(parent,item[0]) == findParent(parent,item[1]): #싸이클 발생
            continue
        else:
            unionParent(parent,item[0],item[1])
            answer += item[2]
            
    return answer

#union-find
def findParent(parent,x):
    #parent가 자기 자신이 아니면 부모 찾기
    if parent[x]!=x:
        return findParent(parent,parent[x])
    return x

def unionParent(parent,a,b):
    #더 작은 값으로 합치기
    a = findParent(parent,a)
    b = findParent(parent,b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
