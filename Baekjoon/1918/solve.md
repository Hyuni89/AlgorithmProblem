# 1918. PostOrder  
후위 순회 방식으로 전환 (InOrder -> PostOrder)  


## Brief  
InOrder로 표기된 식을 PostOrder로 변경해 출력  

> Input  
> equation (length <= 100, '+', '-', '*', '/', '(', ')'와 알파벳으로 구성)  


## Approch  
1. Stack을 이용하여 연산자 우선 순위에 따라 적절히 pop을 한다.  

2. PreOrder, InOrder, PostOrder 내용은 간단하지만 전환 코드는 생각보다 많이 까다로웠다.  


## Solution  
1. 알파벳이 들어갈 stack과 연산자가 들어갈 stack이 필요  

2. 여는 괄호는 stack에 포함이 되어 닫는 괄호가 나왔을 때, 어디까지가 괄호에 포함되는지 파악  

3. 연산자 우선 순위, 혹은 괄호가 열리는 여부에 따라 지금까지 나온 문자열을 조합하는 시점이 변경  
    

## Link  
[1918 PostOrder](https://www.acmicpc.net/problem/1918)  