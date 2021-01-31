# 가장 큰 수
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

### 제한 사항
numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.

### Note
* String은 그 자체를 비교하는 능력 있음
```
class MyComp implements Comparator<String>{
  public int compare(String s1,String s2){
    return (s2+s1).compareTo(s1+s2);
  }
}
```

* Arrays.sort(arr,new MyComp());

* Integer.toString(s) //s가 null일 경우 nullpointerException 오류 발생   
String.valueOf(s) //s가 null일 경우 null 반환 (추천)
