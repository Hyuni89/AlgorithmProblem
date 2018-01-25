# 11401. Binomial Coefficient 3  
이항계수, 수학...  

## Brief  
이항계수 <sub>n</sub>C<sub>k</sub>의 값을 1,000,000,007로 나눈 나머지를 구한다.  

> 예를 들면,  
> <sub>5</sub>C<sub>2</sub> = 10  

> Input  
> N (1 <= N <= 4,000,000) K (1 <= K <= N)  

## Approach  
1. 쉽게 생각하면 단순 수학 계산이다.  
2. 어려운 부분은 너무 큰 수를 계산 한다는 것이다.  
    - Modulus 부분(특히 역원)을 잘 이해 할 필요가 있다.  
    - 큰 수의 큰 수 제곱을 어떤 값으로 나눌 때, O(log)의 시간 복잡도로 계산 할 수 있어야 한다.  

## Solution  
1. 선행 지식  

> - 이항계수  
> <sub>n</sub>C<sub>k</sub> = $\frac{n!}{(n-k)!k!}$  

> - 팩토리얼  
> N 값에 1,000,000,007로 나눈 값은 미리 저장해 놓는다.  

> - Modulus  
    - 7 mod 4 = 3  
    - 3 $\equiv$ 7 (mod 4)  
    - (a + b) mod c = (a mod c + b mod c) mod c  
    - (a * b) mod c = (a mod c * b mod c) mod c  
    - a<sup>c - 1</sup> $\equiv$ 1 (mode c) (a와 c는 서로소)  
    - $\frac{1}{a}$ $\equiv$ a<sup>c - 2</sup> (mod c) (a와 c는 서로소, 위 식에서 유도)  

2. <sub>n</sub>C<sub>k</sub> $\equiv$ $\frac{n!}{(n-k)!k!}$ $\equiv$ n!((n-k)!k!)<sup>1,000,000,005</sup> (mod 1,000,000,007)  

3. 계산  

## Link  
[11401 Binomial Coefficient 3](https://www.acmicpc.net/problem/11401)  
