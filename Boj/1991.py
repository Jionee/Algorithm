import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())

#전위순회(preorder) : Root Left Right
#중위순회(inorder) : Left Root Right
#후위순회(postorder) : Left Right Root

#재귀함수 이용

#트리구성 -> key : root, value : [left,right]
tree = {}
for _ in range(N):
    root,left,right = input().split(" ")
    tree[root] = [left,right]

#print(tree)

def preorder(root):
    if root != '.':
        print(root, end='') #root
        preorder(tree[root][0]) #left
        preorder(tree[root][1]) #right

def inorder(root):
    if root != '.':
        inorder(tree[root][0]) #left
        print(root, end='') #root
        inorder(tree[root][1]) #right

def postorder(root):
    if root != '.':
        postorder(tree[root][0]) #left
        postorder(tree[root][1]) #right
        print(root, end='') #root

preorder('A')
print()
inorder('A')
print()
postorder('A')