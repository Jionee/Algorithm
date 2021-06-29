import sys
from bisect import bisect_left, bisect_right

n,c = map(int,input().split())
house = sorted([int(sys.stdin.readline()) for _ in range(n)])

#가장 인접한 공유기 사이의 거리의 최대 구하기

#집의 개수가 매우 크기 때문에 순차 탐색으로는 불가
#이진 탐색 O(NlogX)아이디어 생각하기

#기준을 뭐로 둘까?
#공유기끼리의 거리들의 최소 값의 최대를 구하면 되겠다
#기준 : 공유기끼리의 거리들의 최소 값

start = 1
end = house[-1]-house[0] #간격 최대 값

while True:
    mid = (start+end) // 2
    #mid만큼의 간격을 이용하면 c개의 공유기를 설치할 수 있는가?
    install_index = 0
    count = 0
    #1 2 4 8 9
    while install_index < len(house):
        count += 1
        #bisect_left : 정렬 순서를 유지하면서 value를 삽입할 순서를 반환
        install_index = bisect_left(house, house[install_index] + mid)

    #공유기 수가 적으면 간격을 더 좁히기
    if count < c:
        end = mid-1
    #공유기 수가 많으면 간격을 더 넓히기
    elif count >= c:
        start = mid + 1

    if mid == end:
        print(mid)
        break