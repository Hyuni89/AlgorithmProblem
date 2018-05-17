# 1300. Kth Number  
K번째 수를 구해라!

## Brief  
N의 길이를 가진 2차원 배열이 존재할 때, 배열의 내부 수 A[i][j] = i * j 이다. 이 배열의 원소를 1차원 배열로 나열하고 정렬 했을 때, K번째 수는 무엇일까?  

> Input  
> N (1 <= N <= 10<sup>5</sup>)  
> K (min(10<sup>9</sup>, N<sup>2</sup>)  

## Approach  
1. 도저히 어떤 방법으로 푸는지 감이 안왔다. 실제 시뮬레이션으로 해 보려고 하면 N이 최대 10<sup>5</sup>이기 때문에 시간초과에 걸린다. 배열 내부 값이 행과 열의 곱이므로 소인수분해를 이용해 볼까 했지만 이것도 for문을 돌다보면 시간초과에 걸린다.  

## Solution  
1. Parametric search  
	- Binary search와 비슷하지만 조금은 다른 성격을 가지고 있다. Binary search가 값을 찾아내는 과정이라면 Parametric search는 답을 가정하고 정당한 답인지 확인하는 과정이다.  
	- 따라서, 임의의 수를 정하고 K번째 수인지 확인을 하고 아니면 범위를 좁히거나 넓히는 과정을 반복함으로서 풀어 낸다.  
	- 배열의 i번째 행의 요소들은 모두 i의 배수이므로 임의로 선택한 수를 i로 나누게 되면 그 행에 i보다 작은 수의 갯수가 몫이 된다. 이 과정을 N개의 행에 모두 처리하면 임의로 선택한 수가 몇 번째 수인지 알 수 있다.  

2. Binary search (Upper and Lower bound)  
	- 사람마다 구현 방법이 다르고 Upper, Lower bound가 들어가면 코드가 좀 더 어려워서 정리가 필요하다.  
	```
	func LowerBound:
		var left, right

		while left <= right:
			let mid = (left + right) / 2

			if A[mid] < K:
				left = mid + 1
			else:
				right = mid

		return right + 1
	
	func UpperBound:
		var left, right

		while left <= right:
			let mid = left + (right - left) / 2

			if A[mid] <= K:
				left = mid + 1
			else:
				right = mid

		return right + 1
	```

## Link  
[1300. Kth Number](https://www.acmicpc.net/problem/1300)  
