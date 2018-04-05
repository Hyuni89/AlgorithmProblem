# 10999. Segment Sumation 2  
구간 합을 구해라!!  

## Brief  
숫자 배열이 주어지고, 구간이 주어질 때, 각 구간의 값을 갱신하면서, 합을 구하여라!!  

> Input  
> N M K (1 <= N <= 1,000,000, 1 <= M <= 10,000, 1 <= K <= 10,000)  
> A<sub>1</sub>  
> ...  
> A<sub>N</sub>  
> a b c d (a == 1, from b, to c, update d)  
> ...  
> a b c (a == 2, from b, to c)  

## Approach  
1. Segment Tree를 사용해야 한다는 것은 알겠는데 값을 갱신하게 되니까 시간초과 발생...  

2. Lazy Propagation이라는 기법을 적용해야 한다는 것은 검색을 통해 알게 되었다.  

3. 문제에서 변수 범위가 상세히 나오지 않아서 계속 틀렸다. d는 long long값이 될 수 있다.  

## Solution  
1. Lazy Propagation  
    - Segment Tree의 값을 생신할 때, 갱신해야 할 값을 기록만 해 두고 다시 그 노드를 방문 할 일이 있을 때 계산을 하는 방식을 말한다.  
    
    1. 갱신해야 할 값이 있으면 범위에 해당하는 노드의 값을 갱신하고, 하위 노드는 lazy라는 배열에 갱신을 해야 한다는 것을 알린다.  
    2. 후위 방식으로 갱신을 하고 빠져 나가면서 상위 노드들의 값을 갱신한다.  
    3. 갱신을 하든 합을 구하든 lazy 배열에 갱신해야 할 값이 있다면 갱신을 하면서 노드를 타고 내려가거나 올라간다.  

## Link  
[10999 Segment Sumation 2](https://www.acmicpc.net/problem/10999)  
