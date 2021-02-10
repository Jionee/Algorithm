# 소수 찾기
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.


### 제한사항
numbers는 길이 1 이상 7 이하인 문자열입니다.
numbers는 0~9까지 숫자만으로 이루어져 있습니다.
013은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

### Note
* join 함수 - list의 문자열 합치기 가능
```
list = ["hi","I","am","Jiwon"]
joined = "".join(list)
> hi I am Jiwon

# 특정 문자로 가능 

joined = "-".join(list)
> hi-I-am-Jiwon
```

* join함수 + for문
* itertools 사용해서 모든 조합 생성 가능 
-- permutations(list, length)

```
from itertools import permutations
```
```
for n in range(len(numbers)):
  tmp = list(permutations(numbers,n+1))
  resultList += [int('').join(i) for i in tmp]
```

* 소수 판별
```
def isDecimal(num):
    for i in range(2,num):
        if num%i == 0: #하나라도 나머지==0이면 소수 아님
            return False
    return True
```
