import sys
def input():
    return sys.stdin.readline().rstrip()

#print(int(47/53*100))

X,Y = map(int,input().split(" ")) # X:게임횟수, Y:이긴게임

Z = int((Y*100/X))
initZ = Z
#print(Z)
if Z >= 99:
    print(-1)
    exit()

start = 0
end = 1000000000
newZ = Z
answer = end
while start <= end:
    mid = (start+end) // 2
    #newZ = int((Y+mid)/(X+mid)*100)
    newZ = (Y+mid)*100 // (X+mid)

    #print(start,end,mid,"==",newZ)

    if newZ > Z:
        answer = min(mid,answer)
        end = mid - 1
    else:
        start = mid + 1

print(answer)