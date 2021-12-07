import sys


def input():
    return sys.stdin.readline().rstrip()


# 들어오는 간선이 하나도 없는 root node가 존재해야 한다.
# 모든 노드는 반드시 단 '하나의' '들어오는' 간선이 존재한다.
# 루트에서 다른 노드로 가는 경로는 반드시 가능하며, 유일하다. 이는 루트를 제외한 모든 노드에 성립해야 한다.
 # -> 노드 수 = 엣지 + 1

# 존재하는 모든 수를 list화 했을 때, value에 존재하지 않는 애가 있다 = root
# value에 같은 것이 한 개 이상이 존재하면 -> 단 하나의 들어오는 간선이 아님
treeList = []
tree = {}

while True:
    aline = input().split(" ")
    #print(aline)
    if aline[0] == '':
        continue
    if aline[0] == '-1':
        break
    for i in range(0,len(aline),3):
        if aline[i] == '0':
            treeList.append(tree)
            tree = {}
            break
        else:
            if aline[i] in tree:
                tree[aline[i]].append(aline[i+1])
            else:
                tree[aline[i]] = [aline[i + 1]]

#print(treeList)

def isTree(aTree,index):
    keyList = list(aTree.keys()) #나가는 엣지 노드들 (u)
    valueList = list(aTree.values()) #들어오는 엣지 노드들 (v)

    #들어오는 엣지가 존재하는 노드들(value)
    totalValueList = []
    for value in valueList:
        totalValueList.extend(value)
    totalValueSet = set(totalValueList)

    isRoot = 0
    for key in keyList:
        if key not in totalValueList: #only u이기만 한 노드 검사 (Root)
            isRoot += 1

    totalKeyValue = set(keyList).union(totalValueSet) #총 노드 개수
    #print(totalKeyValue)
    if len(totalKeyValue) == 0: #노드의 개수가 0개여도 트리이다.
        print("Case " + str(index) + " is a tree.")
        return
    if isRoot != 1: #only u인 루트 노드는 하나만 존재할 수 있다.
        print("Case "+str(index)+" is not a tree.")
        return
    if len(totalValueList) > len(totalValueSet): #v가 두 개 이상인 노드 존재하는지 검사
        print("Case "+str(index)+" is not a tree.")
        return
    # if len(totalKeyValue) != len(totalValueList)+1: #트리는 node = edge + 1이어야 한다.
    #     print("Case " + str(index) + " is not a tree.")
    #     return
    print("Case " + str(index) + " is a tree.") #이외의 것들


for i,aTree in enumerate(treeList):
    isTree(aTree,i+1)