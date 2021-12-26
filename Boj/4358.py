import sys

def input():
    return sys.stdin.readline().rstrip()

tree_dict = {}

while True:
    tree = input()
    if not tree: break
    #print(tree)
    tree_dict[tree] = tree_dict.get(tree,0) + 1
#print(tree_dict)

tree_values = tree_dict.values()
total = sum(tree_values)
tree_keys_sorted = sorted(tree_dict.keys())
#print(tree_keys_sorted)

for tree in tree_keys_sorted:
    tmp = (tree_dict[tree] / total) * 100

    #print(tree,round(tmp,4))
    print('%s %.4f' %(tree,tmp))
