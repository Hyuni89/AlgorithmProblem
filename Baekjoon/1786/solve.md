# 1786. Searching  
KMP(Knuth-Morris-Pratt) Algorithm  

## Brief  
공백을 포함하는 두 문자열이 주어진다.  
처음으로 주어진 문자열에서 두번째로 주어진 문자열이 몇번, 그리고 어디에 위치하는지 구한다.  

> Input  
> T (String, 1 <= T.length <= 1,000,000)  
> P (String, 1 <= P.length <= 1,000,000)  

## Approch  
1. 가장 기본적인 문자열 비교 방법은 처음부터 loop를 돌며 완전 탐색을 하는 방법인데, 문자열의 길이가 길면 시간초과 발생 (Time Complexity: O(T.length * P.length))  

2. 문제에 설명에 KMP Algorithm의 개념이 대략적으로 나와 있어서 그 방법으로 접근  

## Solution  
1. KMP(Knuth-Morris-Pratt) Algorithm  
    - 검색하려는 문자열의 중복되는 내용을 기록하고 문자열 비교시 기록한 내용을 바탕으로 반복 작업을 제거 (Time Complexity: O(T.length + P.length))  
    > 예를 들어,  
    > ABCDABCDABDE에서 ABCDABD를 찾을 경우  
    > | A | B | C | D | A | B | C | D | A | B | D | E |  
    > |:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|  
    > | A | B | C | D | A | B | ~~D~~ | | | | | |  
    > | | | | | A | B | C | D | A | B | D | |  
    > D가 나와야 할 자리에 C가 나와서 일치하지 않게 된다면 C가 나오기 전 AB가 찾을 문자열에 포함 되어 있음을 알고, C위치서부터 AB이후의 문자열을 비교한다.  

    - 검색하려는 문자열의 중복되는 내용을 **정확히 저장**하는 과정이 가장 중요  

    > ABCDABD 문자열의 경우,  
    > | A | B | C | D | A | B | D |  
    > |:-:|:-:|:-:|:-:|:-:|:-:|:-:|  
    > | -1 | -1 | -1 | -1 | 0 | 1 | -1 |  
    

## Link  
[1786 Searching](https://www.acmicpc.net/problem/1786)  

## Optimizing  
- 내가 작성한 코드와 다른 사람의 코드의 채점 시간 차이가 굉장히 크게 나서 최적화 할 여지가 충분히 존재  

    1. Input을 받을 때, new를 호출 할 필요 없음  
    2. 결과를 저장 할 때, ArrayList에 저장하는 것 보다 StringBuilder에 하는 것이 저장하는 과정이나 출력하는 과정에 이점이 큼  
    3. KMP Algorithm의 개선(깔끔하지 않음)  
    ```java
    // 중복되는 내용을 기록하는 부분  
    int[] reg = new int[P.length()];
    int index = 0;
    for (int i = 1; i < reg.length; i++) {
        while (index > 0 && P.charAt(i) != P.charAt(index)) {
            index = reg[index - 1];
        }
        if (P.charAt(i) == P.charAt(index)) {
            reg[i] = ++index;
        }
    }

    // 기록한 내용을 바탕으로 문자열을 검색하는 부분  
    index = 0;
    for (int i = 0; i < T.length(); i++) {
        while (index > 0 && T.charAt(i) != P.charAt(index)) {
            index = reg[index - 1];
        }
        if (T.charAt(i) == P.charAt(index)) {
            if (++index == P.length()) {
                count++;
                sb.append(i - P.length() + 2 + " ");
                index = reg[P.length() - 1];
            }
        }
    }
    ```