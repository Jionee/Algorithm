
def solution(n, computers):
    answer = 0
    visited = [False for i in range(n)]
    
    for com in range(n):
        if visited[com]==False:#아직 방문X
            Dfs(visited,computers,com,n)
            answer+=1 #Dfs끝까지 다 하고 나면 네트워크 하나 완성
    return answer

def Dfs(visited,computers,com,n):
    visited[com]=True #방문했으니까
    for c in range(n):
        if c != com and computers[com][c]==1: #연결됨 -> 다음 연결된 애 찾기
            if visited[c] == False:
                Dfs(visited,computers,c,n)
    
