import sys
# 3 3
# 3 1 2
# 4 1 4
# 2 2 2
n,m = map(int,sys.stdin.readline().split(" "))
nums = []
for _ in range(n):
    nums.append(list(map(int,sys.stdin.readline().split(" "))))
#print(nums)

#각 줄에서 최소 구해서 비교
minArr = []
for n in nums:
    #print(min(n))
    minArr.append(min(n))

print(max(minArr))