import sys


# 모든 단어에 최대 알파벳 개수 = 10개 (0~9)
# 한 수의 최대 자리수 8자리

# greedy -> 각 알파벳의 중요도를 기록하자
# 어차피 모든 알파벳의 합을 구하는 것이므로 각 알파벳이 존재하는 자릿수를 기록해놓는다면 최대를 구할 수 있음

def input():
    return sys.stdin.readline().rstrip()


N = int(input())
word = [input() for _ in range(N)]
#print(word)
alpha_dict = {}  # 알파벳별 자릿수에 몇 번 나타났는지를 기록할 딕셔너리
for w in word:
    for i, alpha in enumerate(w):
        if alpha in alpha_dict:
            alpha_dict[alpha] += pow(10, len(w) - i - 1)
        else:
            alpha_dict[alpha] = pow(10, len(w) - i - 1)
#print(alpha_dict)

sorted_dict = list(alpha_dict.values())
sorted_dict.sort()
sorted_dict = list(reversed(sorted_dict))
#print(sorted_dict)

sum = 0
number = 9
for sd in sorted_dict:
    sum += number * sd
    number -= 1
print(sum)