class Solution {
  public String solution(String[] seoul) {
      String answer = "";
      String kim = "Kim";
      int index = 0;
      for(int i = 0; i < seoul.length; i++)
          if (seoul[i].equals(kim)){
              index = i;
          }

      answer = "김서방은 " + index + "에 있다";
      return answer;
  }
}
